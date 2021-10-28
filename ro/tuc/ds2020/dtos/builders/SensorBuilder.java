package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.SensorDTO;
import ro.tuc.ds2020.entities.Sensor;

public class SensorBuilder {

    private SensorBuilder(){}

    public static SensorDTO toSensorDTO(Sensor sensor){
        return new SensorDTO(sensor.getId_sensor(), sensor.getDescription(), sensor.getMaxValue());
    }

    public static Sensor toEntity(SensorDTO sensorDTO){
        return new Sensor(sensorDTO.getDescription(), sensorDTO.getMaxValue());
    }

    public static Sensor toEntityWithId(SensorDTO sensorDTO){
        return new Sensor(sensorDTO.getId(), sensorDTO.getDescription(), sensorDTO.getMaxValue());
    }
}
