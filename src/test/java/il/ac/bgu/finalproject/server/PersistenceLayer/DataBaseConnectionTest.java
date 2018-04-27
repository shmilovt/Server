package il.ac.bgu.finalproject.server.PersistenceLayer;

import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Address;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Apartment;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.ApartmentLocation;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Contact;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DataBaseConnectionTest {
    private static DataBaseConnection dbc = new DataBaseConnection();

    @BeforeClass
    public static void setup() {
        dbc.connect();
//        dbc.resetAllTables();
    }

    @AfterClass
    public static void endup() {
        dbc.disConnect();
    }


    @Test
    public void addPost() {
        dbc.addPost("1", null, "maayan", "דירה שחבל להפסיד", "1");
        dbc.addPost("2", null, "nof", "דירה מהממת", "2");
        dbc.addPost("3", null, "nof2", "דירה מהממת", "2");
        dbc.addPost("4", null, "nof3", "דירה מהממת", "4");
        dbc.addPost("4", null, "mani", "דירה מדהימה", "5");
        assertTrue(dbc.getPost("1") != null);
        assertTrue(dbc.getPost("2") != null);
        assertTrue(dbc.getPost("3") != null);
        assertTrue(dbc.getPost("4") != null);
        assertTrue(dbc.getPost("5") == null);
        assertEquals("4", dbc.getPost("4").getApartmentID());
        //System.out.println(dbc.GetAllPostsId().toString());
    }

    @Test
    public void update() {
        dbc.addPost("4", null, "nof3", "דירה מהממת", "4");
        dbc.update("4", null, "mani", "דירה מדהימה", "5");
        assertEquals("5", dbc.getPost("4").getApartmentID());
        assertEquals("mani", dbc.getPost("4").getPublisherName());

    }

    @Test
    public void getPost() {
    }

    @Test
    public void deletePost() {
        dbc.addPost("4", null, "nof3", "דירה מהממת", "4");
        dbc.deletePost("4");
        assertTrue(dbc.getPost("4") == null);
    }

    @Test
    public void getAllPostsId() {
    }

    @Test
    public void addAddressDetailsRecord() {
    }

    @Test
    public void updateAddressDetailsRecordaaaa() {
    }

    @Test
    public void getPostaaaa() {
    }

    @Test
    public void deletePostaaa() {
    }

    @Test
    public void addContactsRecord() {
    }

    @Test
    public void addApartmentContactsRecord() {
    }

    @Test
    public void addApartmentRecord() {
        dbc.addApartmentRecord("3", 4, 4, 90, 1000, 1, 1, 0, 0, 0, 0, 0, 0, 3);
    }

    @Test
    public void getApartmentRecordTBD() {
        System.out.println(dbc.getApartmentRecordTBD("3").toString());

    }

    @Test
    public void getApartmentContacts() {
        Set <Contact> contacts= dbc.getApartmentContacts("2");
        for (Contact contact: contacts) {
            System.out.println(contact.toString());
        }

    }

    @Test
    public void allApartmentFromDB (){
        List<Apartment> apartmentsCollection= dbc.allApartmentFromDB();
        if (apartmentsCollection.isEmpty())
            System.out.println("emptyyyyy");
        for(Apartment item: apartmentsCollection){
            System.out.println(item.toString());
        }
    }

    @Test
    public void deleteApartmentRecord() {
    }

    @Test
    public void morePostsWithApartmentID() {
    }


    @Test
    public void isApartmentExist() {
        Address address = new Address("יצחק רגר", 16);
        ApartmentLocation location = new ApartmentLocation(address, 3, 33);
        Contact c1 = new Contact("lior", "0533333333");
        Contact c2 = new Contact("almog", "0534444444");
        Set<Contact> contacts = new HashSet<Contact>();
        contacts.add(c1);
        contacts.add(c2);
        Apartment apartment = new Apartment(location, 1000, contacts);
        //dbc.addApartmentDerivatives(apartment,"2");
        assertTrue(dbc.isApartmentExist(apartment)!=-1);

    }

    @Test
    public void addApartmentDerivatives() {
    }
}