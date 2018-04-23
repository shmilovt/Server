package il.ac.bgu.finalproject.server.Domain.Controllers;

import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Apartment;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Contact;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.ApartmentLocation;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Address;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils.CategoryQuery;


import java.util.*;

public class RegularClientController {

    /*public List<Apartment> searchApartments (UserPreferences userPreferences)  {
        Set<Contact> contacts = new HashSet<>();
        contacts.add(new Contact("tamir" , "0522204747"));
        Apartment apartment1 = new Apartment(new ApartmentLocation(new Address("atehena" , 3)), 1000, contacts );
        Apartment apartment2 = new Apartment(new ApartmentLocation(new Address("rager" , 15)), 2000, contacts );
        List<Apartment> lst = new ArrayList<>();
        lst.add(apartment1);
        lst.add(apartment2);
        return lst;

    }*/

    public Collection<Apartment> filterIntersection (Collection<Apartment> apartments, List<CategoryQuery> categories){
        Collection<Apartment> result = new ArrayList<Apartment>();
        Boolean tempBool;
        for (Apartment element: apartments) {
            tempBool = true;
            for (CategoryQuery category : categories) {
                if (!category.mainQuery(element))
                    tempBool=false;
            }
            if (tempBool)
                result.add(element);
        }
        return result;
    }

    public Collection<Apartment> filterMoreResults (Collection<Apartment> apartments, List<CategoryQuery> categories) {
        Collection<Apartment> result = new ArrayList<Apartment>();
        int size = categories.size();
        CategoryQuery c1, c2, c3;
        if (size < 2)
            return result;
        else {
            c1 = categories.get(0);
            c2 = categories.get(1);
            if (size == 2) {
                for (Apartment element : apartments) {
                    if (c1.mainQuery(element) && (!c2.mainQuery(element)))
                        result.add(element);
                }
                for (Apartment element : apartments) {
                    if ((!c1.mainQuery(element)) && c2.mainQuery(element))
                        result.add(element);
                }
            } else {
                c3 = categories.get(2);
                if (size > 3) {
                    for (Apartment element : apartments) {
                        if (c1.mainQuery(element) && c2.mainQuery(element) && c3.mainQuery(element))
                            result.add(element);
                    }
                }
                for (Apartment element : apartments) {
                    if (c1.mainQuery(element) && c2.mainQuery(element) && (!c3.mainQuery(element)))
                        result.add(element);
                }
                for (Apartment element : apartments) {
                    if (c1.mainQuery(element) && (!c2.mainQuery(element)) && c3.mainQuery(element))
                        result.add(element);
                }
                for (Apartment element : apartments) {
                    if (c1.mainQuery(element) && (!c2.mainQuery(element)) && (!c3.mainQuery(element)))
                        result.add(element);
                }
                for (Apartment element : apartments) {
                    if ((!c1.mainQuery(element)) && c2.mainQuery(element) && c3.mainQuery(element))
                        result.add(element);
                }
            }
        }
        return result;
    }
}
