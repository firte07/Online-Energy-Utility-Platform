package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.DeviceDTO;
import ro.tuc.ds2020.dtos.builders.DeviceBuilder;
import ro.tuc.ds2020.entities.Device;
import ro.tuc.ds2020.entities.Person;
import ro.tuc.ds2020.repositories.DeviceRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DeviceService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeviceService.class);
    private final DeviceRepository deviceRepository;

    @Autowired
    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
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

    public void updateAverageConsumption(UUID deviceId, float newAverageConsumption){
        Optional<Device> deviceOptional = deviceRepository.findById(deviceId);
        if(!deviceOptional.isPresent()){
            LOGGER.error("Device with id {} was not found in db", deviceId);
            throw new ResourceNotFoundException(Device.class.getSimpleName() + " with id: " + deviceId);
        }
        deviceRepository.updateAverageConsumption(newAverageConsumption, deviceId);
    }

    public void deleteDeviceById(UUID deviceId) {
        Optional<Device> deviceOptional = deviceRepository.findById(deviceId);
        if(!deviceOptional.isPresent()){
            LOGGER.error("Device with id {} was not found in db", deviceId);
            throw new ResourceNotFoundException(Device.class.getSimpleName() + " with id: " + deviceId);
        }
        deviceRepository.deleteDeviceById(deviceId);
    }
}
