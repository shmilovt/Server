package il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils;

public class FloorQuery extends CategoryQuery{
    private int min;
    private int max;

    public FloorQuery(int min, int max){
        this.min= min;
        this.max=max;
    }

    @Override
    public boolean mainQuery(ResultRecord apartment){
        int floor= apartment.getFloor();
        if(min==-1)
            return floor<=max;
        else if (max==-1)
            return floor>=min;
        else return (floor<=max && floor>=min);
    }
}
