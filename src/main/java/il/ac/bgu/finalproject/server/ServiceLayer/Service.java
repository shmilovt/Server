package il.ac.bgu.finalproject.server.ServiceLayer;

import il.ac.bgu.finalproject.server.CommunicationLayer.AdminDTOs.ArraySearchRecordDTO;
import il.ac.bgu.finalproject.server.CommunicationLayer.AdminDTOs.CalculatorDTO;
import il.ac.bgu.finalproject.server.CommunicationLayer.AdminDTOs.SearchRecordDTO;
import il.ac.bgu.finalproject.server.CommunicationLayer.DTOs.GroupDTO;
import il.ac.bgu.finalproject.server.CommunicationLayer.DTOs.SearchResultsDTO;
import il.ac.bgu.finalproject.server.Domain.Controllers.ServerController;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Apartment;
import il.ac.bgu.finalproject.server.Domain.Controllers.AdminClientController;
import il.ac.bgu.finalproject.server.Domain.Controllers.RegularClientController;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Post;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils.CategoryQuery;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils.SearchResults;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSuggestionUtils.UserSuggestion;
import il.ac.bgu.finalproject.server.Domain.Exceptions.DataBaseFailedException;
import il.ac.bgu.finalproject.server.Domain.Exceptions.NoUserNameException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.logging.Level;

import il.ac.bgu.finalproject.server.Domain.Controllers.MyLogger;


public class Service implements IService {

    @Autowired
    private AdminClientController adminClientController= new AdminClientController();
    @Autowired
    private RegularClientController regularClientController= new RegularClientController();


    @Override
    public SearchResults searchApartments(List<CategoryQuery> categoryQueryList) {
       return regularClientController.searchApartments(categoryQueryList);
    }

    @Override
    public SearchResults filterMoreResults(List<CategoryQuery> categoryQueryList) {
        return regularClientController.filterMoreResults(categoryQueryList);
    }

    @Override
    public void addSearchRecord(String neighborhood, String timeFromUni, String costMin, String costMax, String floorMin, String floorMax, String sizeMin, String sizeMax, String furnitures,String numOfRoomes, String numOfMates, int protectedSpace,  int garden, int balcony, int pets, int warehouse){
        try {
            regularClientController.addSearchRecord(neighborhood, timeFromUni, costMin, costMax, floorMin, floorMax, sizeMin, sizeMax, furnitures,numOfRoomes, numOfMates, protectedSpace,  garden, balcony, pets, warehouse);
            } catch (DataBaseFailedException e) {
                        MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
        }
    }

    @Override
    public int login(String username, String password)  {
        try {
            return adminClientController.login(username, password);
        } catch (NoUserNameException e) {
            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
            return -1;
        }
    }

    @Override
    public int changePassword(String username, String password) {
        return adminClientController.changePassword(username, password);
    }

    @Override
    public String forgotPassword(String username, String email) {
        return adminClientController.forgotPassword(username, email);
    }

    @Override
    public int checkCCUID(String ccuid){
        return adminClientController.checkCCUID(ccuid);
    }

    @Override
    public int newPostFromAdmin(String nameOfPublisher, String messege){
        return adminClientController.newPostFromAdmin(nameOfPublisher, messege);
    }

    @Override
    public int insertGroup(String groupID) {
        return adminClientController.insertGroup(groupID);
    }

    @Override
    public int deleteGroup(String groupID){
        return adminClientController.deleteGroup(groupID);
    }

    @Override
    public List<GroupDTO> GetAllGroups(){
        return adminClientController.GetAllGroups();
    }

    @Override
    public List<SearchRecordDTO>  getAllUserSearches(){
        return adminClientController.getAllUserSearches();
    }

    @Override
    public CalculatorDTO getCalcCosts(){
        return adminClientController.getCalcCosts();
    }

    @Override
    public SearchResults getAllApartments() {
        return adminClientController.getAllApartments();
    }

    @Override
    public void setNewCalculator(int protectedSpaceCost, int timeFromUniCost_10, int timeFromUniCost_20,
                                 int timeFromUniCost_G_20, int neighborhoodCost_B_Ramot,
                                 int neighborhoodCost_oldV_Wingate, int neighborhoodCost_D,
                                 int neighborhoodCost_G, int furnitureCost_full, int furnitureCost_half,
                                 int furnitureCost_none, int sizeCost_25, int sizeCost_30, int sizeCost_35,
                                 int sizeCost_35_up, int roomatesCost_1, int roomatesCost_2, int roomatesCost_3,
                                 int roomatesCost_4, int roomatesCost_5, int roomatesCost_6, int gardenCost,
                                 int balconyCost) {
        adminClientController.setNewCalculator(protectedSpaceCost, timeFromUniCost_10, timeFromUniCost_20,
                timeFromUniCost_G_20, neighborhoodCost_B_Ramot,
                neighborhoodCost_oldV_Wingate, neighborhoodCost_D,
                neighborhoodCost_G, furnitureCost_full, furnitureCost_half,
                furnitureCost_none, sizeCost_25, sizeCost_30, sizeCost_35,
                sizeCost_35_up, roomatesCost_1, roomatesCost_2, roomatesCost_3,
                roomatesCost_4, roomatesCost_5, roomatesCost_6, gardenCost, balconyCost);
    }

    @Override
    public int addUserSuggestion(String id, String field, String suggestion) throws DataBaseFailedException {
        return regularClientController.addUserSuggestion(id, field, suggestion);
    }

    @Override
    public void suggestionChangesApartmentInt(String id, String field, int suggest){
        regularClientController.suggestionChangesApartmentInt(id, field, suggest);
    }
    public void suggestionChangesApartmentDouble(String id, String field, double suggest){
        regularClientController.suggestionChangesApartmentDouble(id, field, suggest);
    }
    public void suggestionChangesAddress(String id, String field, String street, int numB, String neighborhood){
        regularClientController.suggestionChangesAddress(id, field, street, numB, neighborhood);
    }

    public void addressFieldCase(String id, boolean b, boolean b1, boolean b2, String street, int numOfBuilding, String neighborhood) throws DataBaseFailedException {
        regularClientController.addressFieldCase(id, b,  b1, b2, street, numOfBuilding, neighborhood);
    }

    public static void main(String[] args) throws Exception
    {
        ServerController serverController=new ServerController();
        Post post= serverController.getPost("1231C_2476");
        System.out.println(post.toString());

    }
}




