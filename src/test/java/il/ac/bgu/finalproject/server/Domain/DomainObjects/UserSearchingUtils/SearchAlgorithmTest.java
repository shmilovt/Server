package il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils;

import il.ac.bgu.finalproject.server.Domain.Controllers.DataBaseRequestController;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

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
            System.out.println(item.toString());
        }
    }

    @Test
    public void filterMoreResults() {
    }
}