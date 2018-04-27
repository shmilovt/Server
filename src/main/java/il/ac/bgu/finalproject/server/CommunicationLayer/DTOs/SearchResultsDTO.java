package il.ac.bgu.finalproject.server.CommunicationLayer.DTOs;

import com.google.gson.Gson;

public class SearchResultsDTO {
    private ResultRecordDTO[] resultRecordDTOS;
    public SearchResultsDTO(){}
    public SearchResultsDTO(ResultRecordDTO[] resultRecordDTOS){

        this.resultRecordDTOS = resultRecordDTOS;

    }


    public ResultRecordDTO[] getResultRecordDTOS() {
        return resultRecordDTOS;
    }

    public void setResultRecordDTOS(ResultRecordDTO[] resultRecordDTOS) {
        this.resultRecordDTOS = resultRecordDTOS;
    }

    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
