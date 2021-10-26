package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.CredentialDTO;
import ro.tuc.ds2020.dtos.PersonDetailsDTO;
import ro.tuc.ds2020.entities.Credential;
import ro.tuc.ds2020.entities.Person;

public class CredentialBuilder {

    private CredentialBuilder(){

    }

    public static CredentialDTO toCredentialDTO(Credential credential){
        return new CredentialDTO(credential.getId_credentials(), credential.getUsername(),
                credential.getPassword(), credential.getRole());
    }

    public static Credential toEntity(CredentialDTO credentialDTO) {
        return new Credential(credentialDTO.getUsername(), credentialDTO.getPassword(),
                credentialDTO.getRole());
    }
}
