package il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils;

public class CalculatorCosts {
    private int neighborhoodCost;
    private int furnitureCost;
    // Renovated(shiputz), pool, parentsRoom,
    private int timeFromUniCost;
    private int sizeForPersonCost;
    private int roomatesCost;
    private int gardenCost;
    private int balconyCost;

    private int protectedSpaceCost;

    private static CalculatorCosts instance = null;
    protected CalculatorCosts() {
//        neighborhood=;
//        furniture;
//        timeFromUni;
//        sizeForPerson;
//        roomates;
        gardenCost= 45;
        balconyCost= 35;

        protectedSpaceCost=50;
    }


    public static CalculatorCosts getInstance() {
        if(instance == null) {
            instance = new CalculatorCosts();
        }
        return instance;
    }


}
