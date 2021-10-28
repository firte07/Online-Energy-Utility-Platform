package ro.tuc.ds2020.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Sensor implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")
    private UUID id_sensor;

    @Column(name = "description")
    private String description;

    @Column(name = "max_value")
    private int maxValue;

    @OneToMany(mappedBy = "sensor", cascade = CascadeType.ALL)
    private List<Monitoring> monitorings;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_device", unique = true)
    private Device device;

    public Sensor(){}

    public Sensor(UUID id, String description, int maxValue) {
        this.id_sensor = id;
        this.description = description;
        this.maxValue = maxValue;
    }

    public Sensor(String description, int maxValue) {
        this.description = description;
        this.maxValue = maxValue;
    }

    public UUID getId_sensor() {
        return id_sensor;
    }

    public void setId_sensor(UUID id_sensor) {
        this.id_sensor = id_sensor;
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

    public List<Monitoring> getMonitorings() {
        return monitorings;
    }

    public void setMonitorings(List<Monitoring> monitorings) {
        this.monitorings = monitorings;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }
}
