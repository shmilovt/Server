package il.ac.bgu.finalproject.server.ServiceLayer;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentDetails.ApartmentDetails;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.UserPreferences;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSuggestion;
import il.ac.bgu.finalproject.server.CommunicationLayer.DTOs.Reply;

import java.util.List;

public interface IService {

    // nofar
    Reply login(String username, String password);
    boolean logout(String username, String password);
    boolean changePassword(String username, String OldPassword, String NewPassword);
    boolean deleteAdminAcount(String username, String password);
    boolean createAdminAcount(String username, String password);
    boolean addDomainGroup(Integer groupID, String username, String password);
    boolean removeDomainGroup(Integer groupID, String username, String password);
    List<String> searchDomainGroup(String groupName, String username, String password);
   // boolean updateCalc(String calcFormula, String username, String password); need to talk with yanai
    boolean apartmentRecommendation (Integer apartmentID, String username, String password);

    // shavit
    List<ApartmentDetails> searchApartments(UserPreferences userPreferences);
    boolean userSuggestion (Integer apartmentID, UserSuggestion UserSuggestion);
    // project interface to facebook in another class

}
