package il.ac.bgu.finalproject.server.Domain.NLPHandlers;

import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class AnalyzerTest {

    private static EnvList env,env2;
    private static AnalyzedDS ds,ds2;
    private static Analyzer ana;
    private static String notToIncludeRegex = "([!,~@#$%-:״^&*\\)]|\\d)";
    private static List<String> firstNamesList;
    private static List<String> streetsList;
    private static List<String> neighborhoodList;
    String notToIncludeStreetRegex = "[*!@#'$%^&)]";


    //@Test
    @BeforeClass
    public static void setup(){
        env = new EnvList("להשכרה דירה מהממת ברחוב ברנפלד 13, בעל דירה מדהים! הדירה מרוהטת קומפלט, 4 חדרים, כולל מטבח מאובזר, רק 900 שח בחודש! כדאי מאוד! לפרטים, נופר- 053-3391800, לתיאום לראות את הדירה: נועה- 053-3311010, אגב דירה ממש שווה- 10 דקות מהאוניברסיטה ו100 מ\"ר");
        ds = new AnalyzedDS(env);
        env2 = new EnvList("להשכרה דירה מהממת ברחוב ברנפלד 13, בעל דירה מדהים! הדירה מרוהטת קומפלט, 4 חדרים, כולל מטבח מאובזר, רק 900 שח בחודש! כדאי מאוד! לפרטים, נופר- 053-3391800, לתיאום לראות את הדירה: נועה- 053-3311010, אגב דירה ממש שווה- 10 דקות מהאוניברסיטה ו100 מ\"ר שכונה ג");
        ds2 = new AnalyzedDS(env2);

        ana = new Analyzer(ds);
        firstNamesList=ana.loadFile("firstNames.txt");
        streetsList= ana.loadFile("streets1.txt");
        neighborhoodList = ana.loadFile("neighborhood.txt");
    }

    @Test
    public void extractFirstName() {
        ana.extractFirstName(firstNamesList,notToIncludeRegex);
        assertTrue(ds.GetResultsByClassifyAndIndex(Classify.NAME,5).contains("נופר"));
        assertTrue(ds.GetResultsByClassify(Classify.NAME).contains("נועה"));
    }

    @Test
    public void fullName() {
        assertEquals( "אבן ארי מיכאל",Analyzer.fullName("*אבן ארי", streetsList));
        assertEquals( "רייק חביבה",Analyzer.fullName("*רייק", streetsList));
    }

    @Test
    public void isNumeric() {
        assertTrue(Analyzer.isNumeric("-5"));
        assertTrue(Analyzer.isNumeric("-1.1"));
        assertTrue(Analyzer.isNumeric("+0.2"));
        assertTrue(Analyzer.isNumeric("+45"));
        assertTrue(Analyzer.isNumeric("373"));
        assertTrue(Analyzer.isNumeric("37.6"));

        assertFalse(Analyzer.isNumeric(" 5"));
        assertFalse(Analyzer.isNumeric(" -5"));
        assertFalse(Analyzer.isNumeric(" +-5"));
        assertFalse(Analyzer.isNumeric(" 5חדש"));
        assertFalse(Analyzer.isNumeric("5$"));
        assertFalse(Analyzer.isNumeric("-+"));
        assertFalse(Analyzer.isNumeric("-"));

    }

    @Test
    public void extractAddress() {
        assertEquals(ds.GetResultsByClassifyAndIndex(Classify.STREET,0).get(0),"ברנפלד שמעון");
        assertEquals(ds.GetResultsByClassify(Classify.NEIGHBORHOOD).isEmpty(),true);
        assertEquals(ds2.GetResultsByClassify(Classify.NEIGHBORHOOD).get(ds2.GetResultsByClassify(Classify.NEIGHBORHOOD).size()-1),"שכונה ג'");
    }

    @Test
    public void extractWord() {
    }

    @Test
    public void extractPhoneNumber() {
        ds.Remove(Classify.PHONE,5,"0533391800");
        ds.Remove(Classify.PHONE,6,"0533311010");
        List<String> pl= new ArrayList<String>();
        assertEquals(pl,ds.GetResultsByClassify(Classify.PHONE));
        ana.extractPhoneNumber();
        pl.add("0533391800");
        pl.add("0533311010");
        assertEquals(pl,ds.GetResultsByClassify(Classify.PHONE));
    }

    @Test
    public void extractGapNumber() {;
        assertTrue(ds.GetResultsByClassify(Classify.SIZE_APARTMENT).contains("100"));
        ds.Remove(Classify.SIZE_APARTMENT,7,"100");
        assertFalse(ds.GetResultsByClassify(Classify.SIZE_APARTMENT).contains("100"));
        ana.extractGapNumber(Classify.SIZE_APARTMENT,30,130);
        assertTrue(ds.GetResultsByClassify(Classify.SIZE_APARTMENT).contains("100"));
    }

    @Test
    public void NumberOfGapAccourence() {
        assertEquals(1,ana.NumberOfGapAccourence(30,130));
        assertEquals(5,ana.NumberOfGapAccourence(3,1000));
        assertEquals(4,ana.NumberOfGapAccourence(1,100));
        assertEquals(3,ana.NumberOfGapAccourence(1,99));
    }

}