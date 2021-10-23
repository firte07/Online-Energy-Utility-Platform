package ro.tuc.ds2020.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
public class Monitoring implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    private String id_monitoring;

    @Column(name = "timestamp")
    private LocalDateTime temp;

    @Column(name = "value")
    private float value;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_sensor")
    private Sensor sensor;

    public Monitoring(){}

    public Monitoring(LocalDateTime temp, float value) {
        this.temp = temp;
        this.value = value;
    }

    public String getId_monitoring() {
        return id_monitoring;
    }

    public void setId_monitoring(String id_monitoring) {
        this.id_monitoring = id_monitoring;
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

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}
