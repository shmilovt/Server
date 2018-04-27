package il.ac.bgu.finalproject.server.CommunicationLayer;

import il.ac.bgu.finalproject.server.ServiceLayer.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminCommunicationController {

    @Autowired
    private IService service;





}
