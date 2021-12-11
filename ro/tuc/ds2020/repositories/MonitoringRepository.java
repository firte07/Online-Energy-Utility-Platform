package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ro.tuc.ds2020.entities.Device;
import ro.tuc.ds2020.entities.Monitoring;
import ro.tuc.ds2020.entities.Person;
import ro.tuc.ds2020.entities.Sensor;

import java.util.List;
import java.util.UUID;

public interface MonitoringRepository extends JpaRepository<Monitoring, UUID> {

    @Query(value = "SELECT m FROM Monitoring m WHERE m.sensor= :sensor")
    List<Monitoring> findBySensor(Sensor sensor);

}
