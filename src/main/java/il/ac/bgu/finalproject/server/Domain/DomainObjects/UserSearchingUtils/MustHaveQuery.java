package il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils;

import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Apartment;

public class MustHaveQuery extends CategoryQuery{
    MustHaveThing thing;
    public MustHaveQuery(MustHaveThing thing){
        this.thing=thing;
    }
    public enum MustHaveThing{
        protectedSpace,
        garden,
        balcony,
        pets,
        warehouse
    }

    public boolean MainQuery(Apartment apartment) {
        if (thing == MustHaveThing.balcony) {
            if (apartment.getBalcony() == 1)
                return true;
        } else if (thing == MustHaveThing.garden) {
            if (apartment.getGarden() == 1)
                return true;
        } else if (thing == MustHaveThing.pets) {
            if (apartment.getAnimal() == 1)
                return true;
        } else if (thing == MustHaveThing.protectedSpace) {
            if (apartment.getProtectedSpace() == 1)
                return true;
        } else if (thing == MustHaveThing.warehouse) {
            if (apartment.getWarehouse() == 1)
                return true;
        }
        return false;
    }
}
