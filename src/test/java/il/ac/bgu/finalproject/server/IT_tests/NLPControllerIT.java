package il.ac.bgu.finalproject.server.IT_tests;

import il.ac.bgu.finalproject.server.Domain.Controllers.NLPController;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Apartment;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Post;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class NLPControllerIT {
    public NLPController nlpC = new NLPController();

    @BeforeClass
    public static void setup(){
    }


    @Test
    public void generatedNLPWithAddressTest() {
    Apartment ap = nlpC.generateNLP(new Post("dasdas1231231", new Date(), "shavit", "חדר אחרון(!)  בדירת 4 חדרים ברגר 150 1000 לחדר\n***חודש אוקטובר חינם***\nבדירה סטודנט וסטודנטית. מחפשים את השלישי/ת שישלימו את הדירה.\nבדירה מכונת כביסה, סלון ומרפסת מפנקת, במיקום הכי שווה בב\"ש!!!\nבכל חדרי השינה ובסלון יש מזגנים חדשים, החדר מאובזר בצורה מלאה. כולל מיטה ומזרון חדשים!\nמקרר וכיריים חדשים בדירה\nהדירה על רחוב גדול ומרכזי  צמודה למרכז (מגה, מאפיה ירקן, בנק ומזון מהיר). כל חדרי השינה עורפיים.\nבמיקום הכי טוב בעיר 7 דקות הליכה לאוניברסיטה ו3 דקות הליכה לאזור המסעדות של רינגבלום.\nתאריך כניסה גמיש\nאין אפשרות להכניס בע\"ח לדירה. מעשנים במרפסת בלבד.\nהדירה מרווחת (78 מ\"ר) בקומה שלישית.\nמועד הכניסה גמיש.\nפרטים נוספים בהודעה פרטית או בטלפון.\nנדב - 054-3134989\nניתן לתאם לראות את הדירה ישירות עם אחד הדיירים\nניב - 050-6899821\n", ""));
    assertEquals(31.2692231, ap.getApartmentLocation().getLatitude(),0);
    assertEquals(34.7983045, ap.getApartmentLocation().getLongitude(),0);
    assertEquals("שדרות יצחק רגר", ap.getApartmentLocation().getAddress().getStreet());
    assertEquals(150, ap.getApartmentLocation().getAddress().getNumber(),0);
    assertEquals("שכונה א'", ap.getApartmentLocation().getNeighborhood());
    assertEquals(3, ap.getApartmentLocation().getFloor(),0);
    assertEquals(16, ap.getApartmentLocation().getDistanceFromUniversity(),0);
    }

    @Test
    public void generatedNLPWithoutAddressTest() {
        Apartment ap = nlpC.generateNLP(new Post("dasdas1231231", new Date(), "shavit", "חדר אחרון(!)  בדירת 4 חדרים  1000 לחדר\n***חודש אוקטובר חינם***\nבדירה סטודנט וסטודנטית. מחפשים את השלישי/ת שישלימו את הדירה.\nבדירה מכונת כביסה, סלון ומרפסת מפנקת, במיקום הכי שווה בב\"ש!!!\nבכל חדרי השינה ובסלון יש מזגנים חדשים, החדר מאובזר בצורה מלאה. כולל מיטה ומזרון חדשים!\nמקרר וכיריים חדשים בדירה\nהדירה על רחוב גדול ומרכזי  צמודה למרכז אורן(מגה, מאפיה ירקן, בנק ומזון מהיר). כל חדרי השינה עורפיים.\nבמיקום הכי טוב בעיר 7 דקות הליכה לאוניברסיטה ו3 דקות הליכה לאזור המסעדות של רינגבלום.\nתאריך כניסה גמיש\nאין אפשרות להכניס בע\"ח לדירה. מעשנים במרפסת בלבד.\nהדירה מרווחת (78 מ\"ר) בקומה שלישית.\nמועד הכניסה גמיש.\nפרטים נוספים בהודעה פרטית או בטלפון.\nנדב - 054-3134989\nניתן לתאם לראות את הדירה ישירות עם אחד הדיירים\nניב - 050-6899821\n", ""));
        assertEquals("",ap.getApartmentLocation().getAddress().getStreet());
        assertEquals(-1,ap.getApartmentLocation().getLatitude(),0);
        assertEquals(-1,ap.getApartmentLocation().getLongitude(),0);
        assertEquals(-1,ap.getApartmentLocation().getDistanceFromUniversity(),0);
    }

    @AfterClass
    public static void endup(){
    }

}