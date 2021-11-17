package ro.tuc.ds2020.entities;

import java.util.UUID;

public class MonitoringBuffer {
    private UUID idSensor;
    private float value;
    private String temp;

    public MonitoringBuffer(UUID idSensor, float value, String temp) {
        this.idSensor = idSensor;
        this.value = value;
        this.temp = temp;
    }

    public MonitoringBuffer() {
    }

    public UUID getIdSensor() {
        return idSensor;
    }

    public void setIdSensor(UUID idSensor) {
        this.idSensor = idSensor;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    @Override
    public String toString() {
        return "Monitoring{" +
                "idSensor=" + idSensor +
                ", value=" + value +
                ", temp=" + temp +
                '}';
    }
}
