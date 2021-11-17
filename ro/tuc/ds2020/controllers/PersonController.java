package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.consumer.Consumer;
import ro.tuc.ds2020.dtos.*;
import ro.tuc.ds2020.services.MonitoringService;
import ro.tuc.ds2020.services.PersonService;

import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin
@RequestMapping(value = "/person")
public class PersonController {

    private final PersonService personService;
    private final MonitoringService monitoringService;

    @Autowired
    public PersonController(PersonService personService, MonitoringService monitoringService) {
        this.personService = personService;
        this.monitoringService = monitoringService;
    }

    @GetMapping()
    public ResponseEntity<List<PersonDTO>> getPersons() {
        List<PersonDTO> dtos = personService.findPersons();
        for (PersonDTO dto : dtos) {
            Link personLink = linkTo(methodOn(PersonController.class)
                    .getPerson(dto.getId())).withRel("personDetails");
            dto.add(personLink);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UUID> insertPerson(@Valid @RequestBody PersonDetailsDTO personDTO) {
        UUID personID = personService.insert(personDTO);
        return new ResponseEntity<>(personID, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PersonDetailsDTO> getPerson(@PathVariable("id") UUID personId) {
        PersonDetailsDTO dto = personService.findPersonById(personId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<PersonDTO> update(@PathVariable("id") UUID personId, @Valid @RequestBody PersonDetailsDTO personDTO){
        PersonDTO updatePersonDTO = personService.update(personId, personDTO);
        return new ResponseEntity<>(updatePersonDTO, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Map<String, Boolean>> deletePerson(@PathVariable("id") UUID personId){
        Map<String, Boolean> response = personService.deletePerson(personId);
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/connect-device/{id}")
    public ResponseEntity<String> connectSensor(@PathVariable("id") UUID clientId, @Valid @RequestBody  String deviceId){

        String response = personService.connectClientToDevice(clientId,deviceId);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/view-history/{id}")
    public ResponseEntity<List<ViewDTO>> getViewHistory(@PathVariable("id") UUID clientId) {
        List<ViewDTO> viewDTOS = personService.getViewHistory(clientId);
        return new ResponseEntity<>(viewDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/view-today/{id}")
    public ResponseEntity<List<ViewDTO>> getViewToday(@PathVariable("id") UUID clientId) {
        List<ViewDTO> viewDTOS = personService.getViewToday(clientId);
        return new ResponseEntity<>(viewDTOS, HttpStatus.OK);
    }

    @PostMapping(value = "/chart/{id}")
    public ResponseEntity<List<ValuesDTO>> getChart(@PathVariable("id") UUID clientId, @Valid @RequestBody String timeDTO) throws InterruptedException, TimeoutException, IOException {
        System.out.println("Id e: " + clientId);
        System.out.println("Data e: " + timeDTO);

        Consumer consumer = new Consumer(monitoringService);
        consumer.executeMonitorings();

        List<Float> eachDayConsumption = personService.eachDayConsumption(clientId, timeDTO);
        List<ValuesDTO> valuesDTOS = new ArrayList<>();
        for(Float f: eachDayConsumption){
            ValuesDTO valuesDTO = new ValuesDTO();
            valuesDTO.setValue(f);
            valuesDTOS.add(valuesDTO);
        }

        return new ResponseEntity<>(valuesDTOS, HttpStatus.OK);
    }
}
