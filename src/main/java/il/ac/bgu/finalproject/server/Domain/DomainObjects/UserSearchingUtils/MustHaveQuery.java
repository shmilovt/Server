package il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils;

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

    public boolean MainQuery(ResultRecord apartment) {
        if (thing == MustHaveThing.balcony) {
            if (apartment.isBalcony())
                return true;
        } else if (thing == MustHaveThing.garden) {
            if (apartment.isYard())
                return true;
        } else if (thing == MustHaveThing.pets) {
            if (apartment.isAnimals())
                return true;
        } else if (thing == MustHaveThing.protectedSpace) {
            if (apartment.isProtectedSpace())
                return true;
        } else if (thing == MustHaveThing.warehouse) {
            if (apartment.isWarehouse())
                return true;
        }
        return false;
    }
}
