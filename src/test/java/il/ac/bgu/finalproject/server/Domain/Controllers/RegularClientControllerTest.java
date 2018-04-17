package il.ac.bgu.finalproject.server.Domain.Controllers;

import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Apartment;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils.CategoryQuery;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class RegularClientControllerTest {

    @BeforeClass
    public void setup(){
        Collection<Apartment> apartmentsCollection= new LinkedList<Apartment>();
        List<CategoryQuery> categories = new LinkedList<CategoryQuery>();
    }
    //    }
    //    public void searchApartments() {

    @Test
    public void filterIntersection() {
    }
    //    @Test

    @Test
    public void filterMoreResults() {
    }
}