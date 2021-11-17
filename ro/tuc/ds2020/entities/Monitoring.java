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
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")
    private UUID id_monitoring;

    @Column(name = "timestamp")
    private LocalDateTime temp;

    @Column(name = "value")
    private float value;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_sensor")
    private Sensor sensor;

    public Monitoring(){}

    public Monitoring(UUID id_monitoring, LocalDateTime temp, float value) {
        this.id_monitoring = id_monitoring;
        this.temp = temp;
        this.value = value;
    }

    public Monitoring(LocalDateTime temp, float value) {
        this.temp = temp;
        this.value = value;
    }

    public UUID getId_monitoring() {
        return id_monitoring;
    }

    public void setId_monitoring(UUID id_monitoring) {
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
