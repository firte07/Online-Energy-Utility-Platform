package ro.tuc.ds2020.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.*;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import ro.tuc.ds2020.entities.Device;
import ro.tuc.ds2020.entities.Monitoring;
import ro.tuc.ds2020.entities.MonitoringBuffer;
import ro.tuc.ds2020.entities.Sensor;
import ro.tuc.ds2020.repositories.MonitoringRepository;
import ro.tuc.ds2020.repositories.SensorRepository;
import ro.tuc.ds2020.services.MonitoringService;
import ro.tuc.ds2020.services.PersonService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;

public class Consumer {
    private MonitoringService monitoringService;
    private PersonService personService;
    private final UUID id;

    public Consumer(MonitoringService monitoringService, PersonService personService, UUID id) {
        this.monitoringService = monitoringService;
        this.personService = personService;
        this.id = id;
    }

    public void executeMonitorings() throws IOException, TimeoutException, InterruptedException {
        List<Device> devices = personService.getAllDevices(id);
        Sensor sensor = personService.getSensorByDevice(devices.get(0));
        ConnectionFactory factory = new ConnectionFactory();
        ObjectMapper objectMapper = new ObjectMapper();
        Connection connection = factory.newConnection("amqps://kxugqbea:dJi0LV-JfPaOPi4G4XAAvSq2tphVThFF@rat.rmq2.cloudamqp.com/kxugqbea");
        Channel channel = connection.createChannel();
        final int[] i = {0};
        final float[] lastValue = {0};
        channel.basicConsume(sensor.getId_sensor().toString(), true, (consumerTag, message) -> {

            MonitoringBuffer monitoring = objectMapper.readValue(message.getBody(), MonitoringBuffer.class);

            monitoringService.execute(monitoring, lastValue[0]);

            System.out.println(monitoring.toString() +  "  " + i[0]);
            i[0]++;
        }, consumerTag -> {
        });

    }
}
