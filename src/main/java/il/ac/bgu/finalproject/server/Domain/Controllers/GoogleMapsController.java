package il.ac.bgu.finalproject.server.Domain.Controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.*;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;

import java.io.IOException;

public class GoogleMapsController {
    private static GeoApiContext context = null;
    private static final String YOUR_API_KEY =  "AIzaSyANZzbAFj7R6gYvU26Yl4mzPnME4MOWFxA";

    public GoogleMapsController(){
        if(context==null){
            context =   new GeoApiContext.Builder()
                    .apiKey(YOUR_API_KEY)
                    .build();
        }
    }

    private DistanceMatrix estimateRouteMinuteTime(DirectionsApi.RouteRestriction routeRestriction, String departure, String arrivals) {
        try {
            DistanceMatrixApiRequest req = DistanceMatrixApi.newRequest(context);
            if (routeRestriction == null) {
                routeRestriction = DirectionsApi.RouteRestriction.TOLLS;
            }
            DistanceMatrix trix = req.origins(departure)
                    .destinations(arrivals)
                    .mode(TravelMode.WALKING)
                    .avoid(routeRestriction)
                    .language("en")
                    .await();
            return trix;

        } catch (ApiException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public int getTimeWalkingFromUniByMin(String streetName,int number)
    {
        String address = streetName + " " + number + " " + "באר שבע";
        System.out.println(address);
        DistanceMatrix ds = estimateRouteMinuteTime(null,address,"אוניברסיטת בן גוריון באר שבע");
        String str = ds.rows[0].elements[0].duration.humanReadable;
        return  Integer.parseInt(str.substring(0,str.indexOf("m")-1));

    }

    public void getCoordinates(String street, int buildingNumber)
    {

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


    public static void main(String [] args){
        GoogleMapsController googleMapsController = new GoogleMapsController();
        //googleMapsController.getCoordinates("יצחק רגר", 20);
        //System.out.println(googleMapsController.getTimeWalkingFromUniByMin("יצחק רגר",20));
    }


}
