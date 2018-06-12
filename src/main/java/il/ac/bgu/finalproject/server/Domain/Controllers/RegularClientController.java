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
        SearchResults apartmentList = dataBaseRequestController.allResultsRecordsFromDB();
        return searchAlgorithm.filterIntersection(apartmentList, categoryQueryList);
    }

    public SearchResults filterMoreResults(List<CategoryQuery> categoryQueryList) {
        SearchAlgorithm searchAlgorithm = new SearchAlgorithm();
        SearchResults apartmentList = dataBaseRequestController.allResultsRecordsFromDB();
        return searchAlgorithm.filterMoreResults(apartmentList, categoryQueryList);
    }

    public int addUserSuggestion(String id, String field, String suggestion) throws DataBaseFailedException {
        return  dataBaseRequestController.addUserSuggestion(id, field, suggestion);
    }
    public void suggestionChangesApartmentInt(String id, String field, int suggest){
        dataBaseRequestController.suggestionChangesApartmentInt(id, field, suggest);
    }
    public void suggestionChangesApartmentDouble(String id, String field, double suggest){
        dataBaseRequestController.suggestionChangesApartmentDouble(id, field, suggest);
    }
    public void suggestionChangesAddress(String id, String field, String street, int numB, String neighborhood){
        dataBaseRequestController.suggestionChangesAddress(id, field, street, numB, neighborhood);
    }

    public void addSearchRecord(String neighborhood, String timeFromUni, String costMin, String costMax, String floorMin, String floorMax, String sizeMin, String sizeMax, String furnitures,String numOfRoomes, String numOfMates, int protectedSpace,  int garden, int balcony, int pets, int warehouse) throws DataBaseFailedException{
        dataBaseRequestController.addSearchRecord(neighborhood, timeFromUni, costMin, costMax, floorMin, floorMax, sizeMin, sizeMax, furnitures,numOfRoomes, numOfMates, protectedSpace,  garden, balcony, pets, warehouse);
    }

    public void addressFieldCase(String id, boolean b, boolean b1, boolean b2, String street, int numOfBuilding, String neighborhood) throws DataBaseFailedException {
        dataBaseRequestController.addressFieldCase(id, b,  b1, b2, street, numOfBuilding, neighborhood);
    }
/*
    public static void main(String[] args) throws Exception {
        List<CategoryQuery> categoryQueryList = new LinkedList<CategoryQuery>();
        RegularClientController rg = new RegularClientController();
        SearchResults sr =  rg.searchApartments(categoryQueryList);
        int x=2;
    }
*/
    public void connectToTestDB(){
        dataBaseRequestController.connectToTestDB();
    }
    public void disconnectToTestDB(){
        dataBaseRequestController.disconnectToTestDB();
    }
    }
