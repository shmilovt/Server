package il.ac.bgu.finalproject.server.Domain.Controllers;

import il.ac.bgu.finalproject.server.CommunicationLayer.AdminDTOs.ArraySearchRecordDTO;
import il.ac.bgu.finalproject.server.CommunicationLayer.AdminDTOs.CalculatorDTO;
import il.ac.bgu.finalproject.server.CommunicationLayer.AdminDTOs.SearchRecordDTO;
import il.ac.bgu.finalproject.server.CommunicationLayer.DTOs.GroupDTO;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Post;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils.CalculatorCosts;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils.SearchResults;
import il.ac.bgu.finalproject.server.Domain.Exceptions.DataBaseFailedException;
import il.ac.bgu.finalproject.server.Domain.Exceptions.NoUserNameException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AdminClientController {
    DataBaseRequestController dataBaseRequestController;
    ServerController serverController;
    private static final String dateFormat = "yyyy/MM/dd HH:mm:ss";
    private final String postFromAdminID= "postFromAdminID";


    public AdminClientController() {
        dataBaseRequestController = new DataBaseRequestController();
        ServerController serverController= new ServerController();
    }


    public boolean login(String username, String password) throws NoUserNameException {
        if (username == null || password == null)
            throw new NoUserNameException(username);
        return dataBaseRequestController.login(username, password);
    }

    public boolean changePassword(String username, String password) {
        return dataBaseRequestController.changePassword(username, password);
    }

    public CalculatorDTO getCalcCosts(){
        //I think we should build an object.. im not sure getInstance() is good enough
        CalculatorDTO calculatorDTO= new CalculatorDTO();
        calculatorDTO.setProtectedSpaceCost(CalculatorCosts.getInstance().getProtectedSpaceCost());
        calculatorDTO.setTimeFromUniCost_10(CalculatorCosts.getInstance().getTimeFromUniCost_10());
        calculatorDTO.setTimeFromUniCost_20(CalculatorCosts.getInstance().getTimeFromUniCost_20());
        calculatorDTO.setTimeFromUniCost_G_20(CalculatorCosts.getInstance().getTimeFromUniCost_G_20());
        calculatorDTO.setNeighborhoodCost_B_Ramot(CalculatorCosts.getInstance().getNeighborhoodCost_B_Ramot());
        calculatorDTO.setNeighborhoodCost_oldV_Wingate(CalculatorCosts.getInstance().getNeighborhoodCost_oldV_Wingate());
        calculatorDTO.setNeighborhoodCost_D(CalculatorCosts.getInstance().getNeighborhoodCost_D());
        calculatorDTO.setNeighborhoodCost_G(CalculatorCosts.getInstance().getNeighborhoodCost_G());
        calculatorDTO.setFurnitureCost_full(CalculatorCosts.getInstance().getFurnitureCost_full());
        calculatorDTO.setFurnitureCost_half(CalculatorCosts.getInstance().getFurnitureCost_half());
        calculatorDTO.setFurnitureCost_none(CalculatorCosts.getInstance().getFurnitureCost_none());
        calculatorDTO.setSizeCost_25(CalculatorCosts.getInstance().getSizeCost_25());
        calculatorDTO.setSizeCost_30(CalculatorCosts.getInstance().getSizeCost_30());
        calculatorDTO.setSizeCost_35(CalculatorCosts.getInstance().getSizeCost_35());
        calculatorDTO.setSizeCost_35_up(CalculatorCosts.getInstance().getSizeCost_35_up());
        calculatorDTO.setRoomatesCost_1(CalculatorCosts.getInstance().getRoomatesCost_1());
        calculatorDTO.setRoomatesCost_2(CalculatorCosts.getInstance().getRoomatesCost_2());
        calculatorDTO.setRoomatesCost_3(CalculatorCosts.getInstance().getRoomatesCost_3());
        calculatorDTO.setRoomatesCost_4(CalculatorCosts.getInstance().getRoomatesCost_4());
        calculatorDTO.setRoomatesCost_5(CalculatorCosts.getInstance().getRoomatesCost_5());
        calculatorDTO.setRoomatesCost_6(CalculatorCosts.getInstance().getRoomatesCost_6());
        calculatorDTO.setGardenCost(CalculatorCosts.getInstance().getGardenCost());
        calculatorDTO.setBalconyCost(CalculatorCosts.getInstance().getBalconyCost());
        return calculatorDTO;
    }

    public void setNewCalculator(int protectedSpaceCost, int timeFromUniCost_10, int timeFromUniCost_20,
                                 int timeFromUniCost_G_20, int neighborhoodCost_B_Ramot,
                                 int neighborhoodCost_oldV_Wingate, int neighborhoodCost_D,
                                 int neighborhoodCost_G, int furnitureCost_full, int furnitureCost_half,
                                 int furnitureCost_none, int sizeCost_25, int sizeCost_30, int sizeCost_35,
                                 int sizeCost_35_up, int roomatesCost_1, int roomatesCost_2, int roomatesCost_3,
                                 int roomatesCost_4, int roomatesCost_5, int roomatesCost_6, int gardenCost,
                                 int balconyCost) {
        CalculatorCosts.getInstance().setCalculator(protectedSpaceCost, timeFromUniCost_10, timeFromUniCost_20,
                timeFromUniCost_G_20, neighborhoodCost_B_Ramot,
                neighborhoodCost_oldV_Wingate, neighborhoodCost_D,
                neighborhoodCost_G, furnitureCost_full, furnitureCost_half,
                furnitureCost_none, sizeCost_25, sizeCost_30, sizeCost_35,
                sizeCost_35_up, roomatesCost_1, roomatesCost_2, roomatesCost_3,
                roomatesCost_4, roomatesCost_5, roomatesCost_6, gardenCost, balconyCost);
//        CalculatorCosts.getInstance().setProtectedSpaceCost(protectedSpaceCost);
//        CalculatorCosts.getInstance().setTimeFromUniCost_10(timeFromUniCost_10);
//        CalculatorCosts.getInstance().setTimeFromUniCost_20(timeFromUniCost_20);
//        CalculatorCosts.getInstance().setTimeFromUniCost_G_20(calculator.getTimeFromUniCost_G_20());
//        CalculatorCosts.getInstance().setNeighborhoodCost_B_Ramot(calculator.getNeighborhoodCost_B_Ramot());
//        CalculatorCosts.getInstance().setNeighborhoodCost_oldV_Wingate(calculator.getNeighborhoodCost_oldV_Wingate());
//        CalculatorCosts.getInstance().setNeighborhoodCost_D(calculator.getNeighborhoodCost_D());
//        CalculatorCosts.getInstance().setNeighborhoodCost_G(calculator.getNeighborhoodCost_G());
//        CalculatorCosts.getInstance().setFurnitureCost_full(calculator.getFurnitureCost_full());
//        CalculatorCosts.getInstance().setFurnitureCost_half(calculator.getFurnitureCost_half());
//        CalculatorCosts.getInstance().setFurnitureCost_none(calculator.getFurnitureCost_none());
//        CalculatorCosts.getInstance().setSizeCost_25(calculator.getSizeCost_25());
//        CalculatorCosts.getInstance().setSizeCost_30(calculator.getSizeCost_30());
//        CalculatorCosts.getInstance().setSizeCost_35(calculator.getSizeCost_35());
//        CalculatorCosts.getInstance().setSizeCost_35_up(calculator.getSizeCost_35_up());
//        CalculatorCosts.getInstance().setRoomatesCost_1(calculator.getRoomatesCost_1());
//        CalculatorCosts.getInstance().setRoomatesCost_2(calculator.getRoomatesCost_2());
//        CalculatorCosts.getInstance().setRoomatesCost_3(calculator.getRoomatesCost_3());
//        CalculatorCosts.getInstance().setRoomatesCost_4(calculator.getRoomatesCost_4());
//        CalculatorCosts.getInstance().setRoomatesCost_5(calculator.getRoomatesCost_5());
//        CalculatorCosts.getInstance().setRoomatesCost_6(calculator.getRoomatesCost_6());
//        CalculatorCosts.getInstance().setGardenCost(calculator.getGardenCost());
//        CalculatorCosts.getInstance().setBalconyCost(calculator.getBalconyCost());
    }

    public boolean newPostFromAdmin(String nameOfPublisher, String messege) throws DataBaseFailedException {
        int t= dataBaseRequestController.getConstValue(postFromAdminID);
        Post post= new Post(""+t, new Date(), nameOfPublisher, messege, "-1");
        serverController.newPost(post); //is it correct to use ServerController?
        dataBaseRequestController.setConstValue(postFromAdminID,t+1);
        return true;
    }

    public void insertGroup(String groupID) throws DataBaseFailedException{
        dataBaseRequestController.insertGroup(groupID, "group");
    }
    public void deleteGroup(String groupID) throws DataBaseFailedException{
        dataBaseRequestController.deleteGroup(groupID);
    }
    public List<GroupDTO> GetAllGroups(){
        return dataBaseRequestController.getAllGroups();
    }
    public List<SearchRecordDTO> getAllUserSearches(){
        return dataBaseRequestController.getAllUserSearches();
    }

    public SearchResults getAllApartments() {
        return dataBaseRequestController.allResultsRecordsFromDB();
    }
}
