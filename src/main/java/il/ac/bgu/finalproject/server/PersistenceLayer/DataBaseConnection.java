package il.ac.bgu.finalproject.server.PersistenceLayer;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Apartment;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Post;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils.CostCategory;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

public class DataBaseConnection implements DataBaseConnectionInterface {

    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    private static Connection conn = null;
    private  int apartmentId=-1;
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

    private int apartmentIdCreator(){
        apartmentId=apartmentId+1;
        return apartmentId;
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
                "  apartmentID int PRIMARY KEY,\n" +
                "  numOfRooms int,\n" +
                "  floor int,\n" +
                "  size double,\n" +
                "  cost int NOT NULL ,\n" +
                "  addressDetailsID int,\n" +
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
                "  apartmentID int NOT NULL,\n" +
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

    public void addPostO(String id, String date, String message) {
        try {
            String sql = "INSERT INTO Facebook(id,date,message) VALUES(?,?,?)";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, date);
            pstmt.setString(3, message);
            pstmt.executeUpdate();
            //System.out.println("Added");
        } catch (SQLException e) {
            //System.out.println(e.getMessage());
        }
    }


    public void updateO(String id, String date, String message) {
        String sql = "UPDATE Facebook SET date = ? , "
                + "message= ? "
                + "WHERE id = ?";
        try{
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, date);
            pstmt.setString(2, message);
            pstmt.setString(3, id);

            // update
            pstmt.executeUpdate();
            // System.out.println("update done");
        } catch (SQLException e) {
            //System.out.println(e.getMessage());
        }
    }

    /***
     *
     * @param id
     * @return null if post isn't exist , List<string> if exist
     */
    public List<String> getPostO(String id) {
        try {
            List<String> post = new LinkedList<String>();
            String sql = "SELECT * FROM Facebook where id =" + "'" + id + "'";
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            post.add(rs.getString(1));
            post.add(rs.getString(2));
            post.add(rs.getString(3));
            //System.out.println(rs.getString(3));
            return post;
        } catch (SQLException e) {
            //System.out.println(e.getMessage());
            return null;
        }
    }

    public void deletePostO(String id) {
        //  System.out.println("Hello deletePost");
        String sql = "DELETE FROM facebook WHERE id = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        }
        catch(SQLException e){
            // System.out.println("Hello deletePost failed");

        }
    }

    /***
     * @return null if there is no posts in DB or List<String> of posts else
     */
    public List<String> GetAllPostsIdO() {
        String sql = "SELECT id FROM facebook";
        List<String> post = new LinkedList<String>();
        try {
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            while (rs.next())
                post.add(rs.getString(1));
            return post;
        }
        catch(SQLException e){
            return null;
        }
    }

    /**
     * @param //args the command line arguments
     */

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

    /***
     *
     * @param id
     * @return null if post isn't exist , List<string> if exist
     */
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

    /***
     * @return null if there is no posts in DB or List<String> of posts else
     */
    public List<String> GetAllPostsId() {
        String sql = "SELECT postID FROM posts";
        List<String> posts = new LinkedList<String>();
        //Post tempPost= new Post("");
        try {
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            while (rs.next())
                //tempPost= new Post(rs.getString(1),rs.getDate(2),
                //        rs.getString(3),rs.getString(4),
                //        rs.getString(5));
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
            String sql = "INSERT INTO addressDetails(street, numOfBuilding, timeFromUni, "+
                    "neighborhood, longitude, latitude, addressDetailsNum)"+
                    " VALUES(?,?,?,?,?,?,?)";


            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, street);
            pstmt.setString(2, numOfBuilding);
            pstmt.setDouble(3, timeFromUni);
            pstmt.setInt(4, neighborhood);
            pstmt.setDouble(5, longitude);
            pstmt.setDouble(6, latitude);
            t=apartmentId+1;
            pstmt.setInt(7, t);
            System.out.println("naaaa1111");
            pstmt.executeUpdate();
            //System.out.println("Added");
        } catch (SQLException e) {
            System.out.println("naaaa");
            return -1;
            //System.out.println(e.getMessage());
        }
        return apartmentIdCreator();
    }


    public void updateAddressDetailsRecordaaaa(String street, String numOfBuilding, double timeFromUni, int neighborhood, double longitude, double latitude, int addressDetailsNum) {
        //we need to compare if
        String sql = "UPDATE posts SET dateOfPublish= ? , "
                + "publisherName= ? "
                + "postText= ? "
                + "apartmentID= ? "
                //String street, String numOfBuilding, double timeFromUni, int neighborhood,
                // double longitude, double latitude, int addressDetailsNum
                + "WHERE postID= ?";
        try{
            PreparedStatement pstmt = conn.prepareStatement(sql);

//            pstmt.setString(1, date);
//            pstmt.setString(2, publisherName);
//            pstmt.setString(3, message);
//            pstmt.setString(4, apartmentID);
//            pstmt.setString(5, id);

            // update
            pstmt.executeUpdate();
            // System.out.println("update done");
        } catch (SQLException e) {
            //System.out.println(e.getMessage());
        }
    }

    /***
     *
     * @param id
     * @return null if post isn't exist , List<string> if exist
     */
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
            String sql = "INSERT INTO contacts(phone, name)"+
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
    public void addApartmentContactsRecord(int apartmentID , String phoneNumber) {
        try {
            String sql = "INSERT INTO apartmentContacts(apartmentID, phoneNumber)"+
                    " VALUES(?,?)";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, apartmentID);
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
                                 int size, int cost, int addressDetailsID) {
        try {
            String sql = "INSERT INTO apartment(apartmentID, numOfRooms, floor, size, cost, addressDetailsID)"+
                    " VALUES(?,?,?,?,?,?)";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, apartmentID);
            pstmt.setInt(2, numOfRooms);
            pstmt.setInt(3, floor);
            pstmt.setDouble(4, size);
            pstmt.setInt(5, cost);
            pstmt.setInt(6, addressDetailsID);

            pstmt.executeUpdate();
            //System.out.println("Added");
        } catch (SQLException e) {
            return e.toString();
            //System.out.println(e.getMessage());
        }
        return apartmentID;
    }

    public Apartment getApartmentRecordTBD(String id) {
        try {
            String sql = "SELECT * FROM apartment where apartmentID =" + "'" + id + "'";
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);


            Apartment apartment= new Apartment();
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

    public List<Apartment> SearchQuery (List<CostCategory> catagories){
        List<Apartment> apartments = new LinkedList<Apartment>();
        Apartment temp= new Apartment();
        try {
            String sql = "SELECT Apartment.size, Apartment.cost "
                    + " FROM Apartment"
                    + " JOIN AddressDetails Detail ON Apartment.addressDetailsID = Detail.addressDetailsNum"
                    + " JOIN ApartmentContacts C2 ON Apartment.apartmentID = C2.apartmentID"
                    + " JOIN Contacts C3 ON C2.phoneNumber = C3.phone "
// TBD
//                    + prepareCoditions(catagories)
                    + " ORDER BY Apartment.cost, Apartment.size DESC ";

            Statement stmt = conn.createStatement();
            ResultSet rs= stmt.executeQuery(sql);
            while (rs.next()) {
// TBD: set for all in Apartment OR contructor.... TBDAPARTMENT
                temp.setSize(rs.getInt(1));
                temp.setCost(rs.getInt(2));
                System.out.println(temp.toString());
                apartments.add(temp);
            }

        } catch (SQLException e) { }
        return apartments;
    }

    @Override
    public void updateApartment(Apartment apartment, String post) {

    }

    @Override
    public boolean isApartmentExist(Apartment apartment) {
        return false;
    }

    public static void main(String[] args)
    {
        DataBaseConnection a=new DataBaseConnection();
        a.connect();
        a.resetSearchRecordsTable();
        a.addPost("1",null, "maayan", "דירה שחבל להפסיד", "1");
        a.addPost("2",null, "nof", "דירה מהממת", "2");
        a.addPost("3",null, "nof2", "דירה מהממת", "2");
        a.addPost("4",null, "nof3", "דירה מהממת", "4");
        a.addPost("4",null, "mani", "דירה מדהימה", "5");
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
