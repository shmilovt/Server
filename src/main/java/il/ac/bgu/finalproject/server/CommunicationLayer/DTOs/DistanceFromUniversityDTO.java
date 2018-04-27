package il.ac.bgu.finalproject.server.CommunicationLayer.DTOs;

public enum DistanceFromUniversityDTO {
    unknown(-1),
    until5(5),
    until10(10),
    until15(15),
    until20(20);

    private final int value;
    private DistanceFromUniversityDTO(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
