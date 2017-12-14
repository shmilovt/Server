package il.ac.bgu.finalproject.server.CommunicationLayer.DTOs;

import java.util.List;

public class ApartmentDetailsDTO {
    private ApartmentLocationDTO apartmentLocation;
    private double cost;
    private List<ContactDTO> contacts;

    public ApartmentDetailsDTO(){}
    public ApartmentDetailsDTO(ApartmentLocationDTO apartmentLocation, double cost, List<ContactDTO> contacts) {
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

    public List<ContactDTO> getContacts() {
        return contacts;
    }

    public void setContacts(List<ContactDTO> contacts) {
        this.contacts = contacts;
    }
}
