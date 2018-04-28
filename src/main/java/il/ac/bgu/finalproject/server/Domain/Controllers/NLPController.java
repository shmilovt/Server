package il.ac.bgu.finalproject.server.Domain.Controllers;

import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Apartment;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Post;
import il.ac.bgu.finalproject.server.Domain.NLPHandlers.NLPImp;
import il.ac.bgu.finalproject.server.Domain.NLPHandlers.NLPInterface;

public class NLPController {
    private NLPInterface nlp;
    private GoogleMapsController googleMapsController;

    public NLPController(){
        nlp = new NLPImp();
        googleMapsController= new GoogleMapsController();
    }




    public Apartment generateNLP(Post post) {
        Apartment apartment = nlp.extractApartment(post.getText());
        //googleMapsController ==> get Latitude, Longitude and TimeFromUNI
        String street = apartment.getApartmentLocation().getAddress().getStreet();
        int number = apartment.getApartmentLocation().getAddress().getNumber();
        if(!street.isEmpty() && number>0) {
            int timeToUni = googleMapsController.getTimeWalkingFromUniByMin(street, number);
            double[] locationPoint = googleMapsController.getCoordinates(street, number);
            apartment.getApartmentLocation().getAddress().setLat(locationPoint[0]);
            apartment.getApartmentLocation().getAddress().setLng(locationPoint[1]);
            apartment.getApartmentLocation().setUniversity_distance(timeToUni);
        }
        return  apartment;
    }
}
