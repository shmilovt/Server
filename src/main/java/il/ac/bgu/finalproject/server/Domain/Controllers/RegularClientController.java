package il.ac.bgu.finalproject.server.Domain.Controllers;

import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Apartment;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Contact;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.ApartmentLocation;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Address;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils.UserPreferences;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RegularClientController {

    public List<Apartment> searchApartments (UserPreferences userPreferences)  {
        Set<Contact> contacts = new HashSet<>();
        contacts.add(new Contact("tamir" , "0522204747"));
        Apartment apartment1 = new Apartment(new ApartmentLocation(new Address("atehena" , 3)), 1000, contacts );
        Apartment apartment2 = new Apartment(new ApartmentLocation(new Address("rager" , 15)), 2000, contacts );
        List<Apartment> lst = new ArrayList<>();
        lst.add(apartment1);
        lst.add(apartment2);
        return lst;

    }
}
