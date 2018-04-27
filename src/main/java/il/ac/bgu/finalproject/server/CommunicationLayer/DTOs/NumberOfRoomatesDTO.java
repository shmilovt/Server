package il.ac.bgu.finalproject.server.CommunicationLayer.DTOs;

/**
 * Created by TAMIR on 4/21/2018.
 */

public enum NumberOfRoomatesDTO {
    unknown(-1),
    couple(0),
    one(1),
    two(2),
    three(3),
    four(4),
    fiveOrMore(5);


    private final int value;
    private NumberOfRoomatesDTO(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

