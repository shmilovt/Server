package il.ac.bgu.finalproject.server.CommunicationLayer.DTOs;

import java.util.List;
import java.util.Set;

public class ApartmentDetailsDTO {
    private ApartmentLocationDTO apartmentLocation;
    private double cost;
    private Set<ContactDTO> contacts;

    public ApartmentDetailsDTO(){}
    public ApartmentDetailsDTO(ApartmentLocationDTO apartmentLocation, double cost, Set<ContactDTO> contacts) {
        this.apartmentLocation = apartmentLocation;
        this.cost = cost;
        this.contacts = contacts;
    }

    public ApartmentLocationDTO getApartmentLocation() {
        return apartmentLocation;
    }

    public void setApartmentLocation(ApartmentLocationDTO apartmentLocation) {
        this.apartmentLocation = apartmentLocation;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Set<ContactDTO> getContacts() {
        return contacts;
    }

    public void setContacts(Set<ContactDTO> contacts) {
        this.contacts = contacts;
    }
}
