package il.ac.bgu.finalproject.server.AcceptanceTests;


import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Apartment;
import il.ac.bgu.finalproject.server.Domain.NLPHandlers.NLPImp;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class newPostFromAdminTests {
    private static ServiceConnector serviceConnector;

    @BeforeClass
    public static void setup() {
        serviceConnector= new ServiceConnector();
        serviceConnector.getBridge().connectToTestDB();
    }

    @Test
    public void newPostFromAdmin(){
//        serviceConnector.getBridge().newPostFromAdmin("bupr","דירת גן ! ! ! שלושה חדרים (שני חדרי שינה וסלון) ביואל השופט 6. חצר ענקית מגודרת. מטבח ענק! שירותים ומקלחת משופצים! 70 מ\"ר. איזור סטודנטיאלי. הרחוב הכי יפה בשכונה ד'. כניסה בספטמבר. 2500 ש\"ח.\n" +
//                "בבקשה להתקשר לבעלת הדירה לתיאום. \n" +
//                "052-4770573");

//        String s="{\"publisherName\":\"shavit_The_King\",\"messege\":\"להשכרה דירת 3 חדרים 51 מר משופצת ומקסימה!! אידיאלית לזוג או לשני שותפים. 1800 שח בחודש!!דוד המלך 5 קומה רביעית צמוד למרכז גילת יש סופר, מאפייה, ירקן, בקיצור הכל.מרוהטת קומפלט!!!סלון חדש!מזגנים בכל החדרים, מקרר, תנור, כיריים, מכונת כביסה, שתי מיטות שתי מזרונים, דוד שמש דוד חשמל ועוד.8 דקות הליכה מהאוניברסיטה.כניסה מיידי, יש גם אופציה להשכיר רק לכמה חודשים.ליצירת קשר: אמיר 0547669719\"}";
//        NewPostDTO newPostDTO = NewPostDTO.fromJSON(s);
//        int ans = serviceConnector.getBridge().newPostFromAdmin(newPostDTO.getPublisherName(), newPostDTO.getMessege());
//        Gson gson = new Gson();
//        String json = gson.toJson(ans);
//        System.out.println( json);

        NLPImp nlpImp= new NLPImp();
        Apartment apartment= nlpImp.extractApartment("להשכרה דירת 3 חדרים 51 מר משופצת ומקסימה!! אידיאלית לזוג או לשני שותפים. 1800 שח בחודש!!דוד המלך 5 קומה רביעית צמוד למרכז גילת יש סופר, מאפייה, ירקן, בקיצור הכל.מרוהטת קומפלט!!!סלון חדש!מזגנים בכל החדרים, מקרר, תנור, כיריים, מכונת כביסה, שתי מיטות שתי מזרונים, דוד שמש דוד חשמל ועוד.8 דקות הליכה מהאוניברסיטה.כניסה מיידי, יש גם אופציה להשכיר רק לכמה חודשים.ליצירת קשר: אמיר 0547669719");
        int x=1;

    }

    @AfterClass
    public static void endup() {
        serviceConnector.getBridge().disconnectToTestDB();
    }

}
