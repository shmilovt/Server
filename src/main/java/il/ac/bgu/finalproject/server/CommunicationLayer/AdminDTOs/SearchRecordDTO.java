package il.ac.bgu.finalproject.server.CommunicationLayer.AdminDTOs;

public class SearchRecordDTO {
    private String searchDate;
    private String neighborhood;
    private String distanceFromUniversity;
    private String floor;
    private String cost;
    private String size;
    private String furniture;
    private String numberOfRooms;
    private String numberOfMates;

    public SearchRecordDTO(){}
    public SearchRecordDTO(String searchDate, String neighborhood, String distanceFromUniversity, String floor,
                           String cost, String size, String furniture, String numberOfRooms,
                           String numberOfMates) {
        this.setSearchDate(searchDate);
        this.setNeighborhood(neighborhood);
        this.setDistanceFromUniversity(distanceFromUniversity);
        this.setFloor(floor);
        this.setCost(cost);
        this.setSize(size);
        this.setFurniture(furniture);
        this.setNumberOfRooms(numberOfRooms);
        this.setNumberOfMates(numberOfMates);
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

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
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
}
