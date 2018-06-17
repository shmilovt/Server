package il.ac.bgu.finalproject.server.Domain.NLPHandlers;

import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Apartment;

public interface NLPInterface {

     Apartment extractApartment(String str);
     void setStreetNeigh(String s,String n);

}
