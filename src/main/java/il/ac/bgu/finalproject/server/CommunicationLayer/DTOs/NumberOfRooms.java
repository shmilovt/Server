package il.ac.bgu.finalproject.server.CommunicationLayer.DTOs;

/**
 * Created by TAMIR on 4/21/2018.
 */

public enum NumberOfRooms {
    unknown(-1),
    one(1),
    two(2),
    three(3),
    four(4),
    five(5),
    sixAndMore(6);

    private final int value;
    private NumberOfRooms(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }


}
