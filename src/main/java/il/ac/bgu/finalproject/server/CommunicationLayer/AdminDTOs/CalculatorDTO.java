package il.ac.bgu.finalproject.server.CommunicationLayer.AdminDTOs;

import com.google.gson.Gson;

public class CalculatorDTO {
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
    private int sizeCost_25;
    private int sizeCost_30;
    private int sizeCost_35;
    private int sizeCost_35_up;
    private int roomatesCost_1;
    private int roomatesCost_2;
    private int roomatesCost_3;
    private int roomatesCost_4;
    private int roomatesCost_5;
    private int roomatesCost_6;
    private int gardenCost;
    private int balconyCost;

    public CalculatorDTO(){}

    public static String toJSON(CalculatorDTO calculatorDTO){
        Gson gson = new Gson();
        return gson.toJson(calculatorDTO);
    }

    public static CalculatorDTO fromJSON(String jsonString){
        Gson gson = new Gson();
        return gson.fromJson(jsonString, CalculatorDTO.class);
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
