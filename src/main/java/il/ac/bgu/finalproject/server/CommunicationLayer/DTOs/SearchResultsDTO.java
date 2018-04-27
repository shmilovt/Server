package il.ac.bgu.finalproject.server.CommunicationLayer.DTOs;

import com.google.gson.Gson;

public class SearchResultsDTO {
    private ApartmentDTO [] apartmentDTOS;
    public SearchResultsDTO(){}
    public SearchResultsDTO(ApartmentDTO [] apartmentDTOS ){

        this.apartmentDTOS = apartmentDTOS;

    }


    public ApartmentDTO[] getApartmentDTOS() {
        return apartmentDTOS;
    }

    public void setApartmentDTOS(ApartmentDTO[] apartmentDTOS) {
        this.apartmentDTOS = apartmentDTOS;
    }

    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
