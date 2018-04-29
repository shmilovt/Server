package il.ac.bgu.finalproject.server.CommunicationLayer.DTOs;

import com.google.gson.Gson;


public class UserSearchDTO {
    private CategoryType[] priorities;
    private String neighborhood;
    private int distanceFromUniversity;
    private FloorDTO floorDTO;
    private CostDTO costDTO;
    private SizeDTO sizeDTO;
    private int furniture;
    private int numberOfRooms;
    private int numberOfMates;


    public UserSearchDTO(){
    }





    public static String toJSON(UserSearchDTO userSearchDTO) {
        Gson gson = new Gson();
        return gson.toJson(userSearchDTO);
    }

    public static UserSearchDTO fromJSON(String userSearchDTOString){
        Gson gson = new Gson();
        UserSearchDTO userSearchDTO = gson.fromJson(userSearchDTOString, UserSearchDTO.class);
        return userSearchDTO;

    }


    public CategoryType[] getPriorities() {
        return priorities;
    }

    public void setPriorities(CategoryType[] priorities) {
        this.priorities = priorities;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public int getDistanceFromUniversity() {
        return distanceFromUniversity;
    }

    public void setDistanceFromUniversity(int distanceFromUniversity) {
        this.distanceFromUniversity = distanceFromUniversity;
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

    public SizeDTO getSizeDTO() {
        return sizeDTO;
    }

    public void setSizeDTO(SizeDTO sizeDTO) {
        this.sizeDTO = sizeDTO;
    }

    public int getFurniture() {
        return furniture;
    }

    public void setFurniture(int furniture) {
        this.furniture = furniture;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public int getNumberOfMates() {
        return numberOfMates;
    }

    public void setNumberOfMates(int numberOfMates) {
        this.numberOfMates = numberOfMates;
    }
}
