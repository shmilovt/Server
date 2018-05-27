package il.ac.bgu.finalproject.server.ServiceLayer;

import il.ac.bgu.finalproject.server.CommunicationLayer.AdminDTOs.ArraySearchRecordDTO;
import il.ac.bgu.finalproject.server.CommunicationLayer.AdminDTOs.CalculatorDTO;
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
    private AdminClientController adminClientController;
    @Autowired
    private RegularClientController regularClientController;


    @Override
    public SearchResults searchApartments(List<CategoryQuery> categoryQueryList) {
       return regularClientController.searchApartments(categoryQueryList);

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
    public boolean login(String username, String password)  {
        try {
            return adminClientController.login(username, password);
        } catch (NoUserNameException e) {
            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
            return false;
        }
    }

    @Override
    public boolean changePassword(String username, String password) {
        return adminClientController.changePassword(username, password);
    }

    @Override
    public boolean newPostFromAdmin(String nameOfPublisher, String messege){
        try {
            return adminClientController.newPostFromAdmin(nameOfPublisher, messege);
        } catch (DataBaseFailedException e) {
            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
            return false;
        }
    }

    @Override
    public void insertGroup(String groupID) throws DataBaseFailedException{
        adminClientController.insertGroup(groupID);
    }

    @Override
    public void deleteGroup(String groupID) throws DataBaseFailedException{
        adminClientController.deleteGroup(groupID);
    }

    @Override
    public List<GroupDTO> GetAllGroups(){
        return adminClientController.GetAllGroups();
    }

    @Override
    public ArraySearchRecordDTO getAllUserSearches(){
        return adminClientController.getAllUserSearches();
    }

    @Override
    public CalculatorDTO getCalcCosts(){
        return adminClientController.getCalcCosts();
    }

    public static void main(String[] args) throws Exception
    {
        ServerController serverController=new ServerController();
        Post post= serverController.getPost("1231C_2476");
        System.out.println(post.toString());

    }
}




