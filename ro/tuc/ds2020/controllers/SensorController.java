package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.PersonDTO;
import ro.tuc.ds2020.dtos.PersonDetailsDTO;
import ro.tuc.ds2020.dtos.SensorDTO;
import ro.tuc.ds2020.entities.Sensor;
import ro.tuc.ds2020.services.SensorService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin
@RequestMapping(value = "/sensor")
public class SensorController {
    private final SensorService sensorService;

    @Autowired
    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @GetMapping()
    public ResponseEntity<List<SensorDTO>> getSensors() {
        List<SensorDTO> sensorDTOS = sensorService.findSensors();
        for (SensorDTO sensorDTO : sensorDTOS) {
            Link sensorLink = linkTo(methodOn(SensorController.class)
                    .getSensor(sensorDTO.getId())).withRel("sensorDetails");
            sensorDTO.add(sensorLink);
        }
        return new ResponseEntity<>(sensorDTOS, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UUID> insertSensor(@Valid @RequestBody SensorDTO sensorDTO) {
        UUID sensorId = sensorService.insertSensor(sensorDTO);
        return new ResponseEntity<>(sensorId, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<SensorDTO> getSensor(@PathVariable("id") UUID sensorId) {
        SensorDTO sensorDTO = sensorService.findSensorById(sensorId);
        return new ResponseEntity<>(sensorDTO, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<SensorDTO> update(@PathVariable("id") UUID sensorId, @Valid @RequestBody SensorDTO sensorDTO){
        SensorDTO updateSensorDTO = sensorService.update(sensorId, sensorDTO);
        return new ResponseEntity<>(updateSensorDTO, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void deleteSensor(@PathVariable("id") UUID sensorId){
        sensorService.deleteSensor(sensorId);
    }
}
