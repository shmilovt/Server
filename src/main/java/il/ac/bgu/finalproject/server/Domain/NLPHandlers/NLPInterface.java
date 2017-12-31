package il.ac.bgu.finalproject.server.Domain.NLPHandlers;

import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentDetails.ApartmentDetails;

public interface NLPInterface {

     ApartmentDetails extractApartment(String str);

}
