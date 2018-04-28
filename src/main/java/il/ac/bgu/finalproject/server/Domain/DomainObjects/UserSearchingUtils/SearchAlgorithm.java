package il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils;

import java.util.ArrayList;
import java.util.List;

public class SearchAlgorithm {

    public SearchAlgorithm(){}


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
