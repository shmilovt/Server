package il.ac.bgu.finalproject.server.Domain.NLPHandlers;

import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class AnalyzerTest {

    private static EnvList env;
    private static AnalyzedDS ds;
    private static Analyzer ana;
    private static String notToIncludeRegex = "([!,~@#$%-:״^&*\\)]|\\d)";
    private static List<String> firstNamesList;
    private static List<String> streetsList;
    private static List<String> wordPriceList;


    //@Test
    //public void loadFile() { }
    @BeforeClass
    public static void setup(){
        env = new EnvList("להשכרה דירה מהממת ברחוב ברנפלד 13, בעל דירה מדהים! הדירה מרוהטת קומפלט, 4 חדרים, כולל מטבח מאובזר, רק 900 שח בחודש! כדאי מאוד! לפרטים, נופר- 053-3391800, לתיאום לראות את הדירה: נועה- 053-3311010, אגב דירה ממש שווה- 10 דקות מהאוניברסיטה ו100 מ\"ר");
        ds= new AnalyzedDS(env);
        ana= new Analyzer(ds);
        firstNamesList=ana.loadFile("firstNames.txt");
        streetsList= ana.loadFile("streets.txt");
        wordPriceList=ana.loadFile("price.txt");
    }

    @Test
    public void extractFirstName() {
        ana.extractFirstName(firstNamesList,notToIncludeRegex);
        assertTrue(ds.GetResultsByClassifyAndIndex(Classify.NAME,5).contains("נופר"));
        assertTrue(ds.GetResultsByClassify(Classify.NAME).contains("נועה"));
        assertFalse(ds.GetResultsByClassify(Classify.NAME).contains("נועם"));
        assertFalse(ds.GetResultsByClassify(Classify.NAME).contains(""));
        assertFalse(ds.GetResultsByClassifyAndIndex(Classify.NAME,5).contains("נועה"));
        assertFalse(ds.GetResultsByClassifyAndIndex(Classify.NAME,4).contains("נועה"));
        assertFalse(ds.GetResultsByClassifyAndIndex(Classify.NAME,3).contains("נועה"));
        assertFalse(ds.GetResultsByClassifyAndIndex(Classify.NAME,2).contains("נועה"));
        assertFalse(ds.GetResultsByClassifyAndIndex(Classify.NAME,1).contains("נועה"));
        assertFalse(ds.GetResultsByClassifyAndIndex(Classify.NAME,0).contains("נועה"));
    }

    @Test
    public void fullName() {
        assertEquals( "אוסטרובסקי גרשון",Analyzer.fullName("אוסטרובסקי", streetsList));
        assertEquals( "מיכאל אבן ארי",Analyzer.fullName("אבן ארי", streetsList));
        assertEquals( "חביבה רייק",Analyzer.fullName("חביבה", streetsList));

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
    public void extractAddressField() {
        //assertTrue(Analyzer.extractAddressField());
    }

    @Test
    public void extractAddress() {
        //ana.extractAddress(Classify.STREET,streetsList,notToIncludeRegex);
    }

    @Test
    public void extractWord() {
        //Classify.NAME&firstNamesList
        ds.Remove(Classify.NAME,5,"נופר");
        ds.Remove(Classify.NAME,6,"נועה");
        List<String> pl= new ArrayList<String>();
        assertEquals(pl,ds.GetResultsByClassify(Classify.NAME));
        ana.extractWord(Classify.NAME,firstNamesList,notToIncludeRegex);
        pl.add("נופר");
        pl.add("נועה");
        assertEquals(pl,ds.GetResultsByClassify(Classify.NAME));

        //Classify.NAME&firstNamesList
        ds.Remove(Classify.WORD_PRICE,4,"רק");
        ds.Remove(Classify.WORD_PRICE,4,"שח");
        ds.Remove(Classify.WORD_PRICE,4,"בחודש");
        pl.remove("נופר");
        pl.remove("נועה");
        assertEquals(pl,ds.GetResultsByClassify(Classify.WORD_PRICE));
        ana.extractWord(Classify.WORD_PRICE,wordPriceList,notToIncludeRegex);

        assertTrue(ds.GetResultsByClassifyAndIndex(Classify.WORD_PRICE,4).contains("רק"));
        assertTrue(ds.GetResultsByClassifyAndIndex(Classify.WORD_PRICE,4).contains("שח"));
        assertTrue(ds.GetResultsByClassifyAndIndex(Classify.WORD_PRICE,4).contains("בחודש"));
        assertFalse(ds.GetResultsByClassifyAndIndex(Classify.WORD_PRICE,3).contains("רק"));
        assertFalse(ds.GetResultsByClassifyAndIndex(Classify.WORD_PRICE,3).contains("שח"));
        assertFalse(ds.GetResultsByClassifyAndIndex(Classify.WORD_PRICE,3).contains("בחודש"));
        assertFalse(ds.GetResultsByClassifyAndIndex(Classify.WORD_PRICE,2).contains("רק"));
        assertFalse(ds.GetResultsByClassifyAndIndex(Classify.WORD_PRICE,2).contains("שח"));
        assertFalse(ds.GetResultsByClassifyAndIndex(Classify.WORD_PRICE,2).contains("בחודש"));
        assertFalse(ds.GetResultsByClassifyAndIndex(Classify.WORD_PRICE,1).contains("רק"));
        assertFalse(ds.GetResultsByClassifyAndIndex(Classify.WORD_PRICE,1).contains("שח"));
        assertFalse(ds.GetResultsByClassifyAndIndex(Classify.WORD_PRICE,1).contains("בחודש"));
        assertFalse(ds.GetResultsByClassifyAndIndex(Classify.WORD_PRICE,0).contains("רק"));
        assertFalse(ds.GetResultsByClassifyAndIndex(Classify.WORD_PRICE,0).contains("שח"));
        assertFalse(ds.GetResultsByClassifyAndIndex(Classify.WORD_PRICE,0).contains("בחודש"));

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