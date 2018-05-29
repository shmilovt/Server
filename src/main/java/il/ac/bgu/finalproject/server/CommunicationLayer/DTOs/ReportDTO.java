package il.ac.bgu.finalproject.server.CommunicationLayer.DTOs;

public class ReportDTO {
    private String addressID;
    private Field field;
    private String contentInGson;

    public String getAddressID() {
        return addressID;
    }

    public void setAddressID(String addressID) {
        this.addressID = addressID;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public String getContentInGson() {
        return contentInGson;
    }

    public void setContentInGson(String contentInGson) {
        this.contentInGson = contentInGson;
    }

    public enum Field{
        //double:
        numRooms,
        apartmentSize,

        //int:
        numRoomates,
        cost,
        floor,
        furniture,
        balcony,
        animals,
        protectedSpace,
        yard,
        warehouse,

        address
    }
}
