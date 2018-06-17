package il.ac.bgu.finalproject.server.CommunicationLayer.AdminDTOs;

import com.google.gson.Gson;

public class ManageGroupsDTO {
    private TokenDTO tokenDTO;
    private String groupID;

    public ManageGroupsDTO() {
    }

    public static String toJSON(il.ac.bgu.finalproject.server.CommunicationLayer.AdminDTOs.ManageGroupsDTO manageGroupsDTO) {
        Gson gson = new Gson();
        return gson.toJson(manageGroupsDTO);
    }

    public static il.ac.bgu.finalproject.server.CommunicationLayer.AdminDTOs.ManageGroupsDTO fromJSON(String jsonString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, il.ac.bgu.finalproject.server.CommunicationLayer.AdminDTOs.ManageGroupsDTO.class);
    }

    public TokenDTO getTokenDTO() {
        return tokenDTO;
    }

    public void setTokenDTO(TokenDTO tokenDTO) {
        this.tokenDTO = tokenDTO;
    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }
}
