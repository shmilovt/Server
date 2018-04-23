package il.ac.bgu.finalproject.server.Domain.Controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;

import java.io.IOException;

public class GoogleMapsController {
    private static GeoApiContext context = null;
    private static final String YOUR_API_KEY =  "AIzaSyDCNJw6vgloPG3AAdqcsyFwV2m4L7pzwko";

    public static void main(String [] args){
        GoogleMapsController googleMapsController = new GoogleMapsController();
        googleMapsController.getCoordinates("יצחק רגר", 7000);

    }



public void getCoordinates(String street, int buildingNumber){

   String address =  buildingNumber+" "+street+" באר שבע ";
   System.out.println(address);
    GeocodingResult[] results = new GeocodingResult[0];
    try {
        results = GeocodingApi.geocode(context,address).await();
    } catch (ApiException e) {
     //   e.printStackTrace();
    } catch (InterruptedException e) {
     //   e.printStackTrace();
    } catch (IOException e) {
     //   e.printStackTrace();
    }
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    System.out.println(gson.toJson(results));

}

     public GoogleMapsController(){
         if(context==null){
             context =   new GeoApiContext.Builder()
                     .apiKey(YOUR_API_KEY)
                     .build();
         }
     }

}
