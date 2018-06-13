package il.ac.bgu.finalproject.server.AcceptanceTests;


import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Apartment;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils.ResultRecord;
import il.ac.bgu.finalproject.server.Domain.Exceptions.DataBaseFailedException;
import il.ac.bgu.finalproject.server.Domain.NLPHandlers.NLPImp;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class addUserSuggestionTests {
    private static ServiceConnector serviceConnector;


    @BeforeClass
    public static void setup() {
        serviceConnector= new ServiceConnector();
        serviceConnector.getBridge().connectToTestDB();
    }

    @Test
    public void addUserSuggestionTestsCost(){
        serviceConnector.getBridge().suggestionChangesApartmentInt("12","cost",1000);
        ResultRecord resultRecord= serviceConnector.getBridge().ResultRecordFromDB("12");
        Assert.assertEquals(1000,resultRecord.getCost());
        int count=-1;
        while (count>5) {
            try {
                count = serviceConnector.getBridge().addUserSuggestion("12", "cost", "1100");
            } catch (DataBaseFailedException e) {
                e.printStackTrace();
            }
        }
        serviceConnector.getBridge().suggestionChangesApartmentInt("12", "cost", 1100);
        resultRecord= serviceConnector.getBridge().ResultRecordFromDB("12");
        Assert.assertEquals(1100,resultRecord.getCost());
    }

    @Test
    public void addUserSuggestionTestsSize(){
        serviceConnector.getBridge().suggestionChangesApartmentDouble("12","size",70);
        ResultRecord resultRecord= serviceConnector.getBridge().ResultRecordFromDB("12");
        Assert.assertEquals(resultRecord.getSize(),70);
        int count=-1;
        while (count>5) {
            try {
                count = serviceConnector.getBridge().addUserSuggestion("12", "size", "80");
            } catch (DataBaseFailedException e) {
                e.printStackTrace();
            }
        }
        serviceConnector.getBridge().suggestionChangesApartmentDouble("12", "size", 80);
        resultRecord= serviceConnector.getBridge().ResultRecordFromDB("12");
        Assert.assertEquals(resultRecord.getSize(),80);
    }

    @Test
    public void addUserSuggestionTestsNeighborhood(){
        try {
            serviceConnector.getBridge().addressFieldCase("1",false,false,true,"השושנים",70,"השושנים");
        } catch (DataBaseFailedException e) {
            e.printStackTrace();
        }
        ResultRecord resultRecord= serviceConnector.getBridge().ResultRecordFromDB("1");
        Assert.assertEquals(resultRecord.getNeighborhood(),"השושנים");
    }

    @Test
    public void addressFieldCaseAddress(){
        try {
            serviceConnector.getBridge().addressFieldCase("12",true,true,false,"אברהם אבינו",26,"שכונה ג'");
        } catch (DataBaseFailedException e) {
            e.printStackTrace();
        }
        ResultRecord resultRecord= serviceConnector.getBridge().ResultRecordFromDB("12");
        Assert.assertEquals(resultRecord.getStreet(),"אברהם אבינו");
        Assert.assertEquals(resultRecord.getNumber(),26);
    }

    @AfterClass
    public static void endup() {
        serviceConnector.getBridge().disconnectToTestDB();
    }

}
