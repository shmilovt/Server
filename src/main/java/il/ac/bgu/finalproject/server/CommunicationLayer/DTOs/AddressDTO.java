package il.ac.bgu.finalproject.server.CommunicationLayer.DTOs;

public class AddressDTO {
    private String street;
    private int numOfBuilding;
    private String neighborhood;

    public AddressDTO(){}

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumOfBuilding() {
        return numOfBuilding;
    }

    public void setNumOfBuilding(int numOfBuilding) {
        this.numOfBuilding = numOfBuilding;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }
}
