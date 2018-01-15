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
        ds.Insert(Classify.PHONE,1,"0533391800");
        List<String> ex= new ArrayList<String>();
        ex.add("0533391800");
        assertEquals(ds.GetResultsByClassify(Classify.PHONE),ex);
    }


    @Test
    public void remove() {
        EnvList env = new EnvList("להשכרה דירה מהממת ברחוב ברנפלד 13, בעל דירה מקסים! הדירה מרוהטת קומפלט, 4 חדרים, כולל מטבח מאובזר, רק 900 שח בחודש! כדאי מאוד! לפרטים, נופר- 053-3391800");
        AnalyzedDS ds= new AnalyzedDS(env);
        ds.Insert(Classify.PHONE,1,"0533391800");
        ds.Remove(Classify.PHONE,1,"0533391800");
        List<String> ex= new ArrayList<String>();
        assertEquals(ds.GetResultsByClassify(Classify.PHONE),ex);
    }

    @Test
    public void getEnvsIndex() {
        EnvList env = new EnvList("להשכרה דירה מהממת ברחוב ברנפלד 13, בעל דירה מקסים! הדירה מרוהטת קומפלט, 4 חדרים, כולל מטבח מאובזר, רק 900 שח בחודש! כדאי מאוד! לפרטים, נופר- 053-3391800");
        AnalyzedDS ds= new AnalyzedDS(env);
        //System.out.println(ds.GetEnvsIndex(Classify.PHONE));
        List<Integer> ex= new ArrayList<Integer>();
        assertEquals(ex,ds.GetEnvsIndex(Classify.PHONE));
        ds.Insert(Classify.PHONE,1,"0533391800");
        ex.add(1);
        assertEquals(ex,ds.GetEnvsIndex(Classify.PHONE));

    }

    @Test
    public void getResultsByClassify() {
        EnvList env = new EnvList("להשכרה דירה מהממת ברחוב ברנפלד 13, בעל דירה מקסים! הדירה מרוהטת קומפלט, 4 חדרים, כולל מטבח מאובזר, רק 900 שח בחודש! כדאי מאוד! לפרטים, נופר- 053-3391800, יכולים גם לתאם לראות את הדירה עם נועה- 053-3311010");
        AnalyzedDS ds= new AnalyzedDS(env);
        List<String> ex= new ArrayList<String>();
        assertEquals(ex,ds.GetResultsByClassify(Classify.PHONE));
        ds.Insert(Classify.PHONE,6,"0533391800");
        ex.add("0533391800");
        assertEquals(ex,ds.GetResultsByClassify(Classify.PHONE));
        ds.Insert(Classify.PHONE,7,"0533311010");
        ex.add("0533311010");
        assertEquals(ex,ds.GetResultsByClassify(Classify.PHONE));
    }

    @Test
    public void getResultsByClassifyAndIndex() {
        EnvList env = new EnvList("להשכרה דירה מהממת ברחוב ברנפלד 13, בעל דירה מקסים! הדירה מרוהטת קומפלט, 4 חדרים, כולל מטבח מאובזר, רק 900 שח בחודש! כדאי מאוד! לפרטים, נופר- 053-3391800, יכולים גם לתאם לראות את הדירה עם נועה- 053-3311010");
        AnalyzedDS ds= new AnalyzedDS(env);
        List<String> ex= new ArrayList<String>();
        assertEquals(ex,ds.GetResultsByClassifyAndIndex(Classify.PHONE,6));
        ds.Insert(Classify.PHONE,6,"0533391800");
        ds.Insert(Classify.PHONE,7,"0533311010");
        ex.add("0533391800");
        assertEquals(ex,ds.GetResultsByClassifyAndIndex(Classify.PHONE,6));
        ex.remove("0533391800");
        ex.add("0533311010");
        assertEquals(ex,ds.GetResultsByClassifyAndIndex(Classify.PHONE,7));
    }

    /*
    @Test
    public void getNumberOfGapAccourences() {
    }
    */
}