package ro.tuc.ds2020.controllers;


import jdk.nashorn.internal.ir.RuntimeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.PersonDTO;
import ro.tuc.ds2020.dtos.PersonDetailsDTO;
import ro.tuc.ds2020.services.PersonService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin
@RequestMapping(value = "/person")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
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
    public ResponseEntity<UUID> insertProsumer(@Valid @RequestBody PersonDetailsDTO personDTO) {
        UUID personID = personService.insert(personDTO);
        return new ResponseEntity<>(personID, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PersonDetailsDTO> getPerson(@PathVariable("id") UUID personId) {
        PersonDetailsDTO dto = personService.findPersonById(personId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    /*@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public void updateAddress(@PathVariable("id") UUID personId, @RequestBody String newAddress){
        personService.updateAddress(personId, newAddress);
    }*/

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public void updateNameById(@PathVariable("id") UUID personId, @RequestBody String newName){
        personService.updateNameById(personId, newName);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void deletePerson(@PathVariable("id") UUID personId){
        personService.deletePersonById(personId);
    }
}
