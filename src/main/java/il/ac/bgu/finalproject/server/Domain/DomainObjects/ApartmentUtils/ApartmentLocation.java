package il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils;

public class ApartmentLocation {
    public static final int UNKNOWN_APARTMENT_NUMBER = -1;
    public static final int UNKNOWN_APARTMENT_FLOOR = -1;
    private Address address;
    private String neighborhood;
    private int floor;
    private int apartmentNumber;
    private double distanceFromUniversity;
    private double longitude;
    private double latitude;


    public ApartmentLocation(){}
    public ApartmentLocation(Address address) {
        this.address = address;
        this.floor = UNKNOWN_APARTMENT_FLOOR ;
        this.apartmentNumber = UNKNOWN_APARTMENT_NUMBER;
        this.neighborhood = address.calcNeighborhood();
        distanceFromUniversity = address.calcDistanceFromUniversity();
    }
    public ApartmentLocation(Address address, int floor) {
        this.address = address;
        this.floor = floor;
        this.apartmentNumber = UNKNOWN_APARTMENT_NUMBER;
        this.neighborhood = address.calcNeighborhood();
        distanceFromUniversity = address.calcDistanceFromUniversity();
    }
    public ApartmentLocation(Address address, int floor, int apartmentNumber) {
        this.address = address;
        this.floor = floor;
        this.apartmentNumber = apartmentNumber;
        this.neighborhood = "neighborhood c" ;//address.calcNeighborhood();
        distanceFromUniversity = 5;//address.calcDistanceFromUniversity();
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
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

    public void setUniversity_distance(double university_distance) {
        this.distanceFromUniversity = university_distance;
    }


    public String toString() {
        return "Location: "+address.getStreet()+" "+address.getNumber()+", "+neighborhood;
    }


    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
