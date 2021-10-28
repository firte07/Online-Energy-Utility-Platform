package ro.tuc.ds2020.dtos;

import java.util.UUID;

public class DeviceSensorDTO {
    private UUID idDevice;
    private UUID idSensor;

    public DeviceSensorDTO() {
    }

    public DeviceSensorDTO(UUID idDevice, UUID idSensor) {
        this.idDevice = idDevice;
        this.idSensor = idSensor;
    }

    public UUID getIdDevice() {
        return idDevice;
    }

    public void setIdDevice(UUID idDevice) {
        this.idDevice = idDevice;
    }

    public UUID getIdSensor() {
        return idSensor;
    }

    public void setIdSensor(UUID idSensor) {
        this.idSensor = idSensor;
    }
}
