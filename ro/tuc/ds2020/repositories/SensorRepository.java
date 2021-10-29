package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.tuc.ds2020.entities.Device;
import ro.tuc.ds2020.entities.Person;
import ro.tuc.ds2020.entities.Sensor;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, UUID> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE Sensor s " +
            "SET s.description= :newDescription " +
            "WHERE s.id_sensor = :idSensor")
    void updateDescription(@Param("newDescription") String newDescription, @Param("idSensor") UUID idSensor);


    @Transactional
    @Modifying
    @Query(value = "DELETE FROM Sensor " +
            "WHERE id_sensor = :idSensor")
    void deleteSensorById(@Param("idSensor") UUID idSensor);

    @Query(value = "SELECT s FROM Sensor s WHERE s.device= :device")
    Sensor findByDevice(Device device);
}
