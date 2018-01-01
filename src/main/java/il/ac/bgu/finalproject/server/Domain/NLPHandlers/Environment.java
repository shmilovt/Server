package il.ac.bgu.finalproject.server.Domain.NLPHandlers;

public class Environment {
    private String envString;
    private int index;
    private int offset;


    public Environment(String envString, int offset,int index) {
        this.envString = envString;
        this.index = index;
        this.offset = offset;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }


    public String getEnvString() {
        return envString;
    }

    public void setEnvString(String envString) {
        this.envString = envString;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
