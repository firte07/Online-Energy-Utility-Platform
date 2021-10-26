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
import ro.tuc.ds2020.dtos.PersonDTO;
import ro.tuc.ds2020.dtos.PersonDetailsDTO;
import ro.tuc.ds2020.repositories.CredentialRepository;
import ro.tuc.ds2020.repositories.PersonRepository;
import ro.tuc.ds2020.services.CredentialService;
import ro.tuc.ds2020.services.PersonService;

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

    /*@Bean
    public CommandLineRunner mappingDemo(CredentialRepository credentialRepository,
                                         PersonRepository personRepository) {
        return args -> {

            CredentialService credentialService = new CredentialService(credentialRepository, personRepository);
            PersonService personService = new PersonService(personRepository);
            CredentialDTO credentialDTO = new CredentialDTO(UUID.randomUUID(), "macanache2", "12345",
                    "client");
            PersonDetailsDTO personDTO = new PersonDetailsDTO(UUID.randomUUID(), "Ionut2", "ange2", 21);

            credentialService.insertCredential(credentialDTO, personDTO, "12345");

            RegistrationController registrationController = new RegistrationController(personService, credentialService);
            registrationController.register(credentialDTO, personDTO, "12345");
        };
   }*/
}
