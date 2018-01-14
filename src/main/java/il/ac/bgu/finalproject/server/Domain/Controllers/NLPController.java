package il.ac.bgu.finalproject.server.Domain.Controllers;

import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Post;
import il.ac.bgu.finalproject.server.Domain.NLPHandlers.NLPImp;

import java.util.concurrent.LinkedBlockingQueue;

public class NLPController {
    public static LinkedBlockingQueue<Post>  postsQueue = new LinkedBlockingQueue<>();
    private NLPImp nlpImp;

    public NLPController(){
        nlpImp = new NLPImp();

    }


    public void setupNLPThreads(){
        new Thread(nlpImp).run();
    }


}
