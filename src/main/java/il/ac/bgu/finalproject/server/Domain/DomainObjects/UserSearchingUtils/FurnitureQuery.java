package il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils;

import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Apartment;

public class FurnitureQuery {
    private FurnitureType ftype;
    private int furniture;

    public FurnitureQuery(
            /*FurnitureType furniture*/
            int furniture){
        this.furniture= furniture;

    }

    public enum FurnitureType{
        full,
        half,
        none
    }

    //TBD
    public boolean MainQuery(Apartment apartment){
        int furn= apartment.getFurniture();
        return (furn==this.furniture);
    }
}
