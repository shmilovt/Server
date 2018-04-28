package il.ac.bgu.finalproject.server.PersistenceLayer;

import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Post;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class DataBaseConnectionTest {
    private static DataBaseConnection dbc= new DataBaseConnection();
    @BeforeClass
    public static void setup(){
        dbc.connect();
        dbc.resetAllTables();
    }

    @AfterClass
    public static void endup(){
        dbc.disConnect();
    }


    @Test
    public void addPost() {

        dbc.addPost("1",null, "maayan", "דירה שחבל להפסיד", "1");
        dbc.addPost("2",null, "nof", "דירה מהממת", "2");
        dbc.addPost("3",null, "nof2", "דירה מהממת", "2");
        dbc.addPost("4",null, "nof3", "דירה מהממת", "4");
        dbc.addPost("4",null, "mani", "דירה מדהימה", "5");
        assertTrue(dbc.getPost("1")!=null);
        assertTrue(dbc.getPost("2")!=null);
        assertTrue(dbc.getPost("3")!=null);
        assertTrue(dbc.getPost("4")!=null);
        assertTrue(dbc.getPost("5")==null);
        assertEquals("4",dbc.getPost("4").getApartmentID());
        //System.out.println(dbc.GetAllPostsId().toString());
    }

    @Test
    public void update() {
        dbc.addPost("4",null, "nof3", "דירה מהממת", "4");
        dbc.update("4",null, "mani", "דירה מדהימה", "5");
        assertEquals("5",dbc.getPost("4").getApartmentID());
        assertEquals("mani",dbc.getPost("4").getPublisherName());

    }

    @Test
    public void getPost() {
        dbc.addPost("4",null, "nof3", "דירה מהממת", "4");
        Post post = dbc.getPost("99");
        assertEquals(post.getText(),"דירה מהממת");
    }

    @Test
    public void deletePost() {
        dbc.addPost("4",null, "nof3", "דירה מהממת", "4");
        dbc.deletePost("4");
        assertTrue(dbc.getPost("4")==null);
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
    }

    @Test
    public void getApartmentRecordTBD() {
    }

    @Test
    public void deleteApartmentRecord() {
    }

    @Test
    public void morePostsWithApartmentID() {
    }

    @Test
    public void main() {

    }
}