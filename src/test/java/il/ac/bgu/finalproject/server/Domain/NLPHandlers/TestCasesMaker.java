package il.ac.bgu.finalproject.server.Domain.NLPHandlers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TestCasesMaker {
    public static void main(String [] args) {
        List<TestCase<String, AnalyzedResult>> lst = new ArrayList<>();
        String temp = "";
        FileReader fr = null;
        BufferedReader br  = null;
        String filepath = "C:\\Users\\TAMIR\\IdeaProjects\\Server\\src\\test\\resources\\posts.txt";

            try {
                fr = new FileReader(filepath);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            br = new BufferedReader(fr);


            String line;
            try {
                while((line = br.readLine()) != null)
                {
                    if(line.equals("********************") || line.equals("******************** ")){

                        lst.add(new TestCase<>(temp , new AnalyzedResult()));
                        temp = "";
                        continue;
                    }
                    temp = temp +  line + "\n";

                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            lst.add(new TestCase<>(temp , new AnalyzedResult()));
                Gson gson = new Gson();
     /*   try {
            gson.toJson(lst, new FileWriter("C:\\Users\\TAMIR\\IdeaProjects\\Server\\src\\test\\resources\\NLPImpTestCases.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
*/

      String jsonInString = gson.toJson(lst);
       System.out.println(jsonInString);


  /*     String filename = "C:\\Users\\TAMIR\\IdeaProjects\\Server\\src\\test\\resources\\NLPImpTestCases.json";
        try {
            JsonElement json = gson.fromJson(new FileReader(filename), JsonElement.class);
            String result = gson.toJson(json);
            ObjectMapper mapper = new ObjectMapper();
            try {
                List<TestCase<String, AnalyzedResult>> testCases = mapper.readValue(result, new TypeReference<List<TestCase<String, AnalyzedResult>>>(){});
                for (TestCase<String, AnalyzedResult> testCase: testCases) {
                    System.out.println(testCase.getInput());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

*/


    }

}
