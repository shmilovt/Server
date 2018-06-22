package il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils;

public class TimeFromUniQuery extends CategoryQuery {
    private int max;

    public TimeFromUniQuery(int max){
        this.max=max;
    }

    public boolean mainQuery(ResultRecord apartment){
        double time= apartment.getDistanceFromUniversity();
        return(time<=max);
    }
}
