package il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentDetails.Locations;

public class Address {
    private String street;
    private int number;

    public Address(){}
    public Address(String street, int number){
        this.number = number;
        this.street = street;
    }

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



}
