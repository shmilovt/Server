package il.ac.bgu.finalproject.server.AcceptanceTests;


import il.ac.bgu.finalproject.server.CommunicationLayer.AdminDTOs.CalculatorDTO;
import il.ac.bgu.finalproject.server.CommunicationLayer.DTOs.GroupDTO;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class groupsManagesTests {
    private static ServiceConnector serviceConnector;

    @BeforeClass
    public static void setup() {
        serviceConnector= new ServiceConnector();
        serviceConnector.getBridge().connectToTestDB();
    }

    @Test
    public void groupsManagesTests(){
        GroupDTO groupDTO = new GroupDTO();
        String groupID= "1996";
        groupDTO.setGroupID(groupID);

        serviceConnector.getBridge().insertGroup(groupID);
        List<GroupDTO> groupDTOListNew= serviceConnector.getBridge().GetAllGroups();
        Assert.assertTrue(groupExist(groupDTOListNew, groupDTO));

        serviceConnector.getBridge().deleteGroup(groupID);
        groupDTOListNew= serviceConnector.getBridge().GetAllGroups();
        Assert.assertFalse(groupExist(groupDTOListNew, groupDTO));
    }

    public boolean groupExist(List<GroupDTO> groupDTOList, GroupDTO groupDTO){
        String groupID= groupDTO.getGroupID();
        for (GroupDTO item: groupDTOList){
            if (item.getGroupID().equals(groupID))
                return true;
        }
        return false;
    }

    @AfterClass
    public static void endup() {
        serviceConnector.getBridge().disconnectToTestDB();
    }

}
