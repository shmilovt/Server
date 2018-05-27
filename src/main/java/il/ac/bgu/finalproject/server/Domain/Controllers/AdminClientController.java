package il.ac.bgu.finalproject.server.Domain.Controllers;

import il.ac.bgu.finalproject.server.CommunicationLayer.AdminDTOs.ArraySearchRecordDTO;
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

    public CalculatorCosts getCalcCosts(){
        //I think we should build an object.. im not sure getInstance() is good enough
        return CalculatorCosts.getInstance();
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
