package il.ac.bgu.finalproject.server.Domain.NLPHandlers;

public class Environment {
    private String envString;
    private int index;
    private int offset;
    private char delimeter;

    public Environment(String envString, int offset,int index,char delimeter) {
        this.envString = envString;
        this.index = index;
        this.offset = offset;
        this.delimeter = delimeter;
    }

    public char getDelimeter() {
        return delimeter;
    }

    public void setDelimeter(char delimeter) {
        this.delimeter = delimeter;
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
