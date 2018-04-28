package il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils;

public class Address {
    private String street;
    private int number;
//    private double lat;
//    private double lng;

    public Address(){}
    public Address(String street, int number){
        this.number = number;
        this.street = street;
//        this.lat=-1;
//        this.lng=-1;
    }

//    public Address(String street, int number,double lat,double lng){
//        this.number = number;
//        this.street = street;
//        this.lat=lat;
//        this.lng=lng;
//    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String calcNeighborhood(){ // not implement

      return "neighborhood";//throw new UnsupportedOperationException();
    }

    public double calcDistanceFromUniversity(){ // not implement

        return 20.5;//throw new UnsupportedOperationException();
    }

    public String toString(){
        return "Address: "+street+" "+number;
    }


//    public double getLat() {
//        return lat;
//    }
//
//    public void setLat(double lat) {
//        this.lat = lat;
//    }
//
//    public double getLng() {
//        return lng;
//    }
//
//    public void setLng(double lng) {
//        this.lng = lng;
//    }
}
