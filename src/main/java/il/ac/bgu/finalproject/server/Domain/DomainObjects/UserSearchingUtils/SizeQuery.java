package il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils;

import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Apartment;

public class SizeQuery extends CategoryQuery {
    private int min;
    private int max;

    public SizeQuery(int min, int max){
        min= min;
        max=max;
    }

    public boolean mainQuery(Apartment apartment){
        int size= apartment.getSize();
        if(size>max && size<min)
            return false;
        return true;
    }
}
