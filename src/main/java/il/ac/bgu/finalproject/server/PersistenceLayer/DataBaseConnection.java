package il.ac.bgu.finalproject.server.PersistenceLayer;

import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Post;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DataBaseConnection {

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
        String sql = "UPDATE posts SET dateOfPublish= ? , "
                + "publisherName= ? "
                + "postText= ? "
                + "apartmentID= ? "
                //postID, dateOfPublish, publisherName, " +
                //                        "postText, apartmentID
                + "WHERE postID= ?";
        try{
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, date);
            pstmt.setString(2, publisherName);
            pstmt.setString(3, message);
            pstmt.setString(4, apartmentID);
            pstmt.setString(5, id);

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
    public Post getPost(String id) {
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
    public int addApartmentRecord(int apartmentID, int numOfRooms, int floor,
                                  double size, int cost, int addressDetailsID) {
        try {
            String sql = "INSERT INTO apartment(apartmentID, numOfRooms, floor, size, cost, addressDetailsID)"+
                    " VALUES(?,?,?,?,?,?)";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, apartmentID);
            pstmt.setInt(2, numOfRooms);
            pstmt.setInt(3, floor);
            pstmt.setDouble(4, size);
            pstmt.setInt(5, cost);
            pstmt.setInt(6, addressDetailsID);

            pstmt.executeUpdate();
            //System.out.println("Added");
        } catch (SQLException e) {
            return -1;
            //System.out.println(e.getMessage());
        }
        return apartmentID;
    }

    public static void main(String[] args)
    {
        DataBaseConnection a=new DataBaseConnection();
        a.connect();

        //Post po= new Post("4",null, "nof", "דירה מהממת", "5");
        a.addPost("4",null, "nof", "דירה מהממת", "5");
        a.update("4",null, "may", "דירה מהממת", "5");
        System.out.println(a.GetAllPostsId().get(0));

        a.addAddressDetailsRecord(
                "בן חיים",
                "3",
                10,
                1, 47.0, 47.0);
    }
}

