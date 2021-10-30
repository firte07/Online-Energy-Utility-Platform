package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsProperties;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.dtos.CredentialDTO;
import ro.tuc.ds2020.dtos.PersonDTO;
import ro.tuc.ds2020.dtos.PersonDetailsDTO;
import ro.tuc.ds2020.dtos.builders.CredentialBuilder;
import ro.tuc.ds2020.dtos.builders.PersonBuilder;
import ro.tuc.ds2020.dtos.validators.RegistrationValidator;
import ro.tuc.ds2020.entities.Credential;
import ro.tuc.ds2020.entities.Device;
import ro.tuc.ds2020.entities.Person;
import ro.tuc.ds2020.repositories.CredentialRepository;
import ro.tuc.ds2020.repositories.DeviceRepository;
import ro.tuc.ds2020.repositories.PersonRepository;

import java.util.List;
import java.util.UUID;

@Service
public class CredentialService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CredentialService.class);
    private final CredentialRepository credentialRepository;
    private final PersonRepository personRepository;


    @Autowired
    public CredentialService(CredentialRepository credentialRepository, PersonRepository personRepository) {
        this.credentialRepository = credentialRepository;
        this.personRepository = personRepository;

    }

    public PersonDTO insertCredential(CredentialDTO credentialDTO, PersonDetailsDTO personDetailsDTO/*, String confirmationPass*/) {
        /*RegistrationValidator.validatePasswords(credentialDTO.getPassword(), confirmationPass);*/

        Credential credential = CredentialBuilder.toEntity(credentialDTO);
        Person person = PersonBuilder.toEntity(personDetailsDTO);
        person.setCredential(credential);
        person = personRepository.save(person);

        LOGGER.debug("Person with id {} was inserted in db along with his credential", person.getId());
        return PersonBuilder.toPersonDTO(person);
    }

    public String executeLogin(CredentialDTO credentialDTO){
        List<Credential> credentials = credentialRepository.findAll();
        for(Credential potentialCredential : credentials){
            if(potentialCredential.getUsername().equals(credentialDTO.getUsername()) &&
                    potentialCredential.getPassword().equals(credentialDTO.getPassword())){
                return potentialCredential.getRole();
            }
        }
        return "Login failed";
    }

    public UUID getIdClientAfterRegistration(String username){
        Person person = personRepository.findPersonByCredential(credentialRepository.findCredentialByUsername(username));
        return person.getId();
    }
}
