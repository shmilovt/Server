package il.ac.bgu.finalproject.server.ServiceLayer;

import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Apartment;
import il.ac.bgu.finalproject.server.Domain.Controllers.AdminClientController;
import il.ac.bgu.finalproject.server.Domain.Controllers.RegularClientController;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSuggestionUtils.UserSuggestion;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class Service implements IService {

    @Autowired
    private AdminClientController adminClientController;
    @Autowired
    private RegularClientController regularClientController;

  /* @Override
    public List<Apartment> searchApartments(UserSearch userPreferences) {

        return regularClientController.searchApartments(userPreferences);
    }
*/

}




