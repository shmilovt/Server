package il.ac.bgu.finalproject.server.ServiceLayer;
import il.ac.bgu.finalproject.server.CommunicationLayer.DTOs.SearchResultsDTO;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Apartment;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils.CategoryQuery;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSuggestionUtils.UserSuggestion;

import java.util.List;

public interface IService {

   SearchResultsDTO searchApartments(List<CategoryQuery> categoryQueryList);
    boolean userSuggestion (Integer apartmentID, UserSuggestion UserSuggestion);


}
