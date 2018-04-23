package il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils;

import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Apartment;

public class FurnitureQuery extends CategoryQuery {
    private int furniture;

    public FurnitureQuery(int furniture){
        this.furniture= furniture;
    }

    public boolean MainQuery(Apartment apartment){
        int furn= apartment.getFurniture();
        return (furn==this.furniture);
    }
}
