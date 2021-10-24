package ro.tuc.ds2020.dtos;

import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;
import java.util.UUID;

public class SensorDTO extends RepresentationModel<PersonDTO> {
    private UUID id;
    private String description;
    private int maxValue;
    //private UUID idDevice;

    private SensorDTO(){}

    public SensorDTO(UUID id, String description, int maxValue) {
        this.id = id;
        this.description = description;
        this.maxValue = maxValue;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SensorDTO sensorDTO = (SensorDTO) o;
        return maxValue == sensorDTO.maxValue &&
                Objects.equals(description, sensorDTO.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, maxValue);
    }
}
