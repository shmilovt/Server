package il.ac.bgu.finalproject.server.Domain.NLPHandlers;


import org.junit.Test;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Apartment;
import org.junit.BeforeClass;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;


public class NLPImpTest {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";

    private static List<TestCase<String, AnalyzedResult>> testCases;
    private static NLPImp nlp;



    @BeforeClass
    public static void setup(){
        Gson gson = new Gson();
        String filename = "src\\test\\resources\\NLPImpTestCases.json";
        try {
            JsonElement json = gson.fromJson(new FileReader(filename), JsonElement.class);
            String result = gson.toJson(json);
            ObjectMapper mapper = new ObjectMapper();
            try {
                testCases = mapper.readValue(result, new TypeReference<List<TestCase<String, AnalyzedResult>>>(){});

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        nlp = new NLPImp();
    }

    @Test
    public void extractApartmentTest_Address(){
        List<AssertionError> errors = new ArrayList<>();
        int passStreet = 0, passNeighborhood = 0, passBuildingNumber = 0;

        for (TestCase<String, AnalyzedResult> testCase: testCases) {
            Apartment apartment = nlp.extractApartment(testCase.getInput());
            AnalyzedResult analyzedResult = new AnalyzedResult(apartment);
            AnalyzedResult expectedResult = testCase.getOutput();
            try{
            assertEquals(analyzedResult.getStreet() , expectedResult.getStreet());
            passStreet++;
            assertEquals(analyzedResult.getNeighborhood() ,expectedResult.getNeighborhood());
            passNeighborhood++;
            assertEquals(analyzedResult.getBuildingNumber() ,expectedResult.getBuildingNumber());
            passBuildingNumber++;
            System.out.println(getPassMessage_Address(testCase.getInput(), analyzedResult));
            }
            catch (AssertionError e) {
               errors.add(new AssertionError(getAssertionErrorMessage_Address(testCase.getInput(), analyzedResult, testCase.getOutput())));
            }
        }


       if(errors.size()>0) {
           AssertionErrorAggregation assertionErrorAggregation = new AssertionErrorAggregation(errors);
           System.out.println(assertionErrorAggregation.getMessage());
       }
        System.out.println(printSummary_Address(passStreet, passNeighborhood, passBuildingNumber, testCases.size()));
    }



    @Test
    public void extractApartmentTest_Cost(){
        List<AssertionError> errors = new ArrayList<>();
        int passCost = 0;
        for (TestCase<String, AnalyzedResult> testCase: testCases) {
            Apartment apartment = nlp.extractApartment(testCase.getInput());
            AnalyzedResult analyzedResult = new AnalyzedResult(apartment);
            AnalyzedResult expectedResult = testCase.getOutput();
            try{
            assertEquals((int)(analyzedResult.getCost() - expectedResult.getCost()) , 0);
            passCost ++;
            System.out.println(getPassMessage_Cost(testCase.getInput(), analyzedResult));}
            catch (AssertionError e){
                errors.add(new AssertionError(getAssertionErrorMessage_Cost(testCase.getInput(), analyzedResult, testCase.getOutput())));
            }
        }


        if(errors.size()>0) {
            AssertionErrorAggregation assertionErrorAggregation = new AssertionErrorAggregation(errors);
            System.out.println(assertionErrorAggregation.getMessage());
        }

        System.out.println(printSummary_Cost(passCost ,  testCases.size()));
    }


    @Test
    public void extractApartmentTest_Floor(){
        List<AssertionError> errors = new ArrayList<>();
        int passFloor = 0;
        for (TestCase<String, AnalyzedResult> testCase: testCases) {
            Apartment apartment = nlp.extractApartment(testCase.getInput());
            AnalyzedResult analyzedResult = new AnalyzedResult(apartment);
            AnalyzedResult expectedResult = testCase.getOutput();
            try{
                assertEquals((int)(analyzedResult.getFloor() - expectedResult.getFloor()) , 0);
                passFloor ++;
                System.out.println(getPassMessage_Floor(testCase.getInput(), analyzedResult));}
            catch (AssertionError e){
                errors.add(new AssertionError(getAssertionErrorMessage_Floor(testCase.getInput(), analyzedResult, testCase.getOutput())));
            }
        }


        if(errors.size()>0) {
            AssertionErrorAggregation assertionErrorAggregation = new AssertionErrorAggregation(errors);
            System.out.println(assertionErrorAggregation.getMessage());
        }

        System.out.println(printSummary_Floor(passFloor ,  testCases.size()));
    }


    @Test
    public void extractApartmentTest_Animal(){
        List<AssertionError> errors = new ArrayList<>();
        int passAnimal = 0;
        for (TestCase<String, AnalyzedResult> testCase: testCases) {
            Apartment apartment = nlp.extractApartment(testCase.getInput());
            AnalyzedResult analyzedResult = new AnalyzedResult(apartment);
            AnalyzedResult expectedResult = testCase.getOutput();
            try{
                assertEquals((int)(analyzedResult.getPets() - expectedResult.getPets()) , 0);
                passAnimal ++;
                System.out.println(getPassMessage_Animal(testCase.getInput(), analyzedResult));}
            catch (AssertionError e){
                errors.add(new AssertionError(getAssertionErrorMessage_Animal(testCase.getInput(), analyzedResult, testCase.getOutput())));
            }
        }


        if(errors.size()>0) {
            AssertionErrorAggregation assertionErrorAggregation = new AssertionErrorAggregation(errors);
            System.out.println(assertionErrorAggregation.getMessage());
        }

        System.out.println(printSummary_Animal(passAnimal ,  testCases.size()));
    }



    @Test
    public void extractApartmentTest_ProtectedSpace(){
        List<AssertionError> errors = new ArrayList<>();
        int passProtectedSpace = 0;
        for (TestCase<String, AnalyzedResult> testCase: testCases) {
            Apartment apartment = nlp.extractApartment(testCase.getInput());
            AnalyzedResult analyzedResult = new AnalyzedResult(apartment);
            AnalyzedResult expectedResult = testCase.getOutput();
            try{
                assertEquals((int)(analyzedResult.getProtectedSpace() - expectedResult.getProtectedSpace()) , 0);
                passProtectedSpace ++;
                System.out.println(getPassMessage_ProtectedSpace(testCase.getInput(), analyzedResult));}
            catch (AssertionError e){
                errors.add(new AssertionError(getAssertionErrorMessage_ProtectedSpace(testCase.getInput(), analyzedResult, testCase.getOutput())));
            }
        }


        if(errors.size()>0) {
            AssertionErrorAggregation assertionErrorAggregation = new AssertionErrorAggregation(errors);
            System.out.println(assertionErrorAggregation.getMessage());
        }

        System.out.println(printSummary_ProtectedSpace(passProtectedSpace ,  testCases.size()));
    }


    @Test
    public void extractApartmentTest_Balcony(){
        List<AssertionError> errors = new ArrayList<>();
        int passBalcony = 0;
        for (TestCase<String, AnalyzedResult> testCase: testCases) {
            Apartment apartment = nlp.extractApartment(testCase.getInput());
            AnalyzedResult analyzedResult = new AnalyzedResult(apartment);
            AnalyzedResult expectedResult = testCase.getOutput();
            try{
                assertEquals((int)(analyzedResult.getBalcony() - expectedResult.getBalcony()) , 0);
                passBalcony ++;
                System.out.println(getPassMessage_Balcony(testCase.getInput(), analyzedResult));
            }
            catch (AssertionError e){
                errors.add(new AssertionError(getAssertionErrorMessage_Balcony(testCase.getInput(), analyzedResult, testCase.getOutput())));
            }
        }


        if(errors.size()>0) {
            AssertionErrorAggregation assertionErrorAggregation = new AssertionErrorAggregation(errors);
            System.out.println(assertionErrorAggregation.getMessage());
        }

        System.out.println(printSummary_Balcony(passBalcony ,  testCases.size()));
    }


    @Test
    public void extractApartmentTest_Furniture(){
        List<AssertionError> errors = new ArrayList<>();
        int passFurniture = 0;
        for (TestCase<String, AnalyzedResult> testCase: testCases) {
            Apartment apartment = nlp.extractApartment(testCase.getInput());
            AnalyzedResult analyzedResult = new AnalyzedResult(apartment);
            AnalyzedResult expectedResult = testCase.getOutput();
            try{
                assertEquals((int)(analyzedResult.getFurniture() - expectedResult.getFurniture()) , 0);
                passFurniture ++;
                System.out.println(getPassMessage_Furniture(testCase.getInput(), analyzedResult));
            }
            catch (AssertionError e){
                errors.add(new AssertionError(getAssertionErrorMessage_Furniture(testCase.getInput(), analyzedResult, testCase.getOutput())));
            }
        }


        if(errors.size()>0) {
            AssertionErrorAggregation assertionErrorAggregation = new AssertionErrorAggregation(errors);
            System.out.println(assertionErrorAggregation.getMessage());
        }

        System.out.println(printSummary_Furniture(passFurniture ,  testCases.size()));
    }



    @Test
    public void extractApartmentTest_WareHouse(){
        List<AssertionError> errors = new ArrayList<>();
        int passWarehouse = 0;
        for (TestCase<String, AnalyzedResult> testCase: testCases) {
            Apartment apartment = nlp.extractApartment(testCase.getInput());
            AnalyzedResult analyzedResult = new AnalyzedResult(apartment);
            AnalyzedResult expectedResult = testCase.getOutput();
            try{
                assertEquals((int)(analyzedResult.getWareHouse() - expectedResult.getWareHouse()) , 0);
                passWarehouse ++;
                System.out.println(getPassMessage_Warehouse(testCase.getInput(), analyzedResult));}
            catch (AssertionError e){
                errors.add(new AssertionError(getAssertionErrorMessage_wareHouse(testCase.getInput(), analyzedResult, testCase.getOutput())));
            }
        }


        if(errors.size()>0) {
            AssertionErrorAggregation assertionErrorAggregation = new AssertionErrorAggregation(errors);
            System.out.println(assertionErrorAggregation.getMessage());
        }

        System.out.println(printSummary_Warehouse(passWarehouse ,  testCases.size()));
    }




    @Test
    public void extractApartmentTest_numberOfRooms(){
        List<AssertionError> errors = new ArrayList<>();
        int passRooms = 0;
        for (TestCase<String, AnalyzedResult> testCase: testCases) {
            Apartment apartment = nlp.extractApartment(testCase.getInput());
            AnalyzedResult analyzedResult = new AnalyzedResult(apartment);
            AnalyzedResult expectedResult = testCase.getOutput();
            try{
                assertEquals((int)(analyzedResult.getNumberOfRooms() - expectedResult.getNumberOfRooms()) , 0);
                passRooms ++;
                System.out.println(getPassMessage_numberOfRooms(testCase.getInput(), analyzedResult));}
            catch (AssertionError e){
                errors.add(new AssertionError(getAssertionErrorMessage_numberOfRooms(testCase.getInput(), analyzedResult, testCase.getOutput())));
            }
        }


        if(errors.size()>0) {
            AssertionErrorAggregation assertionErrorAggregation = new AssertionErrorAggregation(errors);
            System.out.println(assertionErrorAggregation.getMessage());
        }

        System.out.println(printSummary_numberOfRooms(passRooms ,  testCases.size()));
    }


    @Test
    public void extractApartmentTest_roomMates(){
        List<AssertionError> errors = new ArrayList<>();
        int passRoomMates = 0;
        for (TestCase<String, AnalyzedResult> testCase: testCases) {
            Apartment apartment = nlp.extractApartment(testCase.getInput());
            AnalyzedResult analyzedResult = new AnalyzedResult(apartment);
            AnalyzedResult expectedResult = testCase.getOutput();
            try{
                assertEquals((int)(analyzedResult.getNumberOfMates() - expectedResult.getNumberOfMates()) , 0);
                passRoomMates ++;
                System.out.println(getPassMessage_roomMates(testCase.getInput(), analyzedResult));}
            catch (AssertionError e){
                errors.add(new AssertionError(getAssertionErrorMessage_roomMates(testCase.getInput(), analyzedResult, testCase.getOutput())));
            }
        }


        if(errors.size()>0) {
            AssertionErrorAggregation assertionErrorAggregation = new AssertionErrorAggregation(errors);
            System.out.println(assertionErrorAggregation.getMessage());
        }

        System.out.println(printSummary_roomMates(passRoomMates ,  testCases.size()));
    }


    private static String getAssertionErrorMessage_Address(String input, AnalyzedResult output, AnalyzedResult expected){
        String isPassColor ;
        String reset = ANSI_RESET;

        String message = "";
        message = message +  "\n\n\n--------------------------------------------------------------------------------------------------------------------------\n";
        message = message + input + "\n";
        if(output.getStreet().equals(expected.getStreet())) {
            isPassColor = ANSI_GREEN;
            reset = ANSI_RED;
        }
        else{
            isPassColor = "";
            reset = "";
        }
            message = message + isPassColor +"\nstreet => ";
            message = message + "expected: ";
            message = message + expected.getStreet()  ;
            message = message + " , but was: ";
            message = message + output.getStreet()+  reset ;

        if(output.getBuildingNumber() == expected.getBuildingNumber()) {
            isPassColor = ANSI_GREEN;
            reset = ANSI_RED;
        }
        else{
            isPassColor = "";
            reset = "";
        }


        message = message + isPassColor+ "\nbuilding number => ";
        message = message + "expected: ";
        message = message + expected.getBuildingNumber();
        message = message + " , but was: ";
        message = message + output.getBuildingNumber() +  reset;

        if(output.getNeighborhood().equals(expected.getNeighborhood())) {
            isPassColor = ANSI_GREEN;
            reset = ANSI_RED;
        }
        else{
            isPassColor = "";
            reset = "";
        }

        message = message + isPassColor+ "\nneighborhood => ";
        message = message + "expected: ";
        message = message + expected.getNeighborhood();
        message = message + " , but was: ";
        message = message + output.getNeighborhood() + reset;
        return message;
    }

    private static String getPassMessage_Address(String input, AnalyzedResult output){
        return ANSI_GREEN +"\n\n\n--------------------------------------------------------------------------------------------------------------------------\n"+
                "pass test-case: \n\n"+input+"\n\n" + "address: "+output.getAddress() + ANSI_RESET ;
    }

    private static String printSummary_Address(int passStreet, int passNeighborhood, int passBuildingNumber, int totalTestCasesNum) {
        String message = ANSI_BLUE + "\n\n\nSUMMARY:\n";
        message = message + passStreet+"/"+totalTestCasesNum+" streets correctly analyzed\n";
        message = message + passNeighborhood+"/"+totalTestCasesNum+" neighborhoods correctly analyzed\n";
        message = message + passBuildingNumber+"/"+totalTestCasesNum+" building numbers correctly analyzed\n";
        message = message +  ANSI_RESET;
        return message;
    }

    private String getAssertionErrorMessage_Garden(String input, AnalyzedResult output, AnalyzedResult expected) {
        String isPassColor ;

        String message = "";
        message = message +  "\n\n\n--------------------------------------------------------------------------------------------------------------------------\n";
        message = message + input + "\n";


        if(output.getGarden() == expected.getGarden()) {
            isPassColor = ANSI_GREEN;
        }
        else{
            isPassColor = ANSI_RED;
        }

        message = message + isPassColor+ "\ngarden => ";
        message = message + "expected: ";
        message = message + expected.getGarden();
        message = message + " , but was: ";
        message = message + output.getGarden()+  ANSI_RED;
        return message;
    }


    private String getAssertionErrorMessage_Cost(String input, AnalyzedResult output, AnalyzedResult expected) {
        String isPassColor ;

        String message = "";
        message = message +  "\n\n\n--------------------------------------------------------------------------------------------------------------------------\n";
        message = message + input + "\n";


        if(output.getCost() == expected.getCost()) {
            isPassColor = ANSI_GREEN;
        }
        else{
            isPassColor = ANSI_RED;
        }

        message = message + isPassColor+ "\ncost => ";
        message = message + "expected: ";
        message = message + expected.getCost();
        message = message + " , but was: ";
        message = message + output.getCost()+  ANSI_RED;
        return message;
    }


    private String getAssertionErrorMessage_Floor(String input, AnalyzedResult output, AnalyzedResult expected) {
        String isPassColor ;

        String message = "";
        message = message +  "\n\n\n--------------------------------------------------------------------------------------------------------------------------\n";
        message = message + input + "\n";


        if(output.getFloor() == expected.getFloor()) {
            isPassColor = ANSI_GREEN;
        }
        else{
            isPassColor = ANSI_RED;
        }

        message = message + isPassColor+ "\nfloor => ";
        message = message + "expected: ";
        message = message + expected.getFloor();
        message = message + " , but was: ";
        message = message + output.getFloor()+  ANSI_RED;
        return message;
    }

    private String getAssertionErrorMessage_Animal(String input, AnalyzedResult output, AnalyzedResult expected) {
        String isPassColor ;

        String message = "";
        message = message +  "\n\n\n--------------------------------------------------------------------------------------------------------------------------\n";
        message = message + input + "\n";


        if(output.getPets() == expected.getPets()) {
            isPassColor = ANSI_GREEN;
        }
        else{
            isPassColor = ANSI_RED;
        }

        message = message + isPassColor+ "\nAnimal => ";
        message = message + "expected: ";
        message = message + expected.getPets();
        message = message + " , but was: ";
        message = message + output.getPets()+  ANSI_RED;
        return message;
    }


    private String getAssertionErrorMessage_ProtectedSpace(String input, AnalyzedResult output, AnalyzedResult expected) {
        String isPassColor ;

        String message = "";
        message = message +  "\n\n\n--------------------------------------------------------------------------------------------------------------------------\n";
        message = message + input + "\n";


        if(output.getProtectedSpace() == expected.getProtectedSpace()) {
            isPassColor = ANSI_GREEN;
        }
        else{
            isPassColor = ANSI_RED;
        }

        message = message + isPassColor+ "\nProtected space => ";
        message = message + "expected: ";
        message = message + expected.getProtectedSpace();
        message = message + " , but was: ";
        message = message + output.getProtectedSpace()+  ANSI_RED;
        return message;
    }

    private String getAssertionErrorMessage_Balcony(String input, AnalyzedResult output, AnalyzedResult expected) {
        String isPassColor ;

        String message = "";
        message = message +  "\n\n\n--------------------------------------------------------------------------------------------------------------------------\n";
        message = message + input + "\n";


        if(output.getBalcony() == expected.getBalcony()) {
            isPassColor = ANSI_GREEN;
        }
        else{
            isPassColor = ANSI_RED;
        }

        message = message + isPassColor+ "\nBalcony => ";
        message = message + "expected: ";
        message = message + expected.getBalcony();
        message = message + " , but was: ";
        message = message + output.getBalcony()+  ANSI_RED;
        return message;
    }

    private String getAssertionErrorMessage_Furniture(String input, AnalyzedResult output, AnalyzedResult expected) {
        String isPassColor ;

        String message = "";
        message = message +  "\n\n\n--------------------------------------------------------------------------------------------------------------------------\n";
        message = message + input + "\n";


        if(output.getFurniture() == expected.getFurniture()) {
            isPassColor = ANSI_GREEN;
        }
        else{
            isPassColor = ANSI_RED;
        }

        message = message + isPassColor+ "\nFurniture => ";
        message = message + "expected: ";
        message = message + expected.getFurniture();
        message = message + " , but was: ";
        message = message + output.getFurniture()+  ANSI_RED;
        return message;
    }


    private String getAssertionErrorMessage_wareHouse(String input, AnalyzedResult output, AnalyzedResult expected) {
        String isPassColor ;

        String message = "";
        message = message +  "\n\n\n--------------------------------------------------------------------------------------------------------------------------\n";
        message = message + input + "\n";


        if(output.getWareHouse() == expected.getWareHouse()) {
            isPassColor = ANSI_GREEN;
        }
        else{
            isPassColor = ANSI_RED;
        }

        message = message + isPassColor+ "\nWarehouse => ";
        message = message + "expected: ";
        message = message + expected.getWareHouse();
        message = message + " , but was: ";
        message = message + output.getWareHouse()+  ANSI_RED;
        return message;
    }

    private String getAssertionErrorMessage_roomMates(String input, AnalyzedResult output, AnalyzedResult expected) {
        String isPassColor ;

        String message = "";
        message = message +  "\n\n\n--------------------------------------------------------------------------------------------------------------------------\n";
        message = message + input + "\n";


        if(output.getNumberOfMates() == expected.getNumberOfMates()) {
            isPassColor = ANSI_GREEN;
        }
        else{
            isPassColor = ANSI_RED;
        }

        message = message + isPassColor+ "\nroomMates => ";
        message = message + "expected: ";
        message = message + expected.getNumberOfMates();
        message = message + " , but was: ";
        message = message + output.getNumberOfMates()+  ANSI_RED;
        return message;
    }



    private String getAssertionErrorMessage_numberOfRooms(String input, AnalyzedResult output, AnalyzedResult expected) {
        String isPassColor ;

        String message = "";
        message = message +  "\n\n\n--------------------------------------------------------------------------------------------------------------------------\n";
        message = message + input + "\n";


        if(output.getNumberOfRooms() == expected.getNumberOfRooms()) {
            isPassColor = ANSI_GREEN;
        }
        else{
            isPassColor = ANSI_RED;
        }

        message = message + isPassColor+ "\nnumber of rooms => ";
        message = message + "expected: ";
        message = message + expected.getNumberOfRooms();
        message = message + " , but was: ";
        message = message + output.getNumberOfRooms()+  ANSI_RED;
        return message;
    }



    private String getPassMessage_Warehouse(String input, AnalyzedResult output) {
        return ANSI_GREEN +"\n\n\n--------------------------------------------------------------------------------------------------------------------------\n"+
                "pass test-case: \n\n"+input+"\n\n" + "Warehouse: "+output.getWareHouse() + ANSI_RESET ;
    }

    private String getPassMessage_roomMates(String input, AnalyzedResult output) {
        return ANSI_GREEN +"\n\n\n--------------------------------------------------------------------------------------------------------------------------\n"+
                "pass test-case: \n\n"+input+"\n\n" + "roomMates: "+output.getNumberOfMates() + ANSI_RESET ;
    }

    private String getPassMessage_numberOfRooms(String input, AnalyzedResult output) {
        return ANSI_GREEN +"\n\n\n--------------------------------------------------------------------------------------------------------------------------\n"+
                "pass test-case: \n\n"+input+"\n\n" + "number of rooms: "+output.getNumberOfRooms() + ANSI_RESET ;
    }

    private String getPassMessage_Balcony(String input, AnalyzedResult output) {
        return ANSI_GREEN +"\n\n\n--------------------------------------------------------------------------------------------------------------------------\n"+
                "pass test-case: \n\n"+input+"\n\n" + "Balcony: "+output.getBalcony() + ANSI_RESET ;
    }

    private String getPassMessage_Furniture(String input, AnalyzedResult output) {
        return ANSI_GREEN +"\n\n\n--------------------------------------------------------------------------------------------------------------------------\n"+
                "pass test-case: \n\n"+input+"\n\n" + "Furniture: "+output.getFurniture() + ANSI_RESET ;
    }

    private String getPassMessage_ProtectedSpace(String input, AnalyzedResult output) {
        return ANSI_GREEN +"\n\n\n--------------------------------------------------------------------------------------------------------------------------\n"+
                "pass test-case: \n\n"+input+"\n\n" + "Protected space: "+output.getProtectedSpace() + ANSI_RESET ;
    }

    private String getPassMessage_Cost(String input, AnalyzedResult output) {
        return ANSI_GREEN +"\n\n\n--------------------------------------------------------------------------------------------------------------------------\n"+
                "pass test-case: \n\n"+input+"\n\n" + "cost: "+output.getCost() + ANSI_RESET ;
    }


    private String getPassMessage_Floor(String input, AnalyzedResult output) {
        return ANSI_GREEN +"\n\n\n--------------------------------------------------------------------------------------------------------------------------\n"+
                "pass test-case: \n\n"+input+"\n\n" + "floor: "+output.getFloor() + ANSI_RESET ;
    }

    private String getPassMessage_Animal(String input, AnalyzedResult output) {
        return ANSI_GREEN +"\n\n\n--------------------------------------------------------------------------------------------------------------------------\n"+
                "pass test-case: \n\n"+input+"\n\n" + "Animal: "+output.getPets() + ANSI_RESET ;
    }

    private String getPassMessage_Garden(String input, AnalyzedResult output) {
        return ANSI_GREEN +"\n\n\n--------------------------------------------------------------------------------------------------------------------------\n"+
                "pass test-case: \n\n"+input+"\n\n" + "garden: "+output.getGarden() + ANSI_RESET ;
    }


    private static String printSummary_Animal(int passAnimal, int totalPostsNum) {
        String message = ANSI_BLUE + "\n\n\nSUMMARY:\n";
        message = message + passAnimal +"/"+totalPostsNum+" Animal correctly analyzed\n";
        message = message +  ANSI_RESET;
        return message;
    }

    private static String printSummary_Floor(int passFloor, int totalPostsNum) {
        String message = ANSI_BLUE + "\n\n\nSUMMARY:\n";
        message = message + passFloor+"/"+totalPostsNum+" floor correctly analyzed\n";
        message = message +  ANSI_RESET;
        return message;
    }

    private static String printSummary_Cost(int passCost, int totalPostsNum) {
        String message = ANSI_BLUE + "\n\n\nSUMMARY:\n";
        message = message + passCost+"/"+totalPostsNum+" streets correctly analyzed\n";
        message = message +  ANSI_RESET;
        return message;
    }

    private static String printSummary_ProtectedSpace(int passProtectedSpace, int totalPostsNum) {
        String message = ANSI_BLUE + "\n\n\nSUMMARY:\n";
        message = message + passProtectedSpace + "/" + totalPostsNum +" Protected space correctly analyzed\n";
        message = message +  ANSI_RESET;
        return message;
    }


    private static String printSummary_Balcony(int passBalcony, int totalPostsNum) {
        String message = ANSI_BLUE + "\n\n\nSUMMARY:\n";
        message = message + passBalcony + "/" + totalPostsNum +" Balcony correctly analyzed\n";
        message = message +  ANSI_RESET;
        return message;
    }


    private static String printSummary_Furniture(int passFurniture, int totalPostsNum) {
        String message = ANSI_BLUE + "\n\n\nSUMMARY:\n";
        message = message + passFurniture + "/" + totalPostsNum +" Furniture correctly analyzed\n";
        message = message +  ANSI_RESET;
        return message;
    }

    private static String printSummary_Warehouse(int passProtectedSpace, int totalPostsNum) {
        String message = ANSI_BLUE + "\n\n\nSUMMARY:\n";
        message = message + passProtectedSpace + "/" + totalPostsNum +" Warehouse correctly analyzed\n";
        message = message +  ANSI_RESET;
        return message;
    }


    private static String printSummary_roomMates(int passProtectedSpace, int totalPostsNum) {
        String message = ANSI_BLUE + "\n\n\nSUMMARY:\n";
        message = message + passProtectedSpace + "/" + totalPostsNum +" room matescorrectly analyzed\n";
        message = message +  ANSI_RESET;
        return message;
    }

    private static String printSummary_numberOfRooms(int passProtectedSpace, int totalPostsNum) {
        String message = ANSI_BLUE + "\n\n\nSUMMARY:\n";
        message = message + passProtectedSpace + "/" + totalPostsNum +" number of rooms correctly analyzed\n";
        message = message +  ANSI_RESET;
        return message;
    }

    private String getAssertionErrorMessage_Size(String input, AnalyzedResult output, AnalyzedResult expected) {
        String isPassColor ;

        String message = "";
        message = message +  "\n\n\n--------------------------------------------------------------------------------------------------------------------------\n";
        message = message + input + "\n";


        if(output.getSize() == expected.getSize()) {
            isPassColor = ANSI_GREEN;
        }
        else{
            isPassColor = ANSI_RED;
        }

        message = message + isPassColor+ "\nsize => ";
        message = message + "expected: ";
        message = message + expected.getSize();
        message = message + " , but was: ";
        message = message + output.getSize()+  ANSI_RED;
        return message;
    }

    private String getPassMessage_Size(String input, AnalyzedResult output) {
        return ANSI_GREEN +"\n\n\n--------------------------------------------------------------------------------------------------------------------------\n"+
                "pass test-case: \n\n"+input+"\n\n" + "size: "+output.getSize() + ANSI_RESET ;
    }

    private static String printSummary_Size(int passSize, int totalPostsNum) {
        String message = ANSI_BLUE + "\n\n\nSUMMARY:\n";
        message = message + passSize+"/"+totalPostsNum+" apartment sizes correctly analyzed\n";
        message = message +  ANSI_RESET;
        return message;
    }

    private String getAssertionErrorMessage_Phones(String input, AnalyzedResult output, AnalyzedResult expected) {
        String isPassColor ;

        String message = "";
        message = message +  "\n\n\n--------------------------------------------------------------------------------------------------------------------------\n";
        message = message + input + "\n";


        if(output.getPhones().equals(expected.getPhones())){
            isPassColor = ANSI_GREEN;
        }
        else{
            isPassColor = ANSI_RED;
        }

        message = message + isPassColor+ "\nphones => ";
        message = message + "expected: ";
        message = message + expected.getPhones();
        message = message + " , but was: ";
        message = message + output.getPhones()+  ANSI_RED;
        return message;
    }

    private String getPassMessage_Phones(String input, AnalyzedResult output) {
        return ANSI_GREEN +"\n\n\n--------------------------------------------------------------------------------------------------------------------------\n"+
                "pass test-case: \n\n"+input+"\n\n" + "phones: "+output.getPhones() + ANSI_RESET ;
    }

    private static String printSummary_Phones(int passPhones, int totalPostsNum) {
        String message = ANSI_BLUE + "\n\n\nSUMMARY:\n";
        message = message + passPhones+"/"+totalPostsNum+" phone sets correctly analyzed\n";
        message = message +  ANSI_RESET;
        return message;
    }



}
