package il.ac.bgu.finalproject.server.PersistenceLayer;

import com.google.gson.Gson;
import il.ac.bgu.finalproject.server.CommunicationLayer.AdminDTOs.SearchRecordDTO;
import il.ac.bgu.finalproject.server.CommunicationLayer.DTOs.AddressDTO;
import il.ac.bgu.finalproject.server.CommunicationLayer.DTOs.GroupDTO;
import il.ac.bgu.finalproject.server.CommunicationLayer.DTOs.ReportDTO;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Address;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Apartment;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.ApartmentLocation;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Contact;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils.ResultRecord;
import il.ac.bgu.finalproject.server.Domain.Exceptions.DataBaseFailedException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DataBaseConnectionTest {
    private static DataBaseConnection dbc = new DataBaseConnection();
    private static Connection testConn = null;


    @BeforeClass
    public static void setup() throws DataBaseFailedException {

        dbc.connect();
//        dbc.resetAllTables();
    }

    @AfterClass
    public static void endup() throws DataBaseFailedException {
        dbc.changePassword("admin","123456");
//        dbc.disConnect();
    }


//    @Test
//    public void addPost() throws DataBaseFailedException {
//        dbc.addPost("114", "", "maayan", "דירה שחבל להפסיד", "1");
//        dbc.addPost("124", "", "nof", "דירה מהממת", "2");
//        dbc.addPost("134", "", "nof2", "דירה מהממת", "2");
//        dbc.addPost("144", "", "nof3", "דירה מהממת", "4");
//        dbc.addPost("144", "", "mani", "דירה מדהימה", "5");
//        assertTrue(dbc.getPost("114") != null);
//        assertTrue(dbc.getPost("124") != null);
//        assertTrue(dbc.getPost("134") != null);
//        assertTrue(dbc.getPost("144") != null);
//        assertTrue(dbc.getPost("154") == null);
//        assertEquals("4", dbc.getPost("144").getApartmentID());
//        System.out.println(dbc.GetAllPostsId().toString());
//    }
//
//    @Test
//    public void update() throws DataBaseFailedException {
//        dbc.addPost("4", "", "nof3", "דירה מהממת", "4");
//        dbc.update("4", "", "mani", "דירה מדהימה", "5");
//        assertEquals("5", dbc.getPost("4").getApartmentID());
//        assertEquals("mani", dbc.getPost("4").getPublisherName());
//
//    }

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
    public void getApartmentContacts() throws DataBaseFailedException {
        Address address = new Address("השלום", 20);
        ApartmentLocation location = new ApartmentLocation(address, 3, 4);
        Contact c1 = new Contact("gal", "0545555555");
        Contact c2 = new Contact("Shir", "0546666666");
        Set<Contact> contacts = new HashSet<Contact>();
        contacts.add(c1);
        contacts.add(c2);
        Apartment apartment = new Apartment(location, 1000, contacts);

        dbc.addApartmentDerivatives(apartment,"8");
        int num=dbc.isApartmentExist(apartment);
        assertTrue(num!=-1);

        Set<Contact> contacts2= dbc.getApartmentContacts(num+"");
        for (Contact contact2: contacts ){
            assertTrue("Shir".equals(contact2.getName())||"gal".equals(contact2.getName()));
            assertTrue("0545555555".equals(contact2.getPhone())||"0546666666".equals(contact2.getPhone()));
        }

    }

//    getAddressDetils, getApartmentRecordTBD, getApartmentContacts, addApartmentDerivatives, allResultsFromDB, isApartmentExist

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
    public void getAddressDetils(){
        System.out.println(dbc.getAddressDetils(5).toString());
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
        int num=dbc.isApartmentExist(apartment);
        assertTrue(num!=-1);
        Apartment apartment1= dbc.getApartmentRecordTBD(num+"");
        assertEquals(apartment.getApartmentLocation().getAddress().getStreet(), apartment1.getApartmentLocation().getAddress().getStreet());
        assertTrue(apartment.getApartmentLocation().getAddress().getNumber()==
                apartment1.getApartmentLocation().getAddress().getNumber());
        assertTrue(apartment.getApartmentLocation().getLongitude()==
                apartment1.getApartmentLocation().getLongitude());
        assertTrue(apartment.getApartmentLocation().getLatitude()==
                apartment1.getApartmentLocation().getLatitude());
        assertTrue(apartment.getApartmentLocation().getNeighborhood().equals(apartment1.getApartmentLocation().getNeighborhood()));
        assertTrue(apartment.getApartmentLocation().getFloor()==
                apartment1.getApartmentLocation().getFloor());
        assertTrue(apartment.getNumberOfMates()==
                apartment1.getNumberOfMates());
        assertTrue(apartment.getGarden()==
                apartment1.getGarden());
        assertTrue(apartment.getAnimal()==
                apartment1.getAnimal());
        assertTrue(apartment.getBalcony()==
                apartment1.getBalcony());
        assertTrue(apartment.getProtectedSpace()==
                apartment1.getProtectedSpace());
        assertTrue(apartment.getCost()==
                apartment1.getCost());
        assertTrue(apartment.getSize()==
                apartment1.getSize());
        assertTrue(apartment.getFurniture()==
                apartment1.getFurniture());
        assertTrue(apartment.getNumberOfRooms()==
                apartment1.getNumberOfRooms());

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

    @Test
    public void login(){
        assertTrue(dbc.login("admin","123456"));
        assertFalse(dbc.login("admin","12346"));
        assertFalse(dbc.login("admina","123456"));
    }

    @Test
    public void changePassword(){
        assertTrue(dbc.login("admin","123456"));
        dbc.changePassword("admin","12345");
        assertTrue(dbc.login("admin","12345"));
        assertFalse(dbc.login("admin","123456"));
    }

    @Test
    public void getAllUserSearches() throws DataBaseFailedException {
        dbc.resetSearchRecordsTable();
        dbc.addSearchRecord("t","t","t","tmax","t","tmax","t","tmax","tt","tt","tt",0,1,0,1,0);
        dbc.addSearchRecord("t","n","mm","oomax","c","cmax","tt","emax","tt","tt","tt",0,1,0,1,0);
        List<SearchRecordDTO> searchRecordDTOList= dbc.getAllUserSearches();
    }

    @Test
    public void suggestionChangesApartmentReacord(){
        ReportDTO.Field field= ReportDTO.Field.address;
        AddressDTO addressDTO= new AddressDTO();
        addressDTO.setNeighborhood("ringle");
        addressDTO.setNumOfBuilding(34);
        addressDTO.setStreet("bloom");
        Gson gson= new Gson();
        String te= gson.toJson(addressDTO);
//        ResultRecord("0",field,)
        System.out.println(te);
//        dbc.allResultsFromDB();
//        dbc.suggestionChangesApartmentReacord("0",2,"floor");
//        dbc.suggestionChangesNeighborhood("0","bupr");
    }

    @Test
    public void suggestionChangesApartmentInt(){
        dbc.suggestionChangesApartmentInt("0","floor",2);
    }

    @Test
    public void deleteGroup() throws DataBaseFailedException {
        System.out.println(dbc.userExist("admin"));
        //ALL GROUP METHODS WORKS!!
//        dbc.resetGroupsTable();
//        dbc.insertGroup("1357","matan");
//        dbc.insertGroup("1358","boaz");
//        dbc.insertGroup("1358","viki");
//        List<GroupDTO> groupDTOList= dbc.GetAllGroups();
//        dbc.deleteGroup("1357");
    }
}