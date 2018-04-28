package il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils;
public class CostQuery extends CategoryQuery{
    private int min;
    private int max;

    public CostQuery(int min, int max){
        this.min= min;
        this.max=max;
    }

    public boolean mainQuery(ResultRecord apartment){
        int cost= apartment.getCost();
        if(min==-1)
            return cost<=max;
        else if (max==-1)
            return cost<=min;
        else return (cost<=max && cost>=min);
    }
}
