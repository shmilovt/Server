package il.ac.bgu.finalproject.server.IT_tests;

import il.ac.bgu.finalproject.server.Domain.Controllers.ServerController;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.ApartmentLocation;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Post;
import il.ac.bgu.finalproject.server.PersistenceLayer.DataBaseConnection;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ServerControllerIT {
    private static DataBaseConnection dbc= new DataBaseConnection();
    private ServerController serverC = new ServerController();
    @BeforeClass
    public static void setup(){
        dbc.connect();
        //dbc.resetAllTables();
    }

    @Test
    public void addNewPostTest() {
        serverC.newPost(new Post("dasdas1231231", new Date(), "shavit", "חדר אחרון(!)  בדירת 4 חדרים ברגר 150 1000 לחדר\n***חודש אוקטובר חינם***\nבדירה סטודנט וסטודנטית. מחפשים את השלישי/ת שישלימו את הדירה.\nבדירה מכונת כביסה, סלון ומרפסת מפנקת, במיקום הכי שווה בב\"ש!!!\nבכל חדרי השינה ובסלון יש מזגנים חדשים, החדר מאובזר בצורה מלאה. כולל מיטה ומזרון חדשים!\nמקרר וכיריים חדשים בדירה\nהדירה על רחוב גדול ומרכזי  צמודה למרכז (מגה, מאפיה ירקן, בנק ומזון מהיר). כל חדרי השינה עורפיים.\nבמיקום הכי טוב בעיר 7 דקות הליכה לאוניברסיטה ו3 דקות הליכה לאזור המסעדות של רינגבלום.\nתאריך כניסה גמיש\nאין אפשרות להכניס בע\"ח לדירה. מעשנים במרפסת בלבד.\nהדירה מרווחת (78 מ\"ר) בקומה שלישית.\nמועד הכניסה גמיש.\nפרטים נוספים בהודעה פרטית או בטלפון.\nנדב - 054-3134989\nניתן לתאם לראות את הדירה ישירות עם אחד הדיירים\nניב - 050-6899821\n", "123"));
        Post p = serverC.getPost("dasdas1231231");
        int x=2;//ApartmentLocation al = dbc.getAddressDetils("dasdas1231231");
    }

    @AfterClass
    public static void endup(){
        dbc.disConnect();
    }


}