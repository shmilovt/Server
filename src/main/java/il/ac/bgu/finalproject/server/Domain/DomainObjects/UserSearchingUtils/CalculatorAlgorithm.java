package il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils;

import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Apartment;

public class CalculatorAlgorithm {
    public static Boolean isFairPrice(Apartment apartment){
        int timeFromUniCost= calcTimeFromUniCost(apartment.getApartmentLocation().getDistanceFromUniversity());
        int neighborhoodCost= calcNeighborhoodCost(apartment.getApartmentLocation().getNeighborhood());

        return false;
    }

    private static int calcNeighborhoodCost(String neighborhood) {
        switch (neighborhood){
            case "שכונה א'":
                return CalculatorCosts.getInstance().getNeighborhoodCost_B_Ramot();////dont have////
            case "שכונה ב'":
                break;
            case "שכונה ד'":
                break;
            case "העיר העתיקה":
                break;
            case "שכונה ג'":
                break;
//            case "שכונה א'":
//                break;
//            case "שכונה א'":
//                break;
//            case "שכונה א'":
//                break;
//            case "שכונה א'":
//                break;
//            case "שכונה א'":
//                break;
//            case "שכונה א'":
//                break;
//            case "שכונה א'":
//                break;
//                case "שכונה א'":
//                break;
                default: break;

        }

        //if (neighborhood==null)
            return -1;
    }

    private static int calcTimeFromUniCost(double timeFromUni) {
        if (timeFromUni==-1)
            return -1;
        else if (timeFromUni<=10)
            return CalculatorCosts.getInstance().getTimeFromUniCost_10();
        else if (timeFromUni<=20)
            return CalculatorCosts.getInstance().getTimeFromUniCost_20();
        else
            return CalculatorCosts.getInstance().getTimeFromUniCost_G_20();
    }
}
