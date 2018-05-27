package il.ac.bgu.finalproject.server.ServiceLayer;
import il.ac.bgu.finalproject.server.CommunicationLayer.AdminDTOs.ArraySearchRecordDTO;
import il.ac.bgu.finalproject.server.CommunicationLayer.DTOs.GroupDTO;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils.CategoryQuery;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils.SearchResults;
import il.ac.bgu.finalproject.server.Domain.Exceptions.DataBaseFailedException;

import java.util.List;

public interface IService {

     SearchResults searchApartments(List<CategoryQuery> categoryQueryList);
     void addSearchRecord(String neighborhood, String timeFromUni, String cost, String floor, String size, String furnitures,String numOfRoomes, String numOfMates) ;
     boolean login(String username, String password);
     boolean changePassword(String username, String password);
     boolean newPostFromAdmin(String nameOfPublisher, String messege);

     void insertGroup(String groupID) throws DataBaseFailedException;
     void deleteGroup(String groupID) throws DataBaseFailedException;
     List<GroupDTO> GetAllGroups();
     ArraySearchRecordDTO getAllUserSearches();
}
