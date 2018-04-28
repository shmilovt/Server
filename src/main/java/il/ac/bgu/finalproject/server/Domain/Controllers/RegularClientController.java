package il.ac.bgu.finalproject.server.Domain.Controllers;

import il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils.CategoryQuery;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils.SearchAlgorithm;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils.SearchResults;

import java.util.List;

public class RegularClientController {

    DataBaseRequestController dataBaseRequestController;
    public RegularClientController() {
        dataBaseRequestController = new DataBaseRequestController();
    }



    /*public List<Apartment> searchApartments (UserPreferences userPreferences)  {
        Set<Contact> contacts = new HashSet<>();
        contacts.add(new Contact("tamir" , "0522204747"));
        Apartment apartment1 = new Apartment(new ApartmentLocation(new Address("atehena" , 3)), 1000, contacts );
        Apartment apartment2 = new Apartment(new ApartmentLocation(new Address("rager" , 15)), 2000, contacts );
        List<Apartment> lst = new ArrayList<>();
        lst.add(apartment1);
        lst.add(apartment2);
        return lst;

    }*/

    public SearchResults searchApartments(List<CategoryQuery> categoryQueryList) {
        SearchAlgorithm searchAlgorithm = new SearchAlgorithm();
        SearchResults apartmentList = dataBaseRequestController.allResultsRecordsFromDB();
        return searchAlgorithm.filterIntersection(apartmentList, categoryQueryList);

    }


}
