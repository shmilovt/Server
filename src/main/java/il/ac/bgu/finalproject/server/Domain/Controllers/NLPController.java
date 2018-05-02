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
        if(apartment!=null) {
            String street = apartment.getApartmentLocation().getAddress().getStreet();
            int number = apartment.getApartmentLocation().getAddress().getNumber();
            if (!street.isEmpty() && number > 0) {
                int timeToUni = googleMapsController.getTimeWalkingFromUniByMin(street, number);
                double[] locationPoint = googleMapsController.getCoordinates(street, number);
                apartment.getApartmentLocation().setLatitude(locationPoint[0]);
                apartment.getApartmentLocation().setLongitude(locationPoint[1]);
                apartment.getApartmentLocation().setUniversity_distance(timeToUni);
            } else {
                apartment.getApartmentLocation().setLatitude(-1);
                apartment.getApartmentLocation().setLongitude(-1);
                apartment.getApartmentLocation().setUniversity_distance(-1);
            }
            return apartment;
        }
        return null;
    }
}