package il.ac.bgu.finalproject.server.Domain.Controllers;

import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Apartment;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Post;
import il.ac.bgu.finalproject.server.Domain.Exceptions.DataBaseFailedException;

import java.util.List;

public class ServerController {
    private NLPController nlpController;
    private DataBaseRequestController dbController;

    public ServerController() {
        nlpController = new NLPController();
        dbController = new DataBaseRequestController();
        try {
            dbController.connect();
        } catch (DataBaseFailedException e) {
            e.printStackTrace();
        }
    }

    public Post getPost(String id) throws DataBaseFailedException {
//        dbController.connect();
        Post post= dbController.getPost(id);
//        dbController.disconnect();
        return post;
    }

    public void newPost(Post ourPost) throws DataBaseFailedException {
//        dbController.connect();
        dbController.addPost(ourPost);
        Apartment apartment = nlpController.generateNLP(ourPost);
        if(apartment!=null)
            dbController.manageApartment(apartment, ourPost.getID());
//        dbController.disconnect();
    }

    public void updatePost(Post ourPost) throws DataBaseFailedException {
//        dbController.connect();
        dbController.updatePost(ourPost);
        Apartment apartment = nlpController.generateNLP(ourPost);

        dbController.manageApartment(apartment, ourPost.getID());
//        dbController.disconnect();
    }

    public void deletePost(String id) throws DataBaseFailedException {
//        dbController.connect();
        dbController.deletePost(id);
//        dbController.disconnect();
    }

    public List<String> getAllPostsId() throws DataBaseFailedException {
//        dbController.connect();
        List<String> postsID= dbController.getAllPostsId();
//        dbController.disconnect();
        return postsID;
    }
}
