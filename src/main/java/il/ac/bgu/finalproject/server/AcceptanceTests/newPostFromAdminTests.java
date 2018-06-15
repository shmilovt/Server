package il.ac.bgu.finalproject.server.AcceptanceTests;


import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Apartment;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils.ResultRecord;
import il.ac.bgu.finalproject.server.Domain.NLPHandlers.NLPImp;
import org.testng.Assert;
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
    public void newGoodPostFromAdmin(){
        int x= serviceConnector.getBridge().newPostFromAdmin("publisher","דירת 2.5 חדרים קרקע בבר גיורא 21 בד'. \n" +
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
        Assert.assertTrue(x!=-1);
        ResultRecord resultRecord= serviceConnector.getBridge().ResultRecordFromDB(""+x);
        Assert.assertEquals(resultRecord.getCost(),2100);
        Assert.assertEquals(resultRecord.getNumber(),21);
        Assert.assertEquals(resultRecord.getStreet(),"שמעון בר גיורא");
        Assert.assertEquals(resultRecord.getSize(),45);
        Assert.assertEquals(resultRecord.getFurniture(),1);
        Assert.assertEquals(resultRecord.getNeighborhood(),"שכונה ד'");
    }

    @Test
    public void newPostFromAdminPhoneIsMissing() {
        int x = serviceConnector.getBridge().newPostFromAdmin("publisher", "דירת 2.5 חדרים קרקע בבר גיורא 21 בד'. \n" +
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
                "⌛אפשר לבוא לראות- לתאם בפרטי או בוואטסאפ");
        Assert.assertEquals(x ,-2);
    }

    @Test
    public void newPostFromAdminFullAddressIsMissing() {
        int x = serviceConnector.getBridge().newPostFromAdmin("publisher", "דירת 2.5 חדרים קרקע בבר גיורא בד'. \n" +
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
        Assert.assertEquals(x ,-3);
    }

    @AfterClass
    public static void endup() {
        serviceConnector.getBridge().disconnectToTestDB();
    }

}
