package il.ac.bgu.finalproject.server.CommunicationLayer.DTOs;

import java.util.List;

public class ApartmentDescriptionListDTO {
    private StatusDTO statusDTO;
    private List<ApartmentDetailsDTO> apartmentDetailsDTOList;

    public ApartmentDescriptionListDTO(){}
    public ApartmentDescriptionListDTO(StatusDTO statusDTO, List<ApartmentDetailsDTO> apartmentDetailsDTOList) {
        this.statusDTO = statusDTO;
        this.apartmentDetailsDTOList = apartmentDetailsDTOList;
    }

    public StatusDTO getStatusDTO() {
        return statusDTO;
    }

    public void setStatusDTO(StatusDTO statusDTO) {
        this.statusDTO = statusDTO;
    }

    public List<ApartmentDetailsDTO> getApartmentDetailsDTOList() {
        return apartmentDetailsDTOList;
    }

    public void setApartmentDetailsDTOList(List<ApartmentDetailsDTO> apartmentDetailsDTOList) {
        this.apartmentDetailsDTOList = apartmentDetailsDTOList;
    }
}
