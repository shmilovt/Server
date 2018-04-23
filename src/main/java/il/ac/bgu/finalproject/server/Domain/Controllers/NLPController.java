package il.ac.bgu.finalproject.server.Domain.Controllers;

import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Apartment;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Post;
import il.ac.bgu.finalproject.server.Domain.NLPHandlers.NLPImp;
import il.ac.bgu.finalproject.server.Domain.NLPHandlers.NLPInterface;

import java.util.Date;

public class NLPController {
    private NLPInterface nlp;
    private DataBaseRequestController dataBaseRequestController;

    public NLPController(){
        nlp = new NLPImp();
        dataBaseRequestController = new DataBaseRequestController();

    }




    public void generateNLP(Post post) {
        Apartment apartment = nlp.extractApartment(post.getText());
        dataBaseRequestController.manageApartment(apartment, post);
    }

    public static void main(String[] args) {
        //Post(String ID, Date dateOfPublish, String publisherName, String text, String apartmentID)
        Date date= new Date();
        Post p1=new Post("121212",date,"mikey","וילה להשכרה ברח' אברהם יפה, רמות, באר שבע. מפלס אחד, חדשה מקבלן(קבלת מפתח לפני פחות מחודש). 5 חדרים ובנוסף יחידת דיור נוספת של 60 מטר (אופציונאלית). הכל חדש, ריצוף מטר על מטר, מטבח אינטגרלי לבן זכוכית של מטבחי זיו, חדרים ענקיים.\n" +
                "כולל מדיח כלים אינטגרלי, מזגן מיני מרכזי, מזגנים עיליים בכל חדר (אינוורטר). תריסי אור חשמליים בכל הבית. 1200 שח\n" +
                "לפרטים: \n" +
                "ניר - 054-9449978 חן- 054-5802333",null);
        Post p2=new Post("121213",date,"mikey1","יחידת דיור להשכרה בהר בוקר 26, זוהי יחידת דיור קרקע הכוללת : סלון, חדר שינה, חדר ממד וגינה .\n" +
                "בדירה ישנו מיזוג גם בסלון וגם בחדר שינה , והדירה נכללת עם מיטה , ארון , פינת אוכל,סלון, מיקרוגל, תנור אובן, ועוד.\n" +
                "הדירה מעולה לזוגות , לשותפים , סטודנטים ..\n" +
                "(החדר מדד הוא מעבר דרך החדר שינה- יתאים לשותפים שהם חברים טובים או פתוחים אחד עם השני בלי קשר וכו׳)\n" +
                "20 דק הליכה מהאוניברסיטה ו7 דק בתחבורה הציבורית.\n" +
                "העלות של הדירה היא 2800 כולל מים ארנונה חשמל וכבלים!!!\n" +
                "*ישנה אפשרות לבעלי חיים\n" +
                "לפרטים :\n" +
                "שירה 0546559992",null);
        Post p3=new Post("121214",date,"mikey2","שאול המלך דירת 2 חדרים קומה קרקע כניסה מידית רק 1800\n" +
                "₪ 1,800\n" +
                "Беэр-Шева\n" +
                "0546329669 דודי נכסים",null);
        Post p4=new Post("121215",date,"mikey3","דירה 2 חדרים כ50 מ\"ר\n" +
                "מתאימה לזוג או ליחיד \n" +
                "נשאר בדירה מקרר ארון ומיטה\n" +
                "הדירה נמצאת 2 דקות מכל המסעדות ברינגנבלום ו5 דקות מהאוניברסיטה\n" +
                "(דירה לכל דבר לא יחידת דיור)\n" +
                "רחוב שמעון ברנפלד 26\n" +
                "אפשר ליצור קשר איתי ב 0524188278 \n" +
                "או קיריל 0527021623",null);
        Post p5=new Post("121216",date,"mikey4","למהירי החלטה דירת 4 חדרים להשכרה בשכונה ה המבוקשת ברחוב הצבי במיקום מרכזי במרחק הליכה מגרנד קניון, בית כנסת הכיפה, מוסדות חינוך, תחבורה ציבורית ומרכזי קניות\n" +
                "דירה מרווחת ומתוכננת היטב בבניין בן 8 קומות כולל מעלית, ללא ריהוט.\n" +
                "כניסה מיידית!!! ללא תיווך\n" +
                "שכ\"ד: 2900ש\"ח בחודש\n" +
                "וועד: 170ש\"ח בחודש\n" +
                "ארנונה:650 ש\"ח לחודשיים.\n" +
                "לפרטים נוספים ניתן ליצור קשר\n" +
                "0549902222/0537476855",null);
        Post p6=new Post("121217",date,"mikey5","***לסטודנטים ללא תיווך! ***\n" +
                "להשכרה בשכונה ג,ברחוב יד ושם 24 מגדל בית שיאים, קומה 8 עם מעלית .\n" +
                "דירת חדר שינה וסלון מרוהטת, שכונה שקטה במיקום מעולה.\n" +
                "רבע שעה הליכה לקניון הנגב והרכבת, חמש דקות הליכה למכללה למנהל, חמש דקות נסיעה לבן גוריון\n" +
                "מחיר 1850.\n" +
                "יש עלות ועד בנין על סך 250 ש\"ח\n" +
                "לפרטים וסיור בנכס:\n" +
                "בן0524446286\n" +
                "דוד 0544293989",null);
        Post p7=new Post("121218",date,"mikey5","השכרה !! מגוון דירות סביב האוניברסיטה ללא דמי תיווך, לסטודנטים ולמורים בלבד!\n" +
                "דירות ל2 3 ו4 שותפים\n" +
                "-דירות עם שירותים ומקלחת פרטיים משופצות ומאובזרות\n" +
                "-כניסות החל מ1.9 \n" +
                "-דירות שמנוהלות עי חברת\n" +
                "לדוגמא דירת 3 חדרים קרקע בשכונה ד' - 10 דק' מהאוניברסיטה\n" +
                "מתאימה לזוג שותפים\n" +
                "-בכל חדר שירותים ומקלחת לכל דייר\n" +
                "-מאובזרת קומפלט\n" +
                "מחיר - 2500 שח ללא חשבונות\n" +
                "\n" +
                "-מוזמנים לשלוח מספר טלפון בפוסט זה או לשלוח הודעה למספר 0525447662\n" +
                "כל מי שמשאיר טלפון נכנס לרשימת תפוצה שבה ישלחו מגוון דירות להשכרה לשנת הלימודים הקרובה בהתאם לצרכים שלכם.\n" +
                "נדאג למצוא לכל אחד את הדירה שתתאים לו! וכמובן סטודנטים ללא דמי תיווך!",null);
        Post p8=new Post("121219",date,"mikey","להשכרה דירת 4.5 חדרים ללא דמי תיווך\n" +
                "₪ 2,800\n" +
                "תל אביב-יפו\n" +
                "מעולה לסטודנטים. ו' הישנה. 100 מטר משער הכניסה לאוניברסיטה.מזגנים בחדרים. ריהוט חלקי. כניסה מיידית. השכירות לטווח ארוך.",null);

        NLPController nlpController= new NLPController();
//        nlpController.generateNLP(p1);
        nlpController.generateNLP(p2);
        nlpController.generateNLP(p3);
        nlpController.generateNLP(p4);
        nlpController.generateNLP(p5);
        nlpController.generateNLP(p6);
        nlpController.generateNLP(p7);
        nlpController.generateNLP(p8);
    }
}
