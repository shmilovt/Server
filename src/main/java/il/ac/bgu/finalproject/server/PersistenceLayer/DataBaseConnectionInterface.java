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

    void addPostO(String id, String date, String message);

    void updateO(String id, String date, String message);


    List<String> getPostO(String id);

    void deletePostO(String id);

    List<String> GetAllPostsIdO();

    void addPost(String id, String date, String publisherName, String message, String apartmentID);


    void update(String id, String date, String publisherName, String message, String apartmentID);

    Post getPost(String id);

    void deletePost(String id);

    List<String> GetAllPostsId();

    int addAddressDetailsRecord(String street, String numOfBuilding, double timeFromUni, int neighborhood, double longitude, double latitude);

    void updateAddressDetailsRecordaaaa(String street, String numOfBuilding, double timeFromUni, int neighborhood, double longitude, double latitude, int addressDetailsNum);

    Post getPostaaaa(String id);

    void deletePostaaa(String id);


    void addContactsRecord(String phone, String name);

    void addApartmentContactsRecord(int apartmentID, String phoneNumber);

    String addApartmentRecord(String apartmentID, int numOfRooms, int floor,
                              int size, int cost, int addressDetailsID);

    Apartment getApartmentRecordTBD(String id);
    void deleteApartmentRecord(String id);
    void deleteAddressDetailsRecord(String id);
    Boolean moreApartmentsWithAddressDetailsNum(String addressDetailsNum);
    void addSearchRecord(String neighborhood, String timeFromUni, String cost, String floor, String size, String furnitures);
    void updateApartment(Apartment apartment, String post);
    Boolean morePostsWithApartmentID(String id);
    boolean isApartmentExist(Apartment apartment);
}


