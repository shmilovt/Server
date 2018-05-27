package il.ac.bgu.finalproject.server.CommunicationLayer.AdminDTOs;

import com.google.gson.Gson;

public class ArraySearchRecordDTO {
    private SearchRecordDTO[] searchRecordDTOS;

    public ArraySearchRecordDTO(){}
    public ArraySearchRecordDTO(SearchRecordDTO[] searchRecordDTOS){
        this.searchRecordDTOS = searchRecordDTOS;
    }

    public SearchRecordDTO[] getResultRecordDTOS() {
        return searchRecordDTOS;
    }

    public void setResultRecordDTOS(SearchRecordDTO[] resultRecordDTOS) {
        this.searchRecordDTOS = resultRecordDTOS;
    }

    public static String toJSON(ArraySearchRecordDTO searchResultsDTO){
        Gson gson = new Gson();
        return gson.toJson(searchResultsDTO);
    }

    public static ArraySearchRecordDTO fromJSON(String jsonString){
        Gson gson = new Gson();
        return gson.fromJson(jsonString, ArraySearchRecordDTO.class);
    }
}
