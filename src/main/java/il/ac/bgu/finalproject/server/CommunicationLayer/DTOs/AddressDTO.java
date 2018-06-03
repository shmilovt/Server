package il.ac.bgu.finalproject.server.CommunicationLayer.DTOs;

import com.google.gson.Gson;

public class AddressDTO {
    private String street;
    private int numOfBuilding;
    private String neighborhood;

    public AddressDTO(){}


    public static String toJSON(AddressDTO addressDTO) {
        Gson gson = new Gson();
        return gson.toJson(addressDTO);
    }

    public static AddressDTO fromJSON(String addressDTOstring){
        Gson gson = new Gson();
        AddressDTO addressDTO= gson.fromJson(addressDTOstring, AddressDTO.class);
        return addressDTO;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumOfBuilding() {
        return numOfBuilding;
    }

    public void setNumOfBuilding(int numOfBuilding) {
        this.numOfBuilding = numOfBuilding;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }
}
