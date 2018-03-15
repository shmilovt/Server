package il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Apartment {
    private List<String> postIDs;
    private ApartmentLocation apartmentLocation;
    private double cost;
    private double size;
    private Set<Contact> contacts;
    private int garden;
    private int gardenSize;
    private int protectedSpace;


    public Apartment() {
        postIDs = new ArrayList<>();
        apartmentLocation = new ApartmentLocation();
        cost = -1;
        size = -1;
        garden = -1;
        gardenSize = -1;
        protectedSpace = -1;
        contacts = new HashSet<>();
    }

    public Apartment(ApartmentLocation apartmentLocation) {
        this.apartmentLocation = apartmentLocation;
        this.cost = -1;
        this.size = -1;
        garden = -1;
        gardenSize = -1;
        protectedSpace = -1;
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
    }

    public Apartment(ApartmentLocation apartmentLocation, double cost, Set<Contact> contacts) {
        this.apartmentLocation = apartmentLocation;
        this.cost = cost;
        this.contacts = contacts;
        this.size = -1;
        garden = -1;
        gardenSize = -1;
        protectedSpace = -1;
    }

    public Apartment(ApartmentLocation apartmentLocation, double cost, double size, Set<Contact> contacts) {
        this.apartmentLocation = apartmentLocation;
        this.cost = cost;
        this.contacts = contacts;
        this.size = size;
        garden = -1;
        gardenSize = -1;
        protectedSpace = -1;
    }

    public ApartmentLocation getApartmentLocation() {
        return apartmentLocation;
    }

    public void setApartmentLocation(ApartmentLocation apartmentLocation) {
        this.apartmentLocation = apartmentLocation;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getGarden() {
        return garden;
    }

    public void setGarden(int garden) {
        this.garden = garden;
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

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
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
        String size = "size: " + this.size + "\n";
        String protectedSpace = "protectedSpace: " + this.protectedSpace + "\n";
        String garden = "garden: " + this.getGarden() + "\n";
        String gardenSize = "garden size: " + this.gardenSize + "\n";
        String location= "Neighborhood: " + apartmentLocation.getNeighborhood() + "\n" + "street: " + apartmentLocation.getAddress().getStreet() +  "\n" +"building number: " + apartmentLocation.getAddress().getNumber();
        return title + contactDetails + cost + garden + gardenSize + protectedSpace + size + location;
    }
}