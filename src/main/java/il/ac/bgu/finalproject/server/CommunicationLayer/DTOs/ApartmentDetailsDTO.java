package il.ac.bgu.finalproject.server.CommunicationLayer.DTOs;


import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Apartment;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Contact;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ApartmentDetailsDTO {
    private Integer postID;
    private String street;
    private int number;
    private String neighborhood;
    private int floor;
    private int apartmentNumber;
    private double distanceFromUniversity;
    private double cost;
    private double size;
    private Set<ContactDTO> contacts;

    public ApartmentDetailsDTO(){}
    public ApartmentDetailsDTO(Apartment apartment){
       // List<Integer> postIDs = apartment.getPostIDs();
        //postID = postIDs.get(postIDs.size()-1);
        street = apartment.getApartmentLocation().getAddress().getStreet();
        number = apartment.getApartmentLocation().getAddress().getNumber();
        neighborhood = apartment.getApartmentLocation().getNeighborhood();
        floor = apartment.getApartmentLocation().getFloor();
        apartmentNumber = apartment.getApartmentLocation().getApartmentNumber();
        distanceFromUniversity = apartment.getApartmentLocation().getDistanceFromUniversity();
        cost = apartment.getCost();
        size = apartment.getSize();
        contacts = new HashSet<>();
        for(Contact contact : apartment.getContacts()) {
            contacts.add(new ContactDTO(contact.getName(),contact.getPhone()));
        }

    }


    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public Set<ContactDTO> getContacts() {
        return contacts;
    }

    public void setContacts(Set<ContactDTO> contacts) {
        this.contacts = contacts;
    }
}
