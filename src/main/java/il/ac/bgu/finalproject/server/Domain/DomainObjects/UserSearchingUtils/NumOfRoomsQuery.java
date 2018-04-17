package il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils;

import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Apartment;

public class NumOfRoomsQuery extends CategoryQuery{
    private int min;
    private int max;

    public NumOfRoomsQuery(int min, int max){
        min= min;
        max=max;
    }

    public boolean mainQuery(Apartment apartment){
        int rooms= apartment.getNumberOfRooms();
        if(rooms>max && rooms<min)
            return false;
        return true;
    }
}
