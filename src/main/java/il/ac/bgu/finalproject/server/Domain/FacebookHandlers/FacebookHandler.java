package il.ac.bgu.finalproject.server.Domain.FacebookHandlers;

import com.restfb.*;
import com.restfb.exception.FacebookOAuthException;
import com.restfb.json.JsonObject;
import com.restfb.types.Post;
import il.ac.bgu.finalproject.server.PersistenceLayer.DataBaseConnection;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class FacebookHandler {
    private static final String accessToken = "EAAESqCOZCzy4BANaOKhN4VeZAtYr0Ja9rAZCPEKVgsl8VFuyW0PY1yNZC5YMhiqPMRYN0VlV4WaZCIxsJz7GvrBHbbpZCIzeVDbXZBdCr2IhlMspJdAjGCZAqUnTRklPcIsDy2hD4ZBjfgL6DM5CBYTeYJS1bwC6FdOEZD";
    private static final FacebookClient fbClient = new DefaultFacebookClient(accessToken,Version.VERSION_2_11);

    private static Date GetDateOfNumOfWeeksBefore(int numOfWeeks)
    {
        int noOfDays = -numOfWeeks;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.WEEK_OF_YEAR, noOfDays);
        return calendar.getTime();
    }

    private static long DateToUnixTime(Date date)
    {
        return date.getTime()/1000;
    }

    /***
     * The function return the feed of _ weeks ago
     * @param sinceWeeks - number of weeks
     * @param groupId
     */
    public static void GetFeed(int sinceWeeks,String groupId) {
        long sinceDate = DateToUnixTime(GetDateOfNumOfWeeksBefore(sinceWeeks));
        Connection<Post> postFeed = fbClient.fetchConnection(groupId + "/feed", Post.class, Parameter.with("since", sinceDate), Parameter.with("limit", 100));
        DataBaseConnection dbConn = new DataBaseConnection();
        List<String> post;
        for (List<Post> postPage : postFeed)
            for (Post apost : postPage) {
                post=dbConn.getPost(apost.getId());
                if(post!=null) {
                    if (post.get(2).compareTo(apost.getMessage()) != 0) {
                        dbConn.update(apost.getId(), apost.getUpdatedTime().toString(), apost.getMessage());
                        //send to nlp
                    }
                }
                else
                    dbConn.addPost(apost.getId(),apost.getUpdatedTime().toString(),apost.getMessage());
                if (apost.getMessage() != null)
                    System.out.println(apost.getMessage());
            }
    }


    private static String GetIdFromExceptionMessage(String expMsg)
    {
        String[] msg= expMsg.replaceAll(" ","").split("exist:|\\(");
        return msg[2];
    }

    public static void IsDeleted(List<String> ids)
    {
        DataBaseConnection dbConn = new DataBaseConnection();
        try {
            fbClient.fetchObjects(ids, JsonObject.class, Parameter.with("fields", "id"));
        }catch(FacebookOAuthException e)
        {
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