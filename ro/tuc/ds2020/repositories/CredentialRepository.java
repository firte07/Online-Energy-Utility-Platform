package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ro.tuc.ds2020.entities.Credential;
import ro.tuc.ds2020.entities.Device;
import ro.tuc.ds2020.entities.Person;

import java.util.List;
import java.util.UUID;

public interface CredentialRepository extends JpaRepository<Credential, UUID> {
    @Query(value = "SELECT c FROM Credential c WHERE c.username= :username")
    Credential findCredentialByUsername(String username);
}
