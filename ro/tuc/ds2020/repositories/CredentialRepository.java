package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.tuc.ds2020.entities.Credential;
import java.util.UUID;

public interface CredentialRepository extends JpaRepository<Credential, UUID> {
}
