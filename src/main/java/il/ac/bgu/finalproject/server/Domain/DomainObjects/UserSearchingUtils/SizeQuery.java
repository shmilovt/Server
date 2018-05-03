package il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils;

public class SizeQuery extends CategoryQuery {
    private int min;
    private int max;

    public SizeQuery(int min, int max){
        this.min= min;
        this.max=max;
    }

    public boolean mainQuery(ResultRecord apartment){
        int size= apartment.getSize();
        if(min==-1)
            return size<=max;
        else if (max==-1)
            return size>=min;
        else return (size<=max && size>=min);
    }
}
