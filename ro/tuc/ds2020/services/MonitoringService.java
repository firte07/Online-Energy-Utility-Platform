package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.dtos.DeviceDTO;
import ro.tuc.ds2020.dtos.MonitoringDTO;
import ro.tuc.ds2020.dtos.PersonDTO;
import ro.tuc.ds2020.dtos.builders.DeviceBuilder;
import ro.tuc.ds2020.dtos.builders.MonitoringBuilder;
import ro.tuc.ds2020.dtos.builders.PersonBuilder;
import ro.tuc.ds2020.entities.*;
import ro.tuc.ds2020.repositories.MonitoringRepository;
import ro.tuc.ds2020.repositories.SensorRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MonitoringService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeviceService.class);
    private final MonitoringRepository monitoringRepository;
    private final SensorRepository sensorRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public MonitoringService(MonitoringRepository monitoringRepository, SensorRepository sensorRepository, ApplicationEventPublisher eventPublisher) {
        this.monitoringRepository = monitoringRepository;
        this.sensorRepository = sensorRepository;
        this.eventPublisher = eventPublisher;
    }

    public void execute(MonitoringBuffer monitoringBuffer, float lastValue){
        Optional<Sensor> sensor = sensorRepository.findById(monitoringBuffer.getIdSensor());
        float peak = sensor.get().getMaxValue();
        float actualValue = monitoringBuffer.getValue() - lastValue;

        if(peak<actualValue) {
            System.out.println("Max value sensor = " +peak);
            System.out.println("Peak = " +actualValue);
            eventPublisher.publishEvent(new Notification("Maximum value exceeded!!!"));
        }
        //2022-05-14T22:00
        Monitoring monitoring = new Monitoring();
        //String buffer = monitoringBuffer.getTemp().substring(0,10) + " " + monitoringBuffer.getTemp().substring(11,  monitoringBuffer.getTemp().length() - 4);
        String buffer = monitoringBuffer.getTemp().substring(0,10) + " " + monitoringBuffer.getTemp().substring(11);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime dateTime = LocalDateTime.parse(buffer, formatter);

        monitoring.setTemp(dateTime);
        monitoring.setValue(monitoringBuffer.getValue());
        monitoring.setSensor(sensor.get());

        monitoringRepository.save(monitoring);
    }

    public List<MonitoringDTO> findMonitorings() {
        List<Monitoring> monitorings = monitoringRepository.findAll();
        return monitorings.stream()
                .map(MonitoringBuilder::toMonitoringDTO)
                .collect(Collectors.toList());
    }


}
