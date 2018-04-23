package il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Apartment {
    private List<String> postIDs;
    private ApartmentLocation apartmentLocation;
    private int cost;
    private int size;
    private Set<Contact> contacts;
    private int garden;
    private int gardenSize;
    private int protectedSpace;
    private int warehouse;
    private int animal;
    private int balcony;
    private int furniture;
    private int numberOfMates;
    private int numberOfRooms;

    public Apartment() {
        postIDs = new ArrayList<>();
        apartmentLocation = new ApartmentLocation();
        cost = -1;
        size = -1;
        garden = -1;
        gardenSize = -1;
        protectedSpace = -1;
        warehouse=-1;
        animal=-1;
        balcony=-1;
        furniture=-1;
        numberOfMates=-1;
        numberOfRooms=-1;
        contacts = new HashSet<>();
    }

    public Apartment(ApartmentLocation apartmentLocation) {
        this.apartmentLocation = apartmentLocation;
        this.cost = -1;
        this.size = -1;
        garden = -1;
        gardenSize = -1;
        protectedSpace = -1;
        warehouse=-1;
        animal=-1;
        balcony=-1;
        furniture=-1;
        numberOfMates=-1;
        numberOfRooms=-1;
        contacts = new HashSet<>();
    }

    public Apartment(ApartmentLocation apartmentLocation, Set<Contact> contacts) {
        this.apartmentLocation = apartmentLocation;
        this.contacts = contacts;
        this.cost = -1;
        this.size = -1;
        garden = -1;
        gardenSize = -1;
        protectedSpace = -1;
        warehouse=-1;
        animal=-1;
        balcony=-1;
        furniture=-1;
        numberOfMates=-1;
        numberOfRooms=-1;
    }

    public Apartment(ApartmentLocation apartmentLocation, int cost, Set<Contact> contacts) {
        this.apartmentLocation = apartmentLocation;
        this.cost = cost;
        this.contacts = contacts;
        this.size = -1;
        garden = -1;
        gardenSize = -1;
        protectedSpace = -1;
        warehouse=-1;
        animal=-1;
        balcony=-1;
        furniture=-1;
        numberOfMates=-1;
        numberOfRooms=-1;
    }

    public Apartment(ApartmentLocation apartmentLocation, int cost, int size, Set<Contact> contacts) {
        this.apartmentLocation = apartmentLocation;
        this.cost = cost;
        this.contacts = contacts;
        this.size = size;
        garden = -1;
        gardenSize = -1;
        protectedSpace = -1;
        warehouse=-1;
        animal=-1;
        balcony=-1;
        furniture=-1;
        numberOfMates=-1;
        numberOfRooms=-1;
    }

    public ApartmentLocation getApartmentLocation() {
        return apartmentLocation;
    }

    public void setApartmentLocation(ApartmentLocation apartmentLocation) {
        this.apartmentLocation = apartmentLocation;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

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

    public int getAnimal() {
        return animal;
    }

    public void setAnimal(int animal) {
        this.animal = animal;
    }

    public int getNumberOfMates() {
        return numberOfMates;
    }

    public void setNumberOfMates(int numberOfMates) {
        this.numberOfMates = numberOfMates;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public int getFurniture() {
        return furniture;
    }

    public void setFurniture(int furniture) {
        this.furniture = furniture;
    }

    public int getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(int warehouse) {
        this.warehouse = warehouse;
    }

    public int getGardenSize() {
        return gardenSize;
    }

    public void setGardenSize(int gardenSize) {
        this.gardenSize = gardenSize;
    }

    public int getProtectedSpace() {
        return protectedSpace;
    }

    public void setProtectedSpace(int protectedSpace) {
        this.protectedSpace = protectedSpace;
    }

    public Set<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<String> getPostIDs() {
        return postIDs;
    }

    public void setPostIDs(List<String> postID) {
        this.postIDs = postID;
    }

    public String toString()
    {
        String title = "Apartment details: " + "\n";
        String contactDetails="";
        for(Contact c:contacts)
        {
            contactDetails = contactDetails + "name: " + c.getName() + "\n" + "phone: " + c.getPhone() + "\n";
        }
        String cost = "cost: " + this.cost + "\n";
        String warehouse = "warehouse: " + this.warehouse + "\n";
        String size = "size: " + this.size + "\n";
        String animal = "animal: " + this.animal + "\n";
        String balcony = "balcony: " + this.balcony + "\n";
        String protectedSpace = "protectedSpace: " + this.protectedSpace + "\n";
        String garden = "garden: " + this.getGarden() + "\n";
        String gardenSize = "garden size: " + this.gardenSize + "\n";
        String location= "Neighborhood: " + apartmentLocation.getNeighborhood() + "\n"
                + "street: " + apartmentLocation.getAddress().getStreet() +  "\n"
                +"building number: " + apartmentLocation.getAddress().getNumber() + "\n";
        String furniture= "Furniture: " +this.furniture + "\n";
        String mates= "number of mates: " +this.numberOfMates + "\n";
        String rooms= "number of rooms: " +this.numberOfRooms + "\n";

        return title + contactDetails + cost + garden + gardenSize + warehouse + protectedSpace + size + location + furniture + mates + rooms;
    }
}