package il.ac.bgu.finalproject.server.CommunicationLayer;

import il.ac.bgu.finalproject.server.CommunicationLayer.DTOs.Reply;
import il.ac.bgu.finalproject.server.ServiceLayer.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AdminCommunicationController {

    @Autowired
    private IService service;

    @RequestMapping(value = "/login" , method = {RequestMethod.POST, RequestMethod.GET})
    public Reply login(@RequestParam Map<String, String> requestParams){
        String username=requestParams.get("username");
        String password=requestParams.get("password");
        return service.login(username, password);

    }



}
