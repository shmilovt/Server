package il.ac.bgu.finalproject.server.Domain.NLPHandlers;

import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Apartment;

public interface NLPInterface extends Runnable {

     Apartment extractApartment(String str);

}
