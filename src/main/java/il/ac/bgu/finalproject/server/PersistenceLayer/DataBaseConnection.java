package il.ac.bgu.finalproject.server.PersistenceLayer;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class DataBaseConnection implements DataBaseConnectionInterface {

    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    private static Connection conn = null;
    private  int addressDetailsID =-1;
    private  int cApartmentID =-1;

    public void connect()
    {
        String url = "jdbc:sqlite:src\\main\\java\\il\\ac\\bgu\\finalproject\\server\\PersistenceLayer\\db\\ApartmentBS.db";
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //System.out.println("Connection to SQLite has been established.");
    }

    public void disConnect() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            //System.out.println(ex.getMessage());
        }
    }

    private int addressDetailsIdCreator(){
        addressDetailsID = addressDetailsID +1;
        return addressDetailsID;
    }
    private int apartmentIdCreator(){
        cApartmentID = cApartmentID +1;
        return cApartmentID;
    }


    public void resetContactsTable(){
        String sql= "DROP TABLE contacts";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);;
            pstmt.executeUpdate();
        }
        catch (SQLException e){}

        sql= "CREATE TABLE Contacts(\n" +
                "  phone text PRIMARY KEY,\n" +
                "  name text NOT NULL\n" +
                ")";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);;
            pstmt.executeUpdate();
        }
        catch (SQLException e){}
    }


    public void resetAddressDetailsTable(){
        String sql= "DROP TABLE addressDetails";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);;
            pstmt.executeUpdate();
        }
        catch (SQLException e){}

        sql= "CREATE TABLE AddressDetails(\n" +
                "  street text NOT NULL ,\n" +
                "  numOfBuilding text NOT NULL,\n" +
                "  timeFromUni double,\n" +
                "  neighborhood int,\n" +
                "  longitude double,\n" +
                "  latitude double,\n" +
                "  addressDetailsNum int,\n" +
                "    PRIMARY KEY(street, numOfBuilding)\n" +
                ")";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);;
            pstmt.executeUpdate();
        }
        catch (SQLException e){}
    }

    public void resetApartmentTable(){
        String sql= "DROP TABLE apartment";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);;
            pstmt.executeUpdate();
        }
        catch (SQLException e){}

        sql=  "CREATE TABLE Apartment(\n" +
                "  apartmentID INTEGER PRIMARY KEY,\n" +
                "  numOfRooms int,\n" +
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
        catch (SQLException e){}
    }

    public void resetApartmentContactsTable(){
        String sql= "DROP TABLE apartmentContacts";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);;
            pstmt.executeUpdate();
        }
        catch (SQLException e){}

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
        catch (SQLException e){}
    }

    public void resetPostsTable(){
        String sql= "DROP TABLE posts";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);;
            pstmt.executeUpdate();
        }
        catch (SQLException e){}

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
        catch (SQLException e){}
    }

    public void resetSearchRecordsTable(){
        String sql= "DROP TABLE searchRecord";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);;
            pstmt.executeUpdate();
        }
        catch (SQLException e){}
        sql=  "CREATE TABLE SearchRecord(\n" +
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
        catch (SQLException e){}
    }

    public void resetAllTables(){
        resetContactsTable();
        resetAddressDetailsTable();
        resetApartmentTable();
        resetApartmentContactsTable();
        resetPostsTable();
        resetSearchRecordsTable();
    }


    ///===========POSTS TABLE==============///
    public void addPost(String id, String date, String publisherName, String message, String apartmentID) {
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
            //System.out.println("Added");
        } catch (SQLException e) {
            //System.out.println(e.getMessage());
        }
    }


    public void update(String id, String date, String publisherName, String message, String apartmentID) {
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
        } catch (SQLException e) {
            //System.out.println(e.getMessage())
        }
    }

    public Post getPost(String id) {
        try {
            String sql = "SELECT * FROM posts where postID =" + "'" + id + "'";
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            Post post= new Post(rs.getString(1),rs.getDate(2),
                    rs.getString(3),rs.getString(4),
                    rs.getString(5));
            return post;
        } catch (SQLException e) {
            //System.out.println(e.getMessage());
            return null;
        }
    }

    public void deletePost(String id) {
        //  System.out.println("Hello deletePost");
        String sql = "DELETE FROM posts WHERE postID = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        }
        catch(SQLException e){
            // System.out.println("Hello deletePost failed");

        }
    }

    public List<String> GetAllPostsId() {
        String sql = "SELECT postID FROM posts";
        List<String> posts = new LinkedList<String>();
        //Post tempPost= new Post("");
        try {
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            while (rs.next())
                posts.add(rs.getString(1));
            return posts;
        }
        catch(SQLException e){
            return null;
        }
    }

    ///===========ADDRESS DETAILS TABLE==============///
    //strinf street, string numOfBuilding, double timeFromUni, int neighborhood, double longitude, double latitude, int addressDetailsNum,
    public int addAddressDetailsRecord(String street, String numOfBuilding, double timeFromUni, int neighborhood, double longitude, double latitude) {
        int t;
        try {
            String sql = "INSERT INTO AddressDetails(street, numOfBuilding, timeFromUni, "+
                    "neighborhood, longitude, latitude, addressDetailsNum)"+
                    " VALUES(?,?,?,?,?,?,?)";


            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, street);
            pstmt.setString(2, numOfBuilding);
            pstmt.setDouble(3, timeFromUni);
            pstmt.setInt(4, neighborhood);
            pstmt.setDouble(5, longitude);
            pstmt.setDouble(6, latitude);
            t= addressDetailsID +1;
            pstmt.setInt(7, t);
            pstmt.executeUpdate();
            return addressDetailsIdCreator();
        } catch (SQLException e) {
            return -1;
        }
    }

    public void updateAddressDetailsRecord(String street, String numOfBuilding, double timeFromUni, int neighborhood, double longitude, double latitude) {
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
            pstmt.setInt(2, neighborhood);
            pstmt.setDouble(3, longitude);
            pstmt.setDouble(4, latitude);
            pstmt.setString(5, street);
            pstmt.setString(6, numOfBuilding);

            // update
            pstmt.executeUpdate();
            // System.out.println("update done");
        } catch (SQLException e) {
            //System.out.println(e.getMessage());
        }
    }

    public Post getPostaaaa(String id) {
        try {
            //List<String> post = new LinkedList<String>();
            //Post post;
            String sql = "SELECT * FROM posts where postID =" + "'" + id + "'";
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            //post.add(rs.getString(1));
            //post.add(rs.getString(2));
            //post.add(rs.getString(3));
            //System.out.println(rs.getString(3));
            Post post= new Post(rs.getString(1),rs.getDate(2),
                    rs.getString(3),rs.getString(4),
                    rs.getString(5));
            return post;
        } catch (SQLException e) {
            //System.out.println(e.getMessage());
            return null;
        }
    }

    public void deletePostaaa(String id) {
        //  System.out.println("Hello deletePost");
        String sql = "DELETE FROM posts WHERE postID = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        }
        catch(SQLException e){
            // System.out.println("Hello deletePost failed");

        }
    }

    ///===========CONTACTS TABLE==============///
    //String phone, String name text
    public void addContactsRecord(String phone, String name) {
        try {
            String sql = "INSERT INTO Contacts(phone, name)"+
                    " VALUES(?,?)";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, phone);
            pstmt.setString(2, name);
            pstmt.executeUpdate();
            //System.out.println("Added");
        } catch (SQLException e) {
            //System.out.println(e.getMessage());
        }
    }

    ///===========APARTMENT_CONTACTS TABLE==============///
    //int apartmentID , String phoneNumber
    public void addApartmentContactsRecord(String apartmentID , String phoneNumber) {
        try {
            String sql = "INSERT INTO ApartmentContacts(apartmentID, phoneNumber)"+
                    " VALUES(?,?)";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, apartmentID);
            pstmt.setString(2, phoneNumber);
            pstmt.executeUpdate();
            //System.out.println("Added");
        } catch (SQLException e) {
            //System.out.println(e.getMessage());
        }
    }

    ///===========APARTMENT TABLE==============///
    //int apartmentID, int numOfRooms, int floor, double size, int cost, int addressDetailsID
    public String addApartmentRecord(String apartmentID, int numOfRooms, int floor,
                                 int size, int cost, int addressDetailsID,
                                     int garden, int gardenSize, int protectedSpace, int warehouse, int animal,
                                     int balcony, int furniture, int numberOfMates
    ) {

        try {
            String sql = "INSERT INTO Apartment(apartmentID, numOfRooms, floor, size, cost, addressDetailsID, " +
                    "garden, gardenSize, protectedSpace, warehouse, animal, " +
                    "balcony, furniture, numberOfMates)"+
                    " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            int t = cApartmentID+1;
            pstmt.setInt(1, t);
            pstmt.setInt(2, numOfRooms);
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
            //System.out.println("Added");
        } catch (SQLException e) {
            return e.toString();
            //System.out.println(e.getMessage());
        }
        return ""+apartmentIdCreator();
    }

    public ApartmentLocation getAddressDetils (int addressDetilsID ){
        ApartmentLocation location= new ApartmentLocation();
        String sql = "SELECT * FROM apartment where apartmentID =" + "'" + addressDetilsID  + "'";
        Statement stmt  = null;
        try {
            stmt = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return location;
    }
    public Apartment getApartmentRecordTBD(String id) {
        try {
            String sql = "SELECT * FROM apartment where apartmentID =" + "'" + id + "'";
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);


            ApartmentLocation location= new ApartmentLocation();
            location = getAddressDetils(rs.getInt(6));
            location.setFloor(rs.getInt(3));

            Apartment apartment= new Apartment();
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
        } catch (SQLException e) {
            //System.out.println(e.getMessage());
            return null;
        }
    }


    public void deleteApartmentRecord(String id) {
        String sql = "SELECT addressDetailsID FROM Apartment WHERE apartmentID= "+id;
        int t=-1;
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs= pstmt.executeQuery(sql);
            t= rs.getInt(1);
        }
        catch(SQLException e){ }
        if(!moreApartmentsWithAddressDetailsNum(""+t))
            deleteAddressDetailsRecord(""+t);

        sql = "DELETE FROM Apartment WHERE apartmentID= "+id;
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        }
        catch(SQLException e){ }

        //apartmentContacts
        sql = "DELETE FROM ApartmentContacts WHERE apartmentID= "+id;
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        }
        catch(SQLException e){ }
    }

    public void deleteAddressDetailsRecord(String id) {
        String sql = "DELETE FROM AddressDetails WHERE addressDetailsNum= id";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (SQLException e) {}
    }

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
        } catch (SQLException e) {
            e.printStackTrace();
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
        catch (SQLException e) { }
        return false;
    }

    public void deleteAllRecords(String tablaName){
        String sql = "DELETE FROM ?;";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, tablaName);
            pstmt.executeUpdate();
        }
        catch(SQLException e){
            // System.out.println("Hello deletePost failed");

        }
    }

    public void addSearchRecord(String neighborhood, String timeFromUni, String cost, String floor, String size, String furnitures){ //func that will be used by the client (Android App)
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
        } catch (SQLException e) { }
    }

