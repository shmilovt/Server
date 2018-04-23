package il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils;

import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Apartment;

public class NumOfRoomatesQuery extends CategoryQuery{
    private int numOfRoomates;

    public NumOfRoomatesQuery(int numOfRoomates){
        this.numOfRoomates=numOfRoomates;
    }

    public boolean mainQuery(Apartment apartment){
        int roomates= apartment.getNumberOfMates();
        if(roomates==numOfRoomates)
            return true;
        return false;
    }
}
