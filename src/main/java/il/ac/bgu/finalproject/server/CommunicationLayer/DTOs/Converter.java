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

    public ApartmentDetailsDTO convertToDTO(Apartment apartment) {
        ApartmentDetailsDTO apartmentDetailsDTO = new ApartmentDetailsDTO(apartment);
        return apartmentDetailsDTO;
    }


    public List<CategoryQuery> convertFromDTO(UserSearchDTO userSearchDTO) {
        List<CategoryQuery> categoryQueries = new ArrayList<>();
        CategoryType [] categoryTypes = userSearchDTO.getPriorities();
        for(CategoryType categoryType: categoryTypes){
           switch (categoryType){
               case numRooms:
                   NumberOfRooms numberOfRoomsEnum = userSearchDTO.getNumberOfRooms();
                   int numberOfRooms = numberOfRoomsEnum.getValue();
                   NumOfRoomsQuery numOfRoomsQuery = new NumOfRoomsQuery(numberOfRooms);
                   categoryQueries.add(numOfRoomsQuery);
                   break;
               case numRoomates:
                   NumberOfRoomates numberOfRoomatesEnum = userSearchDTO.getNumberOfMates();
                   int numberOfRoomaes = numberOfRoomatesEnum.getValue();
                   NumOfRoomatesQuery numOfRoomatesQuery = new NumOfRoomatesQuery(numberOfRoomaes);
                   categoryQueries.add(numOfRoomatesQuery);
                   break;
               case cost:
                   Cost cost = userSearchDTO.getCost();
                   CostQuery costQuery = new CostQuery(cost.getMinCost(), cost.getMaxCost());
                   categoryQueries.add(costQuery);
                   break;
               case floor:
                   Floor floor = userSearchDTO.getFloor();
                   FloorQuery floorQuery= new FloorQuery(floor.getMinFloor(), floor.getMaxFloor());
                   categoryQueries.add(floorQuery);
                   break;
               case distanceFromUniversity:
                   DistanceFromUniversity distanceFromUniversityEnum = userSearchDTO.getDistanceFromUniversity();
                   int distanceFromUniversity = distanceFromUniversityEnum.getValue();
                   TimeFromUniQuery timeFromUniQuery = new TimeFromUniQuery(distanceFromUniversity);
                   categoryQueries.add(timeFromUniQuery);
                   break;
               case neighborhood:
                   String neighborhood =   userSearchDTO.getNeighborhood();
                   NeighborhoodQuery neighborhoodQuery = new  NeighborhoodQuery(neighborhood);
                   categoryQueries.add( neighborhoodQuery);
                   break;
               case  apartmentSize:
                   ApartmentSize apartmentSize = userSearchDTO.getApartmentSize();
                   SizeQuery sizeQuery = new SizeQuery(apartmentSize.getMinSize(), apartmentSize.getMaxSize());
                   categoryQueries.add(sizeQuery);
                   break;
               case furniture:
                   Furniture furnitureEnum = userSearchDTO.getFurniture();
                   int furniture = furnitureEnum.getValue();
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


}
