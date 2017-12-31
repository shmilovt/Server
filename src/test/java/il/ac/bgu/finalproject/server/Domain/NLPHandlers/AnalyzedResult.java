package il.ac.bgu.finalproject.server.Domain.NLPHandlers;

import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentDetails.ApartmentDetails;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentDetails.Contact;

import java.util.ArrayList;
import java.util.List;


public class AnalyzedResult {
    private String street;
    private String neighborhood ;
    private int buildingNumber;
    private List<String> phones ;
    private double cost;
    private double size;

    public AnalyzedResult() {}

    public AnalyzedResult(ApartmentDetails apartmentDetails) {

        street = "";
        neighborhood = "";
        buildingNumber =  -1;
        phones  = new ArrayList<>();
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
                phones = new ArrayList<>();
                for (Contact contact : apartmentDetails.getContacts()) {
                    phones.add(contact.getPhone());
                }
            }
            cost = apartmentDetails.getCost();
            size = apartmentDetails.getSize();
        }
    }

    public AnalyzedResult(String street, String neighborhood, int buildingNumber, List<String> phones, double cost, int size) {
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


    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public int getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(int buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public List<String> getPhones() {
        return phones;
    }

    public void setPhones(List<String> phones) {
        this.phones = phones;
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

    public void setSize(int size) {
        this.size = size;
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
                "street "+ street +
                ", number " + buildingNumber +
                ", neighbor " + neighborhood ;


    }
}
