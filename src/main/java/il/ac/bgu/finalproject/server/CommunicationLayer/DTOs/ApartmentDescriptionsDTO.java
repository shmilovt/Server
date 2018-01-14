package il.ac.bgu.finalproject.server.CommunicationLayer.DTOs;

import java.util.List;

public class ApartmentDescriptionsDTO {
    private boolean success;
    private String message;
    private List<ApartmentDetailsDTO> apartmentDetailsDTOList;

    public ApartmentDescriptionsDTO(){}

    public ApartmentDescriptionsDTO(boolean success, String message, List<ApartmentDetailsDTO> apartmentDetailsDTOList) {
        this.success = success;
        this.message = message;
        this.apartmentDetailsDTOList = apartmentDetailsDTOList;
    }


    public List<ApartmentDetailsDTO> getApartmentDetailsDTOList() {
        return apartmentDetailsDTOList;
    }

    public void setApartmentDetailsDTOList(List<ApartmentDetailsDTO> apartmentDetailsDTOList) {
        this.apartmentDetailsDTOList = apartmentDetailsDTOList;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
