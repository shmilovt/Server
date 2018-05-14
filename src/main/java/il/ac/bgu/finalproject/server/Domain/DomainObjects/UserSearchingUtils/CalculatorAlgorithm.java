package il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils;

import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Apartment;

public class CalculatorAlgorithm {
    public static Boolean isFairPrice(Apartment apartment) {
        int timeFromUniCost = calcTimeFromUniCost(apartment.getApartmentLocation().getDistanceFromUniversity());
        int neighborhoodCost = 0;
        if (apartment.getApartmentLocation().getAddress().getStreet().equals("וינגייט") &&
                apartment.getApartmentLocation().getAddress().getNumber()>51) {
            neighborhoodCost = CalculatorCosts.getInstance().getNeighborhoodCost_oldV_Wingate();
        } else
            neighborhoodCost = calcNeighborhoodCost(apartment.getApartmentLocation().getNeighborhood());
        int sizeCost = calcSizeCost(apartment.getCost());
        int furnitureCost = calcFurnitureCost(apartment.getFurniture());
        int gardenCost = calcGardenCost(apartment.getGarden(), apartment.getGardenSize());
        int balconyCost = calcBalconyCost(apartment.getBalcony());

        int roomatesCost = calcRoomatesCost(apartment.getNumberOfMates());

        int sum = 1100 + timeFromUniCost + neighborhoodCost + sizeCost + furnitureCost + gardenCost + balconyCost + roomatesCost;
        return (sum >= apartment.getCost());
    }

    private static int calcBalconyCost(int balcony) {
        if (balcony==1)
            return CalculatorCosts.getInstance().getBalconyCost();
        return 0;
    }

    private static int calcGardenCost(int garden, int gardenSize) {
        if(garden==1) {
//            if (gardenSize>15)
                return CalculatorCosts.getInstance().getGardenCost();
        }
        return 0;
    }

    private static int calcRoomatesCost(int numberOfMates) {
        if (numberOfMates==1||numberOfMates==0)
            return CalculatorCosts.getInstance().getRoomatesCost_1();
        else if (numberOfMates==2)
            return CalculatorCosts.getInstance().getRoomatesCost_2();
        else if (numberOfMates==3)
            return CalculatorCosts.getInstance().getRoomatesCost_3();
        else if (numberOfMates==4)
            return CalculatorCosts.getInstance().getRoomatesCost_4();
        else if (numberOfMates==5)
            return CalculatorCosts.getInstance().getRoomatesCost_5();
        else
            return CalculatorCosts.getInstance().getRoomatesCost_6();
    }

    private static int calcFurnitureCost(int furniture) {
        if (furniture==1)
            return CalculatorCosts.getInstance().getFurnitureCost_half();
        else if (furniture==2)
            return CalculatorCosts.getInstance().getFurnitureCost_full();
        else
            return CalculatorCosts.getInstance().getFurnitureCost_none();
    }

    private static int calcSizeCost(int cost) { //yanay say we can delete it
        if (cost<=25)
            return CalculatorCosts.getInstance().getSizeCost_25();
        else if (cost<=30)
            return CalculatorCosts.getInstance().getSizeCost_30();
        else if (cost<=35)
            return CalculatorCosts.getInstance().getSizeCost_35();
        else if (cost>35)
            return CalculatorCosts.getInstance().getSizeCost_35_up();
        else return 0;
    }

    private static int calcNeighborhoodCost(String neighborhood) {
        switch (neighborhood) {
            case "שכונה ד'":
                return CalculatorCosts.getInstance().getNeighborhoodCost_D();
            case "העיר העתיקה":
                return CalculatorCosts.getInstance().getNeighborhoodCost_oldV_Wingate();
            case "שכונת דרום":
                return CalculatorCosts.getInstance().getNeighborhoodCost_oldV_Wingate();
            case "שיכון דרום":
                return CalculatorCosts.getInstance().getNeighborhoodCost_oldV_Wingate();
            case "המרכז האזרחי":
                return CalculatorCosts.getInstance().getNeighborhoodCost_D();
            case "שכונה א'":
                return CalculatorCosts.getInstance().getNeighborhoodCost_oldV_Wingate();
            case "שכונה ב'":
                return CalculatorCosts.getInstance().getNeighborhoodCost_B_Ramot();
            case "שכונה ג'":
                return CalculatorCosts.getInstance().getNeighborhoodCost_G();
            case "שכונה ה'":
                return CalculatorCosts.getInstance().getNeighborhoodCost_oldV_Wingate();
            case "שכונה ו' החדשה":
                return CalculatorCosts.getInstance().getNeighborhoodCost_oldV_Wingate();
            case "שכונה ו'":
                return CalculatorCosts.getInstance().getNeighborhoodCost_oldV_Wingate();
            case "שכונה ט'":
                return CalculatorCosts.getInstance().getNeighborhoodCost_oldV_Wingate();
            case "שכונה יא":
                return CalculatorCosts.getInstance().getNeighborhoodCost_oldV_Wingate();
            case "נאות אלון":
                return CalculatorCosts.getInstance().getNeighborhoodCost_B_Ramot();
            case "נווה זאב":
                return CalculatorCosts.getInstance().getNeighborhoodCost_B_Ramot();
            case "נווה נוי":
                return CalculatorCosts.getInstance().getNeighborhoodCost_B_Ramot();
            case "נחל בקע":
                return CalculatorCosts.getInstance().getNeighborhoodCost_B_Ramot();
            case "נחל עשן":
                return CalculatorCosts.getInstance().getNeighborhoodCost_B_Ramot();
            case "נווה מנחם":
                return CalculatorCosts.getInstance().getNeighborhoodCost_B_Ramot();
            case "הסיגליות":
                return CalculatorCosts.getInstance().getNeighborhoodCost_B_Ramot();
            case "רמות":
                return CalculatorCosts.getInstance().getNeighborhoodCost_B_Ramot();
            case "נאות אברהם":
                return CalculatorCosts.getInstance().getNeighborhoodCost_B_Ramot();
//            case "נווה אילן":
//                break; ////
            case "פלח 7":
                return CalculatorCosts.getInstance().getNeighborhoodCost_B_Ramot();
//            case "קריית גנים":
//                break; ////
            case "הכלניות":
                return CalculatorCosts.getInstance().getNeighborhoodCost_B_Ramot();
//            case "שכונת רבין":
//                break; ////
            case "נאות לון":
                return CalculatorCosts.getInstance().getNeighborhoodCost_B_Ramot();
//            case "קריית הייטק":
//                break;
//            case "קרית יהודית":
//                break;
//            case "עמק שרה":
//                break;
            default:
                break;
        }

        //if (neighborhood==null)
        return 0;
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