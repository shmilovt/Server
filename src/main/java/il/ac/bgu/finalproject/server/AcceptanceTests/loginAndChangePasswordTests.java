package il.ac.bgu.finalproject.server.AcceptanceTests;


import il.ac.bgu.finalproject.server.CommunicationLayer.DTOs.GroupDTO;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.Encryption;
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
//        serviceConnector.getBridge().changePassword("admin","123456");
    }


    @Test
    public void loginAndChangePasswordTest(){
        serviceConnector.getBridge().changePassword("admin","123456");
        Assert.assertEquals(serviceConnector.getBridge().login("admin","123456"),1);
        Assert.assertEquals(serviceConnector.getBridge().login("admin","12346"),0);
        Assert.assertEquals(serviceConnector.getBridge().login("admina","123456"),0);
        Assert.assertEquals(serviceConnector.getBridge().login("admin","123456"),1);
        serviceConnector.getBridge().changePassword("admin","12345");
        Assert.assertEquals(serviceConnector.getBridge().login("admin","12345"),1);
        Assert.assertEquals(serviceConnector.getBridge().login("admin","123456"),0);
    }

    @Test
    public void loginAndChangePasswordTestEnc(){
        serviceConnector.getBridge().changePassword("admin", Encryption.hashPass("123456"));
        Assert.assertEquals(serviceConnector.getBridge().login("admin",Encryption.hashPass("123456")),1);
        Assert.assertEquals(serviceConnector.getBridge().login("admin",Encryption.hashPass("12346")),0);
        Assert.assertEquals(serviceConnector.getBridge().login("admina",Encryption.hashPass("123456")),0);
        Assert.assertEquals(serviceConnector.getBridge().login("admin",Encryption.hashPass("123456")),1);
        serviceConnector.getBridge().changePassword("admin",Encryption.hashPass("12345"));
        Assert.assertEquals(serviceConnector.getBridge().login("admin",Encryption.hashPass("12345")),1);
        Assert.assertEquals(serviceConnector.getBridge().login("admin",Encryption.hashPass("123456")),0);
        serviceConnector.getBridge().changePassword("admin", Encryption.hashPass("123456"));

//        serviceConnector.getBridge().disconnectToTestDB();
//        Assert.assertEquals(serviceConnector.getBridge().login("admin",Encryption.hashPass("123456")),1);
//        serviceConnector.getBridge().connectToTestDB();
    }

    @AfterClass
    public static void endup() {
        serviceConnector.getBridge().disconnectToTestDB();
    }
}
