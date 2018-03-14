package il.ac.bgu.finalproject.server.Domain.FacebookHandlers;

import com.restfb.*;
import com.restfb.exception.FacebookOAuthException;
import com.restfb.json.Json;
import com.restfb.json.JsonObject;
import com.restfb.types.Post;
import il.ac.bgu.finalproject.server.Domain.Controllers.NLPController;
import il.ac.bgu.finalproject.server.Domain.NLPHandlers.NLPImp;
import il.ac.bgu.finalproject.server.Domain.NLPHandlers.NLPInterface;
import il.ac.bgu.finalproject.server.PersistenceLayer.DataBaseConnection;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class FacebookHandler {
    private static final String accessToken = "EAAESqCOZCzy4BANaOKhN4VeZAtYr0Ja9rAZCPEKVgsl8VFuyW0PY1yNZC5YMhiqPMRYN0VlV4WaZCIxsJz7GvrBHbbpZCIzeVDbXZBdCr2IhlMspJdAjGCZAqUnTRklPcIsDy2hD4ZBjfgL6DM5CBYTeYJS1bwC6FdOEZD";
    private static final FacebookClient fbClient = new DefaultFacebookClient(accessToken,Version.VERSION_2_11);
    private static DataBaseConnection conn;
    private static NLPImp nlp;
   // private static NLPController nlpController;

    public FacebookHandler()
    {
        nlp=new NLPImp();
       // nlpController = new NLPController();
       // nlpController.setupNLPThreads();
        conn = new DataBaseConnection();
        conn.connect();
    }

    private Date GetDateOfNumOfWeeksBefore(int numOfWeeks)
    {
        int noOfDays = -numOfWeeks;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.WEEK_OF_YEAR, noOfDays);
        return calendar.getTime();
    }

    private  long DateToUnixTime(Date date)
    {
        return date.getTime()/1000;
    }

    /***
     * The function return the feed of _ weeks ago
     * @param sinceWeeks - number of weeks
     * @param groupId
     */
    public void GetFeed(int sinceWeeks,String groupId) {
        System.out.println("GetFeed");
        long sinceDate = DateToUnixTime(GetDateOfNumOfWeeksBefore(sinceWeeks));
        System.out.println("Create Connection");
        Connection<Post> postFeed = fbClient.fetchConnection(groupId + "/feed", Post.class, Parameter.with("since", sinceDate), Parameter.with("limit", 100));
        DataBaseConnection dbConn = new DataBaseConnection();
        System.out.println("Connection established");

        List<String> post;
        int x=1000;
        for (List<Post> postPage : postFeed)
            for (Post apost : postPage) {
                if (apost.getMessage() != null) {
                    post = dbConn.getPost(apost.getId().toString());
                    if (post != null) {
                        if (post.get(2).compareTo(apost.getMessage()) != 0) {
                            System.out.println("\n" + "***** db: post updated *****" + "\n");
                            dbConn.update(apost.getId(), apost.getUpdatedTime().toString(), apost.getMessage());
                            nlp.extractApartment(apost.getMessage());
                            /*try {
                                NLPController.postsQueue.put(new il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Post(apost.getMessage()));
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }*/
                        }
                    } else {
                        System.out.println("\n" + "***** db: post added *****" + "\n");
                        dbConn.addPost(apost.getId(), apost.getUpdatedTime().toString(), apost.getMessage());
                        nlp.extractApartment(apost.getMessage());

                        /* try {

                            NLPController.postsQueue.put(new il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Post(apost.getMessage()));
                            System.out.println(NLPController.postsQueue.size());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }*/
                    }
                }
            }
    }


    private String GetIdFromExceptionMessage(String expMsg)
    {
        String[] msg= expMsg.replaceAll(" ","").split("exist:|\\(");
        return msg[2];
    }

    public  void IsDeleted(List<String> ids)
    {
        DataBaseConnection dbConn = new DataBaseConnection();
        try {
            JsonObject fetchObjectsResults = fbClient.fetchObjects(ids, JsonObject.class, Parameter.with("fields", "id"));
            if(ids.removeAll(fetchObjectsResults.names()) || fetchObjectsResults.names().size()==0)
                for(String id: ids) {
                    System.out.println("\n" + "***** db: post was deleted " + id + " *****" + "\n");
                    dbConn.deletePost(id);
                }
        }catch(FacebookOAuthException e)
        {
            System.out.println("error");
            if(e.getErrorCode()==803)
            {
                ids.remove(GetIdFromExceptionMessage(e.getMessage()));
                dbConn.deletePost(GetIdFromExceptionMessage(e.getMessage()));
                IsDeleted(ids);
            }
        }
    }
}