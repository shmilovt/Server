package il.ac.bgu.finalproject.server.Domain.DomainObjects;

public class SubscribeGroup {
    private String name;
    private String ID;

    public SubscribeGroup(){}
    public SubscribeGroup(String name, String ID) {
        this.name = name;
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public String getID() {
        return ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
