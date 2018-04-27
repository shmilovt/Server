package il.ac.bgu.finalproject.server.ServiceLayer;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils.CategoryQuery;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils.SearchResults;

import java.util.List;

public interface IService {

     SearchResults searchApartments(List<CategoryQuery> categoryQueryList);



}
