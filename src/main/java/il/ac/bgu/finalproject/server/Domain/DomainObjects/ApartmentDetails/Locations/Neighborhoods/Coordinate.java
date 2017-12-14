package il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentDetails.Locations.Neighborhoods;

public class Coordinate {
    private double Longitude;
    private double Latitude;

    public Coordinate(){}
    public Coordinate(double longitude, double latitude) {
        Longitude = longitude;
        Latitude = latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }


}
