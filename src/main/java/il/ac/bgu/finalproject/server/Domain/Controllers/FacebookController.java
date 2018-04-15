package il.ac.bgu.finalproject.server.Domain.Controllers;

import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Post;

import java.util.List;

public class FacebookController {
    private NLPController nlpController;
    private DataBaseRequestController dbController;

    public FacebookController(){
        nlpController = new NLPController();
        dbController = new DataBaseRequestController();
    }

    public void generateNLP(Post post){
        nlpController.generateNLP(post);
    }

    public Post getPost(String id)
    {
        return dbController.getPost(id);
    }

    public void updatePost(Post post)
    {
        dbController.updatePost(post);
    }

    public void addPost(Post post)
    {
        dbController.addPost(post);
    }

    public void deletePost(String id)
    {
        dbController.deletePost(id);
    }

    public List<String> getAllPostsId()
    {
        return dbController.getAllPostsId();
    }
}
