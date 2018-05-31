package il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils;

public class CalculatorCosts {
    public static int basicCost=1100;

    private int protectedSpaceCost;
    private int timeFromUniCost_10;
    private int timeFromUniCost_20;
    private int timeFromUniCost_G_20;
    private int neighborhoodCost_B_Ramot;
    private int neighborhoodCost_oldV_Wingate;
    private int neighborhoodCost_D;
    private int neighborhoodCost_G;
    private int furnitureCost_full;
    private int furnitureCost_half;
    private int furnitureCost_none;
    //We dont have: Renovated(shiputz), pool, parentsRoom,
    private int sizeCost_25;
    private int sizeCost_30;
    private int sizeCost_35;
    private int sizeCost_35_up;
    //size here is for one person
    private int roomatesCost_1;
    private int roomatesCost_2;
    private int roomatesCost_3;
    private int roomatesCost_4;
    private int roomatesCost_5;
    private int roomatesCost_6;
    private int gardenCost;
    private int balconyCost;


//


    private static CalculatorCosts instance = null;
    protected CalculatorCosts() {
        //in the future: read from the file of the values
        setProtectedSpaceCost(50);
        setTimeFromUniCost_10(0);
        setTimeFromUniCost_20(-25);
        setTimeFromUniCost_G_20(-50);
        setNeighborhoodCost_B_Ramot(75);
        setNeighborhoodCost_oldV_Wingate(35);
        setNeighborhoodCost_D(-25);
        setNeighborhoodCost_G(-60);
        setFurnitureCost_full(0);
        setFurnitureCost_half(-25);
        setFurnitureCost_none(-50);
        setSizeCost_25(-70);
        setSizeCost_30(-35);
        setSizeCost_35(0);
        setSizeCost_35_up(50);
        setRoomatesCost_1(945);
        setRoomatesCost_2(0);
        setRoomatesCost_3(-50);
        setRoomatesCost_4(-75);
        setRoomatesCost_5(-90);
        setRoomatesCost_6(-100);
        setGardenCost(45);
        setBalconyCost(35);

    }


    public static CalculatorCosts getInstance() {
        if(instance == null) {
            instance = new CalculatorCosts();
        }
        return instance;
    }

    //the triger to update the calculator will call this function:
    public void setCalculator(int protectedSpaceCost, int timeFromUniCost_10, int timeFromUniCost_20,
                              int timeFromUniCost_G_20, int neighborhoodCost_B_Ramot,
                              int neighborhoodCost_oldV_Wingate, int neighborhoodCost_D,
                              int neighborhoodCost_G, int furnitureCost_full, int furnitureCost_half,
                              int furnitureCost_none, int sizeCost_25, int sizeCost_30, int sizeCost_35,
                              int sizeCost_35_up, int roomatesCost_1, int roomatesCost_2, int roomatesCost_3,
                              int roomatesCost_4, int roomatesCost_5, int roomatesCost_6, int gardenCost,
                              int balconyCost){
        this.setProtectedSpaceCost(protectedSpaceCost);
        this.setTimeFromUniCost_10(timeFromUniCost_10);
        this.setTimeFromUniCost_20(timeFromUniCost_20);
        this.setTimeFromUniCost_G_20(timeFromUniCost_G_20);
        this.setNeighborhoodCost_B_Ramot(neighborhoodCost_B_Ramot);
        this.setNeighborhoodCost_oldV_Wingate(neighborhoodCost_oldV_Wingate);
        this.setNeighborhoodCost_D(neighborhoodCost_D);
        this.setNeighborhoodCost_G(neighborhoodCost_G);
        this.setFurnitureCost_full(furnitureCost_full);
        this.setFurnitureCost_half(furnitureCost_half);
        this.setFurnitureCost_none(furnitureCost_none);
        this.setSizeCost_25(sizeCost_25);
        this.setSizeCost_30(sizeCost_30);
        this.setSizeCost_35(sizeCost_35);
        this.setSizeCost_35_up(sizeCost_35_up);
        this.setRoomatesCost_1(roomatesCost_1);
        this.setRoomatesCost_2(roomatesCost_2);
        this.setRoomatesCost_3(roomatesCost_3);
        this.setRoomatesCost_4(roomatesCost_4);
        this.setRoomatesCost_5(roomatesCost_5);
        this.setRoomatesCost_6(roomatesCost_6);
        this.setGardenCost(gardenCost);
        this.setBalconyCost(balconyCost);

    }

    public int getProtectedSpaceCost() {
        return protectedSpaceCost;
    }

    public void setProtectedSpaceCost(int protectedSpaceCost) {
        this.protectedSpaceCost = protectedSpaceCost;
    }

    public int getTimeFromUniCost_10() {
        return timeFromUniCost_10;
    }

