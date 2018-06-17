package il.ac.bgu.finalproject.server.Domain.NLPHandlers;

import il.ac.bgu.finalproject.server.Domain.Controllers.MyLogger;
import il.ac.bgu.finalproject.server.Domain.Exceptions.DataBaseFailedException;

import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

public class DataBaseNlp {

    private static Connection conn = null;

    public void connect()  {
        String url = "jdbc:sqlite:Dictionary.db";
        try {
            if(conn==null)
                conn = DriverManager.getConnection(url);
        }
        catch (Exception e){
            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
        }
    }
    public void disConnect()  {
        try {
            if (conn != null) {
                conn.close();
                conn=null;
            }
        }
        catch (Exception e){
            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
        }
    }

    public void addValuesOneCol(String tableName, String value) {
        try {
            String sql = "INSERT INTO " + tableName + " (Value)"+
                    " VALUES(?)";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, value);
            pstmt.executeUpdate();
        }
        catch (Exception e){
            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
            }
    }

    public void updateValuesNeigh_Street(String n,String s) {
        try {
            String sql = "UPDATE neigh_street SET Street= ? "+
                    " WHERE Neighborhood= ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, n);
            pstmt.setString(2, s);
            pstmt.executeUpdate();
        }
        catch (Exception e){
            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
        }
    }


    public void addValuesNeigh_Street(String n,String s) {
        try {
            String sql = "INSERT INTO neigh_street (Neighborhood, Street)"+
                    " VALUES(?,?)";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, n);
            pstmt.setString(2, s);
            pstmt.executeUpdate();
        }
        catch (Exception e){
            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
        }
    }


    public Map<String, String> getValuesNeigh_Street() {
        Map<String, String> street_neighborhood_dic  = new HashMap<String, String>();
        try {
            String sql = "SELECT * FROM  neigh_street";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next())
                street_neighborhood_dic.put(rs.getString(1),rs.getString(2));
        }
        catch (Exception e){
            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
            return null;
        }
        return street_neighborhood_dic;
    }


    public List<String> getValuesOneCol(String tableName) {
        List<String> valueList = new LinkedList<String>();
        try {
            String sql = "SELECT * FROM " + tableName;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next())
                valueList.add(rs.getString(1));
        }
        catch (Exception e){
            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
            return null;
        }
        return valueList;
    }


}



