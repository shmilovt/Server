package il.ac.bgu.finalproject.server.ServiceLayer;

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
    public void addSearchRecord(String neighborhood, String timeFromUni, String cost, String floor, String size, String furnitures,String numOfRoomes, String numOfMates)  {
        try {
            regularClientController.addSearchRecord(neighborhood, timeFromUni, cost, floor, size, furnitures,numOfRoomes, numOfMates);
        } catch (DataBaseFailedException e) {
                        MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
        }
    }

    public static void main(String[] args) throws Exception
    {
        ServerController serverController=new ServerController();
        Post post= serverController.getPost("1231C_2476");
        System.out.println(post.toString());

    }
}




