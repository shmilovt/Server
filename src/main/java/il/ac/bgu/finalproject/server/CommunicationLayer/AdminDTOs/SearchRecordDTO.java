package il.ac.bgu.finalproject.server.CommunicationLayer.AdminDTOs;

public class SearchRecordDTO {
    private String searchDate;
    private String neighborhood;
    private String distanceFromUniversity;
    private String floorMin;
    private String floorMax;
    private String costMin;
    private String costMax;
    private String sizeMin;
    private String sizeMax;
    private String furniture;
    private String numberOfRooms;
    private String numberOfMates;

    private int protectedSpace;
    private int garden;
    private int balcony;
    private int pets;
    private int warehous;

    public SearchRecordDTO(){}
    public SearchRecordDTO(String searchDate, String neighborhood, String distanceFromUniversity, String floorMin,
                           String floorMax, String costMin, String costMax, String sizeMin, String sizeMax,
                           String furniture, String numberOfRooms,
                           String numberOfMates,
                           int protectedSpace, int garden, int balcony, int pets, int warehouse) {
        this.setSearchDate(searchDate);
        this.setNeighborhood(neighborhood);
        this.setDistanceFromUniversity(distanceFromUniversity);
        this.setFloorMin(floorMin);
        this.setFloorMax(floorMax);
        this.setCostMin(costMin);
        this.setCostMax(costMax);
        this.setSizeMin(sizeMin);
        this.setSizeMax(sizeMax);
        this.setFurniture(furniture);
        this.setNumberOfRooms(numberOfRooms);
        this.setNumberOfMates(numberOfMates);

        this.setProtectedSpace(protectedSpace);
        this.setGarden(garden);
        this.setBalcony(balcony);
        this.setPets(pets);
        this.setWarehous(warehouse);
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getDistanceFromUniversity() {
        return distanceFromUniversity;
    }

    public void setDistanceFromUniversity(String distanceFromUniversity) {
        this.distanceFromUniversity = distanceFromUniversity;
    }

    public String getFurniture() {
        return furniture;
    }

    public void setFurniture(String furniture) {
        this.furniture = furniture;
    }

    public String getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(String numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public String getNumberOfMates() {
        return numberOfMates;
    }

    public void setNumberOfMates(String numberOfMates) {
        this.numberOfMates = numberOfMates;
    }

    public String getSearchDate() {
        return searchDate;
    }

    public void setSearchDate(String searchDate) {
        this.searchDate = searchDate;
    }

    public int isProtectedSpace() {
        return protectedSpace;
    }

    public void setProtectedSpace(int protectedSpace) {
        this.protectedSpace = protectedSpace;
    }

    public int isGarden() {
        return garden;
    }

    public void setGarden(int garden) {
        this.garden = garden;
    }

    public int isBalcony() {
        return balcony;
    }

    public void setBalcony(int balcony) {
        this.balcony = balcony;
    }

    public int isPets() {
        return pets;
    }

    public void setPets(int pets) {
        this.pets = pets;
    }

    public int isWarehous() {
        return warehous;
    }

    public void setWarehous(int warehous) {
        this.warehous = warehous;
    }

    public String getFloorMin() {
        return floorMin;
    }

    public void setFloorMin(String floorMin) {
        this.floorMin = floorMin;
    }

    public String getFloorMax() {
        return floorMax;
    }

    public void setFloorMax(String floorMax) {
        this.floorMax = floorMax;
    }

    public String getCostMin() {
        return costMin;
    }

    public void setCostMin(String costMin) {
        this.costMin = costMin;
    }

    public String getCostMax() {
        return costMax;
    }

    public void setCostMax(String costMax) {
        this.costMax = costMax;
    }

    public String getSizeMin() {
        return sizeMin;
    }

    public void setSizeMin(String sizeMin) {
        this.sizeMin = sizeMin;
    }

    public String getSizeMax() {
        return sizeMax;
    }

    public void setSizeMax(String sizeMax) {
        this.sizeMax = sizeMax;
    }
}
