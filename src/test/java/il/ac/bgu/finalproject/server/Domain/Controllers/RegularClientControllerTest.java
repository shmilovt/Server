package il.ac.bgu.finalproject.server.Domain.Controllers;

import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Apartment;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils.CategoryQuery;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils.CostQuery;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils.MustHaveQuery;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils.SizeQuery;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class RegularClientControllerTest {

    @BeforeClass
    public static void setup(){


    }
    //    }
    //    public void searchApartments() {

    @Test
    public void filterIntersection() {
        RegularClientController rcc;
        DataBaseRequestController dbc;
        Collection<Apartment> apartmentsCollection;
        rcc= new RegularClientController();
        dbc= new DataBaseRequestController();
        apartmentsCollection= dbc.allApartments();
        List<CategoryQuery> categories = new LinkedList<CategoryQuery>();
        CostQuery cost= new CostQuery(400,2000);
        SizeQuery size= new SizeQuery(60,100);
        MustHaveQuery must= new MustHaveQuery(MustHaveQuery.MustHaveThing.warehouse);
        categories.add(cost);
//        categories.add(must);
        Collection<Apartment> res= rcc.filterIntersection(apartmentsCollection,categories);
        for(Apartment item: res){
            item.toString();
        }
    }

    @Test
    public void filterMoreResults() {
    }

}