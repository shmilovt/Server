package il.ac.bgu.finalproject.server.CommunicationLayer;

import il.ac.bgu.finalproject.server.CommunicationLayer.DTOs.*;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Apartment;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils.CategoryQuery;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils.SearchResults;
import il.ac.bgu.finalproject.server.ServiceLayer.IService;
import il.ac.bgu.finalproject.server.ServiceLayer.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.directory.SearchResult;
import java.util.ArrayList;
import java.util.List;


@RestController
public class RegularClientCommunicationController {

    @Autowired
    private IService service;

    @Autowired
    private Converter converter;


    @RequestMapping(value = "/searchApartments", method = {RequestMethod.POST, RequestMethod.GET})
    public String searchApartments(@RequestParam String userSearchDTOString) {
        UserSearchDTO userSearchDTO = UserSearchDTO.fromJSON(userSearchDTOString);
        List<CategoryQuery> categoryQueryList = converter.convertFromDTO(userSearchDTO);
        SearchResults searchResult = service.searchApartments(categoryQueryList);
        SearchResultsDTO searchResultsDTO = converter.convertToDTO(searchResult);
        String jsonString = SearchResultsDTO.toJSON(searchResultsDTO);
        return jsonString;
    }

    @RequestMapping(value = "/userSearchDTO", method = {RequestMethod.POST, RequestMethod.GET})
    public String search1() {
        UserSearchDTO userSearchDTO = new UserSearchDTO();
        CategoryType [] priorities = new CategoryType[1];
        priorities[0] = CategoryType.cost;
        userSearchDTO.setCostDTO(new CostDTO(1000, 3000));
        userSearchDTO.setPriorities(priorities);
        String json = UserSearchDTO.toJSON(userSearchDTO);
        return json;
    }


    @RequestMapping(value = "/searchResultsDTO", method = {RequestMethod.POST, RequestMethod.GET})
    public String search2() {
        List<CategoryQuery> categoryQueries = new ArrayList<>();
        SearchResults searchResult = service.searchApartments(categoryQueries);
        SearchResultsDTO searchResultsDTO = converter.convertToDTO(searchResult);
        String jsonString = SearchResultsDTO.toJSON(searchResultsDTO);
        return jsonString;
    }

    @RequestMapping(value = "/hello", method = {RequestMethod.POST, RequestMethod.GET})
    public String hello(@RequestParam String name) {

        return "hello " + name + " !";
    }

}
