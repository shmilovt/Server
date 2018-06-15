package il.ac.bgu.finalproject.server.AcceptanceTests;


import il.ac.bgu.finalproject.server.CommunicationLayer.AdminDTOs.CalculatorDTO;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils.CalculatorCosts;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils.ResultRecord;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class calculatorAccessTests {
    private static ServiceConnector serviceConnector;

    @BeforeClass
    public static void setup() {
        serviceConnector= new ServiceConnector();
        serviceConnector.getBridge().connectToTestDB();
    }

    @Test
    public void calculatorAccessTest(){
        serviceConnector.getBridge().setNewCalculator(1,2,3,4,5,6,7,8,
                9,10,11,12,13,14,15,16,17,18,19,20,21,
                22,23);
        CalculatorDTO calculatorCosts= serviceConnector.getBridge().getCalcCosts();
        Assert.assertEquals(calculatorCosts.getProtectedSpaceCost(),1);
        Assert.assertEquals(calculatorCosts.getTimeFromUniCost_10(),2);
        Assert.assertEquals(calculatorCosts.getTimeFromUniCost_20(),3);
        Assert.assertEquals(calculatorCosts.getTimeFromUniCost_G_20(),4);
        Assert.assertEquals(calculatorCosts.getNeighborhoodCost_B_Ramot(),5);
        Assert.assertEquals(calculatorCosts.getNeighborhoodCost_oldV_Wingate(),6);
        Assert.assertEquals(calculatorCosts.getNeighborhoodCost_D(),7);
        Assert.assertEquals(calculatorCosts.getNeighborhoodCost_G(),8);
        Assert.assertEquals(calculatorCosts.getFurnitureCost_full(),9);
        Assert.assertEquals(calculatorCosts.getFurnitureCost_half(),10);
        Assert.assertEquals(calculatorCosts.getFurnitureCost_none(),11);
        Assert.assertEquals(calculatorCosts.getSizeCost_25(),12);
        Assert.assertEquals(calculatorCosts.getSizeCost_30(),13);
        Assert.assertEquals(calculatorCosts.getSizeCost_35(),14);
        Assert.assertEquals(calculatorCosts.getSizeCost_35_up(),15);
        Assert.assertEquals(calculatorCosts.getRoomatesCost_1(),16);
        Assert.assertEquals(calculatorCosts.getRoomatesCost_2(),17);
        Assert.assertEquals(calculatorCosts.getRoomatesCost_3(),18);
        Assert.assertEquals(calculatorCosts.getRoomatesCost_4(),19);
        Assert.assertEquals(calculatorCosts.getRoomatesCost_5(),20);
        Assert.assertEquals(calculatorCosts.getRoomatesCost_6(),21);
        Assert.assertEquals(calculatorCosts.getGardenCost(),22);
        Assert.assertEquals(calculatorCosts.getBalconyCost(),23);
    }

//    @Test
//    public void setNewCalculatorNeedToBeFailed() {  //its only can be failed in AdminCommunicationController.. not in this phase (i diddnt raise it because we can move it to there)
//        CalculatorDTO calculatorCostsOlder= serviceConnector.getBridge().getCalcCosts();
//        serviceConnector.getBridge().setNewCalculator(11111, 2, 3, 4, 5, 6, 7, 8,
//                9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21,
//                22, 23);
//        CalculatorDTO calculatorCosts= serviceConnector.getBridge().getCalcCosts();
//        Assert.assertEquals(calculatorCosts.getProtectedSpaceCost(),calculatorCostsOlder.getProtectedSpaceCost());
//        Assert.assertEquals(calculatorCosts.getTimeFromUniCost_10(),calculatorCostsOlder.getTimeFromUniCost_10());
//        Assert.assertEquals(calculatorCosts.getTimeFromUniCost_20(),calculatorCostsOlder.getTimeFromUniCost_20());
//        Assert.assertEquals(calculatorCosts.getTimeFromUniCost_G_20(),calculatorCostsOlder.getTimeFromUniCost_G_20());
//        Assert.assertEquals(calculatorCosts.getNeighborhoodCost_B_Ramot(),calculatorCostsOlder.getNeighborhoodCost_B_Ramot());
//        Assert.assertEquals(calculatorCosts.getNeighborhoodCost_oldV_Wingate(),calculatorCostsOlder.getNeighborhoodCost_oldV_Wingate());
//        Assert.assertEquals(calculatorCosts.getNeighborhoodCost_D(),calculatorCostsOlder.getNeighborhoodCost_D());
//        Assert.assertEquals(calculatorCosts.getNeighborhoodCost_G(),calculatorCostsOlder.getNeighborhoodCost_G());
//        Assert.assertEquals(calculatorCosts.getFurnitureCost_full(),calculatorCostsOlder.getFurnitureCost_full());
//        Assert.assertEquals(calculatorCosts.getFurnitureCost_half(),calculatorCostsOlder.getFurnitureCost_half());
//        Assert.assertEquals(calculatorCosts.getFurnitureCost_none(),calculatorCostsOlder.getFurnitureCost_none());
//        Assert.assertEquals(calculatorCosts.getSizeCost_25(),calculatorCostsOlder.getSizeCost_25());
//        Assert.assertEquals(calculatorCosts.getSizeCost_30(),calculatorCostsOlder.getSizeCost_30());
//        Assert.assertEquals(calculatorCosts.getSizeCost_35(),calculatorCostsOlder.getSizeCost_35());
//        Assert.assertEquals(calculatorCosts.getSizeCost_35_up(),calculatorCostsOlder.getSizeCost_35_up());
//        Assert.assertEquals(calculatorCosts.getRoomatesCost_1(),calculatorCostsOlder.getRoomatesCost_1());
//        Assert.assertEquals(calculatorCosts.getRoomatesCost_2(),calculatorCostsOlder.getRoomatesCost_2());
//        Assert.assertEquals(calculatorCosts.getRoomatesCost_3(),calculatorCostsOlder.getRoomatesCost_3());
//        Assert.assertEquals(calculatorCosts.getRoomatesCost_4(),calculatorCostsOlder.getRoomatesCost_4());
//        Assert.assertEquals(calculatorCosts.getRoomatesCost_5(),calculatorCostsOlder.getRoomatesCost_5());
//        Assert.assertEquals(calculatorCosts.getRoomatesCost_6(),calculatorCostsOlder.getRoomatesCost_6());
//        Assert.assertEquals(calculatorCosts.getGardenCost(),calculatorCostsOlder.getGardenCost());
//        Assert.assertEquals(calculatorCosts.getBalconyCost(),calculatorCostsOlder.getBalconyCost());
//    }
    @AfterClass
    public static void endup() {
        serviceConnector.getBridge().disconnectToTestDB();
    }

}
