package il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Apartment {
    private List<String> postIDs ;
    private ApartmentLocation apartmentLocation ;
    private double cost;
    private double size;
    private Set<Contact> contacts;


    public Apartment(){
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

    public Apartment(ApartmentLocation apartmentLocation, double cost, Set<Contact> contacts) {
        this.apartmentLocation = apartmentLocation;
        this.cost = cost;
        this.contacts = contacts;
        this.size = -1;
    }

    public Apartment(ApartmentLocation apartmentLocation, double cost, double size, Set<Contact> contacts) {
        this.apartmentLocation = apartmentLocation;
        this.cost = cost;
        this.contacts = contacts;
        this.size = size;
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
}
