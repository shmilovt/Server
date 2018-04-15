package il.ac.bgu.finalproject.server.Domain.FacebookHandlers;

import il.ac.bgu.finalproject.server.PersistenceLayer.DataBaseConnection;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScheduledTasks {

    private static FacebookHandler fb= new FacebookHandler();
    private static DataBaseConnection conn=new DataBaseConnection();

    @Scheduled(cron = "0 0/1 * * * ?")
    public void updatePosts()
    {

        //FacebookHandler fb= new FacebookHandler();
        //function that asks for list of groups to search
        //   foreach group do: FacebookHandler.GetFeed(2,groupId);
        System.out.println("updatePosts");
        fb.GetFeed(2,"516188885429510");

    }


    @Scheduled(cron = "0 0/2 * * * ?")
    public void checkIfDeleted()
    {
        System.out.println("checkDeleted");
        final int maxListSize=50;
        // ask for List of all the posts Ids and save in a
        List<String> a=conn.GetAllPostsId();
        for(int i=0;i<a.size();i=i+maxListSize)
            if(i+maxListSize<a.size())
                fb.IsDeleted(a.subList(i, i + maxListSize));
            else
                fb.IsDeleted(a.subList(i, a.size()));
        //is fb.IsDeleted needs to get a subList
    }
}
