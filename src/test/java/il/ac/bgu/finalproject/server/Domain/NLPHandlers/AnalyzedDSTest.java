package il.ac.bgu.finalproject.server.Domain.NLPHandlers;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AnalyzedDSTest {

    @Test
    public void insert() {
        EnvList env = new EnvList("להשכרה דירה מהממת ברחוב ברנפלד 13, בעל דירה מקסים! הדירה מרוהטת קומפלט, 4 חדרים, כולל מטבח מאובזר, רק 900 שח בחודש! כדאי מאוד! לפרטים, נופר- 053-3391800");
        AnalyzedDS ds= new AnalyzedDS(env);
        ds.Insert(Classify.PHONE,1,"0533391822");
        List<String> ex= new ArrayList<String>();
        ex.add("0533391822");
        ex.add("0533391800");
        assertEquals(ex,ds.GetResultsByClassify(Classify.PHONE));
    }


    @Test
    public void remove() {
        EnvList env = new EnvList("להשכרה דירה מהממת ברחוב ברנפלד 13, בעל דירה מקסים! הדירה מרוהטת קומפלט, 4 חדרים, כולל מטבח מאובזר, רק 900 שח בחודש! כדאי מאוד! לפרטים, נופר- 053-3391800");
        AnalyzedDS ds= new AnalyzedDS(env);
        ds.Remove(Classify.PHONE,5,"0533391800");
        List<String> ex= new ArrayList<String>();
        assertEquals(ex,ds.GetResultsByClassify(Classify.PHONE));
    }

    @Test
    public void getEnvsIndex() {
        EnvList env = new EnvList("להשכרה דירה מהממת ברחוב ברנפלד 13, בעל דירה מקסים! הדירה מרוהטת קומפלט, 4 חדרים, כולל מטבח מאובזר, רק 900 שח בחודש! כדאי מאוד! לפרטים, נופר- 053-3391800");
        AnalyzedDS ds= new AnalyzedDS(env);
        //System.out.println(ds.GetEnvsIndex(Classify.PHONE));
        List<Integer> ex= new ArrayList<Integer>();
        ex.add(5);
        assertEquals(ex,ds.GetEnvsIndex(Classify.PHONE));

    }

    @Test
    public void getResultsByClassify() {
        EnvList env = new EnvList("להשכרה דירה מהממת ברחוב ברנפלד 13, בעל דירה מקסים! הדירה מרוהטת קומפלט, 4 חדרים, כולל מטבח מאובזר, רק 900 שח בחודש! כדאי מאוד! לפרטים, נופר- 053-3391800, יכולים גם לתאם לראות את הדירה עם נועה- 053-3311010");
        AnalyzedDS ds= new AnalyzedDS(env);
        List<String> ex= new ArrayList<String>();
        ex.add("0533391800");
        ex.add("0533311010");
        assertEquals(ex,ds.GetResultsByClassify(Classify.PHONE));
    }

    @Test
    public void getResultsByClassifyAndIndex() {
        EnvList env = new EnvList("להשכרה דירה מהממת ברחוב ברנפלד 13, בעל דירה מקסים! הדירה מרוהטת קומפלט, 4 חדרים, כולל מטבח מאובזר, רק 900 שח בחודש! כדאי מאוד! לפרטים, נופר- 053-3391800, יכולים גם לתאם לראות את הדירה עם נועה- 053-3311010");
        AnalyzedDS ds= new AnalyzedDS(env);
        List<String> ex= new ArrayList<String>();
        ex.add("0533391800");
        assertEquals(ex,ds.GetResultsByClassifyAndIndex(Classify.PHONE,5));
        ex.remove("0533391800");
        ex.add("0533311010");
        assertEquals(ex,ds.GetResultsByClassifyAndIndex(Classify.PHONE,6));
    }

    /*
    @Test
    public void getNumberOfGapAccourences() {
    }
    */
}