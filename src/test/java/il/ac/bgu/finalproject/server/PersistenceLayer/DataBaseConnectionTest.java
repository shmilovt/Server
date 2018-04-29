package il.ac.bgu.finalproject.server.PersistenceLayer;

import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Address;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Apartment;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.ApartmentLocation;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Contact;
import il.ac.bgu.finalproject.server.Domain.Exceptions.DataBaseFailedException;
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
    public static void setup() throws DataBaseFailedException {
        dbc.connect();
//        dbc.resetAllTables();
    }

    @AfterClass
    public static void endup() throws DataBaseFailedException {

        dbc.disConnect();
    }


    @Test
    public void addPost() throws DataBaseFailedException {
        dbc.addPost("114", null, "maayan", "דירה שחבל להפסיד", "1");
        dbc.addPost("124", null, "nof", "דירה מהממת", "2");
        dbc.addPost("134", null, "nof2", "דירה מהממת", "2");
        dbc.addPost("144", null, "nof3", "דירה מהממת", "4");
        dbc.addPost("144", null, "mani", "דירה מדהימה", "5");
        assertTrue(dbc.getPost("1") != null);
        assertTrue(dbc.getPost("2") != null);
        assertTrue(dbc.getPost("3") != null);
        assertTrue(dbc.getPost("4") != null);
        assertTrue(dbc.getPost("5") == null);
        assertEquals("4", dbc.getPost("4").getApartmentID());
        System.out.println(dbc.GetAllPostsId().toString());
    }

    @Test
    public void update() throws DataBaseFailedException {
        dbc.addPost("4", null, "nof3", "דירה מהממת", "4");
        dbc.update("4", null, "mani", "דירה מדהימה", "5");
        assertEquals("5", dbc.getPost("4").getApartmentID());
        assertEquals("mani", dbc.getPost("4").getPublisherName());

    }

    @Test
    public void deletePost() throws DataBaseFailedException {
        dbc.addPost("4", null, "nof3", "דירה מהממת", "4");
        dbc.deletePost("4");
        assertTrue(dbc.getPost("4") == null);
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
    public void morePostsWithApartmentID() {
    }


    @Test
    public void isApartmentExist() throws DataBaseFailedException {
        Address address = new Address("וינגייט", 61);
        ApartmentLocation location = new ApartmentLocation(address, 4, 16);
        Contact c1 = new Contact("Tal", "0535555555");
        Contact c2 = new Contact("Sahar", "0536666666");
        Set<Contact> contacts = new HashSet<Contact>();
        contacts.add(c1);
        contacts.add(c2);
        Apartment apartment = new Apartment(location, 1000, contacts);

        dbc.addApartmentDerivatives(apartment,"");
        assertTrue(dbc.isApartmentExist(apartment)!=-1);
    }

    @Test
    public void addApartmentDerivatives() throws DataBaseFailedException {
        Address address = new Address("יצחק רגר", 16);
        ApartmentLocation location = new ApartmentLocation(address, 3, 33);
        Contact c1 = new Contact("lior", "0533333333");
        Contact c2 = new Contact("almog", "0534444444");
        Set<Contact> contacts = new HashSet<Contact>();
        contacts.add(c1);
        contacts.add(c2);
        Apartment apartment = new Apartment(location, 1000, contacts);

        dbc.addApartmentDerivatives(apartment,"2");
        assertTrue(dbc.isApartmentExist(apartment)!=-1);
    }

    @Test
    public void getConstValue() throws DataBaseFailedException {
//        dbc.resetConstValueTable();
//        assertTrue(dbc.getConstValue("addressDetailsID")==0);
        int t= dbc.getConstValue("addressDetailsID");
        dbc.setConstValue("addressDetailsID",t+1);
        assertEquals(dbc.getConstValue("addressDetailsID"),t+1);
    }

    @Test
    public void setConstValue() throws DataBaseFailedException {
        int t= dbc.getConstValue("addressDetailsID");
        dbc.setConstValue("addressDetailsID",t+1);
        assertEquals(dbc.getConstValue("addressDetailsID"),t+1);
    }
}