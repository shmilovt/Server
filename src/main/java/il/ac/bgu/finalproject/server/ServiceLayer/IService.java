package il.ac.bgu.finalproject.server.ServiceLayer;
import il.ac.bgu.finalproject.server.CommunicationLayer.AdminDTOs.ArraySearchRecordDTO;
import il.ac.bgu.finalproject.server.CommunicationLayer.AdminDTOs.CalculatorDTO;
import il.ac.bgu.finalproject.server.CommunicationLayer.AdminDTOs.SearchRecordDTO;
import il.ac.bgu.finalproject.server.CommunicationLayer.DTOs.GroupDTO;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils.CategoryQuery;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils.SearchResults;
import il.ac.bgu.finalproject.server.Domain.Exceptions.DataBaseFailedException;

import java.util.List;

public interface IService {

     SearchResults searchApartments(List<CategoryQuery> categoryQueryList);
     void addSearchRecord(String neighborhood, String timeFromUni, String costMin, String costMax, String floorMin, String floorMax, String sizeMin, String sizeMax, String furnitures,String numOfRoomes, String numOfMates, int protectedSpace,  int garden, int balcony, int pets, int warehouse);
     boolean login(String username, String password);
     boolean changePassword(String username, String password);
     boolean newPostFromAdmin(String nameOfPublisher, String messege);

     void insertGroup(String groupID) throws DataBaseFailedException;
     void deleteGroup(String groupID) throws DataBaseFailedException;
     List<GroupDTO> GetAllGroups();
     List<SearchRecordDTO> getAllUserSearches();
     CalculatorDTO getCalcCosts();
     SearchResults getAllApartments();
     void setNewCalculator(int protectedSpaceCost, int timeFromUniCost_10, int timeFromUniCost_20,
                           int timeFromUniCost_G_20, int neighborhoodCost_B_Ramot,
                           int neighborhoodCost_oldV_Wingate, int neighborhoodCost_D,
                           int neighborhoodCost_G, int furnitureCost_full, int furnitureCost_half,
                           int furnitureCost_none, int sizeCost_25, int sizeCost_30, int sizeCost_35,
                           int sizeCost_35_up, int roomatesCost_1, int roomatesCost_2, int roomatesCost_3,
                           int roomatesCost_4, int roomatesCost_5, int roomatesCost_6, int gardenCost,
                           int balconyCost);

     int addUserSuggestion(String id, String field, String suggestion) throws DataBaseFailedException ;
     void suggestionChangesApartmentInt(String id, String field, int suggest);
     void suggestionChangesApartmentDouble(String id, String field, double suggest);
     void suggestionChangesAddress(String id, String field, String street, int numB, String neighborhood);
     }
