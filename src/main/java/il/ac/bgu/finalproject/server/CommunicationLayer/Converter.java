package il.ac.bgu.finalproject.server.CommunicationLayer;

import il.ac.bgu.finalproject.server.CommunicationLayer.DTOs.*;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentDetails.ApartmentDetails;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentDetails.Contact;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentDetails.Locations.Address;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentDetails.Locations.ApartmentLocation;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.Categories.FilledCategory;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.Categories.FilledCategoryFactory;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.UserPreferences;
import il.ac.bgu.finalproject.server.Domain.Exceptions.NoCategoryTypeException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class Converter {

    @Autowired
    private ModelMapper modelMapper;

    public ApartmentDetailsDTO convertToDTO(ApartmentDetails apartmentDetails) {
        ApartmentDetailsDTO apartmentDetailsDTO = modelMapper.map(apartmentDetails, ApartmentDetailsDTO.class);
        AddressDTO addressDTO  = convertToDTO(apartmentDetails.getApartmentLocation().getAddress());
        ApartmentLocationDTO apartmentLocationDTO = convertToDTO(apartmentDetails.getApartmentLocation());
        double cost = apartmentDetails.getCost();

        List<ContactDTO> contactDTOList = new ArrayList<>();
        List<Contact> contactList = apartmentDetails.getContacts();
        for(Contact contact : contactList ){
            contactDTOList.add(new ContactDTO(contact.getName(),contact.getPhone()));
        }
        apartmentDetailsDTO.setApartmentLocation(apartmentLocationDTO);
        apartmentDetailsDTO.setCost(cost);
        apartmentDetailsDTO.setContacts(contactDTOList);

        return apartmentDetailsDTO;
    }


    public AddressDTO convertToDTO(Address address){
        AddressDTO addressDTO = modelMapper.map(address, AddressDTO.class);
        String street = address.getStreet();
        int buildingNumber = address.getNumber();
        addressDTO.setStreet(street);
        addressDTO.setNumber(buildingNumber);
        return addressDTO;
    }


    public ApartmentLocationDTO convertToDTO(ApartmentLocation apartmentLocation){
        ApartmentLocationDTO apartmentLocationDTO = modelMapper.map(apartmentLocation, ApartmentLocationDTO.class);
        AddressDTO addressDTO = convertToDTO(apartmentLocation.getAddress());
        int floor = apartmentLocation.getFloor();
        double distanceFromUniversity = apartmentLocation.getDistanceFromUniversity();
        int apartmentNumber = apartmentLocation.getApartmentNumber();
        String neighborhood = apartmentLocation.getNeighborhood();
        apartmentLocationDTO.setAddress(addressDTO);
        apartmentLocationDTO.setApartmentNumber(apartmentNumber);
        apartmentLocationDTO.setDistanceFromUniversity(distanceFromUniversity);
        apartmentLocationDTO.setFloor(floor);
        apartmentLocationDTO.setNeighborhood(neighborhood);
        return apartmentLocationDTO;
    }


     public UserPreferences convertToEntity(UserPreferencesDTO userPreferencesDTO) throws NoCategoryTypeException {
        UserPreferences userPreferences = modelMapper.map(userPreferencesDTO, UserPreferences.class);
        int arraySize = userPreferencesDTO.getPreferences().length;
        FilledCategory[] filledCategories  = new FilledCategory[arraySize];
        int i = 0;
        try {
            for (FilledCategoryDTO filledCategoryDTO : userPreferencesDTO.getPreferences()) {
                filledCategories[i] = new FilledCategoryFactory().createFilledCategory(filledCategoryDTO.getCategoryType(), filledCategoryDTO.getValue());
                i++;
            }
        }
        catch (NoCategoryTypeException e){
            throw e;
        }
        userPreferences.setPreferences(filledCategories);
        return userPreferences;
    }
}
