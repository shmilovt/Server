package il.ac.bgu.finalproject.server.CommunicationLayer.DTOs;


import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Apartment;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils.CategoryType;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils.SearchCategory;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils.SearchCategoryFactory;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils.UserPreferences;
import il.ac.bgu.finalproject.server.Domain.Exceptions.NoCategoryTypeException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class Converter {

    @Autowired
    private ModelMapper modelMapper;

    public ApartmentDetailsDTO convertToDTO(Apartment apartment) {
        ApartmentDetailsDTO apartmentDetailsDTO = new ApartmentDetailsDTO(apartment);
        return apartmentDetailsDTO;
    }


     public UserPreferences convertToEntity(UserPreferencesDTO userPreferencesDTO) throws NoCategoryTypeException {
        UserPreferences userPreferences = modelMapper.map(userPreferencesDTO, UserPreferences.class);
        int arraySize = userPreferencesDTO.getPreferences().length;
        SearchCategory[] filledCategories  = new SearchCategory[arraySize];
        int i = 0;
        try {
            for (SearchCategoryDTO searchCategoryDTO : userPreferencesDTO.getPreferences()) {
                filledCategories[i] = new SearchCategoryFactory().createFilledCategory(CategoryType.values()[searchCategoryDTO.getCategoryType()], searchCategoryDTO.getValue());
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
