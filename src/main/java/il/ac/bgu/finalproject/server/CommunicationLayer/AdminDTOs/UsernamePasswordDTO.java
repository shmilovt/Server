package il.ac.bgu.finalproject.server.CommunicationLayer.AdminDTOs;

import com.google.gson.Gson;

public class UsernamePasswordDTO {
    private String userName;
    private String password;

    public UsernamePasswordDTO(){}

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static String toJSON(UsernamePasswordDTO userSearchDTO) {
        Gson gson = new Gson();
        return gson.toJson(userSearchDTO);
    }

    public static UsernamePasswordDTO fromJSON(String userSearchDTOString){
        Gson gson = new Gson();
        UsernamePasswordDTO userSearchDTO = gson.fromJson(userSearchDTOString, UsernamePasswordDTO.class);
        return userSearchDTO;

    }


}
