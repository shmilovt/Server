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

    public Post getPost(String id)
    {
        return dbController.getPost(id);
    }

    public void newPost(Post ourPost)
    {
        dbController.addPost(ourPost);
        Apartment apartment = nlpController.generateNLP(ourPost);
        if(!apartment.getApartmentLocation().getAddress().getStreet().isEmpty())
            dbController.manageApartment(apartment, ourPost.getID());
    }

    public void updatePost(Post ourPost)
    {
        dbController.updatePost(ourPost);
        Apartment apartment = nlpController.generateNLP(ourPost);
        dbController.manageApartment(apartment, ourPost.getID());
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
