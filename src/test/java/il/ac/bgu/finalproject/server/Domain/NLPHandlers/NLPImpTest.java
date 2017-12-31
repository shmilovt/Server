package il.ac.bgu.finalproject.server.Domain.NLPHandlers;

import org.junit.Rule;
import org.junit.Test;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentDetails.ApartmentDetails;
import org.junit.BeforeClass;
import org.junit.rules.ErrorCollector;
import org.junit.rules.MethodRule;
import org.junit.rules.TestWatchman;
import org.junit.runners.model.FrameworkMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;


public class NLPImpTest {
    private static List<TestCase<String, AnalyzedResult>> testCases;
    private static NLPImp nlp;


    @BeforeClass
    public static void setup(){
        Gson gson = new Gson();
        String filename = "C:\\Users\\TAMIR\\IdeaProjects\\Server\\src\\test\\resources\\NLPImpTestCases.json";
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
        for (TestCase<String, AnalyzedResult> testCase: testCases) {
            ApartmentDetails apartmentDetails = nlp.extractApartment(testCase.getInput());
            AnalyzedResult analyzedResult = new AnalyzedResult(apartmentDetails);
            AnalyzedResult expectedResult = testCase.getOutput();
            try{
            assertEquals(analyzedResult.getStreet() , expectedResult.getStreet());
            assertEquals(analyzedResult.getNeighborhood() ,expectedResult.getNeighborhood());
            assertEquals(analyzedResult.getBuildingNumber() ,expectedResult.getBuildingNumber());}
            catch (AssertionError e) {
                throw new AssertionError("\n*************THE POST*************\n"+testCase.getInput()+"\n"+"expected:\n"+testCase.getOutput().getAddress()+"\n"+"but was:\n"+analyzedResult.getAddress()+"\n\n\n");
            }
        }
    }

    @Test
    public void extractApartmentTest_Cost(){
        for (TestCase<String, AnalyzedResult> testCase: testCases) {
            ApartmentDetails apartmentDetails = nlp.extractApartment(testCase.getInput());
            AnalyzedResult analyzedResult = new AnalyzedResult(apartmentDetails);
            AnalyzedResult expectedResult = testCase.getOutput();
            assertEquals(analyzedResult.getCost(), expectedResult.getCost());
        }
    }


    @Test
    public void extractApartmentTest_Size(){
        for (TestCase<String, AnalyzedResult> testCase: testCases) {
            ApartmentDetails apartmentDetails = nlp.extractApartment(testCase.getInput());
            AnalyzedResult analyzedResult = new AnalyzedResult(apartmentDetails);
            AnalyzedResult expectedResult = testCase.getOutput();
            assertEquals(analyzedResult.getSize() , expectedResult.getSize());
        }
    }



    @Test
    public void extractApartmentTest_Phones(){
        for (TestCase<String, AnalyzedResult> testCase: testCases) {
            ApartmentDetails apartmentDetails = nlp.extractApartment(testCase.getInput());
            AnalyzedResult analyzedResult = new AnalyzedResult(apartmentDetails);
            AnalyzedResult expectedResult = testCase.getOutput();
            assertEquals(analyzedResult.getPhones() , expectedResult.getPhones());
        }
    }
}
