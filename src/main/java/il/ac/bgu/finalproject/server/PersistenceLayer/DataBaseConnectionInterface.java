package il.ac.bgu.finalproject.server.PersistenceLayer;

import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Apartment;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Post;

import java.util.List;

public interface DataBaseConnectionInterface {


    void connect();

    void disConnect();

    void resetContactsTable();

    void resetAddressDetailsTable();

    void resetApartmentTable();

    void resetApartmentContactsTable();

    void resetPostsTable();

    void resetSearchRecordsTable();

    void resetAllTables();

    void addPost(String id, String date, String publisherName, String message, String apartmentID);

    List<Apartment> allApartmentFromDB();

    void update(String id, String date, String publisherName, String message, String apartmentID);

    Post getPost(String id);

    void deletePost(String id);

    List<String> GetAllPostsId();

    int addAddressDetailsRecord(String street, String numOfBuilding, double timeFromUni, int neighborhood, double longitude, double latitude);

// I dont think this function is neccessary:
//    void updateAddressDetailsRecord(String street, String numOfBuilding, double timeFromUni, int neighborhood, double longitude, double latitude, int addressDetailsNum);

    Post getPostaaaa(String id);

    void deletePostaaa(String id);


    void addContactsRecord(String phone, String name);

    void addApartmentContactsRecord(String apartmentID, String phoneNumber);

    String addApartmentRecord(String apartmentID, int numOfRooms, int floor,
                              int size, int cost, int addressDetailsID,
                              int garden, int gardenSize, int protectedSpace, int warehouse, int animal,
                              int balcony, int furniture, int numberOfMates
    );

    Apartment getApartmentRecordTBD(String id);
    void deleteApartmentRecord(String id);
    void deleteAddressDetailsRecord(String id);
    Boolean moreApartmentsWithAddressDetailsNum(String addressDetailsNum);
    void addSearchRecord(String neighborhood, String timeFromUni, String cost, String floor, String size, String furnitures);
    void updateApartmentRecord(Apartment apartment, String apartmentid);
    Boolean morePostsWithApartmentID(String id);
    int isApartmentExist(Apartment apartment);
    void addApartmentDerivatives(Apartment apartment, String post);
    void updateApartmentDerivatives(Apartment apartment,String postID);
}

