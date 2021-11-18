package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ro.tuc.ds2020.entities.Message;
import ro.tuc.ds2020.services.WSService;

@RestController
public class WSController {

    private WSService service;

    @PostMapping("/send-message")
    public void sendMessage(@RequestBody Message message){
        service.notifyFrontend(message.getMessageContent());
    }
}
