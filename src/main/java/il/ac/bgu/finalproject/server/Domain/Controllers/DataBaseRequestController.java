package il.ac.bgu.finalproject.server.Domain.Controllers;

import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Apartment;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Post;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils.SearchResults;
import il.ac.bgu.finalproject.server.Domain.Exceptions.DataBaseFailedException;
import il.ac.bgu.finalproject.server.PersistenceLayer.DataBaseConnection;
import il.ac.bgu.finalproject.server.PersistenceLayer.DataBaseConnectionInterface;

import java.util.List;

public class DataBaseRequestController {
    public DataBaseConnectionInterface dataBaseConnectionInterface;

    public DataBaseRequestController() {
        dataBaseConnectionInterface = new DataBaseConnection();
    }

    public void connect() throws DataBaseFailedException {dataBaseConnectionInterface.connect();}
    public void disconnect() throws DataBaseFailedException {dataBaseConnectionInterface.disConnect();}

    public void manageApartment(Apartment apartment, String postID) throws DataBaseFailedException {
        int apartmentId=dataBaseConnectionInterface.isApartmentExist(apartment);
        if (apartmentId!=-1) {
            dataBaseConnectionInterface.updateApartmentDerivatives(apartment, ""+apartmentId);
        } else {
            dataBaseConnectionInterface.addApartmentDerivatives(apartment, postID);
        }
    }

    public Post getPost(String id){
        return dataBaseConnectionInterface.getPost(id);
    }

    public void updatePost(Post post) throws DataBaseFailedException {
        dataBaseConnectionInterface.update(post.getID(),post.getDateOfPublish().toString(),post.getPublisherName(),post.getText(),post.getApartmentID());
    }


    public void addPost(Post post) throws DataBaseFailedException {
        dataBaseConnectionInterface.addPost(post.getID(),post.getDateOfPublish().toString(),post.getPublisherName(),post.getText(),post.getApartmentID());
    }


    public void deletePost(String id) throws DataBaseFailedException {
        String apartmentID = dataBaseConnectionInterface.getPost(id).getApartmentID();
        dataBaseConnectionInterface.deletePost(id);

        if (!dataBaseConnectionInterface.morePostsWithApartmentID(apartmentID)){
            dataBaseConnectionInterface.deleteApartmentRecord(apartmentID);
        }
    }


    public List<String> getAllPostsId() {
        return dataBaseConnectionInterface.GetAllPostsId();
    }

    public SearchResults allResultsRecordsFromDB() {
        return dataBaseConnectionInterface.allResultsFromDB();
    }
}
