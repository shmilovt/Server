package il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils;

public class NumOfRoomsQuery extends CategoryQuery{
    private double numOfRooms;

    public NumOfRoomsQuery(int numOfRooms){
        this.numOfRooms=numOfRooms;
    }

    @Override
    public boolean mainQuery(ResultRecord apartment){
        double rooms= apartment.getNumberOfRooms();
        if (numOfRooms==6){
            if (rooms>=numOfRooms)
                return true;
        }
        else if(rooms==numOfRooms)
            return true;
        return false;
    }
}
