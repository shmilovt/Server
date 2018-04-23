package il.ac.bgu.finalproject.server.CommunicationLayer;

import il.ac.bgu.finalproject.server.CommunicationLayer.DTOs.*;
import il.ac.bgu.finalproject.server.ServiceLayer.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class RegularClientCommunicationController {

    @Autowired
    private IService service;

    @Autowired
    private Converter converter;


    @RequestMapping(value = "/searchApartments" , method = {RequestMethod.POST, RequestMethod.GET})
    public String searchApartments(@RequestParam String userSearchDTOString){



        SearchResultsDTO searchResultsDTO = new SearchResultsDTO();
        String jsonString = searchResultsDTO.toJson();
        return  jsonString;
    }



    @RequestMapping(value = "/hello" , method = {RequestMethod.POST, RequestMethod.GET})
    public String hello(@RequestParam String name){

        return "hello "+name+" !";
    }

}
