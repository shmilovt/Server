package il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils;

import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Apartment;

public class TimeFromUniQuery extends CategoryQuery {
    private int max;

    public TimeFromUniQuery(int max){
        max=max;
    }

    public boolean mainQuery(Apartment apartment){
        double time= apartment.getApartmentLocation().getDistanceFromUniversity();
        return(time<=max);
    }
}
