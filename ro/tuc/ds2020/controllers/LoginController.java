package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.CredentialDTO;
import ro.tuc.ds2020.dtos.PersonDTO;
import ro.tuc.ds2020.services.CredentialService;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin
@RequestMapping(value = "/")
public class LoginController {
    private final CredentialService credentialService;

    @Autowired
    public LoginController(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @PutMapping()
    public ResponseEntity<String> executeLogin(@Valid @RequestBody CredentialDTO credentialDTO) {
        return new ResponseEntity<>(credentialService.executeLogin(credentialDTO), HttpStatus.OK);
    }
}
