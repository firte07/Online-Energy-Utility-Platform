package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.PersonDTO;
import ro.tuc.ds2020.dtos.PersonDetailsDTO;
import ro.tuc.ds2020.dtos.builders.PersonBuilder;
import ro.tuc.ds2020.entities.Device;
import ro.tuc.ds2020.entities.Person;
import ro.tuc.ds2020.repositories.DeviceRepository;
import ro.tuc.ds2020.repositories.PersonRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PersonService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);
    private final PersonRepository personRepository;
    private final DeviceRepository deviceRepository;

    @Autowired
    public PersonService(PersonRepository personRepository, DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
        this.personRepository = personRepository;
    }

    public List<PersonDTO> findPersons() {
        List<Person> personList = personRepository.findAll();
        return personList.stream()
                .map(PersonBuilder::toPersonDTO)
                .collect(Collectors.toList());
    }

    public PersonDetailsDTO findPersonById(UUID id) {
        Optional<Person> prosumerOptional = personRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Person with id {} was not found in db", id);
            throw new ResourceNotFoundException(Person.class.getSimpleName() + " with id: " + id);
        }
        return PersonBuilder.toPersonDetailsDTO(prosumerOptional.get());
    }

    public UUID insert(PersonDetailsDTO personDTO) {
        Person person = PersonBuilder.toEntity(personDTO);
        person = personRepository.save(person);
        LOGGER.debug("Person with id {} was inserted in db", person.getId());
        return person.getId();
    }

    public PersonDTO update(UUID personId, PersonDetailsDTO personDTO) {
        Optional<Person> person = personRepository.findById(personId);
        if(!person.isPresent()){
            LOGGER.error("Person with id {} was not found in db", personId);
            throw new ResourceNotFoundException(Person.class.getSimpleName() + " with id: " + personId);
        }
        person.get().setAddress(personDTO.getAddress());
        person.get().setAge(personDTO.getAge());
        person.get().setName(personDTO.getName());

        Person updatedPerson = personRepository.save(person.get());

        return PersonBuilder.toPersonDTO(updatedPerson);
    }

    public Map<String, Boolean> deletePerson(UUID personId) {
        Optional<Person> person = personRepository.findById(personId);
        if(!person.isPresent()){
            LOGGER.error("Person with id {} was not found in db", personId);
            throw new ResourceNotFoundException(Person.class.getSimpleName() + " with id: " + personId);
        }
        personRepository.delete(person.get());
        return new HashMap<>();
    }

    public String connectClientToDevice(UUID idClient, String deviceId){
        System.out.println("Id client    "+idClient);
        System.out.println("Id device    "+deviceId);


        StringBuffer sb = new StringBuffer(deviceId);
        sb.deleteCharAt(sb.length()-1);
        deviceId = String.valueOf(sb);

        Person person = personRepository.findById(idClient).get();
        Device device = deviceRepository.findById(UUID.fromString(deviceId)).get();
        device.setPerson(person);
        deviceRepository.save(device);
        return "Successfully connected client to devices!";
    }
}
