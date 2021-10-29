package ro.tuc.ds2020.dtos;

import org.springframework.hateoas.RepresentationModel;

import java.util.UUID;

public class ViewDTO extends RepresentationModel<ViewDTO> {
    private UUID id;
    private String deviceDescription;
    private String location;
    private int maxEnergy;
    private float averageConsumption;
    private String sensorDescription;
    private int maxValueSensor;
    private float totalConsumption;

    public ViewDTO() {
    }

    public ViewDTO(String deviceDescription, String location, int maxEnergy,
                   float averageConsumption, String sensorDescription, int maxValueSensor) {
        this.deviceDescription = deviceDescription;
        this.location = location;
        this.maxEnergy = maxEnergy;
        this.averageConsumption = averageConsumption;
        this.sensorDescription = sensorDescription;
        this.maxValueSensor = maxValueSensor;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDeviceDescription() {
        return deviceDescription;
    }

    public void setDeviceDescription(String deviceDescription) {
        this.deviceDescription = deviceDescription;
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

    public float getAverageConsumption() {
        return averageConsumption;
    }

    public void setAverageConsumption(float averageConsumption) {
        this.averageConsumption = averageConsumption;
    }

    public String getSensorDescription() {
        return sensorDescription;
    }

    public void setSensorDescription(String sensorDescription) {
        this.sensorDescription = sensorDescription;
    }

    public int getMaxValueSensor() {
        return maxValueSensor;
    }

    public void setMaxValueSensor(int maxValueSensor) {
        this.maxValueSensor = maxValueSensor;
    }

    public float getTotalConsumption() {
        return totalConsumption;
    }

    public void setTotalConsumption(float totalConsumption) {
        this.totalConsumption = totalConsumption;
    }
}
