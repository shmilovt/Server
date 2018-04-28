package il.ac.bgu.finalproject.server.Domain.Controllers;

import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Apartment;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Post;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils.SearchResults;
import il.ac.bgu.finalproject.server.PersistenceLayer.DataBaseConnection;
import il.ac.bgu.finalproject.server.PersistenceLayer.DataBaseConnectionInterface;

import java.util.List;

public class DataBaseRequestController {
    public DataBaseConnectionInterface dataBaseConnectionInterface;

    public DataBaseRequestController() {
        dataBaseConnectionInterface = new DataBaseConnection();
    }

    public void manageApartment(Apartment apartment, String postID) {
        dataBaseConnectionInterface.connect();
        int apartmentId=dataBaseConnectionInterface.isApartmentExist(apartment);
        if (apartmentId!=-1) {
            dataBaseConnectionInterface.updateApartmentDerivatives(apartment, ""+apartmentId);
        } else {
            dataBaseConnectionInterface.addApartmentDerivatives(apartment, postID);
        }
        dataBaseConnectionInterface.disConnect();
    }

    public Post getPost(String id){
        return dataBaseConnectionInterface.getPost(id);
    }

    public void updatePost(Post post){
        dataBaseConnectionInterface.connect();
        dataBaseConnectionInterface.update(post.getID(),post.getDateOfPublish().toString(),post.getPublisherName(),post.getText(),post.getApartmentID());
        dataBaseConnectionInterface.disConnect();
    }


    public void addPost(Post post){
        dataBaseConnectionInterface.connect();
        dataBaseConnectionInterface.addPost(post.getID(),post.getDateOfPublish().toString(),post.getPublisherName(),post.getText(),post.getApartmentID());
        dataBaseConnectionInterface.disConnect();
    }


    public void deletePost(String id){
        dataBaseConnectionInterface.connect();
        String apartmentID = dataBaseConnectionInterface.getPost(id).getApartmentID();
        dataBaseConnectionInterface.deletePost(id);

        if (!dataBaseConnectionInterface.morePostsWithApartmentID(apartmentID)){
            dataBaseConnectionInterface.deleteApartmentRecord(apartmentID);
        }
        dataBaseConnectionInterface.disConnect();
    }


    public List<String> getAllPostsId() {
        return dataBaseConnectionInterface.GetAllPostsId();
    }

    public SearchResults allResultsRecordsFromDB() {
        return dataBaseConnectionInterface.allResultsFromDB();
    }
}
