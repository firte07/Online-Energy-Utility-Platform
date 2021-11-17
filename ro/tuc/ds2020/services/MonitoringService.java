package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.dtos.DeviceDTO;
import ro.tuc.ds2020.dtos.builders.DeviceBuilder;
import ro.tuc.ds2020.dtos.builders.MonitoringBuilder;
import ro.tuc.ds2020.entities.Device;
import ro.tuc.ds2020.entities.Monitoring;
import ro.tuc.ds2020.entities.MonitoringBuffer;
import ro.tuc.ds2020.entities.Sensor;
import ro.tuc.ds2020.repositories.MonitoringRepository;
import ro.tuc.ds2020.repositories.SensorRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

@Service
public class MonitoringService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeviceService.class);
    private final MonitoringRepository monitoringRepository;
    private final SensorRepository sensorRepository;

    @Autowired
    public MonitoringService(MonitoringRepository monitoringRepository, SensorRepository sensorRepository) {
        this.monitoringRepository = monitoringRepository;
        this.sensorRepository = sensorRepository;
    }

    public void execute(MonitoringBuffer monitoringBuffer, float lastValue){
        Optional<Sensor> sensor = sensorRepository.findById(monitoringBuffer.getIdSensor());
        float peak = sensor.get().getMaxValue();
        float actualValue = monitoringBuffer.getValue() - lastValue;

        if(peak<actualValue) {
            System.out.println("Peak = " +peak);
            System.out.println("Actual value = " +actualValue);
            System.out.println("BAAAAAAA ");
        }

        Monitoring monitoring = new Monitoring();
        String buffer = monitoringBuffer.getTemp().substring(0,10) + " " + monitoringBuffer.getTemp().substring(11,  monitoringBuffer.getTemp().length() - 4);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(buffer, formatter);

        monitoring.setTemp(dateTime);
        monitoring.setValue(monitoringBuffer.getValue());
        monitoring.setSensor(sensor.get());

        monitoringRepository.save(monitoring);
    }

    /*public UUID insert(Monitoring deviceDTO){
        Device device = DeviceBuilder.toEntity(deviceDTO);
        device = deviceRepository.save(device);
        LOGGER.debug("Device with id {} was inserted in db", device.getId_device());
        return device.getId_device();
    }*/


}
