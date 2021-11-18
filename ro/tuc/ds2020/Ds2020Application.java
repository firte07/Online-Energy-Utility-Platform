package ro.tuc.ds2020;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.annotation.Validated;
import ro.tuc.ds2020.consumer.Consumer;
import ro.tuc.ds2020.controllers.RegistrationController;
import ro.tuc.ds2020.dtos.*;
import ro.tuc.ds2020.entities.MonitoringBuffer;
import ro.tuc.ds2020.repositories.*;
import ro.tuc.ds2020.services.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;

@SpringBootApplication
@Validated
public class Ds2020Application extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Ds2020Application.class);
    }

    public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        SpringApplication.run(Ds2020Application.class, args);
    }

    @Bean
    public CommandLineRunner mappingDemo(DeviceRepository deviceRepository, PersonRepository personRepository,
                                         CredentialRepository credentialRepository, SensorRepository sensorRepository,
                                         MonitoringRepository monitoringRepository, ApplicationEventPublisher eventPublisher) {
        return args -> {
           Consumer consumer = new Consumer(new MonitoringService(monitoringRepository, sensorRepository, eventPublisher));
           //consumer.executeMonitorings();

        /*    PersonService personService = new PersonService(personRepository, deviceRepository, sensorRepository, monitoringRepository);
            LocalDateTime localDateTime = LocalDateTime.of(2021, 10, 28, 10, 0);
            TimeDTO timeDTO = new TimeDTO();
            timeDTO.setTemp(localDateTime);
            personService.eachDayConsumption(UUID.fromString("f9632d91-121a-45c1-b8e5-575e820b451b"), timeDTO);
      */    // CredentialService credentialService = new CredentialService(credentialRepository, personRepository);
          // System.out.println(credentialService.getIdClientAfterRegistration("ion"));
          //DeviceService deviceService = new DeviceService(deviceRepository,sensorRepository);

          //  deviceService.connectSensorToDevice(UUID.fromString("6eaa358a-a642-445a-99d1-49bdba61eae4"),
          //          UUID.fromString("45774962-e6f7-41f6-b940-72ef63fa1943") );*/

        };
   }
}
