package il.ac.bgu.finalproject.server.CommunicationLayer.DTOs;

/**
 * Created by TAMIR on 4/21/2018.
 */

public class ApartmentSizeDTO {

    private int minSize;
    private int maxSize;

    public ApartmentSizeDTO(){}
    public ApartmentSizeDTO(int minSize, int maxSize){
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
