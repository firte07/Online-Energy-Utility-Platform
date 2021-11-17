package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.CredentialDTO;
import ro.tuc.ds2020.dtos.PersonDTO;
import ro.tuc.ds2020.dtos.PersonDetailsDTO;
import ro.tuc.ds2020.services.CredentialService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin
@RequestMapping(value = "/credential")
public class CredentialController {
    private final CredentialService credentialService;

    @Autowired
    public CredentialController(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @GetMapping()
    public ResponseEntity<List<CredentialDTO>> getCredentials() {
        List<CredentialDTO> credentialDTOS = credentialService.findCredentials();
        return new ResponseEntity<>(credentialDTOS, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UUID> insertCredential(@Valid @RequestBody CredentialDTO credentialDTO) {
        UUID crdentialId = credentialService.insert(credentialDTO);
        return new ResponseEntity<>(crdentialId, HttpStatus.CREATED);
    }

}
