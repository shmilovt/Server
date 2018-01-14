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
    public void extractApartmentTest_Size(){
        List<AssertionError> errors = new ArrayList<>();
        int passSize = 0;
        for (TestCase<String, AnalyzedResult> testCase: testCases) {
            Apartment apartment = nlp.extractApartment(testCase.getInput());
            AnalyzedResult analyzedResult = new AnalyzedResult(apartment);
            AnalyzedResult expectedResult = testCase.getOutput();
            try{
            assertEquals((int)(analyzedResult.getSize()- expectedResult.getSize()) , 0);
            passSize++;
            System.out.println(getPassMessage_Size(testCase.getInput(), analyzedResult));
            }
            catch(AssertionError e){
                errors.add(new AssertionError(getAssertionErrorMessage_Size(testCase.getInput(), analyzedResult, testCase.getOutput())));
            }
        }

        if(errors.size()>0) {
            AssertionErrorAggregation assertionErrorAggregation = new AssertionErrorAggregation(errors);
            System.out.println(assertionErrorAggregation.getMessage());
        }

        System.out.println(printSummary_Size(passSize ,  testCases.size()));
    }



    @Test
    public void extractApartmentTest_Phones(){
        List<AssertionError> errors = new ArrayList<>();
        int passPhones = 0;
        for (TestCase<String, AnalyzedResult> testCase: testCases) {
            Apartment apartment = nlp.extractApartment(testCase.getInput());
            AnalyzedResult analyzedResult = new AnalyzedResult(apartment);
            AnalyzedResult expectedResult = testCase.getOutput();
            try{
            assertEquals(analyzedResult.getPhones() , expectedResult.getPhones());
            passPhones ++ ;
            System.out.println(getPassMessage_Phones(testCase.getInput(), analyzedResult));}
            catch(AssertionError e){
                errors.add(new AssertionError(getAssertionErrorMessage_Phones(testCase.getInput(), analyzedResult, testCase.getOutput())));
            }
        }


        if(errors.size()>0) {
            AssertionErrorAggregation assertionErrorAggregation = new AssertionErrorAggregation(errors);
            System.out.println(assertionErrorAggregation.getMessage());
        }

        System.out.println(printSummary_Phones(passPhones ,  testCases.size()));
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

    private String getPassMessage_Cost(String input, AnalyzedResult output) {
        return ANSI_GREEN +"\n\n\n--------------------------------------------------------------------------------------------------------------------------\n"+
                "pass test-case: \n\n"+input+"\n\n" + "cost: "+output.getCost() + ANSI_RESET ;
    }

    private static String printSummary_Cost(int passCost, int totalPostsNum) {
        String message = ANSI_BLUE + "\n\n\nSUMMARY:\n";
        message = message + passCost+"/"+totalPostsNum+" streets correctly analyzed\n";
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
