package il.ac.bgu.finalproject.server.Domain.Controllers;

import com.google.gson.Gson;
import il.ac.bgu.finalproject.server.CommunicationLayer.AdminDTOs.ArraySearchRecordDTO;
import il.ac.bgu.finalproject.server.CommunicationLayer.AdminDTOs.SearchRecordDTO;
import il.ac.bgu.finalproject.server.CommunicationLayer.DTOs.GroupDTO;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Apartment;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.ApartmentLocation;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Post;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.Encryption;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils.SearchResults;
import il.ac.bgu.finalproject.server.Domain.Exceptions.DataBaseFailedException;
import il.ac.bgu.finalproject.server.PersistenceLayer.DataBaseConnection;
import il.ac.bgu.finalproject.server.PersistenceLayer.DataBaseConnectionInterface;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class DataBaseRequestController {
    private static final String dateFormat = "yyyy/MM/dd HH:mm:ss";
    public DataBaseConnectionInterface dataBaseConnectionInterface;
//    private Encryption encryption;

    public DataBaseRequestController() {
        dataBaseConnectionInterface = new DataBaseConnection();
        dataBaseConnectionInterface.connect();
//        encryption= new Encryption();
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


    public int addUserSuggestion(String id, String field, String suggestion) throws DataBaseFailedException {
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

        return ans;
//        if (ans>5){
//            Gson gson= new Gson();
////            if (field.equals("address")){
////
////            }
////            else
////                if(field.equals("address")||field.equals("address")||field.equals("address")) {
////                    dataBaseConnectionInterface.suggestionChangesApartmentReacord(id, suggestion, field);
////                }
//
//            if (field.equals("neighborhood")){
//                dataBaseConnectionInterface.suggestionChangesNeighborhood(id,suggestion);
//            }
//            else if (field.equals("street")||field.equals("numOfBuilding")){
//                //get new apartmentLocation
//                ApartmentLocation apartmentLocation= new ApartmentLocation(); //SHAVIT FUNCTION HERE
//                //insert to addressDetails table
//                int addressDetailsNum= dataBaseConnectionInterface.isAddressDetailsExist(apartmentLocation.getAddress().getStreet(),
//                        apartmentLocation.getAddress().getNumber());
//                if (addressDetailsNum==-1) {
//                    addressDetailsNum = dataBaseConnectionInterface.addAddressDetailsRecord(apartmentLocation.getAddress().getStreet(),
//                            "" + apartmentLocation.getAddress().getNumber(), apartmentLocation.getDistanceFromUniversity(),
//                            apartmentLocation.getNeighborhood(), apartmentLocation.getLongitude(), apartmentLocation.getLatitude());
//                }
//                //change AddressId in Apartment record
//                dataBaseConnectionInterface.changeAddresDetailsForApartment(id, addressDetailsNum);
//
//            }
//            else {
//                int t= gson.fromJson(id,Integer.class);
//                dataBaseConnectionInterface.suggestionChangesApartmentReacord(id, t, field);
//            }
//        }
    }
    public void suggestionChangesApartmentInt(String id, String field, int suggest){
        dataBaseConnectionInterface.suggestionChangesApartmentInt(id, field, suggest);
    }
    public void suggestionChangesApartmentDouble(String id, String field, double suggest){
        dataBaseConnectionInterface.suggestionChangesApartmentDouble(id, field, suggest);
    }
    public void suggestionChangesAddress(String id, String field, String street, int numB, String neighborhood){
        dataBaseConnectionInterface.suggestionChangesAddress(id, field, street, numB, neighborhood);
    }

    public int login (String username, String password){
        if (dataBaseConnectionInterface.userExist(username)){
            try {
                if (dataBaseConnectionInterface.login(username, password)){ return 1; }
                else return -1;
            } catch (SQLException e) {
                return 0;
            } catch (Exception e) {
                return 0;
            }
        }
        else return 0;
    }
    public  int changePassword(String username, String password) {
        if (dataBaseConnectionInterface.userExist(username)){
            if (dataBaseConnectionInterface.changePassword(username, password)){ return 1; }
            else return -1;
        }
        else return 0;
    }
    public  String forgotPassword(String username, String email) {
//            if (dataBaseConnectionInterface.userExist(username)||dataBaseConnectionInterface.isCorrectEmail(username,email)){
        try {
            if (dataBaseConnectionInterface.isCorrectEmail(username,email)) {
                if (dataBaseConnectionInterface.userExist(username)) {
                    String randomUUID = "" + UUID.randomUUID();
                    Date dd = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat();
                    sdf.applyPattern(dateFormat);
                    String newDateString = sdf.format(dd);
                    try {
                        if (dataBaseConnectionInterface.addToUUIDTable(username, newDateString, email)){return randomUUID;}
                        else return "-1";
                    } catch (DataBaseFailedException e) {
                        return "-1";
                    }
                } else return "-2";
            }
            else return "0";
        } catch (SQLException e) {
            return "-1";
        }
    }
    public int checkCCUID(String ccuid) {
        try {
            if (dataBaseConnectionInterface.UUIDExistAndValid(ccuid)){return 1;}
            else return 0;
        } catch (DataBaseFailedException e) {
            return -1;
        }
    }
    //        return dataBaseConnectionInterface.changeEmailAddress(username, emailAddress);
//    public boolean changeEmailAddress(String username, String emailAddress){

//    }
    public void addSearchRecord(String neighborhood, String timeFromUni, String costMin, String costMax, String floorMin, String floorMax, String sizeMin, String sizeMax, String furnitures,String numOfRoomes, String numOfMates, int protectedSpace,  int garden, int balcony, int pets, int warehouse) throws DataBaseFailedException{
        dataBaseConnectionInterface.addSearchRecord(neighborhood, timeFromUni, costMin, costMax, floorMin, floorMax, sizeMin, sizeMax, furnitures,numOfRoomes, numOfMates, protectedSpace,  garden, balcony, pets, warehouse);
    }
    public int getConstValue (String id) throws DataBaseFailedException {
        return  dataBaseConnectionInterface.getConstValue(id);
    }

    public void setConstValue (String id, int val) throws DataBaseFailedException{
        dataBaseConnectionInterface.setConstValue(id, val);
    }
    public int insertGroup(String groupID, String groupName) {
        if (!dataBaseConnectionInterface.groupExist(groupID)) {
            try {
                dataBaseConnectionInterface.insertGroup(groupID, groupName);
                return 1;
            } catch (DataBaseFailedException e) {
                return -1;
            }
        }
        else return 0;
    }
    public int deleteGroup(String groupID){
        if (dataBaseConnectionInterface.groupExist(groupID)) {
            try {
                dataBaseConnectionInterface.deleteGroup(groupID);
                return 1;
            } catch (DataBaseFailedException e) {
                return -1;
            }
        }
        else return 0;
    }

    public List<GroupDTO> getAllGroups(){
        return dataBaseConnectionInterface.GetAllGroups();
    }

    public List<SearchRecordDTO>  getAllUserSearches(){
        return dataBaseConnectionInterface.getAllUserSearches();
//        int size=searchRecordDTOList.size();
//        SearchRecordDTO[] searchRecordDTOS= new SearchRecordDTO[size];
//        for (int i=0; i<size; i++){
//            searchRecordDTOS[i]= searchRecordDTOList.get(i);
//        }
//        ArraySearchRecordDTO arraySearchRecordDTO= new ArraySearchRecordDTO();
//        return arraySearchRecordDTO;
    }

    public void addressFieldCase(String apartmentId, boolean streetBool, boolean numBuildingBool, boolean neighborhoodBool, String street, int numOfBuilding, String neighborhood) throws DataBaseFailedException {
        if (streetBool||numBuildingBool){
            //address has changed
            Apartment apartment= dataBaseConnectionInterface.getApartmentRecordTBD(apartmentId);
            if (!(streetBool && street!=null && street!=""))
                street=apartment.getApartmentLocation().getAddress().getStreet();
            if (!(numBuildingBool && numOfBuilding!= -1))
                numOfBuilding=apartment.getApartmentLocation().getAddress().getNumber();
            int addressDetailsId= dataBaseConnectionInterface.isAddressDetailsExist(street,numOfBuilding);
            if (addressDetailsId!= -1) {//exist
                if (neighborhoodBool && neighborhood!=null && neighborhood!=""){
                    dataBaseConnectionInterface.suggestionChangesNeighborhood(apartmentId, neighborhood);
                }
            }
            else { // addressDetails need to be created
                GoogleMapsController googleMapsController= new GoogleMapsController();

                if (!street.isEmpty() && numOfBuilding> 0) {
                    int timeToUni = googleMapsController.getTimeWalkingFromUniByMin(street, numOfBuilding);
                    double[] locationPoint = googleMapsController.getCoordinates(street, numOfBuilding);
                    if (!neighborhoodBool || neighborhood == null || neighborhood == "") {
                        neighborhood = apartment.getApartmentLocation().getNeighborhood();
                    }
                    if (locationPoint[0]!=-1&&locationPoint[1]!=-1) {
                        addressDetailsId = dataBaseConnectionInterface.addAddressDetailsRecord(
                                street, numOfBuilding + "",
                                timeToUni, neighborhood, locationPoint[0], locationPoint[1]);
                    }
                }
            }
            dataBaseConnectionInterface.changeAddresDetailsForApartment(apartmentId,addressDetailsId);
        }
        else{
            if (neighborhoodBool && neighborhood!=null && neighborhood!=""){
                dataBaseConnectionInterface.suggestionChangesNeighborhood(apartmentId, neighborhood);
            }
        }
    }
}
