package il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentDetails.Locations.Neighborhoods;

import java.util.ArrayList;
import java.util.List;

public class NeighborhoodSurface {
    private String name;
    private List<Coordinate> surface;


    public NeighborhoodSurface(){}
    public NeighborhoodSurface(String name) {
        this.name = name;
        this.surface = new ArrayList<Coordinate>();
    }

    public NeighborhoodSurface(String name, List<Coordinate> surface) {

        this.name = name;
        this.surface = surface;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Coordinate> getSurface() {
        return surface;
    }

    public void setSurface(List<Coordinate> surface) {
        this.surface = surface;
    }

    public String toString(){
        return this.name;
    }
}
