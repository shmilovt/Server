package il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils;

import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Apartment;

public class SizeQuery extends CategoryQuery {
    private int min;
    private int max;

    public SizeQuery(int min, int max){
        this.min= min;
        this.max=max;
    }

    public boolean mainQuery(Apartment apartment){
        int size= apartment.getSize();
        if(min==-1)
            return size<=max;
        else if (max==-1)
            return size<=min;
        else return (size<=max && size>=min);
    }
}
