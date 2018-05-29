package il.ac.bgu.finalproject.server.CommunicationLayer.DTOs;


import il.ac.bgu.finalproject.server.CommunicationLayer.AdminDTOs.ApartmentDTO;
import il.ac.bgu.finalproject.server.CommunicationLayer.AdminDTOs.ContactApartmentDTO;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Contact;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Converter {

    @Autowired
    private ModelMapper modelMapper;
    public List<CategoryQuery> convertFromDTO(UserSearchDTO userSearchDTO) {
        List<CategoryQuery> categoryQueries = new ArrayList<>();
        CategoryType[] categoryTypes = userSearchDTO.getPriorities();
        for(CategoryType categoryType : categoryTypes){
           switch (categoryType){
               case numRooms:
                   int numberOfRooms = userSearchDTO.getNumberOfRooms();
                   NumOfRoomsQuery numOfRoomsQuery = new NumOfRoomsQuery(numberOfRooms);
                   categoryQueries.add(numOfRoomsQuery);
                   break;
               case numRoomates:

                   int numberOfRoomaes =  userSearchDTO.getNumberOfMates();
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
                   int distanceFromUniversity = userSearchDTO.getDistanceFromUniversity();
                   TimeFromUniQuery timeFromUniQuery = new TimeFromUniQuery(distanceFromUniversity);
                   categoryQueries.add(timeFromUniQuery);
                   break;
               case neighborhood:
                   String neighborhood =   userSearchDTO.getNeighborhood();
                   NeighborhoodQuery neighborhoodQuery = new  NeighborhoodQuery(neighborhood);
                   categoryQueries.add( neighborhoodQuery);
                   break;
               case  apartmentSize:
                   SizeDTO apartmentSizeDTO = userSearchDTO.getSizeDTO();
                   SizeQuery sizeQuery = new SizeQuery(apartmentSizeDTO.getMinSize(), apartmentSizeDTO.getMaxSize());
                   categoryQueries.add(sizeQuery);
                   break;
               case furniture:
                   int furniture = userSearchDTO.getFurniture();
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
               default:
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
            tempResultRecordDTO.setApartmentID(resultRecord.getApartmentID());
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
            tempResultRecordDTO.setText(resultRecord.getText());

            resultRecordDTOS[i] = tempResultRecordDTO;
            i++;
        }
        SearchResultsDTO searchResultsDTO= new SearchResultsDTO(resultRecordDTOS);
        return searchResultsDTO;
    }



    public List<ApartmentDTO> convertToDTOAdminApartment(SearchResults searchResult) {
        List<ResultRecord> resultRecordList = searchResult.getResultRecordList();
        List<ApartmentDTO> ans= new LinkedList<ApartmentDTO>();
        int i = 0;
        ApartmentDTO apartmentDTO;
        for (ResultRecord resultRecord : resultRecordList) {
            apartmentDTO = new ApartmentDTO();
            apartmentDTO.setApartmentID(resultRecord.getApartmentID());
            apartmentDTO.setStreet(resultRecord.getStreet());
            apartmentDTO.setNumber(resultRecord.getNumber());
            apartmentDTO.setNeighborhood(resultRecord.getNeighborhood());
            apartmentDTO.setFloor(resultRecord.getFloor());
            apartmentDTO.setDistanceFromUniversity(resultRecord.getDistanceFromUniversity());
            apartmentDTO.setCost(resultRecord.getCost());
            apartmentDTO.setSize(resultRecord.getSize());
            apartmentDTO.setBalcony(resultRecord.isBalcony());
            apartmentDTO.setYard(resultRecord.isYard());
            apartmentDTO.setAnimals(resultRecord.isAnimals());
            apartmentDTO.setWarehouse(resultRecord.isWarehouse());
            apartmentDTO.setProtectedSpace(resultRecord.isProtectedSpace());
            apartmentDTO.setFurniture(resultRecord.getFurniture());
            apartmentDTO.setNumberOfRooms(resultRecord.getNumberOfRooms());
            apartmentDTO.setNumberOfRoomates(resultRecord.getNumberOfRoomates());
            apartmentDTO.setDateOfPublish(resultRecord.getDateOfPublish());
            apartmentDTO.setLat(resultRecord.getLat());
            apartmentDTO.setLon(resultRecord.getLon());
            ContactApartmentDTO[] contactDTOS = convertContectToDTOAdmin(resultRecord.getContacts());
            apartmentDTO.setContacts(contactDTOS);
            apartmentDTO.setText(resultRecord.getText());

            ans.add(apartmentDTO);
            i++;
        }
        return ans;
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
    public ContactApartmentDTO[] convertContectToDTOAdmin(Contact[] contacts) {
        ContactApartmentDTO[] contactDTOS = new ContactApartmentDTO[contacts.length];
        ContactApartmentDTO tempContactDTO;
        for (int i = 0; i < contacts.length; i++) {
            tempContactDTO = new ContactApartmentDTO();
            tempContactDTO.setName(contacts[i].getName());
            tempContactDTO.setPhone(contacts[i].getPhone());
            contactDTOS[i] = tempContactDTO;
        }
        return contactDTOS;
    }

}
