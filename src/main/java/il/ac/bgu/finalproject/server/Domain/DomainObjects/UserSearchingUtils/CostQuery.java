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
        if(cost>max && cost<min)
            return false;
        return true;
    }
}
