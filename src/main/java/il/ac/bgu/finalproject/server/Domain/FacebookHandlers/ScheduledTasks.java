package il.ac.bgu.finalproject.server.Domain.FacebookHandlers;

import java.util.List;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    @Scheduled(cron = "0 0/1 * * * ?")
    public void updatePosts()
    {
        //function that asks for list of groups to search
        //   foreach group do: FacebookHandler.GetFeed(2,groupId);
       // FacebookHandler.GetFeed(2,"279135451973");

    }


    @Scheduled(cron = "0 0/15 * * * ?")
    public void checkIfDeleted()
    {
        final int maxListSize=50;
        // ask for List of all the posts Ids and save in a
       /* List<String> a=null;
        for(int i=0;i<a.size();i=i+maxListSize)
            if(i+maxListSize<a.size())
                FacebookHandler.IsDeleted(a.subList(i, i + maxListSize));
            else
                FacebookHandler.IsDeleted(a.subList(i, a.size()));
                */
    }

}
