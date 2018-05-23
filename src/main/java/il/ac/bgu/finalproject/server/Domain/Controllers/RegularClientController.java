package il.ac.bgu.finalproject.server.Domain.Controllers;

import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Apartment;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils.CategoryQuery;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils.SearchAlgorithm;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils.SearchResults;
import il.ac.bgu.finalproject.server.Domain.Exceptions.DataBaseFailedException;

import java.util.LinkedList;
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
//        try {
//            dataBaseRequestController.connect();
//        } catch (DataBaseFailedException e) {
//            e.printStackTrace();
//        }
        SearchResults apartmentList = dataBaseRequestController.allResultsRecordsFromDB();
//        try {
//            dataBaseRequestController.disconnect();
//        } catch (DataBaseFailedException e) {
//            e.printStackTrace();
//        }
        return searchAlgorithm.filterIntersection(apartmentList, categoryQueryList);
    }

    public void addUserSuggestion(String id, String field, String suggestion) throws DataBaseFailedException {
        dataBaseRequestController.addUserSuggestion(id, field, suggestion);
    }

    public void addSearchRecord(String neighborhood, String timeFromUni, String cost, String floor, String size, String furnitures,String numOfRoomes, String numOfMates) throws DataBaseFailedException{
        dataBaseRequestController.addSearchRecord(neighborhood, timeFromUni, cost, floor, size, furnitures,numOfRoomes, numOfMates);
    }
/*
    public static void main(String[] args) throws Exception {
        List<CategoryQuery> categoryQueryList = new LinkedList<CategoryQuery>();
        RegularClientController rg = new RegularClientController();
        SearchResults sr =  rg.searchApartments(categoryQueryList);
        int x=2;
    }
*/
    }
