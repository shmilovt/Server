package il.ac.bgu.finalproject.server.Domain.Controllers;

import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Apartment;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Post;

import java.util.List;

public class ServerController {
    private NLPController nlpController;
    private DataBaseRequestController dbController;

    public ServerController(){
        nlpController = new NLPController();
        dbController = new DataBaseRequestController();
    }

    public Post getPost(String id){
        dbController.connect();
        Post post= dbController.getPost(id);
        dbController.disconnect();
        return post;
    }

    public void newPost(Post ourPost){
        dbController.connect();
        dbController.addPost(ourPost);
        Apartment apartment = nlpController.generateNLP(ourPost);
        dbController.manageApartment(apartment, ourPost.getID());
        dbController.disconnect();
    }

    public void updatePost(Post ourPost){
        dbController.connect();
        dbController.updatePost(ourPost);
        Apartment apartment = nlpController.generateNLP(ourPost);

        dbController.manageApartment(apartment, ourPost.getID());
        dbController.disconnect();
    }

    public void deletePost(String id)
    {
        dbController.connect();
        dbController.deletePost(id);
        dbController.disconnect();
    }

    public List<String> getAllPostsId()
    {
        dbController.connect();
        List<String> postsID= dbController.getAllPostsId();
        dbController.disconnect();
        return postsID;
    }
}
