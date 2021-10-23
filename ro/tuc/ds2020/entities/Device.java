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
    private String id_device;

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

    @ManyToMany(mappedBy = "devices")
    private List<Person> persons = new ArrayList<>();

    public Device(){
    }

    public Device(String description, String location, int maxEnergy, int maxEnergy1) {
        this.description = description;
        this.location = location;
        this.maxEnergy = maxEnergy;
        this.maxEnergy = maxEnergy1;
    }

    public String getId_device() {
        return id_device;
    }

    public void setId_device(String id_device) {
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

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> person) {
        this.persons = person;
    }
}
