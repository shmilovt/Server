package il.ac.bgu.finalproject.server.PersistenceLayer;

import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Apartment;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Contact;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Post;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils.SearchResults;
import il.ac.bgu.finalproject.server.Domain.Exceptions.DataBaseFailedException;

import java.util.List;
import java.util.Set;

public interface DataBaseConnectionInterface {


    void connect() throws DataBaseFailedException;

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
    void addSearchRecord(String neighborhood, String timeFromUni, String cost, String floor, String size, String furnitures) throws DataBaseFailedException;
    void updateApartmentRecord(Apartment apartment, String apartmentid) throws DataBaseFailedException;
    int isApartmentExist(Apartment apartment) throws DataBaseFailedException;
    void addApartmentDerivatives(Apartment apartment, String post) throws DataBaseFailedException;
    void updateApartmentDerivatives(Apartment apartment,String postID) throws DataBaseFailedException;
    SearchResults allResultsFromDB ();
    Post getPostByApartmentID(String string);


    }


