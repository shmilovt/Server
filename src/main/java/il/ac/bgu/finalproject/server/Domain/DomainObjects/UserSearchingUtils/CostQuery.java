package il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils;

import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Apartment;

public class CostQuery extends CategoryQuery{
    private int min;
    private int max;

    public CostQuery(int min, int max){
        min= min;
        max=max;
    }

    public boolean mainQuery(Apartment apartment){
        int cost= apartment.getCost();
        if(min==-1)
            return cost<=max;
        else if (max==-1)
            return cost<=min;
        else return (cost<=max && cost>=min);
    }
}
