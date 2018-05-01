package il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils;

import il.ac.bgu.finalproject.server.Domain.Controllers.DataBaseRequestController;
import il.ac.bgu.finalproject.server.Domain.Controllers.MyLogger;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SearchAlgorithmTest {


    @Test
    public void filterIntersection() {
        DataBaseRequestController dbc=new DataBaseRequestController();
        SearchAlgorithm search= new SearchAlgorithm();
        SearchResults ResultRecordList= dbc.allResultsRecordsFromDB();

        List<CategoryQuery> categories = new LinkedList<CategoryQuery>();
        CostQuery cost= new CostQuery(400,2000);
        SizeQuery size= new SizeQuery(60,100);
        MustHaveQuery must= new MustHaveQuery(MustHaveQuery.MustHaveThing.warehouse);
        categories.add(cost);
//        categories.add(must);
        SearchResults res= search.filterIntersection(ResultRecordList, categories);
        List<ResultRecord> list = res.getResultRecordList();
         for(ResultRecord item: list){
            assertTrue(item.getCost()>=400&&item.getCost()<=2000);
        }

        categories.add(must);
        res= search.filterIntersection(ResultRecordList, categories);
        list = res.getResultRecordList();
        for(ResultRecord item: list){
            assertTrue(item.getCost()>=400&&item.getCost()<=2000);
            assertTrue(item.isWarehouse());
        }

        categories.remove(1);
        categories.remove(0);
        SizeQuery sizeQuery= new SizeQuery(70, -1);
        categories.add(sizeQuery);
        res= search.filterIntersection(ResultRecordList, categories);
        list = res.getResultRecordList();
        for(ResultRecord item: list){
            assertTrue(item.getSize()>=70);
            assertTrue(item.getLat()!=-1&&item.getLon()!=-1);
            item.getDateOfPublish();
            SimpleDateFormat formatter = new SimpleDateFormat(RelevantQuery.dateFormat);
            Date now= new Date();
            Date gdate = null;
            try {
                gdate = formatter.parse(item.getDateOfPublish());
            } catch (ParseException e) {
                MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
            }
            long gap=now.getTime()- gdate.getTime();
            assertTrue(gap<2592000000.0);
        }
    }

    @Test
    public void filterMoreResults() {
        DataBaseRequestController dbc=new DataBaseRequestController();
        SearchAlgorithm search= new SearchAlgorithm();
        SearchResults ResultRecordList= dbc.allResultsRecordsFromDB();

        List<CategoryQuery> categories = new LinkedList<CategoryQuery>();
        CostQuery cost= new CostQuery(400,2000);
        SizeQuery size= new SizeQuery(60,100);
        MustHaveQuery must= new MustHaveQuery(MustHaveQuery.MustHaveThing.warehouse);
        categories.add(cost);
        categories.add(must);
        categories.add(size);
        SearchResults res= search.filterIntersection(ResultRecordList, categories);
        List<ResultRecord> list = res.getResultRecordList();
        for(ResultRecord item: list) {
            assertFalse(item.getCost() >= 400 && item.getCost() <= 2000
                    && item.isWarehouse() && item.getSize() >= 60 && item.getSize() <= 100);
            assertTrue(item.getLat() != -1 && item.getLon() != -1);
            item.getDateOfPublish();
            SimpleDateFormat formatter = new SimpleDateFormat(RelevantQuery.dateFormat);
            Date now = new Date();
            Date gdate = null;
            try {
                gdate = formatter.parse(item.getDateOfPublish());
            } catch (ParseException e) {
                MyLogger.getInstance().log(Level.SEVERE, e.getMessage(), e);
            }
            long gap = now.getTime() - gdate.getTime();
            assertTrue(gap < 2592000000.0);
        }
    }
}