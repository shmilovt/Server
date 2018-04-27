package il.ac.bgu.finalproject.server.CommunicationLayer.DTOs;

public class FloorDTO {
    private int minFloor;
    private int maxFloor;

    public FloorDTO(){}
    public FloorDTO(int minFloor, int maxFloor){
        this.minFloor = minFloor;
        this.maxFloor = maxFloor;
    }

    public int getMinFloor() {
        return minFloor;
    }

    public void setMinFloor(int minFloor) {
        this.minFloor = minFloor;
    }

    public int getMaxFloor() {
        return maxFloor;
    }

    public void setMaxFloor(int maxFloor) {
        this.maxFloor = maxFloor;
    }
}
