package il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils;

import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Apartment;

import java.util.ArrayList;
import java.util.List;

public class SearchAlgorithm {

    public SearchAlgorithm(){}


    public SearchResults filterIntersection (List<Apartment> apartments, List<CategoryQuery> categories){
        List<Apartment> result = new ArrayList<Apartment>();
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
       // return result;
        return null;
    }

    public SearchResults filterMoreResults (List<Apartment> apartments, List<CategoryQuery> categories) {
        List<Apartment> result = new ArrayList<Apartment>();
        int size = categories.size();
        CategoryQuery c1, c2, c3;
        if (size < 2) {
            //return result;
            return null;
        }
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
        //return result;
        return null;
    }
}
