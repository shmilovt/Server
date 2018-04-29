package il.ac.bgu.finalproject.server.CommunicationLayer.DTOs;

public class SizeDTO {
    private int minSize;
    private int maxSize;

    public SizeDTO(){};

    public SizeDTO(int minSize, int maxSize) {
        this.minSize = minSize;
        this.maxSize = maxSize;
    }

    public int getMinSize() {
        return minSize;
    }

    public void setMinSize(int minSize) {
        this.minSize = minSize;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }
}
