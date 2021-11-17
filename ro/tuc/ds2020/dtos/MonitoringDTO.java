package ro.tuc.ds2020.dtos;

import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;
import java.util.UUID;

public class MonitoringDTO extends RepresentationModel<MonitoringDTO> {
    private UUID id;
    private LocalDateTime temp;
    private float value;

    public MonitoringDTO(UUID id, LocalDateTime temp, float value) {
        this.id = id;
        this.temp = temp;
        this.value = value;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getTemp() {
        return temp;
    }

    public void setTemp(LocalDateTime temp) {
        this.temp = temp;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
