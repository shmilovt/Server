package il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils;

import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Apartment;

public class CalculatorAlgorithm {
    public static Boolean isFairPrice(Apartment apartment){
        int timeFromUniCost= calcTimeFromUniCost(apartment.getApartmentLocation().getDistanceFromUniversity());
        int neighborhoodCost= calcNeighborhoodCost(apartment.getApartmentLocation().getNeighborhood());
        int sizeCost= calcSizeCost(apartment.getCost());
        int furnitureCost= calcFurnitureCost(apartment.getFurniture());
        int gardenCost = calcGardenCost(apartment.getGarden(),apartment.getGardenSize());
        int balconyCost= calcBalconyCost(apartment.getBalcony());

        int roomatesCost= calcRoomatesCost(apartment.getNumberOfMates());

        int sum=1100+ timeFromUniCost+neighborhoodCost+sizeCost+furnitureCost+gardenCost+balconyCost+roomatesCost;
        return (sum>= apartment.getCost());
    }

    private static int calcBalconyCost(int balcony) {
        if (balcony==1)
            return CalculatorCosts.getInstance().getBalconyCost();
        return 0;
    }

    private static int calcGardenCost(int garden, int gardenSize) {
        if(garden==1)
            return CalculatorCosts.getInstance().getGardenCost();
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

    private static int calcSizeCost(int cost) {
        if (cost<=25)
            return CalculatorCosts.getInstance().getSizeCost_25();
        else if (cost<=30)
            return CalculatorCosts.getInstance().getSizeCost_30();
        else if (cost<=35)
            return CalculatorCosts.getInstance().getSizeCost_35();
        else
            return CalculatorCosts.getInstance().getSizeCost_35_up();
    }

    private static int calcNeighborhoodCost(String neighborhood) {
        switch (neighborhood) {
            case "שכונה ד'":
                return CalculatorCosts.getInstance().getNeighborhoodCost_D();
            case "העיר העתיקה":
                break;
            case "שכונת דרום":
                break;
            case "שיכון דרום":
                break;
            case "המרכז האזרחי":
                break;
            case "שכונה א'":
                break;
//                return CalculatorCosts.getInstance().getNeighborhoodCost_B_Ramot();////dont have////
            case "שכונה ב'":
                return CalculatorCosts.getInstance().getNeighborhoodCost_B_Ramot();
            case "שכונה ג'":
                return CalculatorCosts.getInstance().getNeighborhoodCost_G();
            case "שכונה ה'":
                break;
            case "שכונה ו' החדשה":
                break;
            case "שכונה ו'":
                return CalculatorCosts.getInstance().getNeighborhoodCost_oldV_Wingate();
            case "שכונה ט'":
                break;
                case "שכונה יא":
                break;
            case "נאות אלון":
                break;
            case "נווה זאב":
                break;
            case "נווה נוי":
                break;
            case "נחל בקע":
                break;
            case "נחל עשן":
                break;
            case "נווה מנחם":
                break;
            case "הסיגליות":
                break;
            case "רמות":
                return CalculatorCosts.getInstance().getNeighborhoodCost_B_Ramot();
            case "נאות אברהם":
                break;
                case "נווה אילן":
                break;
            case "פלח 7":
                break;
            case "קריית גנים":
                break;
            case "הכלניות":
                break;
            case "שכונת רבין":
                break;
            case "נאות לון":
                break;
            case "קריית הייטק":
                break;
            case "קרית יהודית":
                break;
            case "עמק שרה":
                break;
            default:
                break;
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
