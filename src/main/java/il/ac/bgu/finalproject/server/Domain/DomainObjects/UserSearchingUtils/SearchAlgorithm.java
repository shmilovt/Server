package il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils;

import com.google.gson.Gson;
import il.ac.bgu.finalproject.server.Domain.Controllers.DataBaseRequestController;
import il.ac.bgu.finalproject.server.Domain.Exceptions.DataBaseFailedException;
import il.ac.bgu.finalproject.server.PersistenceLayer.DataBaseConnection;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SearchAlgorithm {

    public SearchAlgorithm(){}

    public static void main(String [] args){
        DataBaseRequestController dataBaseRequestController = new DataBaseRequestController();
        SearchResults apartmentList = null;
        try {
            dataBaseRequestController.connect();
             apartmentList = dataBaseRequestController.allResultsRecordsFromDB();
            System.out.println("func work");

        } catch (DataBaseFailedException e) {
            e.printStackTrace();
        }


        SearchAlgorithm searchAlgorithm = new SearchAlgorithm();
        List<CategoryQuery> categoryQueryList = new ArrayList<>();
        SearchResults searchResults  = searchAlgorithm.filterIntersection(apartmentList ,categoryQueryList);
        System.out.println(searchResults.getResultRecordList().size());
        Gson gson = new Gson();
       System.out.println(gson.toJson(searchResults));
    }

  /*  public SearchResults filterIntersection (SearchResults allFromDB, List<CategoryQuery> categories){
        List<ResultRecord> resultRecordList= allFromDB.getResultRecordList();
        List<ResultRecord> resultAfterFiltering = new ArrayList<ResultRecord>();
//        Boolean tempBool;
//        for (ResultRecord element: resultRecordList) {
//            tempBool = true;
//            for (CategoryQuery category : categories) {
//                if (!category.mainQuery(element)) {
//                    tempBool = false;
//                }
//            }
//            if (tempBool)
//                resultAfterFiltering.add(element);
//        }

        ResultRecord resultRecord= new ResultRecord();
        resultRecord.setLon(37.878);
        resultRecord.setLat(37.878);
        resultRecord.setText("דירה מדהימה! רגר 150 קומה 4 מתאימה ל3 שותפים במיקום מעולה 1000 לשותף שווב במיוחד");
        Date date= new Date();
        resultRecord.setDateOfPublish(date.toString());
        resultRecord.setNumberOfRoomates(3);
        resultRecord.setNumber(150);
        resultRecord.setStreet("רגר");
        resultRecord.setNeighborhood("שכונה א'");
        resultRecord.setFloor(4);
        resultRecord.setDistanceFromUniversity(10);
        resultRecord.setCost(1000);
        resultRecord.setSize(-1);
        resultRecord.setBalcony(false);
        resultRecord.setYard(false);
        resultRecord.setAnimals(false);
        resultRecord.setWarehouse(false);
        resultRecord.setProtectedSpace(false);
        resultRecord.setFurniture(-1);
        resultAfterFiltering.add(resultRecord);
//        Contact[] contacts
//        resultRecord.setContacts();
        return new SearchResults(resultAfterFiltering);
    }*/
    public SearchResults filterIntersection (SearchResults allFromDB, List<CategoryQuery> categories){
        List<ResultRecord> resultRecordList= allFromDB.getResultRecordList();
        List<ResultRecord> resultAfterFiltering = new ArrayList<ResultRecord>();
        Boolean tempBool;
        for (ResultRecord element: resultRecordList) {
            tempBool = true;
            for (CategoryQuery category : categories) {
                if (!category.mainQuery(element)) {
                    tempBool = false;
                }
            }
            if (tempBool)
                resultAfterFiltering.add(element);
        }
        return new SearchResults(resultAfterFiltering);
    }

    public SearchResults filterMoreResults (SearchResults allFromDB, List<CategoryQuery> categories) {
        List<ResultRecord> resultRecordList= allFromDB.getResultRecordList();
        List<ResultRecord> resultAfterFiltering = new ArrayList<ResultRecord>();
        int size = categories.size();
        CategoryQuery c1, c2, c3;
        if (size < 2) {
            return  new SearchResults(resultAfterFiltering);
        }
        else {
            c1 = categories.get(0);
            c2 = categories.get(1);
            if (size == 2) {
                for (ResultRecord element : resultRecordList) {
                    if (c1.mainQuery(element) && (!c2.mainQuery(element)))
                        resultAfterFiltering.add(element);
                }
                for (ResultRecord element : resultRecordList) {
                    if ((!c1.mainQuery(element)) && c2.mainQuery(element))
                        resultAfterFiltering.add(element);
                }
            } else {
                c3 = categories.get(2);
                if (size > 3) {
                    for (ResultRecord element : resultRecordList) {
                        if (c1.mainQuery(element) && c2.mainQuery(element) && c3.mainQuery(element))
                            resultAfterFiltering.add(element);
                    }
                }
                for (ResultRecord element : resultRecordList) {
                    if (c1.mainQuery(element) && c2.mainQuery(element) && (!c3.mainQuery(element)))
                        resultAfterFiltering.add(element);
                }
                for (ResultRecord element : resultRecordList) {
                    if (c1.mainQuery(element) && (!c2.mainQuery(element)) && c3.mainQuery(element))
                        resultAfterFiltering.add(element);
                }
                for (ResultRecord element : resultRecordList) {
                    if (c1.mainQuery(element) && (!c2.mainQuery(element)) && (!c3.mainQuery(element)))
                        resultAfterFiltering.add(element);
                }
                for (ResultRecord element : resultRecordList) {
                    if ((!c1.mainQuery(element)) && c2.mainQuery(element) && c3.mainQuery(element))
                        resultAfterFiltering.add(element);
                }
            }
        }
        return  new SearchResults(resultAfterFiltering);
    }
}
