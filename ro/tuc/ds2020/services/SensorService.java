package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.PersonDTO;
import ro.tuc.ds2020.dtos.PersonDetailsDTO;
import ro.tuc.ds2020.dtos.SensorDTO;
import ro.tuc.ds2020.dtos.builders.PersonBuilder;
import ro.tuc.ds2020.dtos.builders.SensorBuilder;
import ro.tuc.ds2020.entities.Credential;
import ro.tuc.ds2020.entities.Person;
import ro.tuc.ds2020.entities.Sensor;
import ro.tuc.ds2020.repositories.PersonRepository;
import ro.tuc.ds2020.repositories.SensorRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SensorService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SensorService.class);
    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public List<SensorDTO> findSensors() {
        List<Sensor> sensors = sensorRepository.findAll();
        return sensors.stream()
                .map(SensorBuilder::toSensorDTO)
                .collect(Collectors.toList());
    }

    public SensorDTO findSensorById(UUID id){
        Optional<Sensor> sensorOptional = sensorRepository.findById(id);
        if (!sensorOptional.isPresent()) {
            LOGGER.error("Sensor with id {} was not found in db", id);
            throw new ResourceNotFoundException(Sensor.class.getSimpleName() + " with id: " + id);
        }
        return SensorBuilder.toSensorDTO(sensorOptional.get());
    }

    public UUID insertSensor(SensorDTO sensorDTO) {
        Sensor sensor = SensorBuilder.toEntity(sensorDTO);
        sensor = sensorRepository.save(sensor);
        LOGGER.debug("Sensor with id {} was inserted in db", sensor.getId_sensor());
        return sensor.getId_sensor();
    }

    public SensorDTO update(UUID sensorId, SensorDTO sensorDTO) {
        Optional<Sensor> sensor = sensorRepository.findById(sensorId);
        if(!sensor.isPresent()){
            LOGGER.error("Sensor with id {} was not found in db", sensorId);
            throw new ResourceNotFoundException(Sensor.class.getSimpleName() + " with id: " + sensorId);
        }
        sensor.get().setDescription(sensorDTO.getDescription());
        sensor.get().setMaxValue(sensorDTO.getMaxValue());

        Sensor updateSensor = sensorRepository.save(sensor.get());

        return SensorBuilder.toSensorDTO(updateSensor);
    }

    public Map<String, Boolean> deleteSensor(UUID sensorId) {
        Optional<Sensor> sensor = sensorRepository.findById(sensorId);
        if(!sensor.isPresent()){
            LOGGER.error("Sensor with id {} was not found in db", sensorId);
            throw new ResourceNotFoundException(Sensor.class.getSimpleName() + " with id: " + sensorId);
        }
        sensorRepository.delete(sensor.get());
        return new HashMap<>();
    }


}
