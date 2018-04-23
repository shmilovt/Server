package il.ac.bgu.finalproject.server.CommunicationLayer.DTOs;

import com.google.gson.Gson;

public class SearchResultsDTO {

    public SearchResultsDTO(){

    }

    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
