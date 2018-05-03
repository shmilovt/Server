package il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils;

public class NumOfRoomatesQuery extends CategoryQuery{
    private int numOfRoomates;

    public NumOfRoomatesQuery(int numOfRoomates){
        this.numOfRoomates=numOfRoomates;
    }

    @Override
    public boolean mainQuery(ResultRecord apartment){
        int roomates= apartment.getNumberOfRoomates();
        if(roomates==numOfRoomates)
            return true;
        return false;
    }
}
