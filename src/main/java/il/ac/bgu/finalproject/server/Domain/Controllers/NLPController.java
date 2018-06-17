package il.ac.bgu.finalproject.server.Domain.Controllers;

import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Apartment;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Post;
import il.ac.bgu.finalproject.server.Domain.NLPHandlers.Analyzer;
import il.ac.bgu.finalproject.server.Domain.NLPHandlers.DataBaseNlp;
import il.ac.bgu.finalproject.server.Domain.NLPHandlers.NLPImp;
import il.ac.bgu.finalproject.server.Domain.NLPHandlers.NLPInterface;

public class NLPController {
    private NLPInterface nlp;
    private GoogleMapsController googleMapsController;
    private DataBaseNlp dbNLP;
    public NLPController(){
        nlp = new NLPImp();
        dbNLP = new DataBaseNlp();
        googleMapsController= new GoogleMapsController();
    }


    public void changeNeighborhoodStreetRecord(String neighborhood, String street) {
        dbNLP.connect();
        dbNLP.updateValuesNeigh_Street(neighborhood,street);
        nlp.setStreetNeigh(street,neighborhood);
        Analyzer.reload();
        dbNLP.disConnect();
    }

    public void addStreet(String street, String neighborhood) {
        dbNLP.connect();
        dbNLP.addValuesOneCol("streets", street);
        dbNLP.addValuesNeigh_Street(neighborhood,street);
        nlp.setStreetNeigh(street,neighborhood);
        Analyzer.reload();
        dbNLP.disConnect();
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