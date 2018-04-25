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
//        apartment.getApartmentLocation().setLatitude();
//        apartment.getApartmentLocation().setLongitude();
//        apartment.getApartmentLocation().setUniversity_distance();
        return  apartment;
    }
}
