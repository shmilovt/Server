package il.ac.bgu.finalproject.server.AcceptanceTests;


import il.ac.bgu.finalproject.server.CommunicationLayer.DTOs.GroupDTO;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class loginAndChangePasswordTests {
    private static ServiceConnector serviceConnector;

    @BeforeClass
    public static void setup() {
        serviceConnector= new ServiceConnector();
        serviceConnector.getBridge().connectToTestDB();
        serviceConnector.getBridge().changePassword("admin","123456");
    }


    @Test
    public void loginAndChangePasswordTest(){
        Assert.assertEquals(serviceConnector.getBridge().login("admin","123456"),1);
        serviceConnector.getBridge().changePassword("admin","12345");
        Assert.assertEquals(serviceConnector.getBridge().login("admin","12345"),1);
        Assert.assertEquals(serviceConnector.getBridge().login("admin","123456"),0);
    }

    @AfterClass
    public static void endup() {
        serviceConnector.getBridge().disconnectToTestDB();
    }
}
