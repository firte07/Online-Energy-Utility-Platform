package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.entities.Device;
import ro.tuc.ds2020.entities.Monitoring;
import ro.tuc.ds2020.entities.Person;
import ro.tuc.ds2020.entities.Sensor;
import ro.tuc.ds2020.repositories.DeviceRepository;
import ro.tuc.ds2020.repositories.MonitoringRepository;
import ro.tuc.ds2020.repositories.PersonRepository;
import ro.tuc.ds2020.repositories.SensorRepository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RPCService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeviceService.class);
    private final MonitoringRepository monitoringRepository;
    private final PersonRepository personRepository;
    private final DeviceRepository deviceRepository;
    private final SensorRepository sensorRepository;

    @Autowired
    public RPCService(MonitoringRepository monitoringRepository, PersonRepository personRepository,
                      DeviceRepository deviceRepository,  SensorRepository sensorRepository) {
        this.monitoringRepository = monitoringRepository;
        this.personRepository = personRepository;
        this.deviceRepository = deviceRepository;
        this.sensorRepository = sensorRepository;
    }

    public List<Float> getMonitoringLastSevenDays(String clintId){
        System.out.println("client id: "+ clintId);
        Optional<Person> person = personRepository.findById(UUID.fromString(clintId));
        List<Device> device = deviceRepository.findByIdClient(person.get());
        Sensor sensor = sensorRepository.findByDevice(device.get(0));

        List<Monitoring> monitoringList = monitoringRepository.findBySensor(sensor);
        List<Monitoring> sortedList = monitoringList.stream()
                .sorted(Comparator.comparing(Monitoring :: getTemp))
                .collect(Collectors.toList());

        List<Monitoring> sevenDaysMonitorings = new ArrayList<>();
        LocalDateTime localDateTime = LocalDateTime.now().minusDays(7);

        int index = 0;
        for(Monitoring monitoring:sortedList){
            if(monitoring.getTemp().getYear() == localDateTime.getYear() &&
            monitoring.getTemp().getMonthValue() == localDateTime.getMonthValue() &&
            monitoring.getTemp().getDayOfMonth() == localDateTime.getDayOfMonth()){
                System.out.println(monitoring.getTemp() + "   " + monitoring.getValue());
                System.out.println(index);
                break;
            }
            index++;
        }

        for(int i=0; i<7*24; i++){
            sevenDaysMonitorings.add(sortedList.get(index));
            index++;
        }

        List<Float> valuesSevenDays = new ArrayList<>();

        for(Monitoring monitoring:sevenDaysMonitorings){
            valuesSevenDays.add(monitoring.getValue());
        }

        return valuesSevenDays;
    }

    public List<Float> baseline(String clintId){
        System.out.println("client id: "+ clintId);

        List<Float> valuesSevenDays = getMonitoringLastSevenDays(clintId);
        List<Float> baseline = new ArrayList<>();

        float maxHour = 0;
        for(int i=0; i<24;i++){
            for(int j=0; j<7*24; j+=24){
                maxHour += valuesSevenDays.get(i+j);
            }
            baseline.add(maxHour/7);
            maxHour = 0;
        }

        return baseline;
    }

    public List<Float> optimumInterval(String clintId, String interval){
        System.out.println("client id: "+ clintId);

        int intervalValue= Integer.parseInt(interval);
        List<Float> baseline = baseline(clintId);


        float bestValue = 10000000;
        float currentValue = 0;
        int startIndex =0, j;
        for(int i=0; i<24 - intervalValue; i++){
            for(j=0; j<intervalValue; j++){
                currentValue+=baseline.get(i+j);
            }
            if(currentValue < bestValue){
                bestValue = currentValue;
                startIndex = i;
            }
            currentValue=0;
        }

        List<Float> optimalInterval = new ArrayList<>();

        for(int i=0;i<24;i++){
            optimalInterval.add((float)0.0);
        }
        for(int i=startIndex;i<startIndex+intervalValue;i++){
            optimalInterval.set(i,baseline.get(i));
        }

        return optimalInterval;
    }



}
