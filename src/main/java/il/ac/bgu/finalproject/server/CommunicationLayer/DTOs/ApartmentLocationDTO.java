package il.ac.bgu.finalproject.server.CommunicationLayer.DTOs;

public class ApartmentLocationDTO {

    private AddressDTO address;
    private String neighborhood;
    private int floor;
    private int apartmentNumber;
    private double distanceFromUniversity;

    public ApartmentLocationDTO(){}
    public ApartmentLocationDTO(AddressDTO address, String neighborhood, int floor, int apartmentNumber, double distanceFromUniversity) {
        this.address = address;
        this.neighborhood = neighborhood;
        this.floor = floor;
        this.apartmentNumber = apartmentNumber;
        this.distanceFromUniversity = distanceFromUniversity;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(int apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public double getDistanceFromUniversity() {
        return distanceFromUniversity;
    }

    public void setDistanceFromUniversity(double distanceFromUniversity) {
        this.distanceFromUniversity = distanceFromUniversity;
    }
}
