package il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils;

import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Apartment;

public class FloorQuery extends CategoryQuery{
    private int min;
    private int max;

    public FloorQuery(int min, int max){
        min= min;
        max=max;
    }

    public boolean mainQuery(Apartment apartment){
        int floor= apartment.getApartmentLocation().getFloor();
        if(floor>max && floor<min)
            return false;
        return true;
    }
}
