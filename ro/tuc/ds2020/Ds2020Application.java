package ro.tuc.ds2020;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.annotation.Validated;
import ro.tuc.ds2020.controllers.RegistrationController;
import ro.tuc.ds2020.dtos.CredentialDTO;
import ro.tuc.ds2020.dtos.DeviceSensorDTO;
import ro.tuc.ds2020.dtos.PersonDTO;
import ro.tuc.ds2020.dtos.PersonDetailsDTO;
import ro.tuc.ds2020.repositories.*;
import ro.tuc.ds2020.services.CredentialService;
import ro.tuc.ds2020.services.DeviceService;
import ro.tuc.ds2020.services.PersonService;
import ro.tuc.ds2020.services.SensorService;

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
                                         MonitoringRepository monitoringRepository) {
        return args -> {
            PersonService personService = new PersonService(personRepository, deviceRepository, sensorRepository, monitoringRepository);
            personService.getViewHistory(UUID.fromString("574589db-b208-4ac8-aee0-70e2f9c7aef8"));
          // CredentialService credentialService = new CredentialService(credentialRepository, personRepository);
          // System.out.println(credentialService.getIdClientAfterRegistration("ion"));
          //DeviceService deviceService = new DeviceService(deviceRepository,sensorRepository);

          //  deviceService.connectSensorToDevice(UUID.fromString("6eaa358a-a642-445a-99d1-49bdba61eae4"),
          //          UUID.fromString("45774962-e6f7-41f6-b940-72ef63fa1943") );*/

        };
   }
}
