package utcn;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.*;
import com.sun.jndi.cosnaming.CNCtx;
import entity.Monitoring;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

public class Consumer {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        ObjectMapper objectMapper = new ObjectMapper();

        Connection connection = factory.newConnection("amqps://kxugqbea:dJi0LV-JfPaOPi4G4XAAvSq2tphVThFF@rat.rmq2.cloudamqp.com/kxugqbea");
        Channel channel = connection.createChannel();

        channel.basicConsume("Queue", true, (consumerTag, message) -> {

            Monitoring monitoring = objectMapper.readValue(message.getBody(), Monitoring.class);
            System.out.println("Received: " + monitoring.toString());
        }, consumerTag -> {

        });

    }
}
