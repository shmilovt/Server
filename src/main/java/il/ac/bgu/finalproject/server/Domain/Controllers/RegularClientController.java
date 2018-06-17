package il.ac.bgu.finalproject.server.Domain.Controllers;

import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Apartment;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils.CategoryQuery;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils.ResultRecord;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils.SearchAlgorithm;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils.SearchResults;
import il.ac.bgu.finalproject.server.Domain.Exceptions.DataBaseFailedException;

import java.util.LinkedList;
import java.util.List;

public class RegularClientController {

    DataBaseRequestController dataBaseRequestController;
    ServerController serverController;
    public RegularClientController() {
        dataBaseRequestController = new DataBaseRequestController();
        serverController= new ServerController();
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

    public void addressFieldCase(String apartmentId, boolean streetBool, boolean numBuildingBool, boolean neighborhoodBool, String street, int numOfBuilding, String neighborhood) throws DataBaseFailedException {
        ResultRecord resultRecord= dataBaseRequestController.ResultRecordFromDB(apartmentId);
        if (streetBool||numBuildingBool){
            //address has changed
            if (!(streetBool && street!=null && street!=""))
                street=resultRecord.getStreet();
            if (!(numBuildingBool && numOfBuilding!= -1))
                numOfBuilding=resultRecord.getNumber();
            int addressDetailsId= dataBaseRequestController.isAddressDetailsExist(street,numOfBuilding);
            if (addressDetailsId!= -1) {//exist
                if (neighborhoodBool && neighborhood!=null && neighborhood!=""){
                    dataBaseRequestController.suggestionChangesNeighborhood(apartmentId, neighborhood);
//                    serverController.changeNeighborhoodStreetRecord(neighborhood,resultRecord.getStreet());
                }
            }
            else { // addressDetails need to be created
                GoogleMapsController googleMapsController= new GoogleMapsController();
                if (!street.isEmpty() && numOfBuilding> 0) {
                    int timeToUni = googleMapsController.getTimeWalkingFromUniByMin(street, numOfBuilding);
                    double[] locationPoint = googleMapsController.getCoordinates(street, numOfBuilding);
                    if (!neighborhoodBool || neighborhood == null || neighborhood == "") {
                        neighborhood = resultRecord.getNeighborhood();
                    }
                    if (locationPoint[0]!=-1&&locationPoint[1]!=-1) {
                        addressDetailsId = dataBaseRequestController.addAddressDetailsRecord(
                                street, numOfBuilding + "",
                                timeToUni, neighborhood, locationPoint[0], locationPoint[1]);
                        serverController.addStreet(street,neighborhood);
                    }
                }
            }
            dataBaseRequestController.changeAddresDetailsForApartment(apartmentId,addressDetailsId);
        }
        else{
            if (neighborhoodBool && neighborhood!=null && neighborhood!=""){
                dataBaseRequestController.suggestionChangesNeighborhood(apartmentId, neighborhood);
                serverController.changeNeighborhoodStreetRecord(neighborhood,resultRecord.getStreet());
            }
        }
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

    public ResultRecord ResultRecordFromDB(String apartmentID) {
        return dataBaseRequestController.ResultRecordFromDB(apartmentID);
    }
}
