package il.ac.bgu.finalproject.server.CommunicationLayer;

import com.google.gson.Gson;
import il.ac.bgu.finalproject.server.CommunicationLayer.AdminDTOs.*;
import il.ac.bgu.finalproject.server.CommunicationLayer.DTOs.Converter;
import il.ac.bgu.finalproject.server.CommunicationLayer.DTOs.GroupDTO;
import il.ac.bgu.finalproject.server.CommunicationLayer.DTOs.SearchResultsDTO;
import il.ac.bgu.finalproject.server.CommunicationLayer.DTOs.UserSearchDTO;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils.SearchResults;
import il.ac.bgu.finalproject.server.Domain.Exceptions.DataBaseFailedException;
import il.ac.bgu.finalproject.server.ServiceLayer.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminCommunicationController {

    @Autowired
    private IService service;
    @Autowired
    private Converter converter;

    @RequestMapping(value = "/login", method = {RequestMethod.POST, RequestMethod.GET})
    public String login(String usernamePasswordString) {
        UsernamePasswordDTO usernamePasswordDTO = UsernamePasswordDTO.fromJSON(usernamePasswordString);
        boolean ans = service.login(usernamePasswordDTO.getUserName(), usernamePasswordDTO.getPassword());
        Gson gson = new Gson();
        String json = gson.toJson(ans);
        return json;
    }

    @RequestMapping(value = "/changePassword", method = {RequestMethod.POST, RequestMethod.GET})
    public String changePassword(String usernamePasswordString) {
        UsernamePasswordDTO usernamePasswordDTO = UsernamePasswordDTO.fromJSON(usernamePasswordString);
        boolean ans = service.changePassword(usernamePasswordDTO.getUserName(), usernamePasswordDTO.getPassword());
        Gson gson = new Gson();
        String json = gson.toJson(ans);
        return json;
    }

    @RequestMapping(value = "/newPostFromAdmin", method = {RequestMethod.POST, RequestMethod.GET})
    public String newPostFromAdmin(String usernamePasswordString) {
        NewPostDTO usernamePasswordDTO = NewPostDTO.fromJSON(usernamePasswordString);
        boolean ans = service.newPostFromAdmin(usernamePasswordDTO.getPublisherName(), usernamePasswordDTO.getPublisherName());
        Gson gson = new Gson();
        String json = gson.toJson(ans);
        return json;
    }

    @RequestMapping(value = "/getAllGroups", method = {RequestMethod.POST, RequestMethod.GET})
    public String search1() {
        List<GroupDTO> groupDTOList = service.GetAllGroups();
        Gson gson = new Gson();
        String json = gson.toJson(groupDTOList);
        return json;
    }

    @RequestMapping(value = "/getAllUserSearches", method = {RequestMethod.POST, RequestMethod.GET})
    public String search2() {
        ArraySearchRecordDTO arraySearchRecordDTO = service.getAllUserSearches();
        Gson gson = new Gson();
        String json = gson.toJson(arraySearchRecordDTO);
        return json;
    }

    @RequestMapping(value = "/getCalculator", method = {RequestMethod.POST, RequestMethod.GET})
    public String getCalculator() {
        CalculatorDTO calculatorDTO = service.getCalcCosts();
        Gson gson = new Gson();
        String json = gson.toJson(calculatorDTO);
        return json;
    }

    @RequestMapping(value = "/insertGroup", method = {RequestMethod.POST, RequestMethod.GET})
    public String insertGroup(String groupIdString) {
        Gson gson = new Gson();
        String groupID = gson.fromJson(groupIdString, String.class);
        try {
            service.insertGroup(groupID);
        } catch (DataBaseFailedException e) {
            String json = gson.toJson(false);
            return json;
        }
        String json = gson.toJson(true);
        return json;
    }

    @RequestMapping(value = "/deleteGroup", method = {RequestMethod.POST, RequestMethod.GET})
    public String deleteGroup(String groupIdString) {
        Gson gson = new Gson();
        String groupID = gson.fromJson(groupIdString, String.class);
        try {
            service.deleteGroup(groupID);
        } catch (DataBaseFailedException e) {
            String json = gson.toJson(false);
            return json;
        }
        String json = gson.toJson(true);
        return json;
    }

    @RequestMapping(value = "/getAllApartments", method = {RequestMethod.POST, RequestMethod.GET})
    public String getAllApartments() {
        SearchResults searchResult = service.getAllApartments();
        List<ApartmentDTO> apartmentDTOs = converter.convertToDTOAdminApartment(searchResult);
        Gson gson = new Gson();
        String json = gson.toJson(apartmentDTOs);
        return json;
    }

    @RequestMapping(value = "/setNewCalculator", method = {RequestMethod.POST, RequestMethod.GET})
    public String setNewCalculator(String calcolatorString) {
        Gson gson = new Gson();
        CalculatorDTO calculatorDTO = gson.fromJson(calcolatorString, CalculatorDTO.class);
        service.setNewCalculator(calculatorDTO.getProtectedSpaceCost(), calculatorDTO.getTimeFromUniCost_10(),
                calculatorDTO.getTimeFromUniCost_20(), calculatorDTO.getTimeFromUniCost_G_20(), calculatorDTO.getNeighborhoodCost_B_Ramot(),
                calculatorDTO.getNeighborhoodCost_oldV_Wingate(), calculatorDTO.getNeighborhoodCost_D(), calculatorDTO.getNeighborhoodCost_G(),
                calculatorDTO.getFurnitureCost_full(), calculatorDTO.getFurnitureCost_half(), calculatorDTO.getFurnitureCost_none(),
                calculatorDTO.getSizeCost_25(), calculatorDTO.getSizeCost_30(), calculatorDTO.getSizeCost_35(), calculatorDTO.getSizeCost_35_up(),
                calculatorDTO.getRoomatesCost_1(), calculatorDTO.getRoomatesCost_2(), calculatorDTO.getRoomatesCost_3(), calculatorDTO.getRoomatesCost_4(),
                calculatorDTO.getRoomatesCost_5(), calculatorDTO.getRoomatesCost_6(), calculatorDTO.getGardenCost(), calculatorDTO.getBalconyCost());


        String json = gson.toJson(true);
        return json;
    }
}