    public void setTimeFromUniCost_10(int timeFromUniCost_10) {
        this.timeFromUniCost_10 = timeFromUniCost_10;
    }

    public int getTimeFromUniCost_20() {
        return timeFromUniCost_20;
    }

    public void setTimeFromUniCost_20(int timeFromUniCost_20) {
        this.timeFromUniCost_20 = timeFromUniCost_20;
    }

    public int getTimeFromUniCost_G_20() {
        return timeFromUniCost_G_20;
    }

    public void setTimeFromUniCost_G_20(int timeFromUniCost_G_20) {
        this.timeFromUniCost_G_20 = timeFromUniCost_G_20;
    }

    public int getNeighborhoodCost_B_Ramot() {
        return neighborhoodCost_B_Ramot;
    }

    public void setNeighborhoodCost_B_Ramot(int neighborhoodCost_B_Ramot) {
        this.neighborhoodCost_B_Ramot = neighborhoodCost_B_Ramot;
    }

    public int getNeighborhoodCost_oldV_Wingate() {
        return neighborhoodCost_oldV_Wingate;
    }

    public void setNeighborhoodCost_oldV_Wingate(int neighborhoodCost_oldV_Wingate) {
        this.neighborhoodCost_oldV_Wingate = neighborhoodCost_oldV_Wingate;
    }

    public int getNeighborhoodCost_D() {
        return neighborhoodCost_D;
    }

    public void setNeighborhoodCost_D(int neighborhoodCost_D) {
        this.neighborhoodCost_D = neighborhoodCost_D;
    }

    public int getNeighborhoodCost_G() {
        return neighborhoodCost_G;
    }

    public void setNeighborhoodCost_G(int neighborhoodCost_G) {
        this.neighborhoodCost_G = neighborhoodCost_G;
    }

    public int getFurnitureCost_full() {
        return furnitureCost_full;
    }

    public void setFurnitureCost_full(int furnitureCost_full) {
        this.furnitureCost_full = furnitureCost_full;
    }

    public int getFurnitureCost_half() {
        return furnitureCost_half;
    }

    public void setFurnitureCost_half(int furnitureCost_half) {
        this.furnitureCost_half = furnitureCost_half;
    }

    public int getFurnitureCost_none() {
        return furnitureCost_none;
    }

    public void setFurnitureCost_none(int furnitureCost_none) {
        this.furnitureCost_none = furnitureCost_none;
    }

    public int getSizeCost_25() {
        return sizeCost_25;
    }

    public void setSizeCost_25(int sizeCost_25) {
        this.sizeCost_25 = sizeCost_25;
    }

    public int getSizeCost_30() {
        return sizeCost_30;
    }

    public void setSizeCost_30(int sizeCost_30) {
        this.sizeCost_30 = sizeCost_30;
    }

    public int getSizeCost_35() {
        return sizeCost_35;
    }

    public void setSizeCost_35(int sizeCost_35) {
        this.sizeCost_35 = sizeCost_35;
    }

    public int getSizeCost_35_up() {
        return sizeCost_35_up;
    }

    public void setSizeCost_35_up(int sizeCost_35_up) {
        this.sizeCost_35_up = sizeCost_35_up;
    }

    public int getRoomatesCost_1() {
        return roomatesCost_1;
    }

    public void setRoomatesCost_1(int roomatesCost_1) {
        this.roomatesCost_1 = roomatesCost_1;
    }

    public int getRoomatesCost_2() {
        return roomatesCost_2;
    }

    public void setRoomatesCost_2(int roomatesCost_2) {
        this.roomatesCost_2 = roomatesCost_2;
    }

    public int getRoomatesCost_3() {
        return roomatesCost_3;
    }

    public void setRoomatesCost_3(int roomatesCost_3) {
        this.roomatesCost_3 = roomatesCost_3;
    }

    public int getRoomatesCost_4() {
        return roomatesCost_4;
    }

    public void setRoomatesCost_4(int roomatesCost_4) {
        this.roomatesCost_4 = roomatesCost_4;
    }

    public int getRoomatesCost_5() {
        return roomatesCost_5;
    }

    public void setRoomatesCost_5(int roomatesCost_5) {
        this.roomatesCost_5 = roomatesCost_5;
    }

    public int getRoomatesCost_6() {
        return roomatesCost_6;
    }

    public void setRoomatesCost_6(int roomatesCost_6) {
        this.roomatesCost_6 = roomatesCost_6;
    }

    public int getGardenCost() {
        return gardenCost;
    }

    public void setGardenCost(int gardenCost) {
        this.gardenCost = gardenCost;
    }

    public int getBalconyCost() {
        return balconyCost;
    }

    public void setBalconyCost(int balconyCost) {
        this.balconyCost = balconyCost;
    }
}
