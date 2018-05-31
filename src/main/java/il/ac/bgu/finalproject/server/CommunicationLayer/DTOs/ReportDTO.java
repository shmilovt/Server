package il.ac.bgu.finalproject.server.CommunicationLayer.DTOs;

import com.google.gson.Gson;

public class ReportDTO {
    private String apartmentID;
    private Field field;
    private String contentInGson;

    public ReportDTO(){}
    public static String toJSON(ReportDTO reportDTO) {
        Gson gson = new Gson();
        return gson.toJson(reportDTO);
    }

    public static ReportDTO fromJSON(String reportDTOstring){
        Gson gson = new Gson();
        ReportDTO reportDTO = gson.fromJson(reportDTOstring, ReportDTO.class);
        return reportDTO;

    }

    public String getApartmentID() {
        return apartmentID;
    }

    public void setApartmentID(String apartmentID) {
        this.apartmentID = apartmentID;
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
        numOfRooms,
        size,

        //int:
        numberOfMates,
        cost,
        floor,
        furniture,
        balcony,
        animal,
        protectedSpace,
        garden,
        warehouse,

        address
    }
}
