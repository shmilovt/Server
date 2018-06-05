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

    @Override
    public boolean mainQuery(ResultRecord apartment) {
        if (thing == MustHaveThing.balcony) {
            if (apartment.getBalcony()==1)
                return true;
        } else if (thing == MustHaveThing.garden) {
            if (apartment.getYard()==1)
                return true;
        } else if (thing == MustHaveThing.pets) {
            if (apartment.getAnimals()==1)
                return true;
        } else if (thing == MustHaveThing.protectedSpace) {
            if (apartment.getProtectedSpace()==1)
                return true;
        } else if (thing == MustHaveThing.warehouse) {
            if (apartment.getWarehouse()==1)
                return true;
        }
        return false;
    }
}
