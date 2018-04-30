package il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils;

import java.util.ArrayList;
import java.util.List;

public class SearchAlgorithm {
    private RelevantQuery relevantQuery;
    public SearchAlgorithm(){
        relevantQuery= new RelevantQuery();
    }


    public SearchResults filterIntersection (SearchResults allFromDB, List<CategoryQuery> categories){
        List<ResultRecord> resultRecordList= allFromDB.getResultRecordList();
        List<ResultRecord> resultAfterFiltering = new ArrayList<ResultRecord>();
        Boolean tempBool;
        for (ResultRecord element: resultRecordList) {
            tempBool = true;
            if (relevantQuery.mainQuery(element)) {
                for (CategoryQuery category : categories) {
                    if (!category.mainQuery(element)) {
                        tempBool = false;
                    }
                }
            }
            else tempBool=false;
            if (tempBool)
                resultAfterFiltering.add(element);
        }
        return new SearchResults(resultAfterFiltering);
    }

    public SearchResults filterMoreResults (SearchResults allFromDB, List<CategoryQuery> categories) {
        List<ResultRecord> resultRecordList= allFromDB.getResultRecordList();
        List<ResultRecord> resultAfterFiltering = new ArrayList<ResultRecord>();
        List<ResultRecord> afterFiltering2 =new ArrayList<ResultRecord>(), afterFiltering3 =new ArrayList<ResultRecord>(),
                afterFiltering4 =new ArrayList<ResultRecord>(), afterFiltering5 =new ArrayList<ResultRecord>();
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
                    if (relevantQuery.mainQuery(element)) {
                        if (c1.mainQuery(element) && (!c2.mainQuery(element)))
                            resultAfterFiltering.add(element);
                        else if ((!c1.mainQuery(element)) && c2.mainQuery(element))
                            afterFiltering2.add(element);
                    }
                }
                resultAfterFiltering.addAll(afterFiltering2);
            } else {
                c3 = categories.get(2);
                for (ResultRecord element : resultRecordList) {
                    if (relevantQuery.mainQuery(element)) {
                        if (c1.mainQuery(element) && c2.mainQuery(element) && c3.mainQuery(element)) {
                            if (size > 3)
                                resultAfterFiltering.add(element);
                        }
                        else if (c1.mainQuery(element) && c2.mainQuery(element) && (!c3.mainQuery(element)))
                            afterFiltering2.add(element);
                        else if (c1.mainQuery(element) && (!c2.mainQuery(element)) && c3.mainQuery(element))
                            afterFiltering3.add(element);
                        else if (c1.mainQuery(element) && (!c2.mainQuery(element)) && (!c3.mainQuery(element)))
                            afterFiltering4.add(element);
                        else if ((!c1.mainQuery(element)) && c2.mainQuery(element) && c3.mainQuery(element))
                            afterFiltering5.add(element);
                    }
                }
            }
        }
        return  new SearchResults(resultAfterFiltering);
    }



}
