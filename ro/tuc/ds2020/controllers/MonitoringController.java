package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.consumer.Consumer;
import ro.tuc.ds2020.dtos.MonitoringDTO;
import ro.tuc.ds2020.dtos.PersonDTO;
import ro.tuc.ds2020.services.MonitoringService;
import ro.tuc.ds2020.services.PersonService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin
@RequestMapping(value = "/monitoring")
public class MonitoringController {

    private final MonitoringService monitoringService;
    private final Consumer consumer;

    @Autowired
    public MonitoringController(MonitoringService monitoringService, Consumer consumer) {
        this.monitoringService = monitoringService;
        this.consumer = consumer;
    }

    @GetMapping()
    public ResponseEntity<List<MonitoringDTO>> getMonitorings() {
        List<MonitoringDTO> dtos = monitoringService.findMonitorings();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping(value = "/start/{id}")
    public ResponseEntity<String> activateMonitoring(@PathVariable("id") UUID clientId) throws URISyntaxException, KeyManagementException, TimeoutException, NoSuchAlgorithmException, IOException {
        consumer.executeMonitorings(clientId);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
