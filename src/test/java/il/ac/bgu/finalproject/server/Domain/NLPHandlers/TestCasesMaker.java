package il.ac.bgu.finalproject.server.Domain.NLPHandlers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestCasesMaker {
    public static void main(String [] args) {
        List<TestCase<String, AnalyzedResult>> lst = new ArrayList<>();
        String temp = "";
        FileReader fr = null;
        BufferedReader br  = null;
        List<String> streetNames = new ArrayList<>();
        String filepath = "C:\\Users\\TAMIR\\IdeaProjects\\Server\\src\\test\\resources\\streets.txt";

            try {
                fr = new FileReader(filepath);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            br = new BufferedReader(fr);

           String parseName = "        \"STR_NAME\"";
            String line;
            try {
                while((line = br.readLine()) != null)
                {
                    if(!streetNames.contains(line))
                    streetNames.add(line);


                   // temp = temp +  line + "\n";

                }
                for(String str : streetNames){
                    System.out.println(str);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

  //          lst.add(new TestCase<>(temp , new AnalyzedResult()));

   //             Gson gson = new Gson();
     /*   try {
            gson.toJson(lst, new FileWriter("C:\\Users\\TAMIR\\IdeaProjects\\Server\\src\\test\\resources\\NLPImpTestCases.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
*/

  /*    String jsonInString = gson.toJson(lst);
       System.out.println(jsonInString);
*/

   /*    String filename = "C:\\Users\\TAMIR\\IdeaProjects\\Server\\src\\test\\resources\\streets.txt";
       Set<String> streetNames = new HashSet<>();
        try {
            JsonElement json = gson.fromJson(new FileReader(filename), JsonElement.class);
            String result = gson.toJson(json);
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

            try {
                List<MyPojo> addresses = mapper.readValue(result, new TypeReference<List<MyPojo>>(){});
                for (MyPojo address: addresses) {
                    streetNames.add(address.getProperties().getSTR_NAME());
                }

                for(String streetName : streetNames){
                    System.out.println(streetName);
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
