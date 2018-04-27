package il.ac.bgu.finalproject.server.CommunicationLayer.DTOs;

import com.google.gson.Gson;


public class UserSearchDTO {
    private CategoryTypeDTO[] priorities;
    private ApartmentSizeDTO apartmentSizeDTO;
    private String neighborhood;
    private DistanceFromUniversityDTO distanceFromUniversityDTO;
    private FloorDTO floorDTO;
    private CostDTO costDTO;
    private FurnitureDTO furnitureDTO;
    private NumberOfRoomsDTO numberOfRoomsDTO;
    private NumberOfRoomatesDTO numberOfMates;
    private Boolean balcony;
    private Boolean animals;
    private Boolean protectedSpace;
    private Boolean yard;
    private Boolean warehouse;

    public UserSearchDTO(String userSearchDTOString){
        Gson gson = new Gson();
        UserSearchDTO userSearchDTO = gson.fromJson(userSearchDTOString, UserSearchDTO.class);
        /*






         */


    }


    public CategoryTypeDTO[] getPriorities() {
        return priorities;
    }

    public void setPriorities(CategoryTypeDTO[] priorities) {
        this.priorities = priorities;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public DistanceFromUniversityDTO getDistanceFromUniversityDTO() {
        return distanceFromUniversityDTO;
    }





    public void setDistanceFromUniversityDTO(DistanceFromUniversityDTO distanceFromUniversityDTO) {
        this.distanceFromUniversityDTO = distanceFromUniversityDTO;
    }

    public FloorDTO getFloorDTO() {
        return floorDTO;
    }

    public void setFloorDTO(FloorDTO floorDTO) {
        this.floorDTO = floorDTO;
    }

    public CostDTO getCostDTO() {
        return costDTO;
    }

    public void setCostDTO(CostDTO costDTO) {
        this.costDTO = costDTO;
    }

    public FurnitureDTO getFurnitureDTO() {
        return furnitureDTO;
    }

    public void setFurnitureDTO(FurnitureDTO furnitureDTO) {
        this.furnitureDTO = furnitureDTO;
    }

    public NumberOfRoomsDTO getNumberOfRoomsDTO() {
        return numberOfRoomsDTO;
    }

    public void setNumberOfRoomsDTO(NumberOfRoomsDTO numberOfRoomsDTO) {
        this.numberOfRoomsDTO = numberOfRoomsDTO;
    }

    public NumberOfRoomatesDTO getNumberOfMates() {
        return numberOfMates;
    }

    public void setNumberOfMates(NumberOfRoomatesDTO numberOfMates) {
        this.numberOfMates = numberOfMates;
    }

    public Boolean getBalcony() {
        return balcony;
    }

    public void setBalcony(Boolean balcony) {
        this.balcony = balcony;
    }

    public Boolean getAnimals() {
        return animals;
    }

    public void setAnimals(Boolean animals) {
        this.animals = animals;
    }

    public Boolean getProtectedSpace() {
        return protectedSpace;
    }

    public void setProtectedSpace(Boolean protectedSpace) {
        this.protectedSpace = protectedSpace;
    }

    public Boolean getYard() {
        return yard;
    }

    public void setYard(Boolean yard) {
        this.yard = yard;
    }

    public Boolean getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Boolean warehouse) {
        this.warehouse = warehouse;
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public ApartmentSizeDTO getApartmentSizeDTO() {
        return apartmentSizeDTO;
    }

    public void setApartmentSizeDTO(ApartmentSizeDTO apartmentSizeDTO) {
        this.apartmentSizeDTO = apartmentSizeDTO;
    }
}
