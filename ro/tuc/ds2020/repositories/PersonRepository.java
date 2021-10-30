package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.tuc.ds2020.entities.Credential;
import ro.tuc.ds2020.entities.Person;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID> {

    /**
     * Example: JPA generate Query by Field
     */
    List<Person> findByName(String name);

    /**
     * Example: Write Custom Query
     */
    @Query(value = "SELECT p " +
            "FROM Person p " +
            "WHERE p.name = :name " +
            "AND p.age >= 60  ")
    Optional<Person> findSeniorsByName(@Param("name") String name);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Person p " +
            "SET p.address = :newAddress " +
            "WHERE p.id_person = :idPerson")
    void updateAddress(@Param("newAddress") String newAddress, @Param("idPerson") UUID idPerson);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Person p " +
            "SET p.name = :newName " +
            "WHERE p.id_person = :idPerson")
    void updateNameById(@Param("newName") String newName, @Param("idPerson") UUID idPerson);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM Person " +
            "WHERE id_person = :idPerson")
    void deletePersonById(@Param("idPerson") UUID idPerson);

    @Query(value = "SELECT p FROM Person p WHERE p.credential= :credential")
    Person findPersonByCredential(Credential credential);
}
