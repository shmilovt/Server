package il.ac.bgu.finalproject.server.CommunicationLayer;

import com.google.gson.Gson;
import il.ac.bgu.finalproject.server.CommunicationLayer.AdminDTOs.*;
import il.ac.bgu.finalproject.server.CommunicationLayer.DTOs.Converter;
import il.ac.bgu.finalproject.server.CommunicationLayer.DTOs.GroupDTO;
import il.ac.bgu.finalproject.server.CommunicationLayer.DTOs.SearchResultsDTO;
import il.ac.bgu.finalproject.server.CommunicationLayer.DTOs.UserSearchDTO;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils.CalculatorCosts;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils.SearchResults;
import il.ac.bgu.finalproject.server.Domain.Exceptions.DataBaseFailedException;
import il.ac.bgu.finalproject.server.ServiceLayer.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminCommunicationController {

    @Autowired
    private IService service;
    @Autowired
    private Converter converter;

    public String normalizeAnsWithStatus(int ans){
        Gson gson = new Gson();
        String json;
        if (ans==1) {
            json = gson.toJson(true);
            ResponseEntity.status(200).body(json);
        } else if (ans==0){
            json = gson.toJson(false);
            ResponseEntity.status(430).body(json);}
        else{
            json = gson.toJson(false);
            ResponseEntity.status(200).body(json);
        }
        return json;
    }

    @RequestMapping(value = "/login", method = {RequestMethod.POST, RequestMethod.GET})
    public String login(@RequestParam String usernamePasswordString) {
        Gson gson = new Gson();
        UsernamePasswordDTO usernamePasswordDTO = UsernamePasswordDTO.fromJSON(usernamePasswordString);
        int ans = service.login(usernamePasswordDTO.getUserName(), usernamePasswordDTO.getPassword());
        String json= gson.toJson(ans);
        return json;
//        return normalizeAnsWithStatus(ans);
    }

    @RequestMapping(value = "/changePassword", method = {RequestMethod.POST, RequestMethod.GET})
    public String changePassword(@RequestParam String usernamePasswordString) {
        Gson gson = new Gson();
        UsernamePasswordDTO usernamePasswordDTO = UsernamePasswordDTO.fromJSON(usernamePasswordString);
        int ans = service.changePassword(usernamePasswordDTO.getUserName(), usernamePasswordDTO.getPassword());
        String json= gson.toJson(ans);
        return json;
//        return normalizeAnsWithStatus(ans);
    }

    @RequestMapping(value = "/newPostFromAdmin", method = {RequestMethod.POST, RequestMethod.GET})
    public String newPostFromAdmin(@RequestParam String usernamePasswordString) {
        NewPostDTO usernamePasswordDTO = NewPostDTO.fromJSON(usernamePasswordString);
        int ans = service.newPostFromAdmin(usernamePasswordDTO.getPublisherName(), usernamePasswordDTO.getMessege());
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
        List<SearchRecordDTO> SearchRecordDTOs = service.getAllUserSearches();
        Gson gson = new Gson();
        String json = gson.toJson(SearchRecordDTOs);
        return json;
    }

    @RequestMapping(value = "/getCalculator", method = {RequestMethod.POST, RequestMethod.GET})
    public String getCalculator() {
        CalculatorDTO calculatorDTO = service.getCalcCosts();
        calculatorDTO.setBasicCost(CalculatorCosts.basicCost);
        Gson gson = new Gson();
        String json = gson.toJson(calculatorDTO);
        return json;
    }

    @RequestMapping(value = "/insertGroup", method = {RequestMethod.POST, RequestMethod.GET})
    public String insertGroup(@RequestParam String groupIdString) {
        Gson gson = new Gson();
        String groupID = gson.fromJson(groupIdString, String.class);
        int t= service.insertGroup(groupID);
        String json= gson.toJson(t);
        return json;
//        return normalizeAnsWithStatus(t);
    }

    @RequestMapping(value = "/deleteGroup", method = {RequestMethod.POST, RequestMethod.GET})
    public String deleteGroup(@RequestParam String groupIdString) {
        Gson gson = new Gson();
        String groupID = gson.fromJson(groupIdString, String.class);
        int t= service.deleteGroup(groupID);
        String json= gson.toJson(t);
        return json;
//        return normalizeAnsWithStatus(t);
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
    public String setNewCalculator(@RequestParam String calcolatorString) {
        Gson gson = new Gson();
//        String json;
        CalculatorDTO calculatorDTO = gson.fromJson(calcolatorString, CalculatorDTO.class);
        if (calculatorDTO.isLegal()) {
            service.setNewCalculator(calculatorDTO.getProtectedSpaceCost(), calculatorDTO.getTimeFromUniCost_10(),
                    calculatorDTO.getTimeFromUniCost_20(), calculatorDTO.getTimeFromUniCost_G_20(), calculatorDTO.getNeighborhoodCost_B_Ramot(),
                    calculatorDTO.getNeighborhoodCost_oldV_Wingate(), calculatorDTO.getNeighborhoodCost_D(), calculatorDTO.getNeighborhoodCost_G(),
                    calculatorDTO.getFurnitureCost_full(), calculatorDTO.getFurnitureCost_half(), calculatorDTO.getFurnitureCost_none(),
                    calculatorDTO.getSizeCost_25(), calculatorDTO.getSizeCost_30(), calculatorDTO.getSizeCost_35(), calculatorDTO.getSizeCost_35_up(),
                    calculatorDTO.getRoomatesCost_1(), calculatorDTO.getRoomatesCost_2(), calculatorDTO.getRoomatesCost_3(), calculatorDTO.getRoomatesCost_4(),
                    calculatorDTO.getRoomatesCost_5(), calculatorDTO.getRoomatesCost_6(), calculatorDTO.getGardenCost(), calculatorDTO.getBalconyCost());
            CalculatorCosts.basicCost=calculatorDTO.getBasicCost();
            return gson.toJson(true);
        }
        else
            return gson.toJson(false);
//        return json;
    }
}
