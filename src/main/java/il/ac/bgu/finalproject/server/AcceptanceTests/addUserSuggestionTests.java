package il.ac.bgu.finalproject.server.AcceptanceTests;


import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Apartment;
import il.ac.bgu.finalproject.server.Domain.Exceptions.DataBaseFailedException;
import il.ac.bgu.finalproject.server.Domain.NLPHandlers.NLPImp;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class addUserSuggestionTests {
    private static ServiceConnector serviceConnector;


    @BeforeClass
    public static void setup() {
        serviceConnector= new ServiceConnector();
        serviceConnector.getBridge().connectToTestDB();
//        serviceConnector.getBridge().c
        //dbc.connect();
//        dbc.resetAllTables();
    }
    @Test
    public void searchByFloor(){
//        serviceConnector.getBridge().searchApartments()

    }

    @Test
    public void addUserSuggestionTests(){

        try {
            serviceConnector.getBridge().addUserSuggestion("12","cost","1100");
        } catch (DataBaseFailedException e) {
            e.printStackTrace();
        }

    }

    @AfterClass
    public static void endup() {
        serviceConnector.getBridge().disconnectToTestDB();
    }

}
