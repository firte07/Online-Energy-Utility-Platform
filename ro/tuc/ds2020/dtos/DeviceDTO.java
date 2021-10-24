package ro.tuc.ds2020.dtos;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;
import java.util.UUID;

public class DeviceDTO extends RepresentationModel<DeviceDTO> {
    private UUID id;
    private float averageConsumption;
    private String description;
    private String location;
    private int maxEnergy;

    private DeviceDTO(){}

    public DeviceDTO(UUID id, float averageConsumption, String description, String location, int maxEnergy) {
        this.id = id;
        this.averageConsumption = averageConsumption;
        this.description = description;
        this.location = location;
        this.maxEnergy = maxEnergy;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public float getAverageConsumption() {
        return averageConsumption;
    }

    public void setAverageConsumption(float averageConsumption) {
        this.averageConsumption = averageConsumption;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getMaxEnergy() {
        return maxEnergy;
    }

    public void setMaxEnergy(int maxEnergy) {
        this.maxEnergy = maxEnergy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DeviceDTO deviceDTO = (DeviceDTO) o;
        return Float.compare(deviceDTO.averageConsumption, averageConsumption) == 0 &&
                maxEnergy == deviceDTO.maxEnergy &&
                Objects.equals(description, deviceDTO.description) &&
                Objects.equals(location, deviceDTO.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(averageConsumption, description, location, maxEnergy);
    }
}
