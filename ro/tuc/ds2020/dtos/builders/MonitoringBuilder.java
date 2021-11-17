package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.MonitoringDTO;
import ro.tuc.ds2020.entities.Monitoring;

import javax.management.monitor.Monitor;

public class MonitoringBuilder {

    private MonitoringBuilder(){}

    public static MonitoringDTO toMonitoringDTO(Monitoring monitoring){
        return new MonitoringDTO(monitoring.getId_monitoring(), monitoring.getTemp(), monitoring.getValue());
    }

    public static Monitoring toEntityWithId(MonitoringDTO monitoringDTO){
        return new Monitoring(monitoringDTO.getId(), monitoringDTO.getTemp(), monitoringDTO.getValue());
    }

    public static Monitoring toEntity(MonitoringDTO monitoringDTO){
        return new Monitoring(monitoringDTO.getTemp(), monitoringDTO.getValue());
    }
}
