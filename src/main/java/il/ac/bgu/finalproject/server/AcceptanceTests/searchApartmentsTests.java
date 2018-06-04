package il.ac.bgu.finalproject.server.AcceptanceTests;


import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class searchApartmentsTests {
    private ServiceConnector serviceConnector= new ServiceConnector();


    @BeforeClass
    public static void setup() {

        //dbc.connect();
//        dbc.resetAllTables();
    }
    @Test
    public void searchByFloor(){
//        serviceConnector.getBridge().searchApartments()


    }

    @Test
    public void newPostFromAdmin(){
        serviceConnector.getBridge().newPostFromAdmin("bupr","דירת גן ! ! ! שלושה חדרים (שני חדרי שינה וסלון) ביואל השופט 6. חצר ענקית מגודרת. מטבח ענק! שירותים ומקלחת משופצים! 70 מ\"ר. איזור סטודנטיאלי. הרחוב הכי יפה בשכונה ד'. כניסה בספטמבר. 2500 ש\"ח.\n" +
                "בבקשה להתקשר לבעלת הדירה לתיאום. \n" +
                "052-4770573");


    }

    @AfterClass
    public static void endup() {
      //  dbc.changePassword("admin","123456");
//        dbc.disConnect();
    }

}
