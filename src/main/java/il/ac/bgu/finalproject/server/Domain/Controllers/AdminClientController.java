package il.ac.bgu.finalproject.server.Domain.Controllers;

import il.ac.bgu.finalproject.server.CommunicationLayer.AdminDTOs.ArraySearchRecordDTO;
import il.ac.bgu.finalproject.server.CommunicationLayer.AdminDTOs.CalculatorDTO;
import il.ac.bgu.finalproject.server.CommunicationLayer.DTOs.GroupDTO;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Post;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils.CalculatorCosts;
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
    public ArraySearchRecordDTO getAllUserSearches(){
        return dataBaseRequestController.getAllUserSearches();
    }
}
