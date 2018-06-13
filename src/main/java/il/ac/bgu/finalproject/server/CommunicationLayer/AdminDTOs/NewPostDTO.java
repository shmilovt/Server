package il.ac.bgu.finalproject.server.CommunicationLayer.AdminDTOs;

import com.google.gson.Gson;

public class NewPostDTO {
    private String publisherName;
    private String messege;

    public NewPostDTO(){}

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getMessege() {
        return messege;
    }

    public void setMessege(String messege) {
        this.messege = messege;
    }
    public static String toJSON(NewPostDTO newPostDTO) {
        Gson gson = new Gson();
        return gson.toJson(newPostDTO);
    }

    public static NewPostDTO fromJSON(String newPostDTOString){
        Gson gson = new Gson();
        NewPostDTO newPostDTO = gson.fromJson(newPostDTOString, NewPostDTO.class);
        return newPostDTO;

    }
}