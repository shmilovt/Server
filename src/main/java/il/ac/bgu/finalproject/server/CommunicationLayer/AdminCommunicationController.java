package il.ac.bgu.finalproject.server.CommunicationLayer;

import com.google.gson.Gson;
import il.ac.bgu.finalproject.server.CommunicationLayer.AdminDTOs.*;
import il.ac.bgu.finalproject.server.CommunicationLayer.DTOs.Converter;
import il.ac.bgu.finalproject.server.CommunicationLayer.DTOs.GroupDTO;
import il.ac.bgu.finalproject.server.Domain.Controllers.MyLogger;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.Encryption;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils.CalculatorCosts;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils.SearchResults;
import il.ac.bgu.finalproject.server.ServiceLayer.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
//import javax.annotation.PostConstruct;
//import javax.ws.rs.Consumes;
//import javax.ws.rs.GET;
//import javax.ws.rs.POST;
//import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//@Path("/hello")

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

//    @POST
    @RequestMapping(value = "/login", method = {RequestMethod.POST, RequestMethod.GET})
    public String login(@RequestParam String usernamePasswordString) {
        Gson gson = new Gson();
        UsernamePasswordDTO usernamePasswordDTO = UsernamePasswordDTO.fromJSON(usernamePasswordString);
        int ans = 0;
        MyLogger.getInstance().log(Level.SEVERE, usernamePasswordDTO.getPassword(), "original");
        MyLogger.getInstance().log(Level.SEVERE, Encryption.hashPass(usernamePasswordDTO.getPassword()), "encrypted");
        ans = service.login(usernamePasswordDTO.getUserName(),
                Encryption.hashPass(usernamePasswordDTO.getPassword()));
        String json = gson.toJson(ans);
        return json;
//        return normalizeAnsWithStatus(ans);
    }

    @RequestMapping(value = "/changePassword", method = {RequestMethod.POST, RequestMethod.GET})
    public String changePassword(@RequestParam String usernamePasswordString) {
        Gson gson = new Gson();
        UsernamePasswordDTO usernamePasswordDTO = UsernamePasswordDTO.fromJSON(usernamePasswordString);
        int ans = 0;
        if (usernamePasswordDTO.getTokenDTO()!=null&&service.login(usernamePasswordDTO.getTokenDTO().getUserName(), Encryption.hashPass(usernamePasswordDTO.getTokenDTO().getPassword())) == 1) {
            ans = service.changePassword(usernamePasswordDTO.getUserName(), Encryption.hashPass(usernamePasswordDTO.getPassword()));
        }
        String json = gson.toJson(ans);
        return json;
//        return normalizeAnsWithStatus(ans);
    }

    @RequestMapping(value = "/forgotPassword", method = {RequestMethod.POST, RequestMethod.GET})
    public String forgotPassword(@RequestParam String usernamePasswordString) {
        Gson gson = new Gson();
        UsernamePasswordDTO usernamePasswordDTO = UsernamePasswordDTO.fromJSON(usernamePasswordString);
        int ans = 0;
        ans = service.forgotPassword(usernamePasswordDTO.getUserName(), usernamePasswordDTO.getPassword());
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

//    @POST

    @RequestMapping(value = "/publishPost", method = {RequestMethod.POST, RequestMethod.GET})
    public String publishPost(@RequestParam String newPostString) {
        MyLogger.getInstance().log(Level.SEVERE, "publishPost", "hg");
        Gson gson = new Gson();
        int ans = 0;
        NewPostDTO newPostDTO = NewPostDTO.fromJSON(newPostString);
        if ((newPostDTO.getTokenDTO() != null) &&
                (service.login(newPostDTO.getTokenDTO().getUserName(), Encryption.hashPass(newPostDTO.getTokenDTO().getPassword())) == 1)) {
            ans = service.newPostFromAdmin(newPostDTO.getPublisherName(), newPostDTO.getMessege());
        }
        String json = gson.toJson(ans);
        return json;
    }

    @PostMapping("/nofar")
    public int nofar(@RequestBody Map<String,String> body) {
        MyLogger.getInstance().log(Level.SEVERE, "hello Nofar", "hg");
        MyLogger.getInstance().log(Level.SEVERE, "body: "+body.get("text"), "hg");
//        Gson gson = new Gson();
//        int ans = 0;
//        NewPostDTO newPostDTO = NewPostDTO.fromJSON(newPostString);
//        if ((newPostDTO.getTokenDTO() != null) &&
//                (service.login(newPostDTO.getTokenDTO().getUserName(), Encryption.hashPass(newPostDTO.getTokenDTO().getPassword())) == 1)) {
//            ans = service.newPostFromAdmin(newPostDTO.getPublisherName(), newPostDTO.getMessege());
//        }
//        String json = gson.toJson(ans);
//        return json;
        return 1;
    }

    @RequestMapping(value = "/newPostFromAdmin", method = {RequestMethod.POST, RequestMethod.GET})
    public String newPostFromAdmin(@RequestParam String newPostString) {
        MyLogger.getInstance().log(Level.SEVERE,"hello","hg");
//        MyLogger.getInstance().log(Level.SEVERE,newPostString,"hg");
        Gson gson = new Gson();
        int ans = 0;
//        try {
        NewPostDTO newPostDTO = NewPostDTO.fromJSON(newPostString);
        if ((newPostDTO.getTokenDTO() != null) &&
                (service.login(newPostDTO.getTokenDTO().getUserName(), Encryption.hashPass(newPostDTO.getTokenDTO().getPassword())) == 1)) {
            ans = service.newPostFromAdmin(newPostDTO.getPublisherName(), newPostDTO.getMessege());
        }
////        }
////        catch (Exception e){
////            MyLogger.getInstance().log(Level.SEVERE,"weeee haveee a problemmmmmm","hj");
////
////            MyLogger.getInstance().log(Level.SEVERE,newPostString,"hj");
////            gson.toJson(e.getStackTrace());
////            MyLogger.getInstance().log(Level.SEVERE, e.getStackTrace()+"","");
////        }
        String json = gson.toJson(ans);
        return json;
    }

    @RequestMapping(value = "/getAllGroups", method = {RequestMethod.POST, RequestMethod.GET})
    public String search1(@RequestParam String tokenDTOstring) {
        Gson gson = new Gson();
        TokenDTO tokenDTO = gson.fromJson(tokenDTOstring, TokenDTO.class);
        List<GroupDTO> groupDTOList =null;
        if ((tokenDTO!=null) && (service.login(tokenDTO.getUserName(), Encryption.hashPass(tokenDTO.getPassword())) == 1))
            groupDTOList = service.GetAllGroups();
        String json = gson.toJson(groupDTOList);
        return json;
    }

    @RequestMapping(value = "/getAllUserSearches", method = {RequestMethod.POST, RequestMethod.GET})
    public String search2(@RequestParam String tokenDTOstring) {
        Gson gson = new Gson();
        TokenDTO tokenDTO = gson.fromJson(tokenDTOstring, TokenDTO.class);
        List<SearchRecordDTO> SearchRecordDTOs = null;
        if ((tokenDTO!=null) && (service.login(tokenDTO.getUserName(), Encryption.hashPass(tokenDTO.getPassword())) == 1))
            SearchRecordDTOs = service.getAllUserSearches();
        String json = gson.toJson(SearchRecordDTOs);
        return json;
    }

    @RequestMapping(value = "/getCalculator", method = {RequestMethod.POST, RequestMethod.GET})
    public String getCalculator(@RequestParam String tokenDTOstring) {
        Gson gson = new Gson();
        TokenDTO tokenDTO = gson.fromJson(tokenDTOstring, TokenDTO.class);
        CalculatorDTO calculatorDTO =null;
        if (tokenDTO!=null&&service.login(tokenDTO.getUserName(), Encryption.hashPass(tokenDTO.getPassword())) == 1) {
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
        if (manageGroupsDTO.getTokenDTO()!=null&&service.login(manageGroupsDTO.getTokenDTO().getUserName(), Encryption.hashPass(manageGroupsDTO.getTokenDTO().getPassword())) == 1)
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
        if (manageGroupsDTO.getTokenDTO()!=null&&service.login(manageGroupsDTO.getTokenDTO().getUserName(), Encryption.hashPass(manageGroupsDTO.getTokenDTO().getPassword())) == 1)
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
        if (calculatorDTO.getTokenDTO()!=null&&service.login(calculatorDTO.getTokenDTO().getUserName(),Encryption.hashPass(calculatorDTO.getTokenDTO().getPassword()))==1) {
            if (calculatorDTO.isLegal()) {
                service.setNewCalculator(calculatorDTO.getProtectedSpaceCost(), calculatorDTO.getTimeFromUniCost_10(),
                        calculatorDTO.getTimeFromUniCost_20(), calculatorDTO.getTimeFromUniCost_G_20(), calculatorDTO.getNeighborhoodCost_B_Ramot(),
                        calculatorDTO.getNeighborhoodCost_oldV_Wingate(), calculatorDTO.getNeighborhoodCost_D(), calculatorDTO.getNeighborhoodCost_G(),
                        calculatorDTO.getFurnitureCost_full(), calculatorDTO.getFurnitureCost_half(), calculatorDTO.getFurnitureCost_none(),
                        calculatorDTO.getSizeCost_25(), calculatorDTO.getSizeCost_30(), calculatorDTO.getSizeCost_35(), calculatorDTO.getSizeCost_35_up(),
                        calculatorDTO.getRoomatesCost_1(), calculatorDTO.getRoomatesCost_2(), calculatorDTO.getRoomatesCost_3(), calculatorDTO.getRoomatesCost_4(),
                        calculatorDTO.getRoomatesCost_5(), calculatorDTO.getRoomatesCost_6(), calculatorDTO.getGardenCost(), calculatorDTO.getBalconyCost());
                CalculatorCosts.basicCost = calculatorDTO.getBasicCost();
                return gson.toJson(1);
            }
            else return gson.toJson(0);

        }
        else
            return gson.toJson(-1);
//        return json;
    }
}
