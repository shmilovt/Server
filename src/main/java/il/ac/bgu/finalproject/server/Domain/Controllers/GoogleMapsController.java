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
import java.util.logging.Level;

public class GoogleMapsController {
    private static GeoApiContext context = null;
    private static final String YOUR_API_KEY =  "AIzaSyBzx9AlXuEXIpgoZcK_sLQAz1TYVI3CrGw";

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
        } catch (Exception e) {
            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
            return null;
        }
    }

    public int getTimeWalkingFromUniByMin(String streetName,int number) {
        if(number==-1)
            return -1;
        try {
            String address = streetName + " " + number + " " + "באר שבע";
            //System.out.println(address);
            DistanceMatrix ds = estimateRouteMinuteTime(null, address, "אוניברסיטת בן גוריון באר שבע");
            String str = ds.rows[0].elements[0].duration.humanReadable;
            int x = Integer.parseInt(str.substring(0, str.indexOf("m") - 1));
            return x;
        } catch (Exception e) {
            MyLogger.getInstance().log(Level.SEVERE,e.getMessage(),e);
            return -1;
        }
    }


    public double[] getCoordinates(String street, int buildingNumber) {
        if(buildingNumber==-1)
            return new double[]{-1,-1};
        String address = street + " " + buildingNumber + " " + "באר שבע";
        //  System.out.println(address);
        GeocodingResult[] results = new GeocodingResult[0];
        try {
            results = GeocodingApi.geocode(context, address).await();
            double[] a = {results[0].geometry.location.lat, results[0].geometry.location.lng};
            return a;
        } catch (Exception e) {
            MyLogger.getInstance().log(Level.SEVERE, e.getMessage(), e);
            return new double[]{-1, -1};
        }
       // Gson gson = new GsonBuilder().setPrettyPrinting().create();
       // System.out.println(gson.toJson(results));
}


    public static void main(String [] args){
      //  GoogleMapsController googleMapsController = new GoogleMapsController();
       // double[] a=googleMapsController.getCoordinates("יצחק רגר", 20);
      //  System.out.println(a[0]);//System.out.println(googleMapsController.getTimeWalkingFromUniByMin("יצחק רגר",20));
       // System.out.println("*קומה 8\\8\n".replaceAll("[ *\\\\ ]"," "));
    }
}
