package il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils;

import java.util.Date;

public class Post {
    private String ID;
    private Date dateOfPublish;
    private String publisherName;
    private String text;
    private String apartmentID;

    public Post(String text) {
        this.text = text;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Date getDateOfPublish() {
        return dateOfPublish;
    }

    public void setDateOfPublish(Date dateOfPublish) {
        this.dateOfPublish = dateOfPublish;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getApartmentID() {
        return apartmentID;
    }

    public void setApartmentID(String apartmentID) {
        this.apartmentID = apartmentID;
    }
}
