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
@Table(name = "device")
public class Device implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")
    private UUID id_device;

    @Column(name = "description")
    private String description;

    @Column(name = "location")
    private String location;

    @Column(name = "max_energy")
    private int maxEnergy;

    @Column(name = "average_consumption")
    private float averageConsumption;

    @OneToOne(mappedBy = "device")
    private Sensor sensor;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_person")
    private Person person;

    public Device(){
    }

    public Device(UUID uuid, String description, String location, int maxEnergy, float averageConsumption) {
        this.id_device = uuid;
        this.description = description;
        this.location = location;
        this.maxEnergy = maxEnergy;
        this.averageConsumption = averageConsumption;
    }

    public Device(String description, String location, int maxEnergy, float averageConsumption) {
        this.description = description;
        this.location = location;
        this.maxEnergy = maxEnergy;
        this.averageConsumption = averageConsumption;
    }

    public UUID getId_device() {
        return id_device;
    }

    public void setId_device(UUID id_device) {
        this.id_device = id_device;
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

    public float getAverageConsumption() {
        return averageConsumption;
    }

    public void setAverageConsumption(float averageConsumption) {
        this.averageConsumption = averageConsumption;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
