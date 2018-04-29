package il.ac.bgu.finalproject.server.CommunicationLayer.DTOs;


import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Contact;
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
        for (CategoryTypeDTO categoryTypeDTO : categoryTypeDTOS) {
            switch (categoryTypeDTO) {
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
                    FloorQuery floorQuery = new FloorQuery(floorDTO.getMinFloor(), floorDTO.getMaxFloor());
                    categoryQueries.add(floorQuery);
                    break;
                case distanceFromUniversity:
                    DistanceFromUniversityDTO distanceFromUniversityDTOEnum = userSearchDTO.getDistanceFromUniversityDTO();
                    int distanceFromUniversity = distanceFromUniversityDTOEnum.getValue();
                    TimeFromUniQuery timeFromUniQuery = new TimeFromUniQuery(distanceFromUniversity);
                    categoryQueries.add(timeFromUniQuery);
                    break;
                case neighborhood:
                    String neighborhood = userSearchDTO.getNeighborhood();
                    NeighborhoodQuery neighborhoodQuery = new NeighborhoodQuery(neighborhood);
                    categoryQueries.add(neighborhoodQuery);
                    break;
                case apartmentSize:
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


    public SearchResultsDTO convertToDTO(SearchResults searchResult) {
        List<ResultRecord> resultRecordList = searchResult.getResultRecordList();
        ResultRecordDTO[] resultRecordDTOS = new ResultRecordDTO[resultRecordList.size()];
        int i = 0;
        ResultRecordDTO tempResultRecordDTO;
        for (ResultRecord resultRecord : resultRecordList) {
            tempResultRecordDTO = new ResultRecordDTO();
            tempResultRecordDTO.setStreet(resultRecord.getStreet());
            tempResultRecordDTO.setNumber(resultRecord.getNumber());
            tempResultRecordDTO.setNeighborhood(resultRecord.getNeighborhood());
            tempResultRecordDTO.setFloor(resultRecord.getFloor());
            tempResultRecordDTO.setDistanceFromUniversity(resultRecord.getDistanceFromUniversity());
            tempResultRecordDTO.setCost(resultRecord.getCost());
            tempResultRecordDTO.setSize(resultRecord.getSize());
            tempResultRecordDTO.setBalcony(resultRecord.isBalcony());
            tempResultRecordDTO.setYard(resultRecord.isYard());
            tempResultRecordDTO.setAnimals(resultRecord.isAnimals());
            tempResultRecordDTO.setWarehouse(resultRecord.isWarehouse());
            tempResultRecordDTO.setProtectedSpace(resultRecord.isProtectedSpace());
            tempResultRecordDTO.setFurniture(resultRecord.getFurniture());
            tempResultRecordDTO.setNumberOfRooms(resultRecord.getNumberOfRooms());
            tempResultRecordDTO.setNumberOfRoomates(resultRecord.getNumberOfRoomates());
            tempResultRecordDTO.setDateOfPublish(resultRecord.getDateOfPublish());
            tempResultRecordDTO.setLat(resultRecord.getLat());
            tempResultRecordDTO.setLon(resultRecord.getLon());
            ContactDTO[] contactDTOS = convertToDTO(resultRecord.getContacts());
            tempResultRecordDTO.setContacts(contactDTOS);

            resultRecordDTOS[i] = tempResultRecordDTO;
        }
        SearchResultsDTO searchResultsDTO= new SearchResultsDTO(resultRecordDTOS);
        return searchResultsDTO;
    }

    public ContactDTO[] convertToDTO(Contact[] contacts) {
        ContactDTO[] contactDTOS = new ContactDTO[contacts.length];
        ContactDTO tempContactDTO;
        for (int i = 0; i < contacts.length; i++) {
            tempContactDTO = new ContactDTO();
            tempContactDTO.setName(contacts[i].getName());
            tempContactDTO.setPhone(contacts[i].getPhone());
            contactDTOS[i] = tempContactDTO;
        }
        return contactDTOS;
    }


}
