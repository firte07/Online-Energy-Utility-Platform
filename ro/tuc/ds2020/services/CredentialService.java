package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsProperties;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CredentialService implements UserDetailsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CredentialService.class);
    private final CredentialRepository credentialRepository;
    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public CredentialService(CredentialRepository credentialRepository, PersonRepository personRepository, PasswordEncoder passwordEncoder) {
        this.credentialRepository = credentialRepository;
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Credential credential = credentialRepository.findCredentialByUsername(username);
        if(credential==null){
            LOGGER.error("User not found in the db!");
            throw new UsernameNotFoundException("User not found in the db!");
        }else{
            LOGGER.info("user found in the db: {}", username);
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(credential.getRole()));

        return new org.springframework.security.core.userdetails.User(credential.getUsername(),
                credential.getPassword(), authorities);
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
        System.out.println("execute login!!!");
        List<Credential> credentials = credentialRepository.findAll();
        for(Credential potentialCredential : credentials){
            if(potentialCredential.getUsername().equals(credentialDTO.getUsername()) &&
                    potentialCredential.getPassword().equals(credentialDTO.getPassword())){
                System.out.println("Rol " + potentialCredential.getRole());
                return potentialCredential.getRole();
            }
        }
        return "Login failed";
    }

    public UUID getIdClientAfterRegistration(String username){
        Person person = personRepository.findPersonByCredential(credentialRepository.findCredentialByUsername(username));
        return person.getId();
    }

    public List<CredentialDTO> findCredentials(){
        System.out.println("find credentials!!!");
        List<Credential> credentials = credentialRepository.findAll();
        return credentials.stream().map(CredentialBuilder::toCredentialDTO)
                .collect(Collectors.toList());
    }

    public UUID insert(CredentialDTO credentialDTO) {
        Credential credential = CredentialBuilder.toEntity(credentialDTO);

        credential.setPassword(passwordEncoder.encode(credential.getPassword()));

        credential = credentialRepository.save(credential);
        return credential.getId_credentials();
    }


}
