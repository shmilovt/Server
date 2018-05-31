package il.ac.bgu.finalproject.server.CommunicationLayer;

import com.google.gson.Gson;
import il.ac.bgu.finalproject.server.CommunicationLayer.DTOs.*;
import il.ac.bgu.finalproject.server.Domain.Controllers.RegularClientController;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils.*;
import il.ac.bgu.finalproject.server.Domain.Exceptions.DataBaseFailedException;
import il.ac.bgu.finalproject.server.ServiceLayer.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedList;
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
        CategoryType[] categoryTypes = userSearchDTO.getPriorities();
        int protectedSpace=0;
        int garden=0;
        int balcony=0;
        int pets=0;
        int warehouse=0;
        for(CategoryType categoryType : categoryTypes){
            switch (categoryType){
                case balcony:
                    balcony=1;
                    break;
                case animals:
                    pets=1;
                    break;
                case protectedSpace:
                    protectedSpace=1;
                    break;
                case yard:
                    garden=1;
                    break;
                case warehouse:
                    warehouse=1;
                    break;
                default:
                    break;
            }
        }
        service.addSearchRecord(userSearchDTO.getNeighborhood(),""+userSearchDTO.getDistanceFromUniversity(),
                ""+userSearchDTO.getCostDTO().getMinCost(), ""+userSearchDTO.getCostDTO().getMaxCost(),
                ""+userSearchDTO.getFloorDTO().getMinFloor(), ""+userSearchDTO.getFloorDTO().getMaxFloor(),
                ""+userSearchDTO.getSizeDTO().getMinSize(), ""+userSearchDTO.getSizeDTO().getMaxSize(),
                ""+userSearchDTO.getFurniture(),""+userSearchDTO.getNumberOfRooms(),""+userSearchDTO.getNumberOfMates(),
         protectedSpace,  garden, balcony, pets, warehouse);
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

    @RequestMapping(value = "/addUserReport", method = {RequestMethod.POST, RequestMethod.GET})
    public String addUserReport(@RequestParam String report) {
        Gson gson= new Gson();
        ReportDTO reportDTO= ReportDTO.fromJSON(report);
        int count=0;
        try {
            count= service.addUserSuggestion(reportDTO.getAddressID(),""+reportDTO.getField(),reportDTO.getContentInGson());
        } catch (DataBaseFailedException e) {
            e.printStackTrace();
        }
        if (count>5) {
            switch (reportDTO.getField()) {
                case size:
                    double t1 = gson.fromJson(reportDTO.getContentInGson(), Double.class);
                    service.suggestionChangesApartmentDouble(reportDTO.getAddressID(), ""+reportDTO.getField(), t1);
                    break;
                case numOfRooms:
                    double t2 = gson.fromJson(reportDTO.getContentInGson(), Double.class);
                    service.suggestionChangesApartmentDouble(reportDTO.getAddressID(), ""+reportDTO.getField(), t2);
                    break;

                case address:
                    AddressDTO addressDTO= gson.fromJson(reportDTO.getContentInGson(),AddressDTO.class);
                    service.suggestionChangesAddress(reportDTO.getAddressID(), ""+reportDTO.getField(),addressDTO.getStreet(),addressDTO.getNumOfBuilding(),addressDTO.getNeighborhood());
                    break;
                default:
                    int t3 = gson.fromJson(reportDTO.getContentInGson(), Integer.class);
                    service.suggestionChangesApartmentInt(reportDTO.getAddressID(), ""+reportDTO.getField(), t3);
                    break;
            }
        }
        String jsonString = gson.toJson(true);
        return jsonString;
    }

    @RequestMapping(value = "/hello", method = {RequestMethod.POST, RequestMethod.GET})
    public String hello(@RequestParam String name) {

        return "hello " + name + " !";
    }

    public static void main(String [] args){
        RegularClientController rg = new RegularClientController();
        SearchResults sr = rg.searchApartments(new LinkedList<CategoryQuery>());
        int x=2;
        int z=2;
        int y=2;
    }
}
