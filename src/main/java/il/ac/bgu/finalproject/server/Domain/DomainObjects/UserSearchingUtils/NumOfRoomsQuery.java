package il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils;

import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Apartment;

public class NumOfRoomsQuery extends CategoryQuery{
    private int numOfRooms;

    public NumOfRoomsQuery(int numOfRooms){
        this.numOfRooms=numOfRooms;
    }

    public boolean mainQuery(Apartment apartment){
        int rooms= apartment.getNumberOfMates();
        if (numOfRooms==6){
            if (rooms>=numOfRooms)
                return true;
        }
        else if(rooms==numOfRooms)
            return true;
        return false;
    }
}
