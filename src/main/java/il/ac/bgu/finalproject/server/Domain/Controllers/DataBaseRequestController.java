package il.ac.bgu.finalproject.server.Domain.Controllers;

import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Apartment;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.ApartmentLocation;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Post;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils.SearchResults;
import il.ac.bgu.finalproject.server.Domain.Exceptions.DataBaseFailedException;
import il.ac.bgu.finalproject.server.PersistenceLayer.DataBaseConnection;
import il.ac.bgu.finalproject.server.PersistenceLayer.DataBaseConnectionInterface;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DataBaseRequestController {
    private static final String dateFormat = "yyyy/MM/dd HH:mm:ss";
    public DataBaseConnectionInterface dataBaseConnectionInterface;

    public DataBaseRequestController() {

        dataBaseConnectionInterface = new DataBaseConnection();
        dataBaseConnectionInterface.connect();

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
        Date dd = new Date(post.getDateOfPublish().getTime());
        SimpleDateFormat sdf= new SimpleDateFormat();
        sdf.applyPattern(dateFormat);
        String newDateString = sdf.format(dd);
        dataBaseConnectionInterface.addPost(post.getID(),newDateString,post.getPublisherName(),post.getText(),post.getApartmentID());
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


    public void addUserSuggestion(String id, String field, String suggestion) throws DataBaseFailedException {
        int ans;
        int counter=dataBaseConnectionInterface.getUserSuggestionsNum(id,field,suggestion);
        if (counter==-1) {
            dataBaseConnectionInterface.insertUserSuggestionsRecord(id, field, suggestion);
            ans= 1;
        }
        else {
            dataBaseConnectionInterface.setUserSuggestionsCounter(id,field,suggestion,counter+1);
            ans= counter+1;
        }
        if (ans>5){
            if (field.equals("neighborhood")){
                dataBaseConnectionInterface.suggestionChangesNeighborhood(id,suggestion);
            }
            else if (field.equals("street")||field.equals("numOfBuilding")){
                //get new apartmentLocation
                ApartmentLocation apartmentLocation= new ApartmentLocation(); //SHAVIT FUNCTION HERE
                //insert to addressDetails table
                int addressDetailsNum= dataBaseConnectionInterface.isAddressDetailsExist(apartmentLocation.getAddress().getStreet(),
                        apartmentLocation.getAddress().getNumber());
                if (addressDetailsNum==-1) {
                    addressDetailsNum = dataBaseConnectionInterface.addAddressDetailsRecord(apartmentLocation.getAddress().getStreet(),
                            "" + apartmentLocation.getAddress().getNumber(), apartmentLocation.getDistanceFromUniversity(),
                            apartmentLocation.getNeighborhood(), apartmentLocation.getLongitude(), apartmentLocation.getLatitude());
                }
                //change AddressId in Apartment record
                dataBaseConnectionInterface.changeAddresDetailsForApartment(id, addressDetailsNum);

            }
            else
                dataBaseConnectionInterface.suggestionChangesApartmentReacord(id,suggestion,field);
        }
    }

    public boolean login (String username, String password){
        return dataBaseConnectionInterface.login(username, password);
    }


}
