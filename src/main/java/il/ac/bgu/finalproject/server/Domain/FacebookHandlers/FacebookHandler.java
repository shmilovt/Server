package il.ac.bgu.finalproject.server.Domain.FacebookHandlers;

import com.restfb.*;
import com.restfb.exception.FacebookOAuthException;
import com.restfb.json.Json;
import com.restfb.json.JsonObject;
import com.restfb.types.Post;
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

    public FacebookHandler()
    {
        nlp=new NLPImp();
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
        long sinceDate = DateToUnixTime(GetDateOfNumOfWeeksBefore(sinceWeeks));
        Connection<Post> postFeed = fbClient.fetchConnection(groupId + "/feed", Post.class, Parameter.with("since", sinceDate), Parameter.with("limit", 100));
        DataBaseConnection dbConn = new DataBaseConnection();
        List<String> post;
        for (List<Post> postPage : postFeed)
            for (Post apost : postPage) {
                if (apost.getMessage() != null) {
                    //System.out.println("postid: "+ apost.getId());
                    post = dbConn.getPost(apost.getId().toString());
                    //System.out.println("Post?: " + post);
                    if (post != null) {
                        //System.out.println("DB: "+post.get(2));
                        //System.out.println("APOST: " + apost.getMessage());
                        if (post.get(2).compareTo(apost.getMessage()) != 0) {
                            System.out.println("db: post updated");
                            dbConn.update(apost.getId(), apost.getUpdatedTime().toString(), apost.getMessage());
                            nlp.extractApartment(apost.getMessage());
                        }
                    } else {
                        System.out.println("db: post added");
                        dbConn.addPost(apost.getId(), apost.getUpdatedTime().toString(), apost.getMessage());
                        nlp.extractApartment(apost.getMessage());
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
        //System.out.println("Here IsDeleted");
        DataBaseConnection dbConn = new DataBaseConnection();
        try {
            JsonObject fetchObjectsResults = fbClient.fetchObjects(ids, JsonObject.class, Parameter.with("fields", "id"));
            //System.out.println("ids: " + ids.toString());
            //System.out.println("fetch: " + fetchObjectsResults.names().toString());
            if(ids.removeAll(fetchObjectsResults.names()) || fetchObjectsResults.names().size()==0)
                for(String id: ids) {
                    System.out.println("db: post was deleted" + id);
                    dbConn.deletePost(id);
                }
            //System.out.println("ids after: " + ids.toString());


           /* System.out.println("tryn :" +  ids.removeAll(fetchObjectsResults.names()));
            System.out.println("tryP :" +  ids.toString());
            System.out.println("tryT :" +  fetchObjectsResults.names().get(0));
            System.out.println("ids: " + ids.toString());
            System.out.println("fetch: " + fetchObjectsResults.names().toString());*/
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
// DO WE NEED CACHE?
// will we have all ids in collection? or i have to req from DB?