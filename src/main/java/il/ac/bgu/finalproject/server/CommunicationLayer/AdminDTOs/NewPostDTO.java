package il.ac.bgu.finalproject.server.CommunicationLayer.AdminDTOs;

import com.google.gson.Gson;

public class NewPostDTO {
    private String publisherName;
    private String messege;
    private TokenDTO tokenDTO;

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
    public void setMessege1(String messege) {
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

    public TokenDTO getTokenDTO() {
        return tokenDTO;
    }

    public void setTokenDTO(TokenDTO tokenDTO) {
        this.tokenDTO = tokenDTO;
    }
}
