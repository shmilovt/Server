package il.ac.bgu.finalproject.server.Domain.Controllers;

import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Apartment;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Post;
import il.ac.bgu.finalproject.server.Domain.NLPHandlers.NLPImp;
import il.ac.bgu.finalproject.server.Domain.NLPHandlers.NLPInterface;

import java.util.concurrent.LinkedBlockingQueue;

public class NLPController {
    private NLPInterface nlp;
    private DataBaseRequestController dataBaseRequestController;

    public NLPController(){
        nlp = new NLPImp();
        dataBaseRequestController = new DataBaseRequestController();

    }




    public void generateNLP(Post post) {
        Apartment apartment = nlp.extractApartment(post.getText());
        dataBaseRequestController.manageApartment(apartment, post);



    }
}
