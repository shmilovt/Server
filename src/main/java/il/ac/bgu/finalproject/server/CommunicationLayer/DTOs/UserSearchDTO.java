package il.ac.bgu.finalproject.server.CommunicationLayer.DTOs;

import com.google.gson.Gson;


public class UserSearchDTO {
    private CategoryType[] priorities;
    private String neighborhood;
    private Integer distanceFromUniversity;
    private Floor floor;
    private Cost cost;
    private Furniture furniture;
    private NumberOfRooms numberOfRooms;
    private NumberOfRoomates numberOfMates;
    private Boolean balcony;
    private Boolean animals;
    private Boolean protectedSpace;
    private Boolean yard;
    private Boolean warehouse;

    public UserSearchDTO(){}


    public CategoryType[] getPriorities() {
        return priorities;
    }

    public void setPriorities(CategoryType[] priorities) {
        this.priorities = priorities;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public Integer getDistanceFromUniversity() {
        return distanceFromUniversity;
    }

    public void setDistanceFromUniversity(Integer distanceFromUniversity) {
        this.distanceFromUniversity = distanceFromUniversity;
    }

    public Floor getFloor() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }

    public Cost getCost() {
        return cost;
    }

    public void setCost(Cost cost) {
        this.cost = cost;
    }

    public Furniture getFurniture() {
        return furniture;
    }

    public void setFurniture(Furniture furniture) {
        this.furniture = furniture;
    }

    public NumberOfRooms getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(NumberOfRooms numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public NumberOfRoomates getNumberOfMates() {
        return numberOfMates;
    }

    public void setNumberOfMates(NumberOfRoomates numberOfMates) {
        this.numberOfMates = numberOfMates;
    }

    public Boolean getBalcony() {
        return balcony;
    }

    public void setBalcony(Boolean balcony) {
        this.balcony = balcony;
    }

    public Boolean getAnimals() {
        return animals;
    }

    public void setAnimals(Boolean animals) {
        this.animals = animals;
    }

    public Boolean getProtectedSpace() {
        return protectedSpace;
    }

    public void setProtectedSpace(Boolean protectedSpace) {
        this.protectedSpace = protectedSpace;
    }

    public Boolean getYard() {
        return yard;
    }

    public void setYard(Boolean yard) {
        this.yard = yard;
    }

    public Boolean getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Boolean warehouse) {
        this.warehouse = warehouse;
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}
