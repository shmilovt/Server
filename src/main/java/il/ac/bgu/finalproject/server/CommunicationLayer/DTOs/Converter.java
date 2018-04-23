package il.ac.bgu.finalproject.server.CommunicationLayer.DTOs;


import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Apartment;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class Converter {

    @Autowired
    private ModelMapper modelMapper;

    public ApartmentDetailsDTO convertToDTO(Apartment apartment) {
        ApartmentDetailsDTO apartmentDetailsDTO = new ApartmentDetailsDTO(apartment);
        return apartmentDetailsDTO;
    }


}
