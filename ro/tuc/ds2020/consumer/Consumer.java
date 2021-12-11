package ro.tuc.ds2020.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.*;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import ro.tuc.ds2020.entities.Device;
import ro.tuc.ds2020.entities.Monitoring;
import ro.tuc.ds2020.entities.MonitoringBuffer;
import ro.tuc.ds2020.entities.Sensor;
import ro.tuc.ds2020.repositories.MonitoringRepository;
import ro.tuc.ds2020.repositories.SensorRepository;
import ro.tuc.ds2020.services.MonitoringService;
import ro.tuc.ds2020.services.PersonService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class Consumer {
    private MonitoringService monitoringService;
    private PersonService personService;

    @Autowired
    public Consumer(MonitoringService monitoringService, PersonService personService) {
        this.monitoringService = monitoringService;
        this.personService = personService;
    }

    public void executeMonitorings(UUID id) throws IOException, TimeoutException, NoSuchAlgorithmException, KeyManagementException, URISyntaxException {
        List<Device> devices = personService.getAllDevices(id);
        Sensor sensor = personService.getSensorByDevice(devices.get(0));

        ConnectionFactory factory = new ConnectionFactory();
        ObjectMapper objectMapper = new ObjectMapper();

        factory.setHost("rattlesnake.rmq.cloudamqp.com\n");
        factory.setPort(1883);
        factory.setUri("amqps://mzaojrwo:2EmBdThADmNQysvKOP1NG_8fbs3IBvgl@rattlesnake.rmq.cloudamqp.com/mzaojrwo");

        Connection connection = factory.newConnection();
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
