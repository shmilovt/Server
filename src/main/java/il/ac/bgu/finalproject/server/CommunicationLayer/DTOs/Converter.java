package il.ac.bgu.finalproject.server.CommunicationLayer.DTOs;


import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Apartment;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class Converter {

    @Autowired
    private ModelMapper modelMapper;



    public List<CategoryQuery> convertFromDTO(UserSearchDTO userSearchDTO) {
        List<CategoryQuery> categoryQueries = new ArrayList<>();
        CategoryTypeDTO[] categoryTypeDTOS = userSearchDTO.getPriorities();
        for(CategoryTypeDTO categoryTypeDTO : categoryTypeDTOS){
           switch (categoryTypeDTO){
               case numRooms:
                   NumberOfRoomsDTO numberOfRoomsDTOEnum = userSearchDTO.getNumberOfRoomsDTO();
                   int numberOfRooms = numberOfRoomsDTOEnum.getValue();
                   NumOfRoomsQuery numOfRoomsQuery = new NumOfRoomsQuery(numberOfRooms);
                   categoryQueries.add(numOfRoomsQuery);
                   break;
               case numRoomates:
                   NumberOfRoomatesDTO numberOfRoomatesDTOEnum = userSearchDTO.getNumberOfMates();
                   int numberOfRoomaes = numberOfRoomatesDTOEnum.getValue();
                   NumOfRoomatesQuery numOfRoomatesQuery = new NumOfRoomatesQuery(numberOfRoomaes);
                   categoryQueries.add(numOfRoomatesQuery);
                   break;
               case cost:
                   CostDTO costDTO = userSearchDTO.getCostDTO();
                   CostQuery costQuery = new CostQuery(costDTO.getMinCost(), costDTO.getMaxCost());
                   categoryQueries.add(costQuery);
                   break;
               case floor:
                   FloorDTO floorDTO = userSearchDTO.getFloorDTO();
                   FloorQuery floorQuery= new FloorQuery(floorDTO.getMinFloor(), floorDTO.getMaxFloor());
                   categoryQueries.add(floorQuery);
                   break;
               case distanceFromUniversity:
                   DistanceFromUniversityDTO distanceFromUniversityDTOEnum = userSearchDTO.getDistanceFromUniversityDTO();
                   int distanceFromUniversity = distanceFromUniversityDTOEnum.getValue();
                   TimeFromUniQuery timeFromUniQuery = new TimeFromUniQuery(distanceFromUniversity);
                   categoryQueries.add(timeFromUniQuery);
                   break;
               case neighborhood:
                   String neighborhood =   userSearchDTO.getNeighborhood();
                   NeighborhoodQuery neighborhoodQuery = new  NeighborhoodQuery(neighborhood);
                   categoryQueries.add( neighborhoodQuery);
                   break;
               case  apartmentSize:
                   ApartmentSizeDTO apartmentSizeDTO = userSearchDTO.getApartmentSizeDTO();
                   SizeQuery sizeQuery = new SizeQuery(apartmentSizeDTO.getMinSize(), apartmentSizeDTO.getMaxSize());
                   categoryQueries.add(sizeQuery);
                   break;
               case furniture:
                   FurnitureDTO furnitureDTOEnum = userSearchDTO.getFurnitureDTO();
                   int furniture = furnitureDTOEnum.getValue();
                   FurnitureQuery furnitureQuery = new FurnitureQuery(furniture);
                   categoryQueries.add(furnitureQuery);
                   break;
               case balcony:
                   MustHaveQuery mustHaveQuery = new MustHaveQuery(MustHaveQuery.MustHaveThing.balcony);
                   categoryQueries.add(mustHaveQuery);
                   break;
               case animals:
                   MustHaveQuery mustHaveQuery1 = new MustHaveQuery(MustHaveQuery.MustHaveThing.pets);
                   categoryQueries.add(mustHaveQuery1);
                   break;
               case protectedSpace:
                   MustHaveQuery mustHaveQuery2 = new MustHaveQuery(MustHaveQuery.MustHaveThing.protectedSpace);
                   categoryQueries.add(mustHaveQuery2);
                   break;
               case yard:
                   MustHaveQuery mustHaveQuery3 = new MustHaveQuery(MustHaveQuery.MustHaveThing.garden);
                   categoryQueries.add(mustHaveQuery3);
                   break;
               case warehouse:
                   MustHaveQuery mustHaveQuery4 = new MustHaveQuery(MustHaveQuery.MustHaveThing.warehouse);
                   categoryQueries.add(mustHaveQuery4);
                   break;
               default:/*  throw exception  */
                   break;
           }
        }
        return categoryQueries;
    }


    public SearchResultsDTO convertToDTO(SearchResults searchResulta) {
        return null;
    }
}
