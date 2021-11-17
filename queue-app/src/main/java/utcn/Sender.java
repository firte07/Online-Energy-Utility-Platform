package utcn;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.sun.xml.internal.org.jvnet.fastinfoset.stax.LowLevelFastInfosetStreamWriter;
import entity.Monitoring;
import reader.FileReader;
import reader.ValueReader;
import service.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Sender {

    public static void main(String[] args) throws IOException, TimeoutException {
        FileReader fileReader = new FileReader();
        ValueReader valueReader = new ValueReader();
        Service service = new Service();

        UUID id = fileReader.readSensorId();
        List<String> valuesString = valueReader.readValues();
        List<Float> values = service.values(valuesString);

        ConnectionFactory factory = new ConnectionFactory();

        try(Connection connection = factory.newConnection("amqps://kxugqbea:dJi0LV-JfPaOPi4G4XAAvSq2tphVThFF@rat.rmq2.cloudamqp.com/kxugqbea")) {
            Channel channel = connection.createChannel();
            channel.queueDeclare("Queue", false, false, false, null);

            int i=0;
            long addTime = 0;
            while(i<values.size()-1){
                Monitoring monitoring = new Monitoring();
                monitoring.setIdSensor(id);
                monitoring.setValue(values.get(i));

                LocalDateTime currentDateTime = LocalDateTime.now().plusHours(addTime);
                addTime +=1;
                System.out.println(currentDateTime);
                DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
                String formattedDateTime = currentDateTime.format(formatter);

                monitoring.setTemp(formattedDateTime);

                i++;

                ObjectMapper objectMapper = new ObjectMapper()
                        .registerModule(new ParameterNamesModule())
                        .registerModule(new Jdk8Module())
                        .registerModule(new JavaTimeModule());
                String json = objectMapper.writeValueAsString(monitoring);

                channel.basicPublish("", "Queue", false, null, json.getBytes());
                System.out.println("Message has been sent!");
                TimeUnit.SECONDS.sleep(2);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
