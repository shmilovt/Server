package il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils;

import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Apartment;

public class NumOfRoomatesQuery extends CategoryQuery{
    private int min;
    private int max;

    public NumOfRoomatesQuery(int min, int max){
        min= min;
        max=max;
    }

    public boolean mainQuery(Apartment apartment){
        int roomates= apartment.getNumberOfMates();
        if(roomates>max && roomates<min)
            return false;
        return true;
    }
}
