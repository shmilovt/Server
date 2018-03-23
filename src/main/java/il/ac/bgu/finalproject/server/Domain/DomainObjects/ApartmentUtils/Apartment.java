package il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Apartment {
    private List<Integer> postIDs;
    private ApartmentLocation apartmentLocation;
    private int cost;
    private double size;
    private int numOfRooms;
    private Set<Contact> contacts;


    public Apartment() {
        postIDs = new ArrayList<>();
        apartmentLocation = new ApartmentLocation();
        cost = -1;
        size = -1;
        contacts = new HashSet<>();
    }

    public Apartment(ApartmentLocation apartmentLocation) {
        this.apartmentLocation = apartmentLocation;
        this.cost = -1;
        this.size = -1;
        contacts = new HashSet<>();
    }

    public Apartment(ApartmentLocation apartmentLocation, Set<Contact> contacts) {
        this.apartmentLocation = apartmentLocation;
        this.contacts = contacts;
        this.cost = -1;
        this.size = -1;

    }

    public Apartment(ApartmentLocation apartmentLocation, int cost, Set<Contact> contacts) {
        this.apartmentLocation = apartmentLocation;
        this.cost = cost;
        this.contacts = contacts;
        this.size = -1;
    }

    public Apartment(ApartmentLocation apartmentLocation, int cost, double size, Set<Contact> contacts) {
        this.apartmentLocation = apartmentLocation;
        this.cost = cost;
        this.contacts = contacts;
        this.size = size;
    }
    public Apartment(ApartmentLocation apartmentLocation, int cost, double size, Set<Contact> contacts, int numOfRooms) {
        this.apartmentLocation = apartmentLocation;
        this.cost = cost;
        this.contacts = contacts;
        this.size = size;
        this.numOfRooms=numOfRooms;
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

    public int getNumOfRooms() { return numOfRooms; }

    public void setNumOfRooms(int cost) {
        this.numOfRooms= numOfRooms;
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

    public List<Integer> getPostIDs() {
        return postIDs;
    }

    public void setPostIDs(List<Integer> postID) {
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
        String location= "Neighborhood: " + apartmentLocation.getNeighborhood() + "\n" + "street: " + apartmentLocation.getAddress().getStreet() +  "\n" +"building number: " + apartmentLocation.getAddress().getNumber();
        return title+contactDetails+cost+size+location;
    }
}