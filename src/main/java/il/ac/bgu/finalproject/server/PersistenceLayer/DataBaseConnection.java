package il.ac.bgu.finalproject.server.PersistenceLayer;

import il.ac.bgu.finalproject.server.CommunicationLayer.AdminDTOs.ArraySearchRecordDTO;
import il.ac.bgu.finalproject.server.CommunicationLayer.AdminDTOs.SearchRecordDTO;
import il.ac.bgu.finalproject.server.CommunicationLayer.DTOs.GroupDTO;
import il.ac.bgu.finalproject.server.Domain.Controllers.GoogleMapsController;
import il.ac.bgu.finalproject.server.Domain.Controllers.MyLogger;
import il.ac.bgu.finalproject.server.Domain.Controllers.ServerController;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.*;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils.ResultRecord;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils.SearchResults;
import il.ac.bgu.finalproject.server.Domain.Exceptions.DataBaseFailedException;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.*;
import java.util.logging.Level;

public class DataBaseConnection implements DataBaseConnectionInterface {

    private static final String dateFormat = "yyyy/MM/dd HH:mm:ss";

    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    private static Connection conn = null;
    private  String addressDetailsIDString ="addressDetailsID";
    private  String apartmentIDString ="apartmentID";

    public void connect() {
//        String url = "jdbc:sqlite:src\\main\\java\\il\\ac\\bgu\\finalproject\\server\\PersistenceLayer\\db\\ApartmentBS.db";
        String url = "jdbc:sqlite:ApartmentBS.db";
        try {
            conn = DriverManager.getConnection(url);
        }
        catch(SQLException e){}
        catch (Exception e){
            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
//            throw new DataBaseFailedException("disconnect to the DataBase ",1);
        }
        //System.out.println("Connection to SQLite has been established.");
    }
    public void disConnect() throws DataBaseFailedException {
        try {
            if (conn != null) {
                conn.close();
            }
        }
        catch(SQLException e){}
        catch (Exception e){
            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
            throw new DataBaseFailedException(" disconnect to the DataBase ",1);
        }
    }

