package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.CredentialDTO;
import ro.tuc.ds2020.dtos.PersonDTO;
import ro.tuc.ds2020.dtos.PersonDetailsDTO;
import ro.tuc.ds2020.dtos.RegisterDTO;
import ro.tuc.ds2020.services.CredentialService;
import ro.tuc.ds2020.services.PersonService;
import javax.validation.Valid;

import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@CrossOrigin
@RequestMapping(value = "/registration")
public class RegistrationController {

    private final CredentialService credentialService;

    @Autowired
    public RegistrationController(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @PostMapping()
    public ResponseEntity<PersonDTO> register(@Valid @RequestBody RegisterDTO registerDTO){
        CredentialDTO credentialDTO = new CredentialDTO();
        credentialDTO.setUsername(registerDTO.getUsername());
        credentialDTO.setPassword(registerDTO.getPassword());
        credentialDTO.setRole(registerDTO.getRole());

        PersonDetailsDTO personDTO = new PersonDetailsDTO();
        personDTO.setName(registerDTO.getName());
        personDTO.setAddress(registerDTO.getAddress());
        personDTO.setAge(registerDTO.getAge());

        PersonDTO registerPersonDTO = credentialService.insertCredential(credentialDTO, personDTO);
        return new ResponseEntity<>(registerPersonDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/find-id")
    public ResponseEntity<UUID> getIdClientAfterRegistration(@Valid @RequestBody CredentialDTO credentialDTO){
        System.out.println("Aici username:   " + credentialDTO.getUsername());

        return new ResponseEntity<>(credentialService.getIdClientAfterRegistration(credentialDTO.getUsername()),HttpStatus.OK);
    }
}
