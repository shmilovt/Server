package il.ac.bgu.finalproject.server.PersistenceLayer;

import il.ac.bgu.finalproject.server.CommunicationLayer.AdminDTOs.SearchRecordDTO;
import il.ac.bgu.finalproject.server.CommunicationLayer.DTOs.GroupDTO;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Apartment;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Contact;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Post;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils.SearchResults;
import il.ac.bgu.finalproject.server.Domain.Exceptions.DataBaseFailedException;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public interface DataBaseConnectionInterface {


    void connect();

    void disConnect() throws DataBaseFailedException;

    void resetContactsTable() throws DataBaseFailedException;

    void resetAddressDetailsTable() throws DataBaseFailedException;

    void resetApartmentTable() throws DataBaseFailedException;

    void resetApartmentContactsTable() throws DataBaseFailedException;

    void resetPostsTable() throws DataBaseFailedException;

    void resetSearchRecordsTable() throws DataBaseFailedException;

    void resetAllTables() throws DataBaseFailedException;

    void addPost(String id, String date, String publisherName, String message, String apartmentID) throws DataBaseFailedException;

    void update(String id, String date, String publisherName, String message, String apartmentID) throws DataBaseFailedException;

    Post getPost(String id);

    void deletePost(String id) throws DataBaseFailedException;

    List<String> GetAllPostsId();

    int addAddressDetailsRecord(String street, String numOfBuilding, double timeFromUni, String neighborhood, double longitude, double latitude) throws DataBaseFailedException;

    List<Apartment> allApartmentFromDB();

    Set<Contact> getApartmentContacts(String apartmentid);

// I dont think this function is neccessary:
    void updateAddressDetailsRecord(String street, String numOfBuilding, double timeFromUni, String neighborhood, double longitude, double latitude) throws DataBaseFailedException;

    void addContactsRecord(String phone, String name) throws DataBaseFailedException;

    void addApartmentContactsRecord(String apartmentID, String phoneNumber) throws DataBaseFailedException;

    String addApartmentRecord(String apartmentID, double numOfRooms, int floor,
                              int size, int cost, int addressDetailsID,
                              int garden, int gardenSize, int protectedSpace, int warehouse, int animal,
                              int balcony, int furniture, int numberOfMates
    ) throws DataBaseFailedException;
    void deleteAddressDetailsRecord(String id) throws DataBaseFailedException;
    Apartment getApartmentRecordTBD(String id);
    void deleteApartmentRecord(String id) throws DataBaseFailedException;
    Boolean morePostsWithApartmentID(String id);
    Boolean moreApartmentsWithAddressDetailsNum(String addressDetailsNum);

    void addSearchRecord(String neighborhood, String timeFromUni, String costMin, String costMax, String floorMin, String floorMax,
                                String sizeMin, String sizeMax, String furnitures, String numOfRoomes, String numOfMates,
                                int protectedSpace,  int garden, int balcony, int pets, int warehouse) throws DataBaseFailedException;    void updateApartmentRecord(Apartment apartment, String apartmentid) throws DataBaseFailedException;
    int isApartmentExist(Apartment apartment) throws DataBaseFailedException;
    void addApartmentDerivatives(Apartment apartment, String post) throws DataBaseFailedException;
    void updateApartmentDerivatives(Apartment apartment,String postID) throws DataBaseFailedException;
    SearchResults allResultsFromDB ();

    int insertUserSuggestionsNum (String id, String field, String suggestion) throws DataBaseFailedException;
    int isAddressDetailsExist(String street, int number);

    int getUserSuggestionsNum (String id, String field, String suggestion);

    void setUserSuggestionsCounter (String id, String field, String suggestion, int count) throws DataBaseFailedException;
    void insertUserSuggestionsRecord (String id, String field, String suggestion) throws DataBaseFailedException;
    void suggestionChangesApartmentInt(String id, String field, int suggest);
    void suggestionChangesApartmentDouble(String id, String field, double suggest);
    void suggestionChangesAddress(String id, String field, String street, int numB, String neighborhood);
    void suggestionChangesApartmentReacord (String apartmentID, int suggestion, String field);
    void suggestionChangesNeighborhood(String apartmentID, String suggestion);


    void changeAddresDetailsForApartment(String apartmentid, int addressDetailsNum) throws DataBaseFailedException;
    boolean login(String username, String password);
    boolean changePassword(String username, String password);
    boolean userExist(String username);
    boolean isCorrectEmail(String username, String email) throws SQLException;
    boolean addToUUIDTable(String username, String newDateString, String email) throws DataBaseFailedException;
    boolean UUIDExistAndValid(String uuString) throws DataBaseFailedException;
    //    boolean changeEmailAddress(String username, String emailAddress);

    int getConstValue (String id) throws DataBaseFailedException;
    void setConstValue (String id, int val) throws DataBaseFailedException;
    boolean groupExist(String groupid);
    void insertGroup(String groupID, String groupName) throws DataBaseFailedException;
    void deleteGroup(String groupID) throws DataBaseFailedException;

    List<GroupDTO> GetAllGroups();

    List<SearchRecordDTO> getAllUserSearches();
}


