package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.tuc.ds2020.entities.Device;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Repository
public interface DeviceRepository extends JpaRepository<Device, UUID> {

    /*List<Device> findById_device(UUID idDevice);*/

    @Transactional
    @Modifying
    @Query(value = "UPDATE Device d " +
            "SET d.averageConsumption = :newAverageConsumption " +
            "WHERE d.id_device = :idDevice")
    void updateAverageConsumption(@Param("newAverageConsumption") float newAverageConsumption, @Param("idDevice") UUID idDevice);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM Device " +
            "WHERE id_device = :idDevice")
    void deleteDeviceById(@Param("idDevice") UUID idDevice);
}
