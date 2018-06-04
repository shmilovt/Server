package il.ac.bgu.finalproject.server.ServiceLayer;

import il.ac.bgu.finalproject.server.Domain.Controllers.AdminClientController;
import il.ac.bgu.finalproject.server.Domain.Controllers.DataBaseRequestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ServiceTest {

    @Autowired
    private IService service= new Service();
//    @Autowired
//    private AdminClientController adminClientController= new AdminClientController();
//    @Autowired
//    private DataBaseRequestController dataBaseRequestController= new DataBaseRequestController();

    @Test
    public void testSearchApartments() throws Exception {
    }

    @Test
    public void testAddSearchRecord() throws Exception {
    }

    @Test
    public void testLogin() throws Exception {
    }

    @Test
    public void testChangePassword() throws Exception {
    }

    @Test
    public void testNewPostFromAdmin() throws Exception {
        service.newPostFromAdmin("bupr","דירת גן ! ! ! שלושה חדרים (שני חדרי שינה וסלון) ביואל השופט 6. חצר ענקית מגודרת. מטבח ענק! שירותים ומקלחת משופצים! 70 מ\"ר. איזור סטודנטיאלי. הרחוב הכי יפה בשכונה ד'. כניסה בספטמבר. 2500 ש\"ח.\n" +
                "בבקשה להתקשר לבעלת הדירה לתיאום. \n" +
                "052-4770573");
    }

    @Test
    public void testInsertGroup() throws Exception {
    }

    @Test
    public void testDeleteGroup() throws Exception {
    }

    @Test
    public void testGetAllGroups() throws Exception {
    }

    @Test
    public void testGetAllUserSearches() throws Exception {
    }

    @Test
    public void testGetCalcCosts() throws Exception {
    }

    @Test
    public void testGetAllApartments() throws Exception {
    }

    @Test
    public void testSetNewCalculator() throws Exception {
    }

    @Test
    public void testAddUserSuggestion() throws Exception {
    }

    @Test
    public void testSuggestionChangesApartmentInt() throws Exception {
    }

    @Test
    public void testSuggestionChangesApartmentDouble() throws Exception {
    }

    @Test
    public void testSuggestionChangesAddress() throws Exception {
    }
}