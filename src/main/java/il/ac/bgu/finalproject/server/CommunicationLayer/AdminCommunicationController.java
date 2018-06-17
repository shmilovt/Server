package il.ac.bgu.finalproject.server.CommunicationLayer;

import com.google.gson.Gson;
import il.ac.bgu.finalproject.server.CommunicationLayer.AdminDTOs.*;
import il.ac.bgu.finalproject.server.CommunicationLayer.DTOs.Converter;
import il.ac.bgu.finalproject.server.CommunicationLayer.DTOs.GroupDTO;
import il.ac.bgu.finalproject.server.CommunicationLayer.DTOs.SearchResultsDTO;
import il.ac.bgu.finalproject.server.CommunicationLayer.DTOs.UserSearchDTO;
import il.ac.bgu.finalproject.server.Domain.Controllers.MyLogger;
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
import java.util.logging.Level;

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
        int ans = 0;
        if (usernamePasswordDTO.getTokenDTO()!=null&&service.login(usernamePasswordDTO.getTokenDTO().getUserName(), usernamePasswordDTO.getTokenDTO().getPassword()) == 1)
            ans = service.changePassword(usernamePasswordDTO.getUserName(), usernamePasswordDTO.getPassword());
        String json = gson.toJson(ans);
        return json;
//        return normalizeAnsWithStatus(ans);
    }

    @RequestMapping(value = "/forgotPassword", method = {RequestMethod.POST, RequestMethod.GET})
    public String forgotPassword(@RequestParam String usernamePasswordString) {
        Gson gson = new Gson();
        UsernamePasswordDTO usernamePasswordDTO = UsernamePasswordDTO.fromJSON(usernamePasswordString);
        int ans = service.forgotPassword(usernamePasswordDTO.getUserName(), usernamePasswordDTO.getPassword());
        String json= gson.toJson(ans);
        return json;
    }

    @RequestMapping(value = "/checkCCUID", method = {RequestMethod.POST, RequestMethod.GET})
    public String checkCCUID(@RequestParam String ccString) {
        Gson gson = new Gson();
        String ccuid= gson.fromJson(ccString,String.class);
        int ans = service.checkCCUID(ccuid);
        String json= gson.toJson(ans);
        return json;
    }

    @RequestMapping(value = "/newPostFromAdmin", method = {RequestMethod.POST, RequestMethod.GET})
    public String newPostFromAdmin(@RequestParam String newPostString) {
        Gson gson = new Gson();
        int ans = 0;
        NewPostDTO newPostDTO = NewPostDTO.fromJSON(newPostString);
        if (newPostDTO.getTokenDTO()!=null&&service.login(newPostDTO.getTokenDTO().getUserName(), newPostDTO.getTokenDTO().getPassword()) == 1)
            ans = service.newPostFromAdmin(newPostDTO.getPublisherName(), newPostDTO.getMessege());
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
    public String search2(@RequestParam String tokenDTOstring) {
        Gson gson = new Gson();
        TokenDTO tokenDTO = gson.fromJson(tokenDTOstring, TokenDTO.class);
        List<SearchRecordDTO> SearchRecordDTOs = null;
        if (tokenDTO!=null&&service.login(tokenDTO.getUserName(), tokenDTO.getPassword()) == 1)
            SearchRecordDTOs = service.getAllUserSearches();
        String json = gson.toJson(SearchRecordDTOs);
        return json;
    }

    @RequestMapping(value = "/getCalculator", method = {RequestMethod.POST, RequestMethod.GET})
    public String getCalculator(@RequestParam String tokenDTOstring) {
        Gson gson = new Gson();
        TokenDTO tokenDTO = gson.fromJson(tokenDTOstring, TokenDTO.class);
        CalculatorDTO calculatorDTO =null;
        if (tokenDTO!=null&&service.login(tokenDTO.getUserName(), tokenDTO.getPassword()) == 1) {
            calculatorDTO = service.getCalcCosts();
            calculatorDTO.setBasicCost(CalculatorCosts.basicCost);
        }
        String json = gson.toJson(calculatorDTO);
        return json;
    }

    @RequestMapping(value = "/insertGroup", method = {RequestMethod.POST, RequestMethod.GET})
    public String insertGroup(@RequestParam String manageGroupsString) {
        Gson gson = new Gson();
        ManageGroupsDTO manageGroupsDTO = ManageGroupsDTO.fromJSON(manageGroupsString);
        int t = 0;
        if (manageGroupsDTO.getTokenDTO()!=null&&service.login(manageGroupsDTO.getTokenDTO().getUserName(), manageGroupsDTO.getTokenDTO().getPassword()) == 1)
            t = service.insertGroup(manageGroupsDTO.getGroupID());
        String json = gson.toJson(t);
        return json;
//        return normalizeAnsWithStatus(t);
    }

    @RequestMapping(value = "/deleteGroup", method = {RequestMethod.POST, RequestMethod.GET})
    public String deleteGroup(@RequestParam String manageGroupsString) {
        Gson gson = new Gson();
        ManageGroupsDTO manageGroupsDTO = ManageGroupsDTO.fromJSON(manageGroupsString);
        int t = 0;
        if (manageGroupsDTO.getTokenDTO()!=null&&service.login(manageGroupsDTO.getTokenDTO().getUserName(), manageGroupsDTO.getTokenDTO().getPassword()) == 1)
            t = service.deleteGroup(manageGroupsDTO.getGroupID());
        String json = gson.toJson(t);
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
        if (calculatorDTO.getTokenDTO()!=null&&service.login(calculatorDTO.getTokenDTO().getUserName(),calculatorDTO.getTokenDTO().getPassword())==1) {
            if (calculatorDTO.isLegal()) {
                service.setNewCalculator(calculatorDTO.getProtectedSpaceCost(), calculatorDTO.getTimeFromUniCost_10(),
                        calculatorDTO.getTimeFromUniCost_20(), calculatorDTO.getTimeFromUniCost_G_20(), calculatorDTO.getNeighborhoodCost_B_Ramot(),
                        calculatorDTO.getNeighborhoodCost_oldV_Wingate(), calculatorDTO.getNeighborhoodCost_D(), calculatorDTO.getNeighborhoodCost_G(),
                        calculatorDTO.getFurnitureCost_full(), calculatorDTO.getFurnitureCost_half(), calculatorDTO.getFurnitureCost_none(),
                        calculatorDTO.getSizeCost_25(), calculatorDTO.getSizeCost_30(), calculatorDTO.getSizeCost_35(), calculatorDTO.getSizeCost_35_up(),
                        calculatorDTO.getRoomatesCost_1(), calculatorDTO.getRoomatesCost_2(), calculatorDTO.getRoomatesCost_3(), calculatorDTO.getRoomatesCost_4(),
                        calculatorDTO.getRoomatesCost_5(), calculatorDTO.getRoomatesCost_6(), calculatorDTO.getGardenCost(), calculatorDTO.getBalconyCost());
                CalculatorCosts.basicCost = calculatorDTO.getBasicCost();
                return gson.toJson(true);
            }
        }
//        else
            return gson.toJson(false);
//        return json;
    }
}