    public void resetConstValueTable() throws DataBaseFailedException {
        String sql= "DROP TABLE ConstValues";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);;
            pstmt.executeUpdate();
        }
        catch(SQLException e){}
        catch (Exception e){
            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
            throw new DataBaseFailedException("drop the contacts table",4);
        }

        sql= "CREATE TABLE ConstValues(constV text PRIMARY KEY,\n" +
                "  numV int NOT NULL\n" +
                ")";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);;
            pstmt.executeUpdate();
        }
        catch(SQLException e){}
        catch (Exception e){
            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
            throw new DataBaseFailedException("create the contacts table",4);
        }

        sql= "INSERT INTO ConstValues(constV, numV) VALUES (?,?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,"apartmentID");
            pstmt.setInt(2,0);
            pstmt.executeUpdate();
        }
        catch(SQLException e){}
        catch (Exception e){
            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
            throw new DataBaseFailedException("insert into the ConstValues table",4);
        }
        sql= "INSERT INTO ConstValues(constV, numV) VALUES (?,?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,"addressDetailsID");
            pstmt.setInt(2,0);
            pstmt.executeUpdate();
        }
        catch(SQLException e){}
        catch (Exception e){

            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
            throw new DataBaseFailedException("insert into the ConstValues table",4);
        }

        sql= "INSERT INTO ConstValues(constV, numV) VALUES (?,?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,"postFromAdminID");
            pstmt.setInt(2,0);
            pstmt.executeUpdate();
        }
        catch(SQLException e){}
        catch (Exception e){

            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
            throw new DataBaseFailedException("insert into the ConstValues table",4);
        }
    }
    public int getConstValue (String id) throws DataBaseFailedException {
        int value=-1;
        try {
            String sql = "SELECT numV FROM ConstValues WHERE constV= '" + id+"'" ;
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            value= rs.getInt(1);
        }
        catch(SQLException e){}
        catch (Exception e){

            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
            throw new DataBaseFailedException("insert into ConstValues table",4);
        }
        return value;
    }
    public void setConstValue (String id, int val) throws DataBaseFailedException {
        try {
            String sql = "UPDATE ConstValues SET numV= ? WHERE constV= ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,val);
            pstmt.setString(2,id);
            pstmt.executeUpdate();
        }
        catch(SQLException e){}
        catch (Exception e){

            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
            throw new DataBaseFailedException("drop th contacts table",4);
        }
    }

    public void resetContactsTable() throws DataBaseFailedException {
        String sql= "DROP TABLE contacts";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);;
            pstmt.executeUpdate();
        }
        catch(SQLException e){}
        catch (Exception e){
            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
            throw new DataBaseFailedException("drop th contacts table",4);
        }

        sql= "CREATE TABLE Contacts(\n" +
                "  phone text PRIMARY KEY,\n" +
                "  name text NOT NULL\n" +
                ")";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);;
            pstmt.executeUpdate();
        }
        catch(SQLException e){}
        catch (Exception e){
            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
            throw new DataBaseFailedException("create the contacts table",4);}
    }

    public void resetAddressDetailsTable() throws DataBaseFailedException {
        String sql= "DROP TABLE addressDetails";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);;
            pstmt.executeUpdate();
        }
        catch(SQLException e){}
        catch (Exception e){

            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
            throw new DataBaseFailedException("drop th contacts table",4);
        }
        sql= "CREATE TABLE AddressDetails(\n" +
                "  street text NOT NULL ,\n" +
                "  numOfBuilding text NOT NULL,\n" +
                "  timeFromUni double,\n" +
                "  neighborhood text,\n" +
                "  longitude double,\n" +
                "  latitude double,\n" +
                "  addressDetailsNum int,\n" +
                "    PRIMARY KEY(street, numOfBuilding)\n" +
                ")";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);;
            pstmt.executeUpdate();
        }
        catch(SQLException e){}
        catch (Exception e){
            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
            throw new DataBaseFailedException("drop th contacts table",4);
        }    }

    public void resetApartmentTable() throws DataBaseFailedException {
        String sql= "DROP TABLE apartment";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);;
            pstmt.executeUpdate();
        }
        catch(SQLException e){}
        catch (Exception e){

            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
            throw new DataBaseFailedException("drop th contacts table",4);
        }
        sql=  "CREATE TABLE Apartment(\n" +
                "  apartmentID INTEGER PRIMARY KEY,\n" +
                "  numOfRooms DOUBLE ,\n" +
                "  floor int,\n" +
                "  size double,\n" +
                "  cost int NOT NULL ,\n" +
                "  addressDetailsID int,\n" +
                "  garden int,\n" +  //added from here
                "  gardenSize int,\n" +
                "  protectedSpace int,\n" +
                "  warehouse int,\n" +
                "  animal int,\n" +
                "  balcony int,\n" +
                "  furniture int,\n" +
                "  numberOfMates int,\n" + //to here
                "  FOREIGN KEY(addressDetailsID) REFERENCES addressDetails(addressDetailsNum)\n" +
                ")";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);;
            pstmt.executeUpdate();
        }
        catch(SQLException e){}
        catch (Exception e){

            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
            throw new DataBaseFailedException("drop th contacts table",4);
        }    }

    public void resetApartmentContactsTable() throws DataBaseFailedException {
        String sql= "DROP TABLE apartmentContacts";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);;
            pstmt.executeUpdate();
        }
        catch(SQLException e){}
        catch (Exception e){
            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
            throw new DataBaseFailedException("drop th contacts table",4);
        }
        sql=  "CREATE TABLE ApartmentContacts(\n" +
                "  apartmentID text NOT NULL,\n" +
                "  phoneNumber text NOT NULL,\n" +
                "  FOREIGN KEY(apartmentID) REFERENCES apartment(apartmentID),\n" +
                "  FOREIGN KEY(phoneNumber) REFERENCES contacts(phone),\n" +
                "  PRIMARY KEY(apartmentID, phoneNumber)\n" + ")";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);;
            pstmt.executeUpdate();
        }
        catch(SQLException e){}
        catch (Exception e){

            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
            throw new DataBaseFailedException("drop th contacts table",4);
        }    }

    public void resetPostsTable() throws DataBaseFailedException {
        String sql= "DROP TABLE posts";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        }
        catch(SQLException e){}
        catch (Exception e){

            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
            throw new DataBaseFailedException("drop th contacts table",4);
        }
        sql=  "CREATE TABLE Posts(\n" +
                "  postID int PRIMARY KEY,\n" +
                "  dateOfPublish text,\n" +
                "  publisherName text,\n" +
                "  postText text,\n" +
                "  apartmentID int,\n" +
                "    FOREIGN KEY(apartmentID) REFERENCES apartment(apartmentID)\n" +
                ")";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);;
            pstmt.executeUpdate();
        }
        catch(SQLException e){}
        catch (Exception e){
            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
            throw new DataBaseFailedException("drop th contacts table",4);
        }    }

    public void resetSearchRecordsTable() throws DataBaseFailedException {
        String sql= "DROP TABLE searchRecord";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        }
        catch(SQLException e){}
        catch (Exception e){

            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
            throw new DataBaseFailedException("drop th contacts table",4);
        }        sql=  "CREATE TABLE SearchRecord(\n" +
                "  searchDate text ,\n" +
                "  neighborhood text,\n" +
                "  timeFromUni text,\n" +
                "  cost text ,\n" +
                "  floor text,\n" +
                "  size text ,\n" +
                "  furnitures text,\n" +
                "  numOfRoomes text,\n" +
                "  numOfMates text,\n" +
                "  PRIMARY KEY (searchDate, neighborhood, timeFromUni, cost, floor, size, furnitures,numOfRoomes, numOfMates)" +
                ")";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);;
            pstmt.executeUpdate();
        }
        catch(SQLException e){}
        catch (Exception e){

            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
            throw new DataBaseFailedException("drop th contacts table",4);
        }    }

    public void resetAllTables() throws DataBaseFailedException {
        //resetConstValueTable();
        resetContactsTable();
        resetAddressDetailsTable();
        resetApartmentTable();
        resetApartmentContactsTable();
        resetPostsTable();
        resetSearchRecordsTable();
    }


    ///===========POSTS TABLE==============///
    public void addPost(String id, String date, String publisherName, String message, String apartmentID) throws DataBaseFailedException {
        SimpleDateFormat simpleDateFormat= new SimpleDateFormat();
        simpleDateFormat.applyPattern(dateFormat);
        try {
            String sql = "INSERT INTO posts(postID, dateOfPublish, publisherName, " +
                    "postText, apartmentID) VALUES(?,?,?,?,?)";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, date);
            pstmt.setString(3, publisherName);
            pstmt.setString(4, message);
            pstmt.setString(5, apartmentID);
            pstmt.executeUpdate();
        }
        catch(SQLException e){}
        catch (Exception e){
            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
            throw new DataBaseFailedException("drop th contacts table",4);
        }
    }

    public void update(String id, String date, String publisherName, String message, String apartmentID) throws DataBaseFailedException {
        String sql= "UPDATE Posts SET dateOfPublish= ?, publisherName= ?, postText= ?, apartmentID= ? "
                + "WHERE postID= ?";
        try{
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, date);
            pstmt.setString(2, publisherName);
            pstmt.setString(3, message);
            pstmt.setString(4, apartmentID);
            pstmt.setString(5, id);
            pstmt.executeUpdate();
            // System.out.println("update done");
        }
        catch(SQLException e){}
        catch (Exception e){
            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
            throw new DataBaseFailedException("drop th contacts table",4);
        }
    }

    public Post getPost(String id) {
        try {
            String sql = "SELECT * FROM posts where postID =" + "'" + id + "'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            SimpleDateFormat simpleDateFormat= new SimpleDateFormat(dateFormat);

            Post post = new Post(rs.getString(1), simpleDateFormat.parse(rs.getString(2)),
                    rs.getString(3), rs.getString(4),
                    rs.getString(5));
            return post;
        }
        catch (SQLException e) {return null;}
        catch (Exception e) {
            MyLogger.getInstance().log(Level.SEVERE, e.getMessage(), e);
            return null;
        }
    }

    public void deletePost(String id) throws DataBaseFailedException {
        //  System.out.println("Hello deletePost");
        String sql = "DELETE FROM posts WHERE postID = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        }
        catch(SQLException e){}
        catch (Exception e){
            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
            throw new DataBaseFailedException("drop th contacts table",4);
        }
    }

    public List<String> GetAllPostsId() {
//        connect();
        String sql = "SELECT postID FROM posts";
        List<String> posts = new LinkedList<String>();
        //Post tempPost= new Post("");
        try {
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            while (rs.next())
                posts.add(rs.getString(1));
//            disConnect();
            return posts;
        }
        catch(SQLException e){return null;}
        catch (Exception e){
            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
//            throw new DataBaseFailedException("drop th contacts table",4);

            return null;
        }
    }

    ///===========ADDRESS DETAILS TABLE==============///
    public int addAddressDetailsRecord(String street, String numOfBuilding, double timeFromUni, String neighborhood, double longitude, double latitude) throws DataBaseFailedException {
        int t;
        try {
            String sql = "INSERT INTO AddressDetails(street, numOfBuilding, timeFromUni, "+
                    "neighborhood, longitude, latitude, addressDetailsNum)"+
                    " VALUES(?,?,?,?,?,?,?)";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, street);
            pstmt.setString(2, numOfBuilding);
            pstmt.setDouble(3, timeFromUni);
            pstmt.setString(4, neighborhood);
            pstmt.setDouble(5, longitude);
            pstmt.setDouble(6, latitude);
            t= getConstValue(addressDetailsIDString);//addressDetailsID +1;
            pstmt.setInt(7, t);
            pstmt.executeUpdate();
            setConstValue(addressDetailsIDString,t+1);
            return t;
        }
        catch(SQLException e){return -1;}
        catch (Exception e){
            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
//            throw new DataBaseFailedException("drop th contacts table",4);
            return -1;
        }
    }

    public void updateAddressDetailsRecord(String street, String numOfBuilding, double timeFromUni, String neighborhood, double longitude, double latitude) throws DataBaseFailedException {
        //we need to compare if
        String sql = "UPDATE AddressDetails SET timeFromUni= ? , "
                + "neighborhood= ? ,  "
                + "longitude= ? ,"
                + "latitude= ? "
                //String street, String numOfBuilding, double timeFromUni, int neighborhood,
                // double longitude, double latitude, int addressDetailsNum
                + "WHERE street= ? AND numOfBuilding= ?";
        try{
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setDouble(1, timeFromUni);
            pstmt.setString(2, neighborhood);
            pstmt.setDouble(3, longitude);
            pstmt.setDouble(4, latitude);
            pstmt.setString(5, street);
            pstmt.setString(6, numOfBuilding);

            // update
            pstmt.executeUpdate();
            // System.out.println("update done");
        }
        catch(SQLException e){}
        catch (Exception e){
            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
            throw new DataBaseFailedException("drop th contacts table",4);
        }
    }

    public void deleteAddressDetailsRecord(String id) throws DataBaseFailedException {
        String sql = "DELETE FROM AddressDetails WHERE addressDetailsNum= id";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        }
        catch(SQLException e){}
        catch (Exception e) {
            MyLogger.getInstance().log(Level.SEVERE, e.getMessage(), e);
            throw new DataBaseFailedException("drop th contacts table", 4);
        }
    }
    ///===========CONTACTS TABLE==============///

    public void addContactsRecord(String phone, String name) throws DataBaseFailedException {
        try {
            String sql = "INSERT INTO Contacts(phone, name)"+
                    " VALUES(?,?)";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, phone);
            pstmt.setString(2, name);
            pstmt.executeUpdate();
            //System.out.println("Added");
        }
        catch(SQLException e){}
        catch (Exception e){

            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
            throw new DataBaseFailedException("drop th contacts table",4);
        }
    }
    ///===========APARTMENT_CONTACTS TABLE==============///

    public void addApartmentContactsRecord(String apartmentID , String phoneNumber) throws DataBaseFailedException {
        try {
            String sql = "INSERT INTO ApartmentContacts(apartmentID, phoneNumber)"+
                    " VALUES(?,?)";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, apartmentID);
            pstmt.setString(2, phoneNumber);
            pstmt.executeUpdate();
            //System.out.println("Added");
        }
        catch(SQLException e){}
        catch (Exception e){
            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
            throw new DataBaseFailedException("drop th contacts table",4);
        }
    }
    ///===========APARTMENT TABLE==============///

    public String addApartmentRecord(Apartment apartment,int addressDetails_ID) throws DataBaseFailedException {
        int t=-1;
        try {
            String sql = "INSERT INTO Apartment(apartmentID, numOfRooms, floor, size, cost, addressDetailsID, " +
                    "garden, gardenSize, protectedSpace, warehouse, animal, " +
                    "balcony, furniture, numberOfMates)"+
                    " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            t = getConstValue(apartmentIDString);//cApartmentID+1;
            pstmt.setInt(1, t);
            pstmt.setDouble(2, apartment.getNumberOfRooms());
            pstmt.setInt(3, apartment.getApartmentLocation().getFloor());
            pstmt.setDouble(4, apartment.getSize());
            pstmt.setInt(5, apartment.getCost());
            pstmt.setInt(6, addressDetails_ID);

            pstmt.setInt(7, apartment.getGarden());
            pstmt.setInt(8, apartment.getGardenSize());
            pstmt.setInt(9, apartment.getProtectedSpace());
            pstmt.setInt(10, apartment.getWarehouse());
            pstmt.setInt(11, apartment.getAnimal());
            pstmt.setInt(12, apartment.getBalcony());
            pstmt.setInt(13, apartment.getFurniture());
            pstmt.setInt(14, apartment.getNumberOfMates());

            pstmt.executeUpdate();
            setConstValue(apartmentIDString,t+1);
            //System.out.println("Added");
        }
        catch(SQLException e){}
        catch (Exception e){
            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
//            throw new DataBaseFailedException("drop th contacts table",4);
            return e.toString();
        }
        return ""+t;
    }
    public String addApartmentRecord(String apartmentID, double numOfRooms, int floor,
                                     int size, int cost, int addressDetailsID,
                                     int garden, int gardenSize, int protectedSpace, int warehouse, int animal,
                                     int balcony, int furniture, int numberOfMates
    ) throws DataBaseFailedException {
        int t=-1;
        try {
            String sql = "INSERT INTO Apartment(apartmentID, numOfRooms, floor, size, cost, addressDetailsID, " +
                    "garden, gardenSize, protectedSpace, warehouse, animal, " +
                    "balcony, furniture, numberOfMates)"+
                    " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            t = getConstValue(apartmentIDString);//cApartmentID+1;
            pstmt.setInt(1, t);
            pstmt.setDouble(2, numOfRooms);
            pstmt.setInt(3, floor);
            pstmt.setDouble(4, size);
            pstmt.setInt(5, cost);
            pstmt.setInt(6, addressDetailsID);

            pstmt.setInt(7, garden);
            pstmt.setInt(8, gardenSize);
            pstmt.setInt(9, protectedSpace);
            pstmt.setInt(10, warehouse);
            pstmt.setInt(11, animal);
            pstmt.setInt(12, balcony);
            pstmt.setInt(13, furniture);
            pstmt.setInt(14, numberOfMates);

            pstmt.executeUpdate();
            setConstValue(apartmentIDString,t+1);
            //System.out.println("Added");
        }
        catch(SQLException e){}
        catch (Exception e){
            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
            return e.toString();
        }
        return ""+t;
    }

    public void deleteApartmentRecord(String id) throws DataBaseFailedException {
        String sql = "SELECT addressDetailsID FROM Apartment WHERE apartmentID= "+id;
        int t=-1;
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs= pstmt.executeQuery(sql);
            t= rs.getInt(1);
        }
        catch(SQLException e){}
        catch (Exception e){
            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
            throw new DataBaseFailedException("drop th contacts table",4);
        }
        if(!moreApartmentsWithAddressDetailsNum(""+t))
            deleteAddressDetailsRecord(""+t);

        sql = "DELETE FROM Apartment WHERE apartmentID= "+id;
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        }
        catch(SQLException e){}
        catch (Exception e){
            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
            throw new DataBaseFailedException("drop th contacts table",4);
        }
        //apartmentContacts
        sql = "DELETE FROM ApartmentContacts WHERE apartmentID= "+id;
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        }
        catch(SQLException e){}
        catch (Exception e){
            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
            throw new DataBaseFailedException("drop th contacts table",4);
        }
    }

    ///===========GET OBJECTS FROM DB==============///

    public ApartmentLocation getAddressDetils (int addressDetilsID ){

        ApartmentLocation location= new ApartmentLocation();
        Address address= new Address();
        String sql = "SELECT AddressDetails.street, AddressDetails.numOfBuilding, AddressDetails.timeFromUni, " +
                " AddressDetails.neighborhood, AddressDetails.longitude, AddressDetails.latitude " +
                " FROM AddressDetails WHERE addressDetailsNum= '"  + addressDetilsID +"'" ;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            address= new Address(rs.getString(1),rs.getInt(2));
            address.setStreet(rs.getString(1));
            address.setNumber(rs.getInt(2));
//            System.out.println("3) "+address.toString()+  "  4) "+address.getNumber());
            location.setAddress(address);
            location.setUniversity_distance(rs.getInt(3));
            location.setNeighborhood(rs.getString(4));
            location.setLongitude(rs.getDouble(5));
            location.setLatitude(rs.getDouble(6));

        }
        catch(SQLException e){
        }
        catch (Exception e){
            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
//            return null;
        }
        return location;
    }

    public Apartment getApartmentRecordTBD(String id) {
        List<Apartment> apartments = new LinkedList<Apartment>();
        Apartment temp;
        try {
            String sql = "SELECT apartmentID, addressDetailsID, floor, cost, Apartment.size, " +
                    " garden, gardenSize, protectedSpace, warehouse, animal, balcony, furniture," +
                    " numberOfMates, numOfRooms  "
                    + " FROM Apartment"
                    + " WHERE apartmentID= " +id;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                Set<Contact> contacts = getApartmentContacts(rs.getString(1));
                //System.out.println(contacts.toString());
                ApartmentLocation location = getAddressDetils(rs.getInt(2));
                location.setFloor(rs.getInt(3));
                //System.out.println(location.toString());
                temp = new Apartment(location, rs.getInt(4), rs.getInt(5), contacts);
                //System.out.println(temp.toString());
                temp.setGarden(rs.getInt(6));
                temp.setGardenSize(rs.getInt(7));
                temp.setProtectedSpace(rs.getInt(8));
                temp.setWarehouse(rs.getInt(9));
                temp.setAnimal(rs.getInt(10));
                temp.setBalcony(rs.getInt(11));
                temp.setFurniture(rs.getInt(12));
                temp.setNumberOfMates(rs.getInt(13));
                temp.setNumberOfRooms(rs.getDouble(14));
                return temp;
            }

        }
        catch(SQLException e){}
        catch (Exception e){
            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
        }
//        disConnect();
        return null;
        /*try {
            String sql = "SELECT addressDetailsID, numOfRooms, floor, Apartment.size, cost, garden, " +
                    "gardenSize, protectedSpace, warehouse, animal, balcony, furniture, numberOfMates " +
                    "FROM Apartment where apartmentID =" + "" + id + "";
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            ApartmentLocation location= getAddressDetils(rs.getInt(1));
            System.out.println(location.toString());
            location.setFloor(rs.getInt(3));

            Set<Contact> contacts= getApartmentContacts(id);

            Apartment apartment= new Apartment();
            apartment.setContacts(contacts);
            apartment.setNumberOfRooms(rs.getInt(2));
            apartment.setSize(rs.getInt(4));
            apartment.setCost(rs.getInt(5));
            apartment.setGarden(rs.getInt(7));
            apartment.setGardenSize(rs.getInt(8));
            apartment.setProtectedSpace(rs.getInt(9));
            apartment.setWarehouse(rs.getInt(10));
            apartment.setAnimal(rs.getInt(11));
            apartment.setBalcony(rs.getInt(12));
            apartment.setFurniture(rs.getInt(13));
            apartment.setNumberOfMates(rs.getInt(14));
            apartment.setApartmentLocation(location);
            return apartment;
        }
        catch(SQLException e){return null;}
        catch (Exception e){
            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
            return null;
        }*/
    }

    public SearchResults allResultsFromDB () {
//        connect();
        List<ResultRecord> results = new LinkedList<ResultRecord>();
        ResultRecord temp;
        try {
            String sql = "SELECT Apartment.apartmentID , Apartment.addressDetailsID, floor, cost, Apartment.size, balcony, " +
                    "garden, animal, warehouse, protectedSpace, furniture, numOfRooms, numberOfMates, "
                    + "dateOfPublish, postText"
                    + " FROM Apartment"
                    + " JOIN Posts P ON Apartment.apartmentID = P.apartmentID";
            Statement stmt = conn.createStatement();
            System.out.println("fdfdf");
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Set<Contact> contacts = getApartmentContacts(rs.getString(1));
                ApartmentLocation location = getAddressDetils(rs.getInt(2));

                temp = new ResultRecord();
                temp.setStreet(location.getAddress().getStreet());
                temp.setNumber(location.getAddress().getNumber());
                temp.setNeighborhood(location.getNeighborhood());
                temp.setFloor(rs.getInt(3));
                temp.setDistanceFromUniversity(location.getDistanceFromUniversity());
                temp.setCost(rs.getInt(4));
                temp.setSize(rs.getInt(5));
                temp.setBalcony(rs.getBoolean(6));
                temp.setYard(rs.getBoolean(7));
                temp.setAnimals(rs.getBoolean(8));
                temp.setWarehouse(rs.getBoolean(9));
                temp.setProtectedSpace(rs.getBoolean(10));
                temp.setFurniture(rs.getInt(11));
                temp.setNumberOfRooms(rs.getDouble(12));
                temp.setNumberOfRoomates(rs.getInt(13));
//                java.sql.Date date = rs.getDate(14);
//                if(date!=null)
                rs.getString(14);
                 temp.setDateOfPublish(rs.getString(14));
//                else{
//                    temp.setDateOfPublish("");
//                }
                temp.setText(rs.getString(15));
                Contact [] contactsArray= new Contact [contacts.size()] ;
                int i=0;
                for (Contact con: contacts){
                    contactsArray[i]=con;
                    i++;
                }
                temp.setContacts(contactsArray);
                temp.setLat(location.getLatitude());
                temp.setLon(location.getLongitude());
                results.add(temp);
            }

        }
        catch(SQLException e){System.out.println(e);}
        catch (Exception e){
            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
//            throw new DataBaseFailedException("drop th contacts table",4);
        }
        SearchResults searchResults= new SearchResults(results);
        return searchResults;
    }

    public List<Apartment> allApartmentFromDB () {
//        connect();
        List<Apartment> apartments = new LinkedList<Apartment>();
        Apartment temp;
        try {
            String sql = "SELECT apartmentID, addressDetailsID, floor, cost, size"
                    + " FROM Apartment";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Set<Contact> contacts = getApartmentContacts(rs.getString(1));
                //System.out.println(contacts.toString());
                ApartmentLocation location = getAddressDetils(rs.getInt(2));
                location.setFloor(rs.getInt(3));
                //System.out.println(location.toString());
                temp = new Apartment(location, rs.getInt(4), rs.getInt(5), contacts);
                //System.out.println(temp.toString());
                apartments.add(temp);
            }

        }
        catch(SQLException e){}
        catch (Exception e){
            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
        }
//        disConnect();
        return apartments;
    }

    public Apartment getApartmentByID(String apartmentID) {
//        connect();
        List<Apartment> apartments = new LinkedList<Apartment>();
        Apartment temp;
        try {
            String sql = "SELECT apartmentID, addressDetailsID, floor, cost, size"
                    + " FROM Apartment"
                    + " WHERE apartmentID= " +apartmentID;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                Set<Contact> contacts = getApartmentContacts(rs.getString(1));
                //System.out.println(contacts.toString());
                ApartmentLocation location = getAddressDetils(rs.getInt(2));
                location.setFloor(rs.getInt(3));
                //System.out.println(location.toString());
                temp = new Apartment(location, rs.getInt(4), rs.getInt(5), contacts);
                //System.out.println(temp.toString());
                return temp;
            }

        }
        catch(SQLException e){}
        catch (Exception e){
            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
        }
//        disConnect();
        return null;
    }

    public Set<Contact> getApartmentContacts(String id) {
        Set<Contact> contacts= new HashSet<Contact>();
        Contact tempContact;
        String sql = "SELECT phone, name FROM Contacts " +
                "JOIN ApartmentContacts C2 ON Contacts.phone = C2.phoneNumber" +
                " WHERE apartmentID= "  + id ;
        Statement stmt  = null;
        try {
            stmt = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            while (rs.next()){
                tempContact= new Contact(rs.getString(2),rs.getString(1));
                contacts.add(tempContact);
            }
        }
        catch(SQLException e){}
        catch (Exception e){
            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
        }
        return  contacts;
    }
    ///===========MORE FROM... (BOOLEAN) ==============///

    public Boolean morePostsWithApartmentID(String id){
        String sql = "SELECT * FROM posts where apartmentID =" + "'" + id + "'";
        Statement stmt  = null;
        try {
            stmt = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        }
        catch(SQLException e){}
        catch (Exception e){
            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
        }
        return false;
    }

    public Boolean moreApartmentsWithAddressDetailsNum(String addressDetailsNum){
//        String sql = "SELECT * FROM AddressDetails where addressDetailsNum=" + "'" + addressDetailsID + "'";
        String sql = "SELECT * FROM AddressDetails where addressDetailsNum="  + addressDetailsNum;
        Statement stmt  = null;
        try {
            stmt = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            if (rs.next()) { return true; }
            else { return false; }
        }
        catch(SQLException e){}
        catch (Exception e){
            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
        }
        return false;
    }

    public void deleteAllRecords(String tablaName) throws DataBaseFailedException {
        String sql = "DELETE FROM ?;";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, tablaName);
            pstmt.executeUpdate();
        }
        catch(SQLException e){}
        catch (Exception e){
            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
            throw new DataBaseFailedException("drop th contacts table",4);
        }
    }

    public void addSearchRecord(String neighborhood, String timeFromUni, String cost, String floor,
                                String size, String furnitures, String numOfRoomes, String numOfMates) throws DataBaseFailedException { //func that will be used by the client (Android App)
        LocalDateTime now = LocalDateTime.now();
        try {
            String sql = "INSERT INTO SearchRecord(searchDate, neighborhood,"+
                    " timeFromUni, cost, floor, size, furnitures, numOfRoomes ,numOfMates)"+
                    " VALUES(?,?,?,?,?,?,?,?,?)";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, dtf.format(now));
            pstmt.setString(2, neighborhood);
            pstmt.setString(3, timeFromUni);
            pstmt.setString(4, cost);
            pstmt.setString(5, floor);
            pstmt.setString(6, size);
            pstmt.setString(7, furnitures);
            pstmt.setString(8, numOfRoomes);
            pstmt.setString(9, numOfMates);

            pstmt.executeUpdate();
        }
        catch(SQLException e){}
        catch (Exception e){
            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
            throw new DataBaseFailedException("drop th contacts table",4);
        }
    }

    public List<SearchRecordDTO> getAllUserSearches(){
        SearchRecordDTO tempSearchRecordDTO;
        List<SearchRecordDTO> searchRecordDTOList= new LinkedList<SearchRecordDTO>();
        try {
            String sql = "SELECT searchDate, neighborhood, timeFromUni, cost, floor, SearchRecord.size," +
                    " furnitures, numOfRoomes, numOfMates"
                    + " FROM SearchRecord";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                tempSearchRecordDTO = new SearchRecordDTO();
                tempSearchRecordDTO.setSearchDate(rs.getString(1));
                tempSearchRecordDTO.setNeighborhood(rs.getString(2));
                tempSearchRecordDTO.setDistanceFromUniversity(rs.getString(3));
                tempSearchRecordDTO.setCost(rs.getString(4));
                tempSearchRecordDTO.setFloor(rs.getString(5));
                tempSearchRecordDTO.setSize(rs.getString(6));
                tempSearchRecordDTO.setFurniture(rs.getString(7));
                tempSearchRecordDTO.setNumberOfRooms(rs.getString(8));
                tempSearchRecordDTO.setNumberOfMates(rs.getString(9));
                searchRecordDTOList.add(tempSearchRecordDTO);
            }
        }
        catch(SQLException e){System.out.println(e);}
        catch (Exception e){
            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
//            throw new DataBaseFailedException("drop th contacts table",4);
        }

        return searchRecordDTOList;
    }

    public void updateApartmentRecord(Apartment apartment, String apartmentid) throws DataBaseFailedException {
        try {
            String sql = "UPDATE Apartment SET numOfRooms=? , floor=? , size=? , cost=? ,  " +
                    "garden=? , gardenSize=? , protectedSpace=? , warehouse=? , animal=? , " +
                    "balcony=? , furniture=? , numberOfMates=? " +
                    "WHERE apartmentID=? ";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1, apartment.getNumberOfRooms());
            pstmt.setInt(2, apartment.getApartmentLocation().getFloor());
            pstmt.setDouble(3, apartment.getSize());
            pstmt.setInt(4, apartment.getCost());

            pstmt.setInt(5, apartment.getGarden());
            pstmt.setInt(6, apartment.getGardenSize());
            pstmt.setInt(7, apartment.getProtectedSpace());
            pstmt.setInt(8, apartment.getWarehouse());
            pstmt.setInt(9, apartment.getAnimal());
            pstmt.setInt(10, apartment.getBalcony());
            pstmt.setInt(11, apartment.getFurniture());
            pstmt.setInt(12, apartment.getNumberOfMates());
            pstmt.setString(13, apartmentid);
            pstmt.executeUpdate();
            //System.out.println("Added");
//            UpdateAddressDetailsForApartment(apartmentid,)
            int addressDetailsNum= isAddressDetailsExist(apartment.getApartmentLocation().getAddress().getStreet(),
                    apartment.getApartmentLocation().getAddress().getNumber());
            if (addressDetailsNum==-1) {
                addressDetailsNum = addAddressDetailsRecord(apartment.getApartmentLocation().getAddress().getStreet(),
                        "" + apartment.getApartmentLocation().getAddress().getNumber(),
                        apartment.getApartmentLocation().getDistanceFromUniversity(),
                        apartment.getApartmentLocation().getNeighborhood(),
                        apartment.getApartmentLocation().getLongitude(),
                        apartment.getApartmentLocation().getLatitude());
                changeAddresDetailsForApartment(apartmentid,addressDetailsNum);
            }
        }
        catch(SQLException e){}
        catch (Exception e){
            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
            throw new DataBaseFailedException("drop th contacts table",4);
        }
    }

    public void changeAddresDetailsForApartment(String apartmentid, int addressDetailsNum) throws DataBaseFailedException {
        try {
            String sql = "UPDATE Apartment SET addressDetailsID=? " +
                    "WHERE apartmentID=? ";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,apartmentid);
            pstmt.setInt(2,addressDetailsNum);
            pstmt.executeUpdate();
        }
        catch(SQLException e){}
        catch (Exception e){
            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
            throw new DataBaseFailedException("drop th contacts table",4);
        }
    }

    public int isApartmentExist(Apartment apartment) throws DataBaseFailedException {
        Set<Contact> contacts = apartment.getContacts();
        for (Contact eachcotact : contacts) {
            Statement stmt = null;
            String sql = "SELECT Apartment.apartmentID FROM Apartment "
                    + " JOIN AddressDetails Detail ON Apartment.addressDetailsID = Detail.addressDetailsNum"
                    + " JOIN ApartmentContacts C2 ON Apartment.apartmentID = C2.apartmentID"
                    + " JOIN Contacts C3 ON C2.phoneNumber = C3.phone "
                    + " WHERE phoneNumber= '"+eachcotact.getPhone()
                    +"' AND street= '"+apartment.getApartmentLocation().getAddress().getStreet()
                    +"' AND numOfBuilding= "+ apartment.getApartmentLocation().getAddress().getNumber();
            try {
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
            catch(SQLException e){}
            catch (Exception e){
                MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
                throw new DataBaseFailedException("drop th contacts table",4);
            }
        }
        return -1;
    }

    public int isAddressDetailsExist(String street, int number) {
        Statement stmt = null;
        String sql = "SELECT addressDetailsNum FROM AddressDetails "
                +" WHERE street= '"+street
                +"' AND numOfBuilding= "+ number;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        catch(SQLException e){}
        catch (Exception e){
            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
        }

        return -1;
    }

    public void addApartmentDerivatives(Apartment apartment, String postID) throws DataBaseFailedException {
        int tempForAddressDetaileNum= isAddressDetailsExist(apartment.getApartmentLocation().getAddress().getStreet(),
                apartment.getApartmentLocation().getAddress().getNumber());
        String tempForApartment;
        if (tempForAddressDetaileNum==-1) {
            //TODO: calc longitude, latitude, neighborhood
            tempForAddressDetaileNum = addAddressDetailsRecord(
                    apartment.getApartmentLocation().getAddress().getStreet(),
                    apartment.getApartmentLocation().getAddress().getNumber() + "",
                    apartment.getApartmentLocation().getDistanceFromUniversity(),
                    apartment.getApartmentLocation().getNeighborhood(),
                    apartment.getApartmentLocation().getLongitude(), apartment.getApartmentLocation().getLatitude());
        }
        tempForApartment= addApartmentRecord(
                postID,
                apartment.getNumberOfRooms(),
                apartment.getApartmentLocation().getFloor(),
                apartment.getSize(),
                apartment.getCost(),
                tempForAddressDetaileNum,
                apartment.getGarden(),
                apartment.getGardenSize(),
                apartment.getProtectedSpace(),
                apartment.getWarehouse(),
                apartment.getAnimal(),
                apartment.getBalcony(),
                apartment.getFurniture(),
                apartment.getNumberOfMates());
        Set<Contact> contacts= apartment.getContacts();
        for(Contact eachContact: contacts){
            addContactsRecord(eachContact.getPhone(),eachContact.getName());
            addApartmentContactsRecord(tempForApartment,eachContact.getPhone());
        }
        updateApartmentIDInPostRecord(postID,""+tempForApartment);
    }

    public void updateApartmentIDInPostRecord(String postID, String apartmentID) throws DataBaseFailedException {
        String sql= "UPDATE Posts SET apartmentID= ? "
                + "WHERE postID= ?";
        try{
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, apartmentID);
            pstmt.setString(2, postID);
            pstmt.executeUpdate();
        }
        catch(SQLException e){}
        catch (Exception e){
            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
            throw new DataBaseFailedException("drop th contacts table",4);
        }
    }

    public void updateApartmentDerivatives(Apartment apartment,String apartmentID) throws DataBaseFailedException {
        //AddressDetailsRecord will not change
        updateApartmentRecord(apartment, apartmentID);
        Set<Contact> contacts= apartment.getContacts();
        for(Contact eachContact: contacts){
            addContactsRecord(eachContact.getPhone(),eachContact.getName());
            addApartmentContactsRecord(apartmentID,eachContact.getPhone());
        }
    }

    public void resetUserSuggestionsTable() throws DataBaseFailedException {
        String sql= "DROP TABLE userSuggestions";
//        try {
//            PreparedStatement pstmt = conn.prepareStatement(sql);;
//            pstmt.executeUpdate();
//        }
//        catch(SQLException e){}
//        catch (Exception e){
//            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
//            throw new DataBaseFailedException("drop th contacts table",4);
//        }

        sql= "CREATE TABLE UserSuggestions(\n" +
                "  apartmentID text NOT NULL,\n" +
                "  field text NOT NULL,\n" +
                "  suggestion text NOT NULL,\n" +
                "  counter int NOT NULL,\n" +
                "  FOREIGN KEY(apartmentID) REFERENCES apartment(apartmentID),\n" +
                "  PRIMARY KEY(apartmentID, field, suggestion)" +
                ")";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);;
            pstmt.executeUpdate();
        }
        catch(SQLException e){}
        catch (Exception e){
            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
            throw new DataBaseFailedException("create the contacts table",4);}
    }
    public int getUserSuggestionsNum (String id, String field, String suggestion) {
        int value=-1;
        try {
            String sql = "SELECT counter FROM UserSuggestions " +
                    " WHERE apartmentID= " + id+" AND field= "+field+
                    " AND suggestion= " + suggestion;
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            value= rs.getInt(1);
        }
        catch(SQLException e){}
        catch (Exception e){
            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
            return -1;
        }
        return value;
    }
    public void setUserSuggestionsCounter (String id, String field, String suggestion, int count) throws DataBaseFailedException {
        try {
            String sql = "UPDATE UserSuggestions SET counter= ? " +
                    " WHERE apartmentID= ? AND field= ? AND suggestion= ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,count);
            pstmt.setString(2,id);
            pstmt.setString(3,field);
            pstmt.setString(4,suggestion);
            pstmt.executeUpdate();
        }
        catch(SQLException e){}
        catch (Exception e){
            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
            throw new DataBaseFailedException("drop th contacts table",4);
        }
    }
    public void insertUserSuggestionsRecord (String id, String field, String suggestion) throws DataBaseFailedException {
        try {
            String sql = "INSERT INTO UserSuggestions(apartmentID, field, suggestion, counter)" +
                    " VALUES (?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, field);
            pstmt.setString(3, suggestion);
            pstmt.setInt(4, 1);
            pstmt.executeUpdate();
        }
        catch(SQLException e){}
        catch (Exception e){
            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
            throw new DataBaseFailedException("drop th contacts table",4);
        }
    }
    public int insertUserSuggestionsNum (String id, String field, String suggestion) throws DataBaseFailedException {
        int counter=getUserSuggestionsNum(id,field,suggestion);
        if (counter==-1) {
            insertUserSuggestionsRecord(id, field, suggestion);
            return 1;
        }
        else {
            setUserSuggestionsCounter(id,field,suggestion,counter+1);
            return counter+1;
        }
    }

    public void suggestionChangesApartmentReacord (String apartmentID, String suggestion, String field){
        try {
            String sql = "UPDATE Apartment SET Apartment.? = ? WHERE apartmentID= ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, field);
            pstmt.setString(2, suggestion);
            pstmt.setString(3, apartmentID);
            pstmt.executeUpdate();
        }
        catch(SQLException e){}
        catch (Exception e){
            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
        }
    }

    public void suggestionChangesNeighborhood(String apartmentID, String suggestion){
        int addressDetailsNum=-1;
        try {
            String sql = "SELECT Apartment.addressDetailsID "
                    + " FROM Apartment"
                    + " WHERE apartmentID= "+ apartmentID;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                addressDetailsNum= rs.getInt(1);
            }
        }
        catch(SQLException e){System.out.println(e);}
        catch (Exception e){
            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
        }
        if (addressDetailsNum!=-1) {
            try {
                String sql = "UPDATE AddressDetails SET AddressDetails.neighborhood = ? WHERE apartmentID= ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, suggestion);
                pstmt.setInt(2, addressDetailsNum);
                pstmt.executeUpdate();
            } catch (SQLException e) {
            } catch (Exception e) {
                MyLogger.getInstance().log(Level.SEVERE, e.getMessage(), e);
            }
        }
    }

//    public void suggetionChangesAddress (String apartmentID, String street, int num ) throws DataBaseFailedException {
//        GoogleMapsController googleMapsController= new GoogleMapsController();
//        Apartment apartment= getApartmentRecordTBD(apartmentID);
//        int tempForAddressDetaileNum = isAddressDetailsExist(street,num);
//        String tempForApartment;
//        if (tempForAddressDetaileNum == -1) {
//            if (!street.isEmpty() && num > 0) {
//                int timeToUni = googleMapsController.getTimeWalkingFromUniByMin(street, num);
//                double[] locationPoint = googleMapsController.getCoordinates(street, num);
//                tempForAddressDetaileNum = addAddressDetailsRecord(
//                        street, num + "",
//                        timeToUni, "", locationPoint[0], locationPoint[1]);
//            }
//        }
//    }

    public void resetAdminTable() throws DataBaseFailedException {
        String sql= "DROP TABLE Admin ";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);;
            pstmt.executeUpdate();
        }
        catch(SQLException e){}
        catch (Exception e){

            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
            throw new DataBaseFailedException("drop th admin table",4);
        }
        sql= "CREATE TABLE Admin(\n" +
                "  username text PRIMARY KEY,\n" +
                "  password text NOT NULL,\n" +
                "  mailAddress text, \n" +
                "  dateOfLastChange text \n" +
                ")";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);;
            pstmt.executeUpdate();
        }
        catch(SQLException e){}
        catch (Exception e){
            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
            throw new DataBaseFailedException("drop th contacts table",4);
        }
        Date dd = new Date();
        SimpleDateFormat sdf= new SimpleDateFormat();
        sdf.applyPattern(dateFormat);
        String newDateString = sdf.format(dd);
        sql= "INSERT INTO Admin(username, password, mailAddress, dateOfLastChange) VALUES ('admin','123456','admin@gmail.com', '"+
                newDateString+"' ) ";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);;
            pstmt.executeUpdate();
        }
        catch(SQLException e){}
        catch (Exception e){
            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
            throw new DataBaseFailedException("drop th contacts table",4);
        }
    }

    public boolean login(String username, String password){
        String passwordFromDB="";
        try {
            String sql = "SELECT Admin.password "
                    + " FROM Admin"
                    + " WHERE username= '"+ username+"'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                passwordFromDB= rs.getString(1);
            }
            return passwordFromDB.equals(password);
        }
        catch(SQLException e){return false;}
        catch (Exception e){
            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
        }
        return false;
    }

    public boolean changePassword(String username, String password){
        Date dd = new Date();
        SimpleDateFormat sdf= new SimpleDateFormat();
        sdf.applyPattern(dateFormat);
        String newDateString = sdf.format(dd);
        try {
            String sql = "UPDATE Admin "
                    + " SET password=? , dateOfLastChange= ? "
                    + " WHERE username= ? ";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, password);
            pstmt.setString(2, newDateString);
            pstmt.setString(3, username);
            pstmt.executeUpdate();
            return true;
        }
        catch(SQLException e){System.out.println(e);}
        catch (Exception e){
            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
        }
        return false;
    }

