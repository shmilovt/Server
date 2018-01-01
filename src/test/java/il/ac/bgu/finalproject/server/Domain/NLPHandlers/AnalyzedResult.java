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
    private double cost = -1;
    private int timeFromUni = -1;
    private int garden= 0;
    private int balcony= 0;
    private int protectedSpace= 0;
    private int wareHouse= 0;
    private int pets= 0;
    private int seperatedShowerToilet = 0;
    private int roomType = 0;

    public int getGarden() {
        return garden;
    }

    public void setGarden(int garden) {
        this.garden = garden;
    }

    public int getBalcony() {
        return balcony;
    }

    public void setBalcony(int balcony) {
        this.balcony = balcony;
    }

    public int getProtectedSpace() {
        return protectedSpace;
    }

    public void setProtectedSpace(int protectedSpace) {
        this.protectedSpace = protectedSpace;
    }

    public int getWareHouse() {
        return wareHouse;
    }

    public void setWareHouse(int wareHouse) {
        this.wareHouse = wareHouse;
    }

    public int getPets() {
        return pets;
    }

    public void setPets(int pets) {
        this.pets = pets;
    }

    public int getSeperatedShowerToilet() {
        return seperatedShowerToilet;
    }

    public void setSeperatedShowerToilet(int seperatedShowerToilet) {
        this.seperatedShowerToilet = seperatedShowerToilet;
    }

    public int getRoomType() {
        return roomType;
    }

    public void setRoomType(int roomType) {
        this.roomType = roomType;
    }

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
