package il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils;

public class FloorQuery extends CategoryQuery{
    private int min;
    private int max;

    public FloorQuery(int min, int max){
        min= min;
        max=max;
    }

    public boolean mainQuery(ResultRecord apartment){
        int floor= apartment.getFloor();
        if(floor>max && floor<min)
            return false;
        return true;
    }
}
