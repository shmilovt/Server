package il.ac.bgu.finalproject.server.Domain.Controllers;

import il.ac.bgu.finalproject.server.Domain.DomainObjects.*;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentDetails.ApartmentDetails;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentDetails.Contact;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentDetails.Locations.ApartmentLocation;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentDetails.Locations.Address;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RegularClientController {

    public List<ApartmentDetails> searchApartments (UserPreferences userPreferences)  {
        Set<Contact> contacts = new HashSet<>();
        contacts.add(new Contact("tamir" , "0522204747"));
        ApartmentDetails apartmentDetails1 = new ApartmentDetails(new ApartmentLocation(new Address("atehena" , 3)), 1000, contacts );
        ApartmentDetails apartmentDetails2 = new ApartmentDetails(new ApartmentLocation(new Address("rager" , 15)), 2000, contacts );
        List<ApartmentDetails> lst = new ArrayList<>();
        lst.add(apartmentDetails1);
        lst.add(apartmentDetails2);
        return lst;

    }
}
