package il.ac.bgu.finalproject.server.AcceptanceTests;


import il.ac.bgu.finalproject.server.CommunicationLayer.DTOs.Reply;
import il.ac.bgu.finalproject.server.ServiceLayer.IService;
import il.ac.bgu.finalproject.server.ServiceLayer.Service;

public class ServiceConnector {

    private IService bridge;

    public ServiceConnector(){
        bridge = new Service();
    }


    public Reply login(String username, String password) {
        return bridge.login(username, password);
    }






}
