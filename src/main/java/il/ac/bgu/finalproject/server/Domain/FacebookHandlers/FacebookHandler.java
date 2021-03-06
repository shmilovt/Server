package il.ac.bgu.finalproject.server.Domain.FacebookHandlers;

import com.restfb.*;
import com.restfb.exception.FacebookOAuthException;
import com.restfb.json.JsonObject;
import com.restfb.types.Post;
import il.ac.bgu.finalproject.server.Domain.Controllers.ServerController;
import il.ac.bgu.finalproject.server.Domain.Exceptions.DataBaseFailedException;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class FacebookHandler {
    private static final String accessToken = "EAAESqCOZCzy4BANaOKhN4VeZAtYr0Ja9rAZCPEKVgsl8VFuyW0PY1yNZC5YMhiqPMRYN0VlV4WaZCIxsJz7GvrBHbbpZCIzeVDbXZBdCr2IhlMspJdAjGCZAqUnTRklPcIsDy2hD4ZBjfgL6DM5CBYTeYJS1bwC6FdOEZD";
    private static final FacebookClient fbClient = new DefaultFacebookClient(accessToken,Version.VERSION_2_11);
    private ServerController fbController = new ServerController();



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
     * @param /sinceWeeks - number of weeks
     * @param /groupId
     */


    public void GetFeed(int sinceWeeks,String groupId) throws DataBaseFailedException {
        long sinceDate = DateToUnixTime(GetDateOfNumOfWeeksBefore(sinceWeeks));
        Connection<Post> postFeed = fbClient.fetchConnection(groupId + "/feed", Post.class, Parameter.with("since", sinceDate), Parameter.with("limit", 100));
        //DataBaseConnection dbConn = new DataBaseConnection();
        //List<String> post;
        //Apartment apartment;
        //int tempForApartment;
        //int tempForApartmentD;
        //il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Post tempPost;
        //int x=1000;
        for (List<Post> postPage : postFeed)
            for (Post apost : postPage) {
                il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Post ourPost = new il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Post(apost.getId(), apost.getUpdatedTime(), apost.getAdminCreator().getName(), apost.getMessage(), "");
                //System.out.println("here1");
                if (apost.getMessage() != null) {
                    //System.out.println("here2");
                    String tempPost = fbController.getPost(apost.getId().toString()).getText();
                    if (tempPost!= null) {
                        //System.out.println("here3");
                        if (tempPost.compareTo(apost.getMessage()) != 0) {
                            //System.out.println("\n" + "***** db: post updated *****" + "\n");
                            //postUpdated_changesInDB(apost,dbConn);
                            //dbConn.update(apost.getId(),apost.getUpdatedTime().toString(),apost.getAdminCreator().getName(),apost.getMessage(),""+11111111);
                            fbController.updatePost(ourPost);
                        }
                    } else {
                        System.out.println("here4");
                        System.out.println("\n" + "***** db: post added *****" + "\n");
                        fbController.newPost(ourPost);

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

    public  void IsDeleted(List<String> ids) throws DataBaseFailedException {
       // DataBaseConnection dbConn = new DataBaseConnection();
        try {
            JsonObject fetchObjectsResults = fbClient.fetchObjects(ids, JsonObject.class, Parameter.with("fields", "id"));
            if(ids.removeAll(fetchObjectsResults.names()) || fetchObjectsResults.names().size()==0)
                for(String id: ids) {
                    System.out.println("\n" + "***** db: post was deleted " + id + " *****" + "\n");
                    fbController.deletePost(id);
                }
        }catch(FacebookOAuthException e)
        {
            System.out.println("error");
            if(e.getErrorCode()==803)
            {
                ids.remove(GetIdFromExceptionMessage(e.getMessage()));
                fbController.deletePost(GetIdFromExceptionMessage(e.getMessage()));
                IsDeleted(ids);
            }
        }
    }

}