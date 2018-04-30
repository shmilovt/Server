package il.ac.bgu.finalproject.server.PersistenceLayer;
import il.ac.bgu.finalproject.server.Domain.Controllers.MyLogger;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.*;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils.ResultRecord;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils.SearchResults;
import il.ac.bgu.finalproject.server.Domain.Exceptions.DataBaseFailedException;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

public class DataBaseConnection implements DataBaseConnectionInterface {

    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    private static Connection conn = null;
    private  String addressDetailsIDString ="addressDetailsID";
    private  String apartmentIDString ="apartmentID";

    public void connect() throws DataBaseFailedException {
        String url = "jdbc:sqlite:src\\main\\java\\il\\ac\\bgu\\finalproject\\server\\PersistenceLayer\\db\\ApartmentBS.db";
        try {
            conn = DriverManager.getConnection(url);
        }
        catch(SQLException e){}
        catch (Exception e){
            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
            throw new DataBaseFailedException("disconnect to the DataBase ",1);
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
        }    }
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

    public void resetUserSuggestionsTable() throws DataBaseFailedException {
        String sql= "DROP TABLE contacts";
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
    public void setUserSuggestions (String id, String field, String suggestion, int count) throws DataBaseFailedException {
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
            PreparedStatement pstmt = conn.prepareStatement(sql);;
            pstmt.executeUpdate();
        }
        catch(SQLException e){}
        catch (Exception e){

            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
            throw new DataBaseFailedException("drop th contacts table",4);
        }
        sql=  "CREATE TABLE Posts(\n" +
                "  postID int PRIMARY KEY,\n" +
                "  dateOfPublish Date,\n" +
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
            PreparedStatement pstmt = conn.prepareStatement(sql);;
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
                "  PRIMARY KEY (searchDate, neighborhood, timeFromUni, cost, floor, size, furnitures)" +
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
                Post post = new Post(rs.getString(1), rs.getDate(2),
                        rs.getString(3), rs.getString(4),
                        rs.getString(5));
                return post;
            }
            catch (SQLException e) {return null;}
            catch (Exception e) {
                MyLogger.getInstance().log(Level.SEVERE, e.getMessage(), e);
                return null;
            }
//        try {
//            String sql = "SELECT postID, dateOfPublish, publisherName, postText, apartmentID " +
//                    "FROM posts WHERE postID =" + "'" + id + "'";
//            Statement stmt = conn.createStatement();
//            ResultSet rs = stmt.executeQuery(sql);
//            Post post = new Post(rs.getString(1), rs.getDate(2),
//                    rs.getString(3), rs.getString(4),
//                    rs.getString(5));
//            return post;
//        }
//        catch (SQLException e) {return  null;}
//        catch (Exception e) {
//            MyLogger.getInstance().log(Level.SEVERE, e.getMessage(), e);
//            return null;
//        }
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
        catch(SQLException e){
            return null;
        }
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
        Address address;
        String sql = "SELECT street, numOfBuilding, timeFromUni, neighborhood " +
                "FROM AddressDetails where addressDetailsNum= "  + addressDetilsID  ;
        Statement stmt  = null;
        try {
            stmt = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            address=new Address(rs.getString(1),rs.getInt(2));
            location.setAddress(address);
            location.setUniversity_distance(rs.getInt(3));
            location.setNeighborhood(rs.getString(4));
        }
        catch(SQLException e){}
        catch (Exception e){
            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
//            return null;
        }
        return location;
    }

    public Apartment getApartmentRecordTBD(String id) {
        try {
            String sql = "SELECT * " +
                    "FROM apartment where apartmentID =" + "'" + id + "'";
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            ApartmentLocation location= new ApartmentLocation();
            location = getAddressDetils(rs.getInt(1));
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
        }
    }

    public SearchResults allResultsFromDB () {
//        connect();
        List<ResultRecord> results = new LinkedList<ResultRecord>();
        ResultRecord temp;
        try {
            String sql = "SELECT apartmentID, addressDetailsID, floor, cost, Apartment.size, balcony, " +
                    "garden, animal, warehouse, protectedSpace, furniture, numOfRooms, numberOfMates "+
                    " FROM Apartment";
// JOIN Posts P ON Apartment.apartmentID = P.apartmentID";
            Statement stmt = conn.createStatement();
            System.out.println("1");
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("1");
            while (rs.next()) {
                System.out.println("1");
                Set<Contact> contacts = getApartmentContacts(rs.getString(1));
                ApartmentLocation location = getAddressDetils(rs.getInt(2));
                Post post= getPostByApartmentID(rs.getString(1));

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
                if(post==null){
                    Date date=new Date();
                    temp.setDateOfPublish(date.toString());
                }
                else
                    temp.setDateOfPublish(post.getDateOfPublish().toString());
                temp.setText(rs.getString(post.getText()));
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
        catch(SQLException e){}
        catch (Exception e){
            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
//            throw new DataBaseFailedException("drop th contacts table",4);
        }
        SearchResults searchResults= new SearchResults(results);
        return searchResults;
    }

    public Post getPostByApartmentID(String id) {
        try {
            String sql = "SELECT postID, dateOfPublish, publisherName, postText, apartmentID" +
                    " FROM Posts WHERE apartmentID = '" + id +"'" ;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            Post post = new Post(rs.getString(1), rs.getDate(2),
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

    public void addSearchRecord(String neighborhood, String timeFromUni, String cost, String floor, String size, String furnitures) throws DataBaseFailedException { //func that will be used by the client (Android App)
        LocalDateTime now = LocalDateTime.now();
        try {
            String sql = "INSERT INTO SearchRecord(searchDate, neighborhood,"+
                    " timeFromUni, cost, floor, size, furnitures)"+
                    " VALUES(?,?,?,?,?,?,?)";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, dtf.format(now));
            pstmt.setString(2, neighborhood);
            pstmt.setString(3, timeFromUni);
            pstmt.setString(4, cost);
            pstmt.setString(5, floor);
            pstmt.setString(6, size);
            pstmt.setString(7, furnitures);

            pstmt.executeUpdate();
        }
        catch(SQLException e){}
        catch (Exception e){
            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
            throw new DataBaseFailedException("drop th contacts table",4);
        }
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

    public int isAddressDetailsExist(Address address) {
        Statement stmt = null;
        String sql = "SELECT addressDetailsNum FROM AddressDetails "
                +" WHERE street= '"+address.getStreet()
                +"' AND numOfBuilding= "+ address.getNumber();
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
        int tempForAddressDetaileNum= isAddressDetailsExist(apartment.getApartmentLocation().getAddress());
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



    public static void main(String[] args) throws Exception
    {
        DataBaseConnection a=new DataBaseConnection();
        a.connect();
        a.resetUserSuggestionsTable();
//        a.resetAllTables();
        a.disConnect();
//        Date date = new Date();
//        Post p1=new Post("121212",date,"mikey","וילה להשכרה ברח' אברהם יפה, רמות, באר שבע. מפלס אחד, חדשה מקבלן(קבלת מפתח לפני פחות מחודש). 5 חדרים ובנוסף יחידת דיור נוספת של 60 מטר (אופציונאלית). הכל חדש, ריצוף מטר על מטר, מטבח אינטגרלי לבן זכוכית של מטבחי זיו, חדרים ענקיים.\n" +
//                "כולל מדיח כלים אינטגרלי, מזגן מיני מרכזי, מזגנים עיליים בכל חדר (אינוורטר). תריסי אור חשמליים בכל הבית. 1200 שח\n" +
//                "לפרטים: \n" +
//                "ניר - 054-9449978 חן- 054-5802333",null);
//        Post p2=new Post("121213",date,"mikey1","יחידת דיור להשכרה בהר בוקר 26, זוהי יחידת דיור קרקע הכוללת : סלון, חדר שינה, חדר ממד וגינה .\n" +
//                "בדירה ישנו מיזוג גם בסלון וגם בחדר שינה , והדירה נכללת עם מיטה , ארון , פינת אוכל,סלון, מיקרוגל, תנור אובן, ועוד.\n" +
//                "הדירה מעולה לזוגות , לשותפים , סטודנטים ..\n" +
//                "(החדר מדד הוא מעבר דרך החדר שינה- יתאים לשותפים שהם חברים טובים או פתוחים אחד עם השני בלי קשר וכו׳)\n" +
//                "20 דק הליכה מהאוניברסיטה ו7 דק בתחבורה הציבורית.\n" +
//                "העלות של הדירה היא 2800 כולל מים ארנונה חשמל וכבלים!!!\n" +
//                "*ישנה אפשרות לבעלי חיים\n" +
//                "לפרטים :\n" +
//                "שירה 0546559992",null);
//        Post p3=new Post("121214",date,"mikey2","שאול המלך דירת 2 חדרים קומה קרקע כניסה מידית רק 1800\n" +
//                "₪ 1,800\n" +
//                "Беэр-Шева\n" +
//                "0546329669 דודי נכסים",null);
//        Post p4=new Post("121215",date,"mikey3","דירה 2 חדרים כ50 מ\"ר\n" +
//                "מתאימה לזוג או ליחיד \n" +
//                "נשאר בדירה מקרר ארון ומיטה\n" +
//                "הדירה נמצאת 2 דקות מכל המסעדות ברינגנבלום ו5 דקות מהאוניברסיטה\n" +
//                "(דירה לכל דבר לא יחידת דיור)\n" +
//                "רחוב שמעון ברנפלד 26\n" +
//                "אפשר ליצור קשר איתי ב 0524188278 \n" +
//                "או קיריל 0527021623",null);
//        Post p5=new Post("121216",date,"mikey4","למהירי החלטה דירת 4 חדרים להשכרה בשכונה ה המבוקשת ברחוב הצבי במיקום מרכזי במרחק הליכה מגרנד קניון, בית כנסת הכיפה, מוסדות חינוך, תחבורה ציבורית ומרכזי קניות\n" +
//                "דירה מרווחת ומתוכננת היטב בבניין בן 8 קומות כולל מעלית, ללא ריהוט.\n" +
//                "כניסה מיידית!!! ללא תיווך\n" +
//                "שכ\"ד: 2900ש\"ח בחודש\n" +
//                "וועד: 170ש\"ח בחודש\n" +
//                "ארנונה:650 ש\"ח לחודשיים.\n" +
//                "לפרטים נוספים ניתן ליצור קשר\n" +
//                "0549902222/0537476855",null);
//        Post p6=new Post("121217",date,"mikey5","***לסטודנטים ללא תיווך! ***\n" +
//                "להשכרה בשכונה ג,ברחוב יד ושם 24 מגדל בית שיאים, קומה 8 עם מעלית .\n" +
//                "דירת חדר שינה וסלון מרוהטת, שכונה שקטה במיקום מעולה.\n" +
//                "רבע שעה הליכה לקניון הנגב והרכבת, חמש דקות הליכה למכללה למנהל, חמש דקות נסיעה לבן גוריון\n" +
//                "מחיר 1850.\n" +
//                "יש עלות ועד בנין על סך 250 ש\"ח\n" +
//                "לפרטים וסיור בנכס:\n" +
//                "בן0524446286\n" +
//                "דוד 0544293989",null);
//        Post p7=new Post("121218",date,"mikey5","השכרה !! מגוון דירות סביב האוניברסיטה ללא דמי תיווך, לסטודנטים ולמורים בלבד!\n" +
//                "דירות ל2 3 ו4 שותפים\n" +
//                "-דירות עם שירותים ומקלחת פרטיים משופצות ומאובזרות\n" +
//                "-כניסות החל מ1.9 \n" +
//                "-דירות שמנוהלות עי חברת\n" +
//                "לדוגמא דירת 3 חדרים קרקע בשכונה ד' - 10 דק' מהאוניברסיטה\n" +
//                "מתאימה לזוג שותפים\n" +
//                "-בכל חדר שירותים ומקלחת לכל דייר\n" +
//                "-מאובזרת קומפלט\n" +
//                "מחיר - 2500 שח ללא חשבונות\n" +
//                "\n" +
//                "-מוזמנים לשלוח מספר טלפון בפוסט זה או לשלוח הודעה למספר 0525447662\n" +
//                "כל מי שמשאיר טלפון נכנס לרשימת תפוצה שבה ישלחו מגוון דירות להשכרה לשנת הלימודים הקרובה בהתאם לצרכים שלכם.\n" +
//                "נדאג למצוא לכל אחד את הדירה שתתאים לו! וכמובן סטודנטים ללא דמי תיווך!",null);
//        Post p8=new Post("121219",date,"mikey","להשכרה דירת 4.5 חדרים ללא דמי תיווך\n" +
//                "₪ 2,800\n" +
//                "תל אביב-יפו\n" +
//                "מעולה לסטודנטים. ו' הישנה. 100 מטר משער הכניסה לאוניברסיטה.מזגנים בחדרים. ריהוט חלקי. כניסה מיידית. השכירות לטווח ארוך.",null);
//
//        ServerController serverController= new ServerController();
////        serverController.newPost(p1);
//        serverController.newPost(p2);
//        serverController.newPost(p3);
//        serverController.newPost(p4);
//        serverController.newPost(p5);
//        serverController.newPost(p6);
//        serverController.newPost(p7);
//        serverController.newPost(p8);


//        Date date = new Date();
//        a.updateO("516188885429510_516287808752951",date.toString(),"succ");

//        a.resetSearchRecordsTable();
//        LocalDateTime now = LocalDateTime.now();
//        a.addPost("66",null, "nof", "דירה מהממת", "5");
//        a.update("66",dtf.format(now), "may", "דירה אליפות", "5");
        //a.deletePost("66");

        //System.out.println(a.GetAllPostsId().get(0));

        //a.deleteAllRecords("posts");
        //a.deleteAllRecords("addressDetails");
        //a.addAddressDetailsRecord(
        //        "בן חיימוש",
        //        "3",
        //       10,
        //      1, 47.0, 47.0);
    }
}
