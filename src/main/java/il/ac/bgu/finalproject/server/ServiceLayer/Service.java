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
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class Service implements IService {

    @Autowired
    private AdminClientController adminClientController;
    @Autowired
    private RegularClientController regularClientController;


    @Override
    public SearchResults searchApartments(List<CategoryQuery> categoryQueryList) {
       return regularClientController.searchApartments(categoryQueryList);

    }

    public static void main(String[] args) throws Exception
    {
        ServerController serverController=new ServerController();
        Post post= serverController.getPost("1231C_2476");
        System.out.println(post.toString());
    }
}




