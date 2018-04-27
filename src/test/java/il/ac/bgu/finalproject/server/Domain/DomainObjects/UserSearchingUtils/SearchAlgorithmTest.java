package il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils;

import il.ac.bgu.finalproject.server.Domain.Controllers.DataBaseRequestController;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Apartment;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class SearchAlgorithmTest {


    @Test
    public void filterIntersection() {
        DataBaseRequestController dbc;
        SearchAlgorithm search= new SearchAlgorithm();
        List<Apartment> apartmentsCollection;
        dbc= new DataBaseRequestController();
        apartmentsCollection= dbc.allApartments();
        List<CategoryQuery> categories = new LinkedList<CategoryQuery>();
        CostQuery cost= new CostQuery(400,2000);
        SizeQuery size= new SizeQuery(60,100);
        MustHaveQuery must= new MustHaveQuery(MustHaveQuery.MustHaveThing.warehouse);
        categories.add(cost);
//        categories.add(must);
        List<Apartment> res= search.filterIntersection(apartmentsCollection, categories);
        for(Apartment item: res){
            System.out.println(item.toString());
        }
    }

    @Test
    public void filterMoreResults() {
    }
}