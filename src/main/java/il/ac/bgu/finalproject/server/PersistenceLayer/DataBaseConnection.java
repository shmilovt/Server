package il.ac.bgu.finalproject.server.PersistenceLayer;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DataBaseConnection {

    private Connection conn = null;

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
            System.out.println(ex.getMessage());
        }
    }

    public void addPost(String id, String date, String message) {
        try {
            String sql = "INSERT INTO Facebook(id,date,message) VALUES(?,?,?)";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "123123123");
            pstmt.setString(2, "3123123123");
            pstmt.setString(3, "DASDASDAS");
            pstmt.executeUpdate();
            //System.out.println("Added");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void update(String id, String date, String message) {
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
        System.out.println("update done");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /***
     *
     * @param id
     * @return null if post isn't exist , List<string> if exist
     */
    public List<String> getPost(String id) {
        try {
            List<String> post = new LinkedList<String>();
            String sql = "SELECT * FROM Facebook where id =" +id;
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            post.add(rs.getString(1));
            post.add(rs.getString(2));
            post.add(rs.getString(3));
            System.out.println(rs.getString(3));

            return post;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void deletePost(String id) {
        String sql = "DELETE FROM facebook WHERE id = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        }
        catch(SQLException e){
        }
    }

        /**
         * @param args the command line arguments
         */
    public static void main(String[] args) {
        DataBaseConnection a=new DataBaseConnection();
        a.connect();
        //a.getPost("12313123");
        //a.update("123123123","ttttt","this is this is");
        //a.deletePost("123123123");
        //a.getPost("123123123");
       // a.disConnect();
    }
}

