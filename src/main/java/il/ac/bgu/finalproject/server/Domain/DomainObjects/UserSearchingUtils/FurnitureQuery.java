package il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils;

public class FurnitureQuery extends CategoryQuery {
    private int furniture;

    public FurnitureQuery(int furniture){
        this.furniture= furniture;
    }

    public boolean MainQuery(ResultRecord apartment){
        int furn= apartment.getFurniture();
        return (furn==this.furniture);
    }
}