//    public boolean changeEmailAddress(String username, String emailAddress){
//        try {
//            String sql = "UPDATE Admin "
//                    + " SET mailAddress=? "
//                    + " WHERE username= "+ username;
//            PreparedStatement pstmt = conn.prepareStatement(sql);
//            pstmt.setString(1, emailAddress);
//            pstmt.setString(2, username);
//            pstmt.executeUpdate();
//            return true;
//        }
//        catch(SQLException e){System.out.println(e);}
//        catch (Exception e){
//            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
//        }
//        return false;
//    }

    public void resetGroupsTable() throws DataBaseFailedException {
        String sql= "DROP TABLE Groups";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);;
            pstmt.executeUpdate();
        }
        catch(SQLException e){}
        catch (Exception e){

            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
            throw new DataBaseFailedException("drop th admin table",4);
        }
        sql= "CREATE TABLE Groups(\n" +
                "  groupID text PRIMARY KEY,\n" +
                "  groupName text \n" +
                ")";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);;
            pstmt.executeUpdate();
        }
        catch(SQLException e){}
        catch (Exception e){
            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
            throw new DataBaseFailedException("drop th contacts table",4);
        }
    }

    public void insertGroup(String groupID, String groupName) throws DataBaseFailedException {
        String sql= "INSERT INTO Groups(groupID, groupName) VALUES (?,?) ";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,groupID);
            pstmt.setString(2,groupName);
            pstmt.executeUpdate();
        }
        catch(SQLException e){}
        catch (Exception e){
            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
            throw new DataBaseFailedException("drop th contacts table",4);
        }
    }

    public void deleteGroup(String groupID) throws DataBaseFailedException {
        String sql = "DELETE FROM Groups WHERE groupID = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,groupID);
            pstmt.executeUpdate();
        }
        catch(SQLException e){}
        catch (Exception e){
            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
            throw new DataBaseFailedException("drop th contacts table",4);
        }
    }

    public List<GroupDTO> GetAllGroups() {
        String sql = "SELECT groupID, groupName FROM Groups ";
        List<GroupDTO> groups= new LinkedList<GroupDTO>();
        try {
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            while (rs.next())
                groups.add(new GroupDTO(rs.getString(1),rs.getString(2)));
            return groups;
        }
        catch(SQLException e){return null;}
        catch (Exception e){
            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
            return null;
        }
    }

    public static void main(String[] args) throws Exception
    {

        DataBaseConnection a=new DataBaseConnection();
        a.connect();
//        a.resetSearchRecordsTable();
        a.resetAdminTable();
        a.resetGroupsTable();
//        a.resetAllTables();
//        a.resetConstValueTable();
//        a.resetUserSuggestionsTable();
        a.disConnect();

/*
        ServerController servercontroller=new ServerController();
        //servercontroller
        servercontroller.newPost(new Post("1231C_2476", new Date(new Date().getTime()-1000), "shlomi arzi","השותפה המקסימה שלנו Yarden Peretz עוזבת את הדירה. אני וGal Ben Maman מחפשים מישהי שתחליף את מקומה בחדר. דירת 4 חדרים חדשה משופצת לחלוטין עם חצר ענקית. הדירה באלעזר בן יאיר 16 כרבע שעה מאוניברסיטה. הדירה מרוהטת ויש בה הכל, רק להביא בגדים ולהכנס. עלות 1150 ש\\\"ח. כניסה מיידית :)\\nלפרטים:\\n0526516656\\n", ""));
        servercontroller.newPost(new Post("1231C_2123", new Date(new Date().getTime()-10000), "yoni bloch","להשכרה ללא עמלת תיווך! בשכונה ה', מגדלי עופר, רחוב ש\\\"י עגנון. דירת 5 חדרים יפה, גדולה ומרווחת. מטבח גדול, פינת אוכל מוגדרת, סלון מרווח חדרי שינה גדולים 3 שירותים 2 מקלחות יחידת הורים. בניין שמור נקי ומטופח עם 2מעליות ו 2 חניות פרטיות. גישה לנכים, מעולה למשפחה גדולה, שכ\\\"ד 4000 ש\\\"ח, כניסה מיידית! לפרטים נוספים נא ליצור קשר בנייד: 054-5533351 יריב. כניסה מיידית!\\", ""));
        servercontroller.newPost(new Post("1231C_2234", new Date(new Date().getTime()-50000), "omer adam","למכירה בשכונה ד ברחוב דוד המלך חדר וסלון 50 מ\\\"ר קומה 3/4\\nבמחיר חסר תקדים רק 480.000 ש\\\"ח הדירה נמצאת בקרבת האוניברסיטה מרחק הליכה ברגל 8 דקות\\nלפרטים נוספים וסיור בנכס\\nתיווך אדיר - אדיר 0506796969\\n", ""));
        servercontroller.newPost(new Post("1231C_2345", new Date(new Date().getTime()-70000), "dani shovevani"," \"לסטודנטים שנזכרו מאוחר!\\nרגר 163\\n4 חדרים\\n100 מ\\\"ר (לפי טופס ארנונה)\\n3300 ש\\\"ח\\nדירה מרווחת מאוד ומפנקת,מול גן סיאטל,קומה  4(לא על עמודים),הדירה מאובזרת במכונת כביסה חדשה, תנור משולב עם כריים,מקרר ומרוהטת מלא למעט 2 מזרונים.\\n*ללא עמלת תיווך לסטודנטים\\nאיציק 0547334682 *עדיפות לוואטסאפ!\\nאיציק ויהב- מתווכים\\n\",\n", ""));
        servercontroller.newPost(new Post("1231C_2336", new Date(new Date().getTime()-2000), "ben arzi","להשכרה 3 חד' בשכונת יא' ברחוב שיטרית קומה 3 מ4\nללא ריהוט כניסה 1/12\nרק 1900 שח\n0503403041 עופר\n", ""));
        servercontroller.newPost(new Post("1231C_2337", new Date(new Date().getTime()-1000), "avichai yadin","יחידת דיור 2 חדרים להשכרה בשכונה ב' רחוב בצלאל 20 חמש דקות הליכה למכללת סמי שמעון וקרובה מאוד למרכז חן. היחידה מרוהטת. 2150 כולל מים וארנונה וחשמל עד 150 שח לחודש. לכניסה מיידית .\n", ""));
        servercontroller.newPost(new Post("1231C_2338", new Date(new Date().getTime()-86400000), "faisel molich","דירה לאחר שיפוץ מלאאא!!!\nדירת 4 חדרים מקסימה ברחוב אברהם אבינו 26, שכונה ד' במרחק של 7 דקות הליכה משער רגר. מתאימה ל-2 שותפים או לזוג שרוצה להתפנק ?? הדירה מרוהטת קומפלט (מקרר,מיקרוגל,תנור,כיריים,מטבח חדש,מזגנים חדשים, סלון,שולחנות לימוד,שולחן פינת אוכל וכיסאות, מיטות וארונות).\nמיקום מרכזי, מול מרכז קניות, תחנת אוטובוס מתחת לבית וקרוב לאזורי הבילויים.\nשכ\"ד-2400 ש\"ח.\nזמין בפרטי\n", ""));
        servercontroller.newPost(new Post("1231C_454", new Date(new Date().getTime()-8640000), "ronen harazi","דירת 3 חדרים ברגר\n₪2,500 - ‎אוניברסיטת בן-גוריון בנגב‎\n\nלהשכרה דירת 60 מטר ברגר 163\nשמורה\nחדרים מרווחים\nסלון מרווח\n-חצר\n-ריהוט לפי הדרישה\n-כניסה מיידיצ\n-2500\n-לתיאום-0525447662\n", ""));
        servercontroller.newPost(new Post("1231C_2474", new Date(new Date().getTime()-126400000), "ben aflalo","דירה יפייפיה להשכרה\nברחוב גוש עציון 25\n8 דקות הליכה מהאוניברסיטה\n2 חדרים 35 מ\"ר\nחצר פרטית עם חניה 60 מ\"ר\n2000 ש\"ח כולל מים וארנונה\n050-8693633\n", ""));
        servercontroller.newPost(new Post("1231C_2595", new Date(new Date().getTime()-216400000), "dor malol","?????? הקשיבו הקשיבו ??????\nבית החלומות נמצא ממש פה מהרו לפני שלא ישאר מקום !!!\nסמטת יהונתן מיקום פצצה !!!\nדירת   4 חדרים יפייפיה משופצת מהייסוד! מוארת ונעימה !\nמרוהטת קומפקלט!\n מה יש בה ?\n? חדרים ענקיים בכל חדר יש מיטה ארון ושולחן\n ? מטבח יפייפה וגדול\n ? טלוויזיה\n ? פינת אוכל בנפרד\n ? שירותים + מקלחת\n ? שירותים בנפרד נוספים\n ? מכונת כביסה\n ? תנור\n1100 לשותף\n# נמצאת בשכונה ד' רחוב סמטת יהונתן על גבול ב'\nדירה ברמה גבוהה רק נשאר להביא בגדים ולהכנס ?\nלתיאום ושאלות נוספות מוזמנים ליצור קשר במספר הבא\n 054 - 2818529 רננה\n", ""));
        servercontroller.newPost(new Post("1231C_2606", new Date(new Date().getTime()-1386400000), "dikla green","דירת 4 חדרים משופצת ומרוהטת במשעול יודפת כניסה מיידית לפרטים נוספים רועי 0544218258\n", ""));
        servercontroller.newPost(new Post("1231C_24919", new Date(new Date().getTime()-896400000), "shalom asyag","יחידת דיור להשכרה בשכונה א\n1850 ש\"ח כולל מים וארנונה\nרחוב אוסישקין 2\nקומה 4 מתוך 4\n30 מ\"ר\nמשופצת\nכניסה ב1/10\n2 חדרים\nמרוהטת: מיטב זוגית, שידה, שידת טואלט ומראה, סלון, שולחן וכיסא,\nמקרר, כיריים, מכונת כביסה, שני מזגנים.\n4 דקות הליכה מסמי שמעון\n050-8693633\n", ""));
        servercontroller.newPost(new Post("1231C_2411", new Date(new Date().getTime()-106400000), "nimrod shoval","דירת 2.5 חד' מרווחת 50 מטר פלוס גינה במכתשים 22\nמרוהטת קומפלט מיטה זוגית\nכל מכשירי החשמל (תנור משולב עם כיריים גז, מקרר, מכונת כביסה)\nארון קיר בעל בית דואג להכול היחידה עברה שיפוץ מרחק הליכה לאוניברסיטה.\n\nמתאימה לזוג סטודנטים או זוג עם ילד מחיר לדירה 2500 כולל כול החשבונות למעט חשמל טלפון ליצירת קשר איוון :0542033228\n", ""));
        servercontroller.newPost(new Post("1231C_2422", new Date(new Date().getTime()-116400000), "ron doron","מפרסמת בשביל בעל הבית:\nלהשכרה דירה בווילות מצדה :4 חדרים+ מרפסת ענקית, משופצת ומרוהטת קומפלט. מחיר 3,000 ש\"ח לא כולל חשבונות.\nכניסה ב20.11.2017.ללא בעלי חיים! לפרטים מבקשת ליצור קשר עם בעלי בית: 052-3783637\n", ""));
        servercontroller.newPost(new Post("1231C_2433", new Date(new Date().getTime()-216400000), "nofar naftchi","**אמ:לק; נשאר חדר יחיד ב-1050 ש\"ח בשכונה ב, ביאליק 24**\nבשכונה ב' השקטה והחביבה מתפנה חדר בדירת 5 חדרים גדולה ומרווחת, רח' ביאליק 24 בבניין יפהפה וצעיר (יחסית :) ) עם 2 מעליות, מקלט בקומה וחניה פרטית. כרגע בדירה 3 שותפות שמחפשות את הרביעית המשלימה!\nבדירה - סלון ומטבח ענקיים, חדר שירות, ומרפסת קטנה וחמודה!\nקיים מזגן בכל חדר שינה וכן מזגן גדול בסלון. ריהוט חלקי (מיטה וארון) בחדרים, סלון מלא כולל פלזמה, ומטבח מרווח ומפנק לחובבי הבישול - כולל תנור, כיריים ומקרר חדש.\n*הדירה ממוקמת מעל מרכז חן/ביאליק, הכולל מכולת, ירקן, מאפיה, פיצריה ויש אוטובוסים לכל מקום לעצלנים *\nמבחינת מרחק מהאוניברסיטה - בהליכה מדובר בכרבע שעה עד 20 דקות. באופניים 5-7 דקות, וכאמור יש תחנת אוטובוס מתחת לדירה שמגיעה לאוניברסיטה.\nלסטודנטים בסמי והמכללה הטכנולוגית - אפשר לקום 5 דקות לפני תחילת השיעור ועוד להספיק לקפה.\nבדירה שומרים כשרות.\nכניסה מיידית.\nלתיאום מראש להגעה - יש ליצור קשר בפרטי.\nאו בניידים:\nשיר-0545446792\nכרמל-0543055096\n", ""));
        servercontroller.newPost(new Post("1231C_2444", new Date(new Date().getTime()-326400000), "tamir shmilovich","דירה להשכרה!\nויינגייט 12...\nדירה גדולה ומרווחת... 3 חדרי שינה שירוקלחת ושירותים נוספים\nמתאימה מאד ל3 שותפים.\nקרובה מאד לרכבת צפון ולכניסה הצפונית לאוניברסיטה.\nכולל ריהוט מלא בכל החדרים - שולחן, מיטה וארון בכל חדר!\nשכ\"ד 2700 ש\"ח\n054-9435353\nמראים היום ב21:30\n", ""));
        servercontroller.newPost(new Post("1231C_2455", new Date(new Date().getTime()-436400000), "shavit chernihov","להשכרה דירה חדשה מהניילונים!!!\nפרויקט חדש של ארזי הנגב ברמות בן 5 חודשים רח' גדעון האוזנר ברמות דירת 4 חדרים + מרפסת 14.5 מטר\nקומה 3 מתוך 5\nתקרה גבוהה 3.20, מטבח משודרג של PANEL\nדלתות פנדור לבנות, רשתות + תריסים חשמליים בכל הבית, ממוזגת, חניה, מעלית ופינת אוכל מוגדרת.\nרק 2 דיירים בקומה, ללא שטחי מסחר מתחת!\nקרובה לבתי הספר ובקרבת הגנים, יציאה מהירה לכביש תל אביב ולכביש 6\nכל הקודם זוכה, כניסה מיידית!\nשכירות 3800₪\nוועד בית 240₪\nארנונה 940₪\n\nלפרטים נוספים אך ורק בנייד:\n054-320-6958\n", ""));
        servercontroller.newPost(new Post("1231C_2466", new Date(new Date().getTime()-44400000), "omer shoshan","להשכרה בשאול המלך 141\nדירת 2 חדרים יפהפייה, משופצת ומרוהטת קומפלט (ספות, שולחנות, מיטה זוגית, ארונות קיר, תנור, מקרר, מכונת כביסה, מזגן, וכל מה שצריך).\nהמחיר - 1900 לחודש כולל מים, ארנונה וועד בית\nלפרטים - 0522353174 זיו\n", ""));
        servercontroller.newPost(new Post("1231C_2477", new Date(new Date().getTime()-145400000), "naor ashkenazi","להשכרה בשכונה ה בני אור 40 קומה 3 מתוך 5 ללא מעלית, דירה גדולה ומרווחת.\nדירת 4 חדרים שלושה חדרי שינה, וסלון + חדר ארונות בנוסף ישנה מרפסת שירות.\nדירה מרווחת, מתאימה לשותפים או למשפחה, ריהוט חלקי\nמחיר: 2900 ש\"ח\nלפרטים:\nאביבית - 0502225392\nאבי - 0507289757\n", ""));
        servercontroller.newPost(new Post("1231C_2488", new Date(new Date().getTime()-146400000), "liron feliss","*** דירה ליחיד/ זוג ב1700 ***\n\nדירה מקסימה כ-35 מ\"ר בארלוזורוב 14- 10 דקות מהאוניברסיטה ומסורוקה.\nבדירה חדר שינה גדול הכולל מיטה זוגית, ארון ושידה.\nבסלון- ספה, מכונת כביסה, כיריים חשמליות, מקרר, שולחן אוכל.\nבנוסף יש מרפסת קטנה מיוחדת עם מתלה כביסה ושימושים אחרים ??\nכניסה מידית.\nלפרטים נוספים ותיאום מראש:\nספיר- 0505359900 (זמינה בוואטאפ)\n", ""));
        servercontroller.newPost(new Post("1231C_1419", new Date(new Date().getTime()-147400000), "dafna armoni","מראים את החדר היום בערב !\nמתפנה חדר באלעזר בן יאיר במחיר 1150 ש\"ח הדירה חדשה, חצר מדהימה ו2 שותפים לא פחות ממושלמים לפרטים נוספים בפרטי??\n", ""));
        servercontroller.newPost(new Post("1231C_2766", new Date(new Date().getTime()-86400000), "shlomi shabat","השותפה שלי החליטה לפרוש מהלימודים בבש ברגע האחרון, אז אנחנו מחפשות שותפה חדשה..\nמדובר על דירת בנות מקסימה בביאליק 24, דירה ענקית 160 מר 5 חדרים, מתאים ל4 שותפות...\nאנחנו מחפשות משהי שומרת כשרות עם ראשון טוב ??\nאם אתן מכירות משהי מתאימה תתיגו:))\n**תמונות ופרטים נוספים בפרטי**\nכרמל 0543055096\n", ""));
        servercontroller.newPost(new Post("1231C_8776", new Date(new Date().getTime()-226400000), "shlomi shaban","להשכרה יחידת דיור 3 חד' קרקע עם גינה וכניסה פרטית\nברח' הכותל המערבי בשכונה א'\nריהוט מלא כולל הכל כניסה מידית\nמתאים ליחיד או זוג ללא בעלי חיים\nהמחיר 3000 כולל ארנונה ומים\nלפרטים רונן 050-5300038\n", ""));
        servercontroller.newPost(new Post("1231C_2687", new Date(new Date().getTime()-193400000), "nichal yanai","#חדשה_להשכרה! ??\nיחידת דיור מדהימה בשכונת נאות לון!\nכ-80מ\"ר בנוי, 2חדרים (חדר שינה גדול עם חדר ארונות + סלון מרווח מאוד), גינה של כ-40מ\"ר. #ריהוט_מלא!\n*ללא חיות מחמד\n3,500ש\"ח כולל הכל! חשמל, מים, גז, ארנונה, אינטרנט ו-yes.\nלפרטים נוספים : 054-3037805 נתי - ארץ עיר\nארץ עיר - נדל\"ן ויזמות\n", ""));
        servercontroller.newPost(new Post("1231C_2232", new Date(new Date().getTime()-72400000), "oded menashe","להשכרה דירת 2 חד'\n\nשכונה ד', רח' בר גיורא\n\n*ממוזגת\n*קומת קרקע\n*50 מ\"ר\n\nריהוט: בסיס מיטה + מזרן, מקרר, ארון 3 דלתות.\n\nכניסה מיידית.\n\nמחיר: 1,700 ש\"ח.\n\nלפרטים: 054-449-0025\n", ""));
        servercontroller.newPost(new Post("1231C_2222", new Date(new Date().getTime()-91400000), "roth sirkis","מפנה חדר אלעזר בן יאיר במחיר 1150 , משאירה אחריי שתי שותפים מדהימים הדירה חדשה ואחרי צבע לפרטים נוספים בפרטי??\n", ""));
        servercontroller.newPost(new Post("1231C_1111", new Date(new Date().getTime()-11100000), "yosef ben david","עקב קבלה ללימודים במוסד מרוחק אני נאלצת לעזוב את הדירה ומחפשת שותפה שתחליף אותי בחדר, המחיר הוא 1150 לחודש וכמובן 2 שותפים מדהימים!!! לעוד פרטים בפרטי??\n", ""));
        servercontroller.newPost(new Post("1231C_5555", new Date(new Date().getTime()-1186400000), "dan doron","מחפשת בשביל חברה שותף/ה בראש טוב! לדירה במצדה 43, קומה ראשונה (מעל הפסטינה) לשני שותפים, דירה גדולה ומשופצת, מרווחת,  במצב טוב, מתפנה חדר גדול ונחמד. בחדר נשארים שני ארונות ומדפים אבל חסרה מיטה. שכ'\"ד 2,400.\nמחפשת שותף/ה שרוצים לגור באווירה טובה, קלילה וחברית..\nלפרטים 0525834867 נוי\n", ""));
        servercontroller.newPost(new Post("1231C_8888", new Date(new Date().getTime()-286400000), "bibi netanyhau","להשכרה : דירת 2.5 חד' קומה 4/4,משופצת מרוהטת ממוזגת.\n60 מטר ברחוב מבצע עובדה\n2000 ש\"ח.\nכניסה 15.10\n\"פרי נכסים\"\n0508812541\n", ""));
        servercontroller.newPost(new Post("1231C_9999", new Date(new Date().getTime()-864222000), "achmed tibi","***רינגלבלום 7***\nדירת 2 חד' 50 מ\"ר משופצת ומרוהטת עם מרפסת פנימית\nמתאימה לזוגות או ליחידים\nממש על הכניסה לרחוב בן מתתיהו ומטר מרגר\nרק 2000 ש\"ח!!\nאיתי 050-2516898\n", ""));
        servercontroller.newPost(new Post("1231C_1111", new Date(new Date().getTime()-999999999), "dafna briliant","דירה להשכרה ברחוב רגר 126 (צמוד לרח׳ רינגנבלום) 3 חדרים ,משופצת כחדשה, קומת קרקע, כולל מרפסת (גינה -אפשר בעלי חיים) +מחסן. מאובזרת במוצרי חשמל רובם חדשים: מכונת כביסה, מקרר, כריים, תנור ,2 מזגנים וטוסטר אובן. מקלחת ושירותים חדשים, מטבח חדש .ארונות ומיטות זוגיות בחדרי השינה. לבית 2 כניסות אחת מהמרפסת והשניה מחדר המדרגות. 2 דקות הליכה מהאוניברסיטה (בקרבת שער 90 )\n2600 שח.\nלפרטים: 0525411144\n", ""));
        servercontroller.newPost(new Post("1231C_9275", new Date(new Date().getTime()-888888888), "yoel solomon","?דירה להשכרה הכי יפה בשכונה ג?\n₪ 3,300\nבאר שבע\nשכונה ג- דירת 4 חדרים- 1100 ש\"ח\nהדירה הכי יפה בשכונה ג'!\nדירת 4 חדרים, עברה שיפוץ קומלפט- הכל חדש!\nבנוסך, הדירה מרוהטת קומפלט! (בכל חדר: מיטה+ שולחן + כסא+ ארון+ מזגן- הכל חדש)\nבחלל המשותף: ספה, שולחן, שידה, LCD, מזגן, פינת אוכל, מכונת כביסה, מקרר, מיקרו, תנור, כיריים- הכל חדש מהניילונים!\nאמיר מנצור - יועץ נדל\"ן באר-שבע 360\nלתיאום - 054-5514300\n", ""));
        servercontroller.newPost(new Post("1231C_1231", new Date(new Date().getTime()-777777777), "ronit pik","*********ללא תיווך!!************\nדירה להשכרה בשכונה ה' המתחדשת!!\nמילוס 12, קומה 2, 3 חדרים, ממוזגת, מטבח,מקלחת + שירותים משופצים. מתאימה למשפחות ושותפים.\nהדירה לא מרוהטת!\nישנה דרישה לצ'קים + ערב!\nשכ\"ד 2300 ש\"ח.\nלתאום נא ליצור קשר\nמקסים 052-345-7591\nאלי: 052-4848-414 { עדיפות לווטאסאפ }\n", ""));
        servercontroller.newPost(new Post("1231C_1231", new Date(new Date().getTime()-666666666), "zvika pik","דירה ענקית מוארת ומרווחת (96 מ\"ר) בת 3 חדרים (מרוהטת) מיועדת  לזוג סטודנטים, יחיד או משפחה. 2 חדרי שינה, סלון, חדר ארונות, מקלחת, שירותים, מרפסת שירות ומרפסת גדולה שמתחברת לסלון ולחדר.\nחניה פרטית לתושבי הבנין .\nשדרות שזר 19 שכונה ג' ,\n2 דק הליכה לבית הספר למשחק ,\nקרובה לאוניברסיטה למרכזי קניות ותחנת רכבת.\nמרוהטת . מטבח ומקלחת אחרי שיפוץ !\n2200 שקלים סה\"כ\nלשאלות ותיאומים - יובל - 052-470-8798\nמנחם- 050-6233-437\nהמשך יום טוב :)\n", ""));
        servercontroller.newPost(new Post("1231C_7372", new Date(new Date().getTime()-555555555), "boris klaiman","***מחפסת שותפים!!***\nסטודנטית מעוניינת בשני שותפים/שותפות נוספים,לדירת 5 חדרים מקסימה במבצע נחשון 76, צמוד למרכז גילת. (סהכ 3 שותפים)\n\n2 חדרים פנויים :\nחדר אחד עם שירותים ומקלחת פרטיים ( לחדר 1000₪)\nחדר נוסף עם מרפסת פרטית ונוף יפה. (לחדר 1100₪)\n\nדירה גדולה ומרווחת עם מטבח\nמאובזר (הרבה ארוחות שישי שוות) סלון מפנק וגדול עם מרפסת צמודה, חנייה פרטית ובריכה מקורה(כרגע לא בשימוש)\n\nמחפשת שותפים רציניים עם אנרגיות חיובית??\n\nאם מעניין אותכם שלחו לי בפרטי:\n0586823161\n", ""));
        servercontroller.newPost(new Post("1231C_7914", new Date(new Date().getTime()-444444444), "shavit elimelech","***כניסה במיידי***\n$$$גמישות במחיר$$$\nשתי שותפות מחפשות שותף/ה בבית קרקע מדהים??\n*בית קרקע שממוקם ברחוב האיסיים 16 בשכונה ד'.\n*3 חדרי שינה, סלון מאובזר ושירוקלחת ??\n*מרוהטת באופן מלא! שופצה בקיץ האחרון :)\n*שתי חצרות מקורות! (לישיבות בחורף בלי גשם) ?????\n*3150₪ לכל הבית! ??\n*בעל דירה אכפתי\n*כניסה מיידית!\nלפרטים: יער 0549747552\nVeronika Davidov 0543112881\n", ""));
        servercontroller.newPost(new Post("1231C_11232", new Date(new Date().getTime()-333333333), "shlomit soka","לרציניים בלבד, אני מפרסם את הדירה בשם בעל הבית,\nהדירה מתאימה לזוג או 2 שותפים,\n2500 ש\"ח כולל ארנונה\nעם 3 מזגנים מעולים וחסכוניים של אלקטרה אחד בכל חדר,\nארונות מטבח חדשים,\nדוד שמש סולארי,\nחלונות עם סורגים,\nחנייה בשפע,\nיש מרכול שכונתי קרוב לבית,\nהדירה קרובה למעון ילדים,\nהמחיר חודשי לא כולל מים וחשמל,\nתשלום לפי צריכה של מונה חשמל ומים פעם בחודשיים.\nגודל הדירה כ62 מ\"ר, החצר סגורה עם גג בגודל כ15 מ\"ר.\nליצירת קשר לשלוח לי הודעה בפרטי.\nהדירה נמצאת ברחוב אסירי ציון, שכונה ג' בבאר שבע\n", ""));
        servercontroller.newPost(new Post("1231C_11232", new Date(new Date().getTime()-222222222), "zevik pevik","בבאר שבע להשכרה במגדל הספורט החדש מול ביה\"ח סורוקה ובקרבת האוניברסיטה דירת 2 חדרים מרוהטת בקומה 7 כניסה 15.11.17 שכירות 2700 ש\"ח לתיאום יאיר 0528683499\n", ""));
        servercontroller.newPost(new Post("1231C_22341", new Date(new Date().getTime()-1111111111), "john ogo","ללא עמלת תיווך! להשכרה ברחוב דויד המלך 31 דירת 3 חדרים יפייפיה גדולה ממוזגת,מרווחת ומרוהטת, התקנה של מטבח חדש בשלבי סיום, סלון גדול חדרי שינה גדולים מטבח פונקציונלי במרחק 10 דקות הליכה לאוניברסיטת בן גוריון מעולה לשותפים סטודנטים. כניסה מיידית!\nשכ\"ד 2100 ש\"ח!\nלפרטים / תאום: 054-5533351 יריב.\n", ""));

        servercontroller.newPost(new Post("1231C_223411", new Date(new Date().getTime()-1111111111), "srolik prolik","\"דירה מרוהטת ?? משופצת ?? ליד האוניברסיטה??\\n??מראה את הדירה היום בתיאום מראש??\\n\uD83D\uDCF1לפרטים: תומר 054-5918161\\nמתאימה ל-3 שותפים. \uD83D\uDCA5החל מ-850 ש\\\"ח לחדר!\uD83D\uDCA5\\n??דירה משופצת מן היסוד בקומה שנייה, מאווררת ומרווחת (70 מ\\\"ר) :\\n??מרוהטת: בכל חדר מיטה, מזגן, ארון, שולחן כתיבה וכיסא\\n??מקרר, כיריים, מכונת כביסה ועוד\\n??11 דק' הליכה מהאוניברסיטה\\n??1 דקה הליכה מ\\\"בר גיורא\\\" ו5 דק מהפאבים/מסעדות במצדה.\\n?? רק 850-950 ש\\\"ח לחדר\\nבבר גיורא פינת רבי-עקיבא\\n\"", ""));
        servercontroller.newPost(new Post("1231C_223412", new Date(new Date().getTime()-222222333), "arnon sturm","דירה מתאימה לזוגות/יחיד- זיסו 7!!!\n\nזיסו 7 (בקרבה לאוניברסיטה) קומה 1 בבית פרטי (ללא גינה) בשכונה ג'. 42 מ\"ר.\nדירת 2 חדרים משופצת ומרוהטת, כולל: מקרר, כיריים גז, סלון, ארון, מזגנים, מיטה, שולחן למידה.\n\nכניסה מיידית!\nשכ\"ד: 1700 כולל ארנונה.\nמותר בעלי חיים!\n\nלפרטים ותיאום הגעה: בן - 050-5470703\n", ""));
        servercontroller.newPost(new Post("1231C_223413", new Date(new Date().getTime()-333332222), "yosi yosi","להשכרה!!\nמתפנה, דירה משופצת ברמה גבוהה, מוארת מאוד (כולל חלונות, ריצוף, מטבח, מקלחת, שירותים, צנרת, מזגנים) מתאימה ל 3 שותפים ביצחק שיפר 4 קומה רביעית. במיקום מעולה, 9 דקות הליכה מהאוניברסיטה משער 90, קרובה לתחבורה ציבורית למרכז מסחרי. הדירה נעימה ומרוהטת. פירוט הריהוט - מקרר חדש , מכונת כביסה חדשה , מזגן חדש, תנור וכיריים, מיקרוגל, שולחן פינת אוכל+ כיסאות, ספות + שולחן בסלון. בכל חדר מיטה מזרן, ארון שולחן. מזגן בכל אחד משלושת החדרים + סלון.\nהמחיר 1,100 ש\"ח לשותף. כניסה מיידי.\nלפרטים: דורון 054-7541707\n", ""));
        servercontroller.newPost(new Post("1231C_223414", new Date(new Date().getTime()-333334444), "avi hayun","** דירת 3 חדרים מרווחת **\nדירה גדולה עם סלון גדול, ושני חדרי שינה שווים בגודלם\nמתאימה לשותפים, לזוג ולמשפחה\nקרובה מאוד למכללת קיי, ומרחב נסיעה של כ- 10 דק' מהאוניברסיטה\nריהוט כמעט מלא\nכתובת: בעלי התוספות 22, שכונה ד'\nמחיר: 2000 ש\"ח\nפרטים נוספים בפרטי\n", ""));
        servercontroller.newPost(new Post("1231C_223415", new Date(new Date().getTime()-444444555), "dudu namer","מראה היום ב17:00 להשכרה דירת 3 חד'\nבשכונה ט' רחבת צפת קומה 2\nבבניין מסודר עם מעלית לכניסה מיידית\n2700 שח 0503403041 עופר\n", ""));
        servercontroller.newPost(new Post("1231C_223416", new Date(new Date().getTime()-555566656), "rami gershon","דירת 3 חדרים לכניסה ביום שלישי\n₪2,500 - ‎אוניברסיטת בן-גוריון בנגב‎\n\nלהשכרה כניסה מיידית!\n-דירת קרקע בשכונה ד' על רחוב אברהם אבינו-כיכר האבות\n-חצר פרטית\nשירותים ומקלחת בחדר\n-ריהוט מלא וחדש\n-0525447662\n-2500 לכל הדירה (2 שותפים)\n", ""));
        servercontroller.newPost(new Post("1231C_223417", new Date(new Date().getTime()-676767676), "shimon gershon","*** דירה משופצת ליחיד/לזוג במקום מרכזי רק ב1700**\n\nדירה מקסימה כ-35 מ\"ר בארלוזורוב 4/4 קומה שנייה.\nמתחת לדירה- מאפיה, מכולת, תחנת אוטובוס, 7 דקות מסורוקה ו10 דקות מהאוניברסיטה.\nבנוסף יש גם מרפסת מגניבה ??\nהדירה מרוהטת חלקית אך בעל הדירה גמיש לעזרה.\nכניסה- ב1.11.\nלפרטים נוספים ותיאום מראש- זמינים בוואטאפ:\nספיר- 0505359900\nשירן- 0507914455\n", ""));
        servercontroller.newPost(new Post("1231C_223418", new Date(new Date().getTime()-252525252), "ben caspit","יחידת דיור יפהפיה,\nמשופצת מהיסוד,\nברחוב שאול המלך 93,\nשכונה ו' החדשה,\nמרוהטת קומפלט ומאובזרת,\nקומה 4.\nכניסה מיידית.\nשכ\"ד 2300 כולל ארנונה ומים.\nלפרטים- אדוארד 0503133399\n", ""));
        servercontroller.newPost(new Post("1231C_223419", new Date(new Date().getTime()-1414141411), "dalia rabinovich","רק 800 ש\"ח לשותף בדירת 3 שותפים!\nכל הדירה פנויה.\n4 חדרים ברחוב משעול ביתר- מרחק 12 דקות הליכה מהאוניברסיטה.\nכל החדרים מרוהטים - מיטה, ארון ושולחן כתיבה. באחד החדרים יש מרפסת.\n85 מ\"ר.\nשכ\"ד לכל הדירה- 2400 ש\"ח.\nכניסה מיידית.\n0503133399\n", ""));
        servercontroller.newPost(new Post("1231C_2234111", new Date(new Date().getTime()-477477474), "yosi aboksis","לטודנטים ,קרוב לוינגייט,דירת קרקע,+ מרפסת עצומה וחצר בדו קומתי, קלישר דירת 4 חדרים +שני חדרי שירותים ,הדירה מרווחת ומוארת אחרי שיפוץ ,מרוהטת מלא, במרחק הליכה של כ- 7 דקות להאוניברסיטה, למרכז הספורט של האוניברטיטה ולתחנת הרכבת .ולסורוקה.מתאימה ל-3 שותפים או לזוג\n.דמי השכירות : 2950 ש\"ח לפרטים: לנייד 0545634377\n", ""));
        servercontroller.newPost(new Post("1231C_2234112", new Date(new Date().getTime()-979797979), "shachar hirsh","****מראים היום, תאום בפרטי *********\nהשכרה ,ללא תיווך ,בבר גיורא 9 ,שכונה(ד'), דירת גן,אפשר בע\"ח.\nדירת גן עם חצר לזוג/שותפים, 3 חדרים + שרותים ומקלחת בכל חדר.\nהדירה לאחר שיפוץ מלא כולל אינסטלציה , חשמל, ריצוף(גרניט פורצלן) , מזגנים בכל החדרים , ריהוט , מקלחונים , סורגים , פלדלת, דוד שמש וחשמל.\nבסלון- פינת אוכל וכסאות, מקרר, כיריים גז,\nבחדרים- בסיס מיטה זוגית וארון בכל חדר .\nשכד 2200, כולל חצי מחשבון חשמל !. פרטים נוספים בפרטי.\nהגינה פרטית ומגודרת .\nכניסה 1/10\nנא לתאם בפרטי\n", ""));
        servercontroller.newPost(new Post("1231C_2234113", new Date(new Date().getTime()-1919191919), "niv sela","לכניסה מיידית ל3 שותפים\n₪1,100 - ‎אוניברסיטת בן-גוריון בנגב‎\n\nלכניסה מיידית דירת 4 חדרים בשכונה ד' על גבול ב'\nרחוב סמטת יהונתן ל3 שותפים\n1100 לשותף\nמרוהטת קומפלט\nכמו בתמונות\n-לתיאום 0525447662\n", ""));
        servercontroller.newPost(new Post("1231C_2234114", new Date(new Date().getTime()-2020202020), "omer sela","להשכרה דירת 4 חדרים משופצת ביעקב דורי 13 ב״ש. ללא תיווך!\n***3000 לכל הדירה -מתאימה ל3 שותפים***\n קומה 3/4 בלי מעלית כולל מחסן פרטי בקומת קרקע.\nהדירה מרווחת ומרוהטת לגמרי, הכל חדש! מכונת כביסה, גז, מקרר, ספה, ארונות, מזגן בכל חדר, טלוויזיה ״43 , מיטות ומזרנים.\n לכניסה מיידית\nלפרטים נוספים אייל 0506533911\n", ""));
        servercontroller.newPost(new Post("1231C_2234115", new Date(new Date().getTime()-373738393), "dani danon","להשכרה בשכונת ו' החדשה!\n??ללא עמלת תיווך!!!??\n\nברחוב מבצע נחשון (בפינת הרחובות מצדה - יהודה הלוי)\nדירת 4 חד'\n90 מ\"ר\nקומה 4\n???משופצת ומרוהטת\n???ממוזגת (מזגן בכל חדר ובסלון)\n???בניין מסודר ונקי עם ועד בית\nהדירה קרובה להכל (ממש במרכז העיר!!)\n\nמחיר ?? 2700 ש\"ח\nלפרטים נוספים ולתאום\n????????????????\n053-2787802\n", ""));
        servercontroller.newPost(new Post("1231C_2234116", new Date(new Date().getTime()-494749474), "roni rahav","חדשה להשכרה בבלעדיות\n\n??במגד העיר\n??דירת 5 חד'\n??חדשה מקבלן\n??2סוויטות הורים\n??מרפסת שמש\n??מחסן\n??מגדל יוקרה.לובי ברמה גבוהה\n??חדר כשר בבנין\n??ממול בית חולים סורוקה\n??כמה צעדים ממרכז הנגב\nחובה לראות\nלפרטים וסיור בנכס: מאגר נכסים\nגולי אזולאי נציגת שרות  0503336803??\n", ""));
        servercontroller.newPost(new Post("1231C_2234117", new Date(new Date().getTime()-989876523), "yair role","*** להשכרה***\nדירהת 2 חדרים משופצת ברחוב גיורא יוספטל 26 בכניסה של באר שבע מצפון בסמיכות למרכז אורן 7 דקות הליכה מהאוניברסיטה סביבה שקטה מתאימה לזוג ו\\או לשני שותפים ,משופצת במלואה, מחיר 1700 ש\"ח.\nבדירה יש מקרר,שולחן מתקפל פינת אוכל,כיריים חשמליות וגז ,ארון קיר דלתות כפולות,מיטה זוגית,שידת טלויזיה +כונניות,מזגן, 2 ספות ושולחן\nאמנון -054-3406666\n", ""));
        servercontroller.newPost(new Post("1231C_22341171", new Date(new Date().getTime()-200872821), "shavit tamir", "להשכרה ללא עמלת תיווך! שכונה ה' רחוב טבנקין דירת 3 חדרים 84מ\"ר ק.1. סלון גדול, מטבח גדול וחדש, חדר רחצה חדש, חדרי שינה מרווחים בדירה יש שני ארונות בגדים כל השאר ללא ריהוט! דירה מדהימה, יש חניה עם גישה נוחה, שכ\"ד 2500 ש\"ח כניסה בינואר 2018 פרטים נוספים בנייד:\n054-5533351 יריב.\n",""));
        servercontroller.newPost(new Post("1231C_22341172", new Date(new Date().getTime()-300492921), "tamir nofar", "שלום לכולם,\nמחפש שותף לדירה ליד שער 90 חצי דקה מהאוניברסיטה\nממש ליד השניצלה\nהדירה במצב מעולה, נקייה, גז תנור מקרוגל מחסן טלוויזיה וממיר וכמובן מכונת כביסה. הכל מוכן.\nבעלת דירה נחמדה, כניסה מיידית עד אוקטובר\nלפרטים 0546310727\n",""));
        servercontroller.newPost(new Post("1231C_22341173", new Date(new Date().getTime()-400292819), "user one", "להשכרה (ללא תיווך) דירת 5 חדרים חדשה ומושקעת מאביסרור ברמות החדשה, קרוב ליציאה מהעיר, קומה 5 עם מעלית .\nהחדרים כוללים יחידת הורים וחדר מתבגר/אורחים עם מקלחת ושירותים צמודים. מטבח משודרג, דלתות פנדור מעוצבות בכל הדירה, מזגנים בכל החדרים, מרפסת שמש עם נוף פתוח, 2 חניות פרטיות.\nכניסה גמישה החל מינואר\nלפרטים נוספים: יקיר - 0502750090\n",""));
        servercontroller.newPost(new Post("1231C_22341174", new Date(new Date().getTime()-500193812), "user tow", "להשכרה דירת 4 חדרים רק ב2,300 ש\"ח.\n\n ברחוב הכ\"ג פינת הלפרין, שכונה ג'.\n\n*96 מ\"ר\n*קומה 3\\3\n*ממוזגת\n\nהדירה מוארת, גדולה ומרווחת להפליא. מתאימה למשפחה, לשותפים או לסטודנטים.\n\nמיקומה הנפלא של הדירה הנ\"ל בעצם מאפשרת הגעה לכל מקום מרכזי וחשוב לתושב העיר, בצעידה של כרבע שעה - מתחם הקניות ביג, קניון הנגב, התחנה המרכזית, בניין המכינה של בן-גוריון, אוניברסיטת בן-גוריון.\n\nריהוט: קומפלט פרט למכונת כביסה\n\nלפרטים: 054-449-0025\n",""));
        servercontroller.newPost(new Post("1231C_22341175", new Date(new Date().getTime()-600194919), "user user", "קוטג' ברמות להשכרה רק ב2,900 ש\"ח!!!\n\n2 חדרי שינה וסלון\n\nהקוטג' מתאים למשפחות, סטודנטים, שותפים או לזוגות.\nמדובר בשכונה הישנה של רמות.\n\n* כניסה מיידית!!!!!\n* 80 מ\"ר\n* דוד חשמל ושמש\n* גינה ענקית (170 מ\"ר)\n* מזגן בכל חדר\n\nריהוט: ספות 2+3, שידה, שולחן סלון, מיקרוגל, 2 ארונות 4 דלתות, מיטה זוגית+שידות.\n\nלפרטים: 054-449-0025\n",""));
        servercontroller.newPost(new Post("1231C_22341176", new Date(new Date().getTime()-702395923), "dan arieli", "להשכרה קוטג' 3 חדרים בשכונת נחל עשן\n\nממוזגת\nמשופצת\nכניסה מיידית\n65 מ\"ר\nגינה 230 מ\"ר\n\nריהוט: מקרר.\n\nמחיר: רק ב3,500.\n\nטלפון: 052-477-8940\n",""));
        servercontroller.newPost(new Post("1231C_22341177", new Date(new Date().getTime()-812271299), "dor ashkenazi", "שותף (סטודנט) מחפש 2 שותפים נוספים לדירת 4 חדרים\nשכ״ד לכל חדר - 900 ש״ח\nהדירה היא ברחוב יואל השופט 2 - 5 דקות הליכה מהאוניברסיטה ו5 דקות הליכה ממרכז אורן!\nהדירה משופצת, נעימה ואידיאלית לסטודנטים\nמבחינת ריהוט יש הכל כולל הכל: ספות, שולחן אוכל קטן + 2 כסאות, שולחן סלון, טלוויזיה, מקרר, מיקרוגל, טוסטר אובן, כיריים גז ומכונת כביסה.\nבכל חדר: מזגן, מיטה, מזרון, שולחן כתיבה, כסא וארון\n\nכניסה מיידית אז מוזמנים לפנות אליי בפרטי :)\n",""));
        servercontroller.newPost(new Post("1231C_22341178", new Date(new Date().getTime()-912818281), "shachar hasson", "להשכרה דירת קרקע 2 חדרים מזגנים בכל הבית רהוט מלא מכונת כביסה מקרר תנור משולב עם גז סלון ועוד... רחוב בר גיורא 7\n חמש דקות מהאוניברסיטה\n1700 בלבד לחודש\nפינוי גמיש 0522623356\n",""));
        servercontroller.newPost(new Post("1231C_22341179", new Date(new Date().getTime()-100219129), "adi ashkenazi", "נותר חדר אחרון הכולל מקלחת ושרותים צמודים!\nברחוב השלום 20...עם עוד 2 שותפים...\nהדירה ממוקמת מול בת דור ובקרבת בית הספר למשחק ,מרכז מסחרי והמכינה של בן גוריון... הדירה נמצאת בלב העיר וקרובה כמעט להכל כדאי לבוא להתרשם נעשה שיפוץ בסטנדרטיים גבוהים!..החדר כולל טלוויזיה LCD 32 אינצ' מיטה זוגית+מזרן ארון בגדים,מקרר מיני בר ושולחן כתיבה...כניסה מיידית\nמחיר לחדר 1200 שח גמיש למהירי החלטה.\nלפרטים 0524601230 או בפרטי\n",""));
        servercontroller.newPost(new Post("1231C_22341170", new Date(new Date().getTime()-128391220), "ron shoval", "רוצים לחנוך דירה חדשה דנדשה?! אברהם אבינו 14 פינת אלכסנדר ינאי קומה 1. מנעול בכניסה לבניין 80 מטר ל3 שותפים מחיר 1050 לשותף כמובן שגם ריהוט כלול כניסה באמצע חודש 10 תשלום החל מחודש 11. לפרטים 0532731622\n",""));
        servercontroller.newPost(new Post("1231C_22341111", new Date(new Date().getTime()-123), "yuval mendelson", "להשכרה דירת 2.5 ח\"ד\n\nשכונה ד', רח' אלעזר בן יאיר\n\n*משופצת\n*מזגן בכל חדר\n*קומה 3\\1\n\nריהוט: מקרר, ארון 3 דלתות.\n\nכניסה מיידית\n\nמחיר: 2,000 ש\"ח.\n\nלפרטים: 054-449-0025\n",""));
        servercontroller.newPost(new Post("1231C_22341112", new Date(new Date().getTime()-21312321), "arkadi dochin", "להשכרה יחידת דיור 3 חד' כולל מים וארנונה ב-2,100 ש\"ח\n\nשכונה ג', רח' קלישר 54\nמרחק הליכה קצר לאוניברסיטת בן גוריון\n\n*משופצת\n*מזגן בכל חדר (3 מזגנים)\n*קומה 3\\3\n*יש תשתית אינטרנט של חברת Unlimited!!!\n\nריהוט: עפ\"י דרישה.\n\nכניסה מיידית.\n\nלפרטים: 054-449-0025\n",""));
        servercontroller.newPost(new Post("1231C_22341113", new Date(new Date().getTime()-98986523), "ronen bobo", "נותרו שני חדרים אחרונים !!\nברחוב השלום 29\nהדירה הינה דירת סוייטות מרווחת ומאובזרת(כל חדר מקלחת ושירותים) המיועדת ל3 שותפים\nשני חדרים שנותרו חדר רגיל (1150) וחדר ענק הכולל סלון (1300 ש\"ח לחודש כבר )\nבכל חדר שולחן לימוד+כסא,ארון, מיטה זוגית+מזרן וטלוויזית LCD.\nקיים מרחב משותף(מטבח סלון ופינת אוכל) הכולל סלון,שולחן סלוני,מקרר,טוסטר,מיקרוגל טלוויזיה ומכונת כביסה.\nהדירה בת כ90 מ\"ר בקומה ג' וממוקמת ליד בת דור ,בסמוך לבית הספר למשחק והקמפוס האקדמי של בן גוריון ובמרחק הליכה ממרכז מסחרי בו ניתן למצוא הכל.\nמיקום מרכזי ביותר ונגיש,בלוק וכניסה סטודנטיאלית וצעירה.\nלפרטים נוספים ותיאום 0524601230\nתינתן הנחה לבאים כקבוצה ולמהירי החלטה\n",""));
        servercontroller.newPost(new Post("1231C_22341114", new Date(new Date().getTime()-9896523), "sara aharon", "**עדין רלוונטי! - ללא תיווך**\nהתפנתה דירה משופצת מוארת ומדהימה!\nמיקום מעולה - וינגייט שקטה ליד סורוקה והאוניברסיטה\n1000 לכל שותף\nירקן וסופר מעבר לכביש\nשלושה חדרי שינה שווים ומרווחים, בכל אחד ארון, מיטה, שולחן, מזגן וחלון\nמרחב משותף רחב ונעים, מטבח מאובזר כולל מקרר, פינת אוכל מרווחת\nשירותים ומקלחת נפרדים\nמכונת כביסה\nבעל דירה נוח!\nבר 0584050960\nיכולים להתקשר גם אלי-\nשיר 0506966516\n",""));
        servercontroller.newPost(new Post("1231C_22341115", new Date(new Date().getTime()-989523), "margalit zhanani", "להשכרה בדרך מצדה\n₪2,700\n\nכתובת :דרך מצדה 95, קומה 2\n75 מטר נטו.\nמקום שקט ובטוח,יש שערים עם מפתח בכניסה לבניין\nדירת ארבעה חדרים-שלושה חדרי שינה, סלון מרווח ומואר,מטבח וחדר שירות.\nעברה שיפוץ כולל לפני שנה וחצי.\nכניסה מיידית\nמזגן בסלון+בשני חדרי שינה.\nהדירה מושכרת עם תנור משולב כיריים+מקרר חדשים.\nהדירה לא מרוהטת והמחיר לא כולל חשבונות.\nיש וועד בית.\n052-6035054 גיל.\n",""));
        servercontroller.newPost(new Post("1231C_22341116", new Date(new Date().getTime()-976523), "ben osor", "להשכרה במיתר יחידת דיור בשכונת רבין.\n70 מ\"ר + חצר פרטית + מחסן ומזווה חיצוני.\nנמצאת במקום מרכזי, קרובה לבתי הספר ולמרכז המסחרי של הישוב.\nשני חדרי שינה, מטבח, סלון ושני חדרי שירותים.\nיחידה יפה ומושקעת, בית אבן וגינה גדולה, יפה ומטופחת.\n\nמחיר 2700 ₪\nכדאי לראות.\n\nלפרטים -\nמאיר - 0525816460\nכרמלית - 0525816440\n",""));
        servercontroller.newPost(new Post("1231C_22341117", new Date(new Date().getTime()-96523), "rivka tamir", "***להשכרה ברחוב דונקלבלום 1 ב-ו' החדשה***\nדירת 4 חדרים משופצת מהיסוד!!\n-חדר הורים עם שרותים ומקלחת\n-בניין מתוחזק ברמה יוצאת דופן.\n-חניה+מחסן\n-ריהוט: פינת אוכל, 2 מיטות יחיד, 2 ארונות עץ מלא, ארון הזזה עם טריקה שקטה, 2 שידות עץ מלא, כריים, תנור, שולחן סלון, מזנון.\nכניסה: 14/11\nמחיר: 3400\nטלפון לפרטים: 0542054048 יעקב\n",""));
*/
    }
}
