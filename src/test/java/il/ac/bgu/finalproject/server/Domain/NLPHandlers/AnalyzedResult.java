package il.ac.bgu.finalproject.server.Domain.NLPHandlers;

import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentDetails.ApartmentDetails;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentDetails.Contact;
import java.util.HashSet;
import java.util.Set;



public class AnalyzedResult
{
    private String neighborhood = "" ;
    private String street = "";
    private int buildingNumber = -1 ;
    private int floor = -2;
    private int size= -1;
    private int numberOfRooms= -1;
    private int numberOfMates= -1;
    private Set<String> furniture = new HashSet<>() ;
    private Set<String> phones = new HashSet<>() ;
    private Set<String> additions = new HashSet<>() ;
    private double cost = -1;
    private int timeFromUni = -1;

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(int buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public int getNumberOfMates() {
        return numberOfMates;
    }

    public void setNumberOfMates(int numberOfMates) {
        this.numberOfMates = numberOfMates;
    }

    public Set<String> getFurniture() {
        return furniture;
    }

    public void setFurniture(Set<String> furniture) {
        this.furniture = furniture;
    }

    public Set<String> getPhones() {
        return phones;
    }

    public void setPhones(Set<String> phones) {
        this.phones = phones;
    }

    public Set<String> getAdditions() {
        return additions;
    }

    public void setAdditions(Set<String> additions) {
        this.additions = additions;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getTimeFromUni() {
        return timeFromUni;
    }

    public void setTimeFromUni(int timeFromUni) {
        this.timeFromUni = timeFromUni;
    }

    public AnalyzedResult() {}

    public AnalyzedResult(ApartmentDetails apartmentDetails) {

        street = "";
        neighborhood = "";
        buildingNumber =  -1;
        phones  = new HashSet<>();
        cost = -1;
        size = -1;

        if(apartmentDetails != null) {
            if (apartmentDetails.getApartmentLocation() != null) {
                if(apartmentDetails.getApartmentLocation().getAddress()!= null) {
                    street = apartmentDetails.getApartmentLocation().getAddress().getStreet();
                    buildingNumber = apartmentDetails.getApartmentLocation().getAddress().getNumber();
                }
                if(apartmentDetails.getApartmentLocation().getNeighborhood()!= null)
                    neighborhood = apartmentDetails.getApartmentLocation().getNeighborhood();
            }
            if(apartmentDetails.getContacts()!=null) {
                phones = new HashSet<>();
                for (Contact contact : apartmentDetails.getContacts()) {
                    phones.add(contact.getPhone());
                }
            }
            cost = apartmentDetails.getCost();
            size = apartmentDetails.getSize();
        }
    }

    public AnalyzedResult(String street, String neighborhood, int buildingNumber, Set<String> phones, double cost, int size) {
        this.street = street;
        this.neighborhood = neighborhood;
        this.buildingNumber = buildingNumber;
        this.phones = phones;
        this.cost = cost;
        this.size = size;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnalyzedResult that = (AnalyzedResult) o;

        if (buildingNumber != that.buildingNumber) return false;
        if (Double.compare(that.cost, cost) != 0) return false;
        if (size != that.size) return false;
        if (street != null ? !street.equals(that.street) : that.street != null) return false;
        if (neighborhood != null ? !neighborhood.equals(that.neighborhood) : that.neighborhood != null) return false;
        return phones != null ? phones.equals(that.phones) : that.phones == null;
    }


    @Override
    public String toString() {
        return "AnalyzedResult{" +
                "street='" + street + '\'' +
                ", neighborhood='" + neighborhood + '\'' +
                ", buildingNumber=" + buildingNumber +
                ", phones=" + phones +
                ", cost=" + cost +
                ", size=" + size +
                '}';
    }

    public String getAddress() {
        return
                "street: "+ street +
                ", building number: " + buildingNumber +
                ", neighborhood: " + neighborhood ;


    }


}
