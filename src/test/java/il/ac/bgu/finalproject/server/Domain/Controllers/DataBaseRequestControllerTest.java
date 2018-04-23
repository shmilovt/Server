package il.ac.bgu.finalproject.server.Domain.Controllers;

import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.*;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class DataBaseRequestControllerTest {

    @Test
    public void manageApartment() {
        DataBaseRequestController dbrc= new DataBaseRequestController();

        Address address= new Address("יצחק רגר",16);
        ApartmentLocation location= new ApartmentLocation(address,4,34);
        Contact c1= new Contact("liora","0533333333");
        Contact c2= new Contact("almoga","0534444444");
        Set<Contact> contacts= new HashSet<Contact>();
        contacts.add(c1);
        contacts.add(c2);
        Apartment apartment= new Apartment(location,1001,contacts);
        Date date= new Date();
        Post post= new Post("123456", date, "lior", "סתם טקסט כי ה-NLP כבר נעשה", "-1");
        dbrc.manageApartment(apartment, post);
    }

    @Test
    public void getPost() {
    }

    @Test
    public void updatePost() {
    }

    @Test
    public void addPost() {
    }

    @Test
    public void deletePost() {
    }

    @Test
    public void getAllPostsId() {
    }
}