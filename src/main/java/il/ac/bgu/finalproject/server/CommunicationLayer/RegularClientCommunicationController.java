package il.ac.bgu.finalproject.server.CommunicationLayer;

import il.ac.bgu.finalproject.server.CommunicationLayer.DTOs.*;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Apartment;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils.UserPreferences;
import il.ac.bgu.finalproject.server.Domain.Exceptions.NoCategoryTypeException;
import il.ac.bgu.finalproject.server.ServiceLayer.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RegularClientCommunicationController {

    @Autowired
    private IService service;

    @Autowired
    private Converter converter;

    @RequestMapping(value = "/allApartments" , method = {RequestMethod.GET})
    public ApartmentDescriptionsDTO allApartments() {
        LinkedList<ApartmentDetailsDTO> apartments = new LinkedList<>();
        apartments.push(new ApartmentDetailsDTO(new Apartment()));

        return new ApartmentDescriptionsDTO(true, "we win", apartments);
    }

    @RequestMapping(value = "/searchApartments" , method = {RequestMethod.POST, RequestMethod.GET})
    public ApartmentDescriptionsDTO searchApartments(@RequestParam UserPreferencesDTO userPreferencesDTO){
        UserPreferences userPreferences;
        List<Apartment> apartmentsDetails = new ArrayList<>();
        List<ApartmentDetailsDTO> apartmentDetailsDTOList = new ArrayList<>();
      try {
      userPreferences = converter.convertToEntity(userPreferencesDTO);
      }
      catch (NoCategoryTypeException e){
          return new ApartmentDescriptionsDTO(false, e.getMessage(), apartmentDetailsDTOList);
          }
        apartmentsDetails  = service.searchApartments(userPreferences);
        apartmentDetailsDTOList = apartmentsDetails.stream().map(apartmentDetails -> converter.convertToDTO(apartmentDetails)).collect(Collectors.toList());
        return new ApartmentDescriptionsDTO(true, "success",apartmentDetailsDTOList);
    }

}
