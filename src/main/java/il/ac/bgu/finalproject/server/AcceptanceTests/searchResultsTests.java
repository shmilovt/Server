package il.ac.bgu.finalproject.server.AcceptanceTests;


import il.ac.bgu.finalproject.server.CommunicationLayer.DTOs.GroupDTO;
import il.ac.bgu.finalproject.server.Domain.Controllers.DataBaseRequestController;
import il.ac.bgu.finalproject.server.Domain.Controllers.MyLogger;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils.*;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;

public class searchResultsTests {
    private static ServiceConnector serviceConnector;

    @BeforeClass
    public static void setup() {
        serviceConnector= new ServiceConnector();
        serviceConnector.getBridge().connectToTestDB();
    }

    @Test
    public void searchResultsTest(){
        List<CategoryQuery> categories = new LinkedList<CategoryQuery>();
        CostQuery cost= new CostQuery(400,2000);
        SizeQuery size= new SizeQuery(60,100);
        MustHaveQuery must= new MustHaveQuery(MustHaveQuery.MustHaveThing.warehouse);
        categories.add(cost);
//        categories.add(must);
        SearchResults res= serviceConnector.getBridge().searchApartments(categories);
        List<ResultRecord> list = res.getResultRecordList();
        for(ResultRecord item: list){
            Assert.assertTrue(item.getCost()>=400&&item.getCost()<=2000);
        }

        MustHaveQuery must2= new MustHaveQuery(MustHaveQuery.MustHaveThing.garden);
        categories.add(must2);
        res= serviceConnector.getBridge().searchApartments(categories);
        list = res.getResultRecordList();
        for(ResultRecord item: list){
            Assert.assertTrue(item.getCost()>=400&&item.getCost()<=2000);
            Assert.assertTrue(item.getYard()==1);
        }

        categories.remove(1);

        categories.add(must);
        res= serviceConnector.getBridge().searchApartments(categories);
        list = res.getResultRecordList();
        for(ResultRecord item: list){
            Assert.assertTrue(item.getCost()>=400&&item.getCost()<=2000);
            Assert.assertTrue(item.getWarehouse()==1);
        }

        categories.remove(1);
        categories.remove(0);
        SizeQuery sizeQuery= new SizeQuery(70, -1);
        categories.add(sizeQuery);
        res= serviceConnector.getBridge().searchApartments(categories);
        list = res.getResultRecordList();
        for(ResultRecord item: list){
            Assert.assertTrue(item.getSize()>=70);
            Assert.assertTrue(item.getLat()!=-1&&item.getLon()!=-1);
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
            Assert.assertTrue(gap<2592000000.0);
        }

//        serviceConnector.getBridge().addSearchRecord("1","2","3","4","5","6","7","8","9","10","11",
//                1,1,1,1,1);
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
        SearchResults res= search.filterMoreResults(ResultRecordList, categories);
        List<ResultRecord> list = res.getResultRecordList();
        for(ResultRecord item: list) {
            Assert.assertFalse(item.getCost() >= 400 && item.getCost() <= 2000
                    && item.getWarehouse()==1 && item.getSize() >= 60 && item.getSize() <= 100);
            Assert.assertTrue(item.getLat() != -1 && item.getLon() != -1);
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
            Assert.assertTrue(gap < 2592000000.0);
        }
    }

    public boolean searchRecordExist(List<GroupDTO> groupDTOList, GroupDTO groupDTO){
        String groupID= groupDTO.getGroupID();
        for (GroupDTO item: groupDTOList){
            if (item.getGroupID().equals(groupID))
                return true;
        }
        return false;
    }

    @AfterClass
    public static void endup() {
        serviceConnector.getBridge().disconnectToTestDB();
    }

}
