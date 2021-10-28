package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.DeviceDTO;
import ro.tuc.ds2020.dtos.DeviceSensorDTO;
import ro.tuc.ds2020.dtos.builders.DeviceBuilder;
import ro.tuc.ds2020.dtos.builders.SensorBuilder;
import ro.tuc.ds2020.entities.Device;
import ro.tuc.ds2020.entities.Person;
import ro.tuc.ds2020.entities.Sensor;
import ro.tuc.ds2020.repositories.DeviceRepository;
import ro.tuc.ds2020.repositories.SensorRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DeviceService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeviceService.class);
    private final DeviceRepository deviceRepository;
    private final SensorRepository sensorRepository;

    @Autowired
    public DeviceService(DeviceRepository deviceRepository, SensorRepository sensorRepository) {
        this.deviceRepository = deviceRepository;
        this.sensorRepository = sensorRepository;
    }

    public List<DeviceDTO> findDevices(){
        List<Device> deviceList = deviceRepository.findAll();
        return deviceList.stream()
                .map(DeviceBuilder::toDeviceDTO)
                .collect(Collectors.toList());
    }

    public DeviceDTO findDeviceById(UUID id){
        Optional<Device> deviceOptional = deviceRepository.findById(id);
        if(!deviceOptional.isPresent()){
            LOGGER.error("Device with id {} was not found in db", id);
            throw new ResourceNotFoundException(Device.class.getSimpleName()+ " with id: " + id);
        }
        return DeviceBuilder.toDeviceDTO(deviceOptional.get());
    }

    public UUID insert(DeviceDTO deviceDTO){
        Device device = DeviceBuilder.toEntity(deviceDTO);
        device = deviceRepository.save(device);
        LOGGER.debug("Device with id {} was inserted in db", device.getId_device());
        return device.getId_device();
    }

    public DeviceDTO update(UUID deviceId, DeviceDTO deviceDTO){
        Optional<Device> deviceOptional = deviceRepository.findById(deviceId);
        if(!deviceOptional.isPresent()){
            LOGGER.error("Device with id {} was not found in db", deviceId);
            throw new ResourceNotFoundException(Device.class.getSimpleName() + " with id: " + deviceId);
        }
        deviceOptional.get().setMaxEnergy(deviceDTO.getMaxEnergy());
        deviceOptional.get().setAverageConsumption(deviceDTO.getAverageConsumption());
        deviceOptional.get().setDescription(deviceDTO.getDescription());
        deviceOptional.get().setLocation(deviceDTO.getLocation());

        Device updateDevice = deviceRepository.save(deviceOptional.get());

        return DeviceBuilder.toDeviceDTO(updateDevice);
    }

    public Map<String, Boolean> deleteDevice(UUID deviceId) {
        Optional<Device> deviceOptional = deviceRepository.findById(deviceId);
        if(!deviceOptional.isPresent()){
            LOGGER.error("Device with id {} was not found in db", deviceId);
            throw new ResourceNotFoundException(Device.class.getSimpleName() + " with id: " + deviceId);
        }
        deviceRepository.delete(deviceOptional.get());
        return new HashMap<>();
    }

    public String connectSensorToDevice(UUID deviceId, String sensorId){
        StringBuffer sb = new StringBuffer(sensorId);
        sb.deleteCharAt(sb.length()-1);
        sensorId = String.valueOf(sb);

        Device device = deviceRepository.findById(deviceId).get();
        Sensor sensor = sensorRepository.findById(UUID.fromString(sensorId)).get();
        device.setSensor(sensor);
        sensor.setDevice(device);
        sensorRepository.save(sensor);
        return "Successfully connected device to sensor!";
    }
}
