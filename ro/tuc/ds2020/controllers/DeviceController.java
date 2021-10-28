package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.DeviceDTO;
import ro.tuc.ds2020.dtos.DeviceSensorDTO;
import ro.tuc.ds2020.dtos.builders.DeviceBuilder;
import ro.tuc.ds2020.entities.Device;
import ro.tuc.ds2020.services.DeviceService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin
@RequestMapping(value = "/device")
public class DeviceController {

    private final DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService){ this.deviceService = deviceService;}

    @GetMapping()
    public ResponseEntity<List<DeviceDTO>> getDevices() {
        List<DeviceDTO> deviceDTOList = deviceService.findDevices();
        for (DeviceDTO deviceDTO : deviceDTOList) {
            Link deviceLink = linkTo(methodOn(DeviceController.class)
                    .getDevice(deviceDTO.getId())).withRel("device");
            deviceDTO.add(deviceLink);
        }
        return new ResponseEntity<>(deviceDTOList, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DeviceDTO> getDevice(@PathVariable("id") UUID deviceId) {
        DeviceDTO deviceDTO= deviceService.findDeviceById(deviceId);
        return new ResponseEntity<>(deviceDTO, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UUID> insertDevice(@Valid @RequestBody DeviceDTO deviceDTO) {
        UUID deviceId = deviceService.insert(deviceDTO);
        return new ResponseEntity<>(deviceId, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<DeviceDTO> update(@PathVariable("id") UUID deviceId,@Valid @RequestBody DeviceDTO deviceDTO){
        DeviceDTO updateDeviceDTO = deviceService.update(deviceId, deviceDTO);
        return new ResponseEntity<>(updateDeviceDTO, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteDevice(@PathVariable("id") UUID deviceId){
        Map<String, Boolean> response = deviceService.deleteDevice(deviceId);
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/connect-sensor/{id}")
    public ResponseEntity<String> connectSensor(@PathVariable("id") UUID deviceId, @Valid @RequestBody  String sensorId){

        String response = deviceService.connectSensorToDevice(deviceId,sensorId);
        return ResponseEntity.ok(response);

    }
}
