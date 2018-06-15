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
        switch (thing) {
            case warehouse:
                return apartment.getWarehouse() == 1;
            case garden:
                return apartment.getYard() == 1;
            case pets:
                return apartment.getAnimals() == 1;
            case balcony:
                return apartment.getBalcony() == 1;
            case protectedSpace:
                return apartment.getProtectedSpace() == 1;
            default:
                return false;
        }
//        if (thing == MustHaveThing.balcony) {
//            return apartment.getBalcony()==1;
//        } else if (thing == MustHaveThing.garden) {
//            return apartment.getYard()==1;
//        } else if (thing == MustHaveThing.pets) {
//            return apartment.getAnimals()==1;
//        } else if (thing == MustHaveThing.protectedSpace) {
//            return apartment.getProtectedSpace()==1;
//        } else if (thing == MustHaveThing.warehouse) {
//            return apartment.getWarehouse()==1;
//        }
//        return false;
    }
}
