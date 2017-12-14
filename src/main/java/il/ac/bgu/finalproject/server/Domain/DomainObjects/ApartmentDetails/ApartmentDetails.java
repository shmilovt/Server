package il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentDetails;

import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentDetails.Locations.ApartmentLocation;

import java.util.ArrayList;
import java.util.List;

public class ApartmentDetails {
    private ApartmentLocation apartmentLocation;
    private double cost;
    private List<Contact> contacts;


    public ApartmentDetails(){}
    public ApartmentDetails(ApartmentLocation apartmentLocation) {
        this.apartmentLocation = apartmentLocation;
        this.cost = -1;
        contacts = new ArrayList<>();
    }

    public ApartmentDetails(ApartmentLocation apartmentLocation, List<Contact> contacts) {
        this.apartmentLocation = apartmentLocation;
        this.contacts = contacts;
        this.cost = -1;

    }

    public ApartmentDetails(ApartmentLocation apartmentLocation, double cost, List<Contact> contacts) {
        this.apartmentLocation = apartmentLocation;
        this.cost = cost;
        this.contacts = contacts;
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

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
}
