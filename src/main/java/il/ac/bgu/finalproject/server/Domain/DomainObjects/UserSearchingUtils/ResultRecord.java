package il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils;

import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Contact;

public class ResultRecord {
    private String apartmentID;
    private String street;
    private int number;
    private String neighborhood;
    private int floor;
    private double distanceFromUniversity;
    private int cost;
    private int size;

    private int balcony;
    private int yard;
    private int animals;
    private int warehouse ;
    private int protectedSpace;

    private int furniture;
    private double numberOfRooms;
    private int numberOfRoomates;
    private String dateOfPublish; //just date without time
    private String text;
    private Contact[] contacts;
    private double lat; // קווי אורך
    private double lon; // קווי רוחב

    public ResultRecord(){
    }
    public ResultRecord(String street, int number, String neighborhood, int floor, double distanceFromUniversity,
             int cost, int size, int balcony, int yard, int animals, int warehouse ,
                        int protectedSpace, int furniture, double numberOfRooms, int numberOfRoomates, String dateOfPublish, //just date without time
             String text, Contact[] contacts, double lat, // קווי אורך
             double lon){
        this.street= street;
        this.number= number;
        this.neighborhood= neighborhood;
        this.floor= floor;
        this.distanceFromUniversity= distanceFromUniversity;
        this.cost= cost;
        this.size= size;
        this.balcony= balcony;
        this.yard= yard;
        this.animals= animals;
        this.warehouse= warehouse;
        this.protectedSpace= protectedSpace;
        this.furniture= furniture;
        this.numberOfRooms= numberOfRooms;
        this.numberOfRoomates= numberOfRoomates;
        this.dateOfPublish= dateOfPublish;
        this.text=text;
        this.contacts=contacts;
        this.lat= lat;
        this.lon= lon;
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

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public double getDistanceFromUniversity() {
        return distanceFromUniversity;
    }

    public void setDistanceFromUniversity(double distanceFromUniversity) {
        this.distanceFromUniversity = distanceFromUniversity;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getBalcony() {
        return balcony;
    }

    public void setBalcony(int balcony) {
        this.balcony = balcony;
    }

    public int getYard() {
        return yard;
    }

    public void setYard(int yard) {
        this.yard = yard;
    }

    public int getAnimals() {
        return animals;
    }

    public void setAnimals(int animals) {
        this.animals = animals;
    }

    public int getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(int warehouse) {
        this.warehouse = warehouse;
    }

    public int getProtectedSpace() {
        return protectedSpace;
    }

    public void setProtectedSpace(int protectedSpace) {
        this.protectedSpace = protectedSpace;
    }

    public int getFurniture() {
        return furniture;
    }

    public void setFurniture(int furniture) {
        this.furniture = furniture;
    }

    public double getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(double numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public int getNumberOfRoomates() {
        return numberOfRoomates;
    }

    public void setNumberOfRoomates(int numberOfRoomates) {
        this.numberOfRoomates = numberOfRoomates;
    }

    public String getDateOfPublish() {
        return dateOfPublish;
    }

    public void setDateOfPublish(String dateOfPublish) {
        this.dateOfPublish = dateOfPublish;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Contact[] getContacts() {
        return contacts;
    }

    public void setContacts(Contact[] contacts) {
        this.contacts = contacts;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getApartmentID() {
        return apartmentID;
    }

    public void setApartmentID(String apartmentID) {
        this.apartmentID = apartmentID;
    }
}
