package il.ac.bgu.finalproject.server.AcceptanceTests;


import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class searchApartmentsTests {
    private ServiceConnector serviceConnector;


    @BeforeClass
    public static void setup() {

        //dbc.connect();
//        dbc.resetAllTables();
    }
    @Test
    public void searchByFloor(){
//        serviceConnector.getBridge().searchApartments()


    }

    @AfterClass
    public static void endup() {
      //  dbc.changePassword("admin","123456");
//        dbc.disConnect();
    }

}
