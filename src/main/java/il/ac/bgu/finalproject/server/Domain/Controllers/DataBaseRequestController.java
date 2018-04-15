package il.ac.bgu.finalproject.server.Domain.Controllers;

import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Apartment;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Post;
import il.ac.bgu.finalproject.server.PersistenceLayer.DataBaseConnection;
import il.ac.bgu.finalproject.server.PersistenceLayer.DataBaseConnectionInterface;

public class DataBaseRequestController {
    public DataBaseConnectionInterface dataBaseConnectionInterface;

    public DataBaseRequestController() {
        dataBaseConnectionInterface = new DataBaseConnection();
    }

    public void manageApartment(Apartment apartment, Post post) {
        if( dataBaseConnectionInterface.isApartmentExist(apartment)){
            dataBaseConnectionInterface.updateApartment(apartment, post.getID());
        }
        else{
           // dataBaseConnectionInterface.addApartmentRecord()
        }

        }


}
