package il.ac.bgu.finalproject.server.Domain.Controllers;

import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Post;

public class FacebookController {
    public NLPController nlpController;
    public FacebookController(){
        nlpController = new NLPController();
    }

    public void generateNLP(Post post){
        nlpController.generateNLP(post);
    }

}
