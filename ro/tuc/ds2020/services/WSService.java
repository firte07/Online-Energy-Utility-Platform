package ro.tuc.ds2020.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.entities.Notification;

@Service
public class WSService {

    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public WSService(SimpMessagingTemplate simpMessagingTemplate){
        this.messagingTemplate = simpMessagingTemplate;
    }

    public void notifyFrontend(String message){
        Notification notification = new Notification(message + "ciolan");

        messagingTemplate.convertAndSend("/topic/messages", notification);
    }
}
