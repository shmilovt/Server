package il.ac.bgu.finalproject.server.CommunicationLayer.DTOs;

public class GroupDTO {
    private String groupID;
    private String groupName;

    public GroupDTO(){ }
    public GroupDTO(String groupID, String groupName) {
        this.setGroupID(groupID);
        this.setGroupName(groupName);
    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
