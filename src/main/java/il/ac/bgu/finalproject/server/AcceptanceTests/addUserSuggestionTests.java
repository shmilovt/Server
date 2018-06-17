package il.ac.bgu.finalproject.server.AcceptanceTests;


import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Apartment;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils.ResultRecord;
import il.ac.bgu.finalproject.server.Domain.Exceptions.DataBaseFailedException;
import il.ac.bgu.finalproject.server.Domain.NLPHandlers.NLPImp;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class addUserSuggestionTests {
    private static ServiceConnector serviceConnector;


    @BeforeClass
    public static void setup() {
        serviceConnector= new ServiceConnector();
        serviceConnector.getBridge().connectToTestDB();
    }

    @Test
    public void addUserSuggestionTestsCost(){
        serviceConnector.getBridge().suggestionChangesApartmentInt("12","cost",1000);
        ResultRecord resultRecord= serviceConnector.getBridge().ResultRecordFromDB("12");
        Assert.assertEquals(1000,resultRecord.getCost());
        int count=-1;
        while (count>5) {
            try {
                count = serviceConnector.getBridge().addUserSuggestion("12", "cost", "1100");
            } catch (DataBaseFailedException e) {
                e.printStackTrace();
            }
        }
        serviceConnector.getBridge().suggestionChangesApartmentInt("12", "cost", 1100);
        resultRecord= serviceConnector.getBridge().ResultRecordFromDB("12");
        Assert.assertEquals(1100,resultRecord.getCost());
    }

    @Test
    public void addUserSuggestionTestsSize(){
        serviceConnector.getBridge().suggestionChangesApartmentDouble("12","size",70);
        ResultRecord resultRecord= serviceConnector.getBridge().ResultRecordFromDB("12");
        Assert.assertEquals(resultRecord.getSize(),70);
        int count=-1;
        while (count>5) {
            try {
                count = serviceConnector.getBridge().addUserSuggestion("12", "size", "80");
            } catch (DataBaseFailedException e) {
                e.printStackTrace();
            }
        }
        serviceConnector.getBridge().suggestionChangesApartmentDouble("12", "size", 80);
        resultRecord= serviceConnector.getBridge().ResultRecordFromDB("12");
        Assert.assertEquals(resultRecord.getSize(),80);
    }

    @Test
    public void addUserSuggestionTestsNeighborhood(){
        try {
            serviceConnector.getBridge().addressFieldCase("1",false,false,true,"השושנים",70,"השושנים");
        } catch (DataBaseFailedException e) {
            e.printStackTrace();
        }
        ResultRecord resultRecord= serviceConnector.getBridge().ResultRecordFromDB("1");
        Assert.assertEquals(resultRecord.getNeighborhood(),"השושנים");
        serviceConnector.getBridge().newPostFromAdmin("publisher","דירת 2.5 חדרים קרקע בבר גיורא 21 בד'. \n" +
                "מחיר סופר הוגן- שימו לב כי תעלם מהר⏳\n" +
                "✅ 2100 לחודש.\n" +
                "✅חדשה ונדירה! מתאימה לזוג סטודנטים או יחיד/ה. לא לשותפים.\n" +
                "✅מרווחת (45 מ\"ר) עם כניסה נפרדת (!) מגינה גדולה פרטית כולל שער ננעל. \n" +
                "✅שני צעדים מכיכר האבות. 600 מטר מהאוניברסיטה. משופצת מלא כולל אינסטלציה וחשמל. איזור מלא סטודנטים.\n" +
                "✅חדר שינה ענק, סלון ומטבח בחלל אחד גדול וכיפי וחדר עבודה חמוד.\n" +
                "כולל: שני מזגנים, פלדלת, שולחן כתיבה, תנור, מקרר חדש, כיריים, שולחן בר ,תאורה לגינה, דודש, סורגים ורשתות על כל החלונות, הכנה ומקום למכונת כביסה. בחדר שינה: פרקט פורצלן, מיטה זוגית, מזרון, שידות וארון חדש לבן 6 דלתות.\n" +
                "\uD83C\uDFCB️\u200D♂️ארנונה 70 לחודש. אין ועד בית ואתם לא רואים תחדר מדרגות או תשכנים אם לא בא לכם. \n" +
                "\uD83C\uDFCB️\u200D♀️*לא* יחידת דיור ולא דירה מחולקת לכן המחיר לא כולל חשמל או מים.\n" +
                "\uD83C\uDFCB️\u200D♂️מתפנה 1 ספטמבר 2018. חוזה רגיל לשנה עד 31.8.19 . \n" +
                "⌛אפשר לבוא לראות- לתאם בפרטי או בוואטסאפ: קרן 054-7774292");
    }

    @Test
    public void addressFieldCaseAddress(){
        try {
            serviceConnector.getBridge().addressFieldCase("12",true,true,false,"אברהם אבינו",26,"שכונה ג'");
        } catch (DataBaseFailedException e) {
            e.printStackTrace();
        }
        ResultRecord resultRecord= serviceConnector.getBridge().ResultRecordFromDB("12");
        Assert.assertEquals(resultRecord.getStreet(),"אברהם אבינו");
        Assert.assertEquals(resultRecord.getNumber(),26);
    }

    @AfterClass
    public static void endup() {
        serviceConnector.getBridge().disconnectToTestDB();
    }

}
