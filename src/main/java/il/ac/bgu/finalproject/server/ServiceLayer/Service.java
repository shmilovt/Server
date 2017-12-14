package il.ac.bgu.finalproject.server.ServiceLayer;

import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentDetails.ApartmentDetails;
import il.ac.bgu.finalproject.server.Domain.Controllers.AdminClientController;
import il.ac.bgu.finalproject.server.Domain.Controllers.RegularClientController;
import il.ac.bgu.finalproject.server.Domain.Exceptions.ProjectException;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.UserPreferences;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSuggestion;
import il.ac.bgu.finalproject.server.CommunicationLayer.DTOs.Reply;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class Service implements IService {

    @Autowired
    private AdminClientController adminClientController;
    @Autowired
    private RegularClientController regularClientController;

    public Reply login(String username, String password) {
        boolean success;
        try {
             success = (adminClientController.login(username, password));
        }
        catch (ProjectException e) {
            success = false;
            return new Reply(success, e.getMessage());
        }
        if (success) {
            String message = "Hello " + username + "!";
            return new Reply(success, message);
        } else
            return new Reply(false, "");

    }

    @Override
    public boolean logout(String username, String password) {
        return false;
    }

    @Override
    public boolean changePassword(String username, String OldPassword, String NewPassword) {
        return false;
    }

    @Override
    public boolean deleteAdminAcount(String username, String password) {
        return false;
    }

    @Override
    public boolean createAdminAcount(String username, String password) {
        return false;
    }

    @Override
    public boolean addDomainGroup(Integer groupID, String username, String password) {
        return false;
    }

    @Override
    public boolean removeDomainGroup(Integer groupID, String username, String password) {
        return false;
    }

    @Override
    public List<String> searchDomainGroup(String groupName, String username, String password) {
        return null;
    }

    @Override
    public boolean apartmentRecommendation(Integer apartmentID, String username, String password) {
        return false;
    }

    @Override
    public List<ApartmentDetails> searchApartments(UserPreferences userPreferences) {

        return regularClientController.searchApartments(userPreferences);
    }

    @Override
    public boolean userSuggestion(Integer apartmentID, UserSuggestion UserSuggestion) {
        return false;
    }
}




