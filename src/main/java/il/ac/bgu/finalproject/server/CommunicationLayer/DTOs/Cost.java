package il.ac.bgu.finalproject.server.CommunicationLayer.DTOs;

public class Cost { // -1 indicate no limit
    private int minCost;
    private int maxCost;

    public Cost(){}
    public Cost(int minCost, int maxCost){
        this.minCost = minCost;
        this.maxCost = maxCost;
    }


    public int getMinCost() {
        return minCost;
    }

    public void setMinCost(int minCost) {
        this.minCost = minCost;
    }

    public int getMaxCost() {
        return maxCost;
    }

    public void setMaxCost(int maxCost) {
        this.maxCost = maxCost;
    }
}
