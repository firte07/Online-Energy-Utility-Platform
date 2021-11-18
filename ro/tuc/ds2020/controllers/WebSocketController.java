package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;
import ro.tuc.ds2020.dtos.TimeStringDTO;
import ro.tuc.ds2020.entities.Message;
import ro.tuc.ds2020.entities.Notification;

@RestController
public class WebSocketController {

    /*@MessageMapping("/message")
    @SendTo("/topic/messages")
    public Notification getMessage(Message messageReceived) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Notification(HtmlUtils.htmlEscape(messageReceived.getMessageContent()));
    }*/

    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public WebSocketController(SimpMessagingTemplate simpMessagingTemplate){
        this.messagingTemplate = simpMessagingTemplate;
    }

    @EventListener(Notification.class)
    public void handleEvent(Notification notification) {
        messagingTemplate.convertAndSend("/topic/notifications", notification);
    }
}