//    public List<Apartment> SearchQuery (List<CostCategory> catagories){
//        List<Apartment> apartments = new LinkedList<Apartment>();
//        Apartment temp= new Apartment();
//        try {
//            String sql = "SELECT Apartment.size, Apartment.cost "
//                    + " FROM Apartment"
//                    + " JOIN AddressDetails Detail ON Apartment.addressDetailsID = Detail.addressDetailsNum"
//                    + " JOIN ApartmentContacts C2 ON Apartment.apartmentID = C2.apartmentID"
//                    + " JOIN Contacts C3 ON C2.phoneNumber = C3.phone "
//// TBD
////                    + prepareCoditions(catagories)
//                    + " ORDER BY Apartment.cost, Apartment.size DESC ";
//
//            Statement stmt = conn.createStatement();
//            ResultSet rs= stmt.executeQuery(sql);
//            while (rs.next()) {
//// TBD: set for all in Apartment OR contructor.... TBDAPARTMENT
//                temp.setSize(rs.getInt(1));
//                temp.setCost(rs.getInt(2));
//                System.out.println(temp.toString());
//                apartments.add(temp);
//            }
//
//        } catch (SQLException e) { }
//        return apartments;
//    }

    public List<Apartment> allApartmentFromDB (){
        //connect();
        List<Apartment> apartments = new LinkedList<Apartment>();
        Apartment temp= new Apartment();
        try {
            String sql = "SELECT street, numOfBuilding, floor, cost, Apartment.apartmentID, "
                    + " FROM Apartment"
                    + " JOIN AddressDetails Detail ON Apartment.addressDetailsID = Detail.addressDetailsNum"
                    + " JOIN ApartmentContacts C2 ON Apartment.apartmentID = C2.apartmentID"
//                    + " JOIN Contacts C3 ON C2.phoneNumber = C3.phone "
                    + " ORDER BY Apartment.cost";

            Statement stmt = conn.createStatement();
            ResultSet rs= stmt.executeQuery(sql);
            while (rs.next()) {
                Address address= new Address(rs.getString("street"),rs.getInt("numOfBuilding"));
                System.out.println(address.toString());
                ApartmentLocation location= new ApartmentLocation(address, rs.getInt("floor"));
                Set <Contact> contacts= new HashSet<Contact>();
                contacts.add(new Contact(rs.getString("name"),rs.getString("phone")));
                Apartment apartment= new Apartment(location,rs.getInt("cost"),rs.getInt("size"), contacts);

//                System.out.println(temp.toString());
                apartments.add(temp);
            }

        } catch (SQLException e) { }
    //    disConnect();
        return apartments;
    }

    //@Override
    public void updateApartmentRecord(Apartment apartment, String apartmentid) {
        try {
            String sql = "UPDATE Apartment SET numOfRooms=? , floor=? , size=? , cost=? ,  " +
                    "garden=? , gardenSize=? , protectedSpace=? , warehouse=? , animal=? , " +
                    "balcony=? , furniture=? , numberOfMates=? " +
                    "WHERE apartmentID=? ";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, apartment.getNumberOfRooms());
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
        } catch (SQLException e) {
            //System.out.println(e.getMessage());
        }
    }

    //@Override
    public int isApartmentExist(Apartment apartment) {
        Set<Contact> contacts = apartment.getContacts();
        for (Contact eachcotact : contacts) {
            Statement stmt = null;
            String sql = "SELECT Apartment.apartmentID FROM Apartment "
                    + " JOIN AddressDetails Detail ON Apartment.addressDetailsID = Detail.addressDetailsNum"
                    + " JOIN ApartmentContacts C2 ON Apartment.apartmentID = C2.apartmentID"
                    + " JOIN Contacts C3 ON C2.phoneNumber = C3.phone "
                    + "WHERE phoneNumber= '"+eachcotact.getPhone()
                    +"' AND street= '"+apartment.getApartmentLocation().getAddress().getStreet()
                    +"' AND numOfBuilding= "+ apartment.getApartmentLocation().getAddress().getNumber();
            try {
                stmt = conn.createStatement();
//                stmt.setString(1, eachcotact.getPhone());
//                stmt.setString(2, apartment.getApartmentLocation().getAddress().getStreet());
//                stmt.setInt(3, apartment.getApartmentLocation().getAddress().getNumber());
                ResultSet rs = stmt.executeQuery(sql);
                if (rs.next()) {
                    return rs.getInt(1);
                    //return true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public int isAddressDetailsExist(Address address) {
            Statement stmt = null;
            String sql = "SELECT AddressDetails.addressDetailsNum FROM AddressDetails"
                    + "WHERE street= '"+address.getStreet()
                    +"' AND numOfBuilding= "+ address.getNumber();
            try {
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                if (rs.next()) {
                    return rs.getInt(1);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return -1;
    }

    public void addApartmentDerivatives(Apartment apartment, String postID){
        int tempForAddressDetaileNum= isAddressDetailsExist(apartment.getApartmentLocation().getAddress());
        String tempForApartment;
        if (tempForAddressDetaileNum==-1) {
            //TODO: calc longitude, latitude, neighborhood
            tempForAddressDetaileNum = addAddressDetailsRecord(
                    apartment.getApartmentLocation().getAddress().getStreet(),
                    apartment.getApartmentLocation().getAddress().getNumber() + "",
                    apartment.getApartmentLocation().getDistanceFromUniversity(),
                    1, 47.0, 47.0);
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

    public void updateApartmentIDInPostRecord(String postID, String apartmentID){
        String sql= "UPDATE Posts SET apartmentID= ? "
                + "WHERE postID= ?";
        try{
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, apartmentID);
            pstmt.setString(2, postID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            //System.out.println(e.getMessage())
        }
    }

    public void updateApartmentDerivatives(Apartment apartment,String apartmentID){

        //AddressDetailsRecord will not change
        updateApartmentRecord(apartment, apartmentID);
        Set<Contact> contacts= apartment.getContacts();
        for(Contact eachContact: contacts){
            addContactsRecord(eachContact.getPhone(),eachContact.getName());
            addApartmentContactsRecord(apartmentID,eachContact.getPhone());
        }
    }

    public static void main(String[] args)
    {
        DataBaseConnection a=new DataBaseConnection();
        a.connect();
        a.resetAllTables();
//        a.addApartmentRecord("3",4,4,90,1000,1,1,0,0,0,0,0,0,3);
//        a.addAddressDetailsRecord("בן ארי","34",12,2,12,12);

//        a.resetSearchRecordsTable();
//        a.addPost("1",null, "maayan", "דירה שחבל להפסיד", "1");
//        a.addPost("2",null, "nof", "דירה מהממת", "2");
//        a.addPost("3",null, "nof2", "דירה מהממת", "2");
//        a.addPost("4",null, "nof3", "דירה מהממת", "4");
//        a.addPost("4",null, "mani", "דירה מדהימה", "5");
       // a.addSearchRecord("n","10","1000","3","90","מלא");
        //a.resetAllTables();

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
        a.disConnect();
    }
}
