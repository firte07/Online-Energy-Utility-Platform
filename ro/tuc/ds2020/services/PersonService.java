package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.PersonDTO;
import ro.tuc.ds2020.dtos.PersonDetailsDTO;
import ro.tuc.ds2020.dtos.TimeDTO;
import ro.tuc.ds2020.dtos.ViewDTO;
import ro.tuc.ds2020.dtos.builders.PersonBuilder;
import ro.tuc.ds2020.entities.Device;
import ro.tuc.ds2020.entities.Monitoring;
import ro.tuc.ds2020.entities.Person;
import ro.tuc.ds2020.entities.Sensor;
import ro.tuc.ds2020.repositories.DeviceRepository;
import ro.tuc.ds2020.repositories.MonitoringRepository;
import ro.tuc.ds2020.repositories.PersonRepository;
import ro.tuc.ds2020.repositories.SensorRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PersonService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);
    private final PersonRepository personRepository;
    private final DeviceRepository deviceRepository;
    private final SensorRepository sensorRepository;
    private final MonitoringRepository monitoringRepository;

    @Autowired
    public PersonService(PersonRepository personRepository, DeviceRepository deviceRepository,
                         SensorRepository sensorRepository, MonitoringRepository monitoringRepository) {
        this.deviceRepository = deviceRepository;
        this.personRepository = personRepository;
        this.sensorRepository = sensorRepository;
        this.monitoringRepository = monitoringRepository;
    }

    public List<PersonDTO> findPersons() {
        List<Person> personList = personRepository.findAll();
        return personList.stream()
                .map(PersonBuilder::toPersonDTO)
                .collect(Collectors.toList());
    }

    public PersonDetailsDTO findPersonById(UUID id) {
        Optional<Person> prosumerOptional = personRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Person with id {} was not found in db", id);
            throw new ResourceNotFoundException(Person.class.getSimpleName() + " with id: " + id);
        }
        return PersonBuilder.toPersonDetailsDTO(prosumerOptional.get());
    }

    public UUID insert(PersonDetailsDTO personDTO) {
        Person person = PersonBuilder.toEntity(personDTO);
        person = personRepository.save(person);
        LOGGER.debug("Person with id {} was inserted in db", person.getId());
        return person.getId();
    }

    public PersonDTO update(UUID personId, PersonDetailsDTO personDTO) {
        Optional<Person> person = personRepository.findById(personId);
        if(!person.isPresent()){
            LOGGER.error("Person with id {} was not found in db", personId);
            throw new ResourceNotFoundException(Person.class.getSimpleName() + " with id: " + personId);
        }
        person.get().setAddress(personDTO.getAddress());
        person.get().setAge(personDTO.getAge());
        person.get().setName(personDTO.getName());

        Person updatedPerson = personRepository.save(person.get());

        return PersonBuilder.toPersonDTO(updatedPerson);
    }

    public Map<String, Boolean> deletePerson(UUID personId) {
        Optional<Person> person = personRepository.findById(personId);
        if(!person.isPresent()){
            LOGGER.error("Person with id {} was not found in db", personId);
            throw new ResourceNotFoundException(Person.class.getSimpleName() + " with id: " + personId);
        }
        personRepository.delete(person.get());
        return new HashMap<>();
    }

    public String connectClientToDevice(UUID idClient, String deviceId){
        System.out.println("Id client    "+idClient);
        System.out.println("Id device    "+deviceId);

        StringBuffer sb = new StringBuffer(deviceId);
        sb.deleteCharAt(sb.length()-1);
        deviceId = String.valueOf(sb);

        Person person = personRepository.findById(idClient).get();
        Device device = deviceRepository.findById(UUID.fromString(deviceId)).get();
        device.setPerson(person);
        deviceRepository.save(device);
        return "Successfully connected client to devices!";
    }

    private List<Monitoring> getAllMonitoringBySensorExceptToday(Sensor sensor){
        List<Monitoring> monitorings = monitoringRepository.findBySensor(sensor);
        List<Monitoring> copy = new ArrayList<>(monitorings);
        System.out.println(monitorings);
        for(Monitoring monitoring: monitorings){
            if(monitoring.getTemp().getMonthValue() == LocalDateTime.now().getMonthValue()
                    && monitoring.getTemp().getDayOfMonth() == LocalDateTime.now().getDayOfMonth()){
                copy.remove(monitoring);
            }
        }
        System.out.println(copy);
        return copy;
    }


    private Monitoring getMonitoringFromToday(Sensor sensor){
        List<Monitoring> monitorings = monitoringRepository.findBySensor(sensor);
        for(Monitoring monitoring: monitorings){
            if(monitoring.getTemp().getMonthValue() == LocalDateTime.now().getMonthValue() &&
                    monitoring.getTemp().getDayOfMonth() == LocalDateTime.now().getDayOfMonth()){
               return monitoring;
            }
        }
        return null;
    }

    private List<Float> getValues(List<Monitoring> monitorings){
        List<Float> values = new ArrayList<>();
        List<Monitoring> sortedList = monitorings.stream()
                .sorted(Comparator.comparing(Monitoring :: getTemp))
                .collect(Collectors.toList());

        for(Monitoring monitoring: sortedList){
             values.add(monitoring.getValue());
        }
        return values;
    }

    private float findAverage(List<Monitoring> monitorings){
        List<Float> values = this.getValues(monitorings);
        float sum = 0;
        for(Float value: values){
            sum+=value;
        }
        return sum/values.size();
    }

    private int findMaxValueForEnergy(List<Monitoring> monitorings){
        int maxValue = 0;
        List<Float> values = this.getValues(monitorings);
        for(Float value: values){
            if(maxValue < value)
                maxValue = value.intValue();
        }
        return maxValue;
    }

    private float totalConsumption(List<Monitoring> monitorings){
        List<Monitoring> sortedList = monitorings.stream()
                .sorted(Comparator.comparing(Monitoring :: getTemp))
                .collect(Collectors.toList());
        System.out.println(sortedList);
        float totalValue = 0;
        for(Monitoring monitoring: monitorings){
            totalValue+=monitoring.getValue();
        }

        return totalValue;
    }

    public List<ViewDTO> getViewHistory(UUID clientId) {
        Person person = personRepository.findById(clientId).get();
        List<Device> devices = deviceRepository.findByIdClient(person);
        List<ViewDTO> viewDTOS = new ArrayList<>();
        for(Device device: devices){
            ViewDTO viewDTO = new ViewDTO();
            viewDTO.setId(UUID.randomUUID());
            viewDTO.setDeviceDescription(device.getDescription());
            viewDTO.setLocation(device.getLocation());

            Sensor sensor = sensorRepository.findByDevice(device);
            viewDTO.setSensorDescription(sensor.getDescription());
            viewDTO.setMaxValueSensor(sensor.getMaxValue());

            List<Monitoring> monitorings = this.getAllMonitoringBySensorExceptToday(sensor);
            viewDTO.setAverageConsumption(this.findAverage(monitorings));
            device.setAverageConsumption(viewDTO.getAverageConsumption());

            viewDTO.setMaxEnergy(this.findMaxValueForEnergy(monitorings));
            device.setMaxEnergy(viewDTO.getMaxEnergy());

            deviceRepository.save(device);

            viewDTO.setTotalConsumption(this.totalConsumption(monitorings));

            viewDTOS.add(viewDTO);
        }
        return viewDTOS;
    }

    public List<ViewDTO> getViewToday(UUID clientId) {
        Person person = personRepository.findById(clientId).get();
        List<Device> devices = deviceRepository.findByIdClient(person);
        List<ViewDTO> viewDTOS = new ArrayList<>();
        for(Device device: devices){
            ViewDTO viewDTO = new ViewDTO();
            viewDTO.setId(UUID.randomUUID());
            viewDTO.setDeviceDescription(device.getDescription());
            viewDTO.setLocation(device.getLocation());

            Sensor sensor = sensorRepository.findByDevice(device);
            viewDTO.setSensorDescription(sensor.getDescription());
            viewDTO.setMaxValueSensor(sensor.getMaxValue());

            Monitoring monitoring = this.getMonitoringFromToday(sensor);
            viewDTO.setTotalConsumption(monitoring.getValue());

            viewDTOS.add(viewDTO);
        }
        return viewDTOS;
    }


    private List<Monitoring> getAllMonitoringFromASpecificDay(LocalDateTime temp, Sensor sensor) {
        List<Monitoring> monitorings = monitoringRepository.findBySensor(sensor);
        List<Monitoring> specificDayMonitorings = new ArrayList<>();

        for(Monitoring monitoring: monitorings){
            if(monitoring.getTemp().getDayOfMonth() == temp.getDayOfMonth() &&
            monitoring.getTemp().getMonthValue() == temp.getMonthValue() &&
            monitoring.getTemp().getYear() == temp.getYear())
                specificDayMonitorings.add(monitoring);
        }
        return specificDayMonitorings;
    }

    private List<Float> consumptionPerHour(List<Monitoring> monitorings){
        List<Float> consumptionPerHour = new ArrayList<>();
        int index = 0;
        float lastConsumption = 0;
        for(int counter = 0; counter < 24; counter ++){
            if(index < monitorings.size() &&
               monitorings.get(index).getTemp().getHour() == counter){
                consumptionPerHour.add(monitorings.get(index).getValue());
                lastConsumption = monitorings.get(index).getValue();
                index++;
            }else if(index == monitorings.size()){
                        consumptionPerHour.add((float)0.0);
                    }else {
                        consumptionPerHour.add(lastConsumption);
                    }
        }
        return consumptionPerHour;
    }

    /*private List<Float> consumptionPerHour(List<Monitoring> monitorings){
        List<Float> consumptionPerHour = new ArrayList<>();
        int index = 0;
        float lastConsumption = 0;
        for(int counter = 0; counter < 24; counter ++){
            if(index < monitorings.size() &&
                    monitorings.get(index).getTemp().getHour() == counter){
                consumptionPerHour.add(monitorings.get(index).getValue());
                lastConsumption = monitorings.get(index).getValue();
                index++;
            }else{
                consumptionPerHour.add(lastConsumption);
            }
        }
        return consumptionPerHour;
    }*/

    private List<Float> processingConsumptionPerHour(List<Float> values){
        List<Float> consumptionOnEachHour = new ArrayList<>();
        consumptionOnEachHour.add(values.get(0));
        for(int i=1; i<24; i++){
            if(values.get(i - 1).equals(values.get(i))){
              consumptionOnEachHour.add((float)0.0);
            }else{
                consumptionOnEachHour.add(values.get(i) - values.get(i-1));
            }
        }
        return consumptionOnEachHour;
    }

    private List<Float> finalProcessing(List<Float> finalConsumption, List<Float> values){
        for(int i=0;i<24;i++){
            Float currentValue = finalConsumption.get(0);
            finalConsumption.remove(0);
            currentValue+=values.get(i);
            finalConsumption.add(currentValue);
        }
        return finalConsumption;
    }

    public List<Float> eachDayConsumption(UUID clientId, String timeDTO) {
        String buffer = timeDTO.substring(1,11) + " " + timeDTO.substring(12, timeDTO.length() - 6);
        System.out.println(buffer);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(buffer, formatter);

        Person person = personRepository.findById(clientId).get();
        List<Device> devices = deviceRepository.findByIdClient(person);
        List<Float> consumptionPerHour = new ArrayList<>();
        //List<Float> finalConsumptionPerHour = new ArrayList<>();
        /*for(int i =0; i<24; i++){
            finalConsumptionPerHour.add((float)0.0);
        }
        *///for(Device device: devices){
            Sensor sensor = sensorRepository.findByDevice(devices.get(0));
            List<Monitoring> monitoringsFromSpecificDay = this.getAllMonitoringFromASpecificDay(localDateTime, sensor);
            List<Monitoring> sortedList = monitoringsFromSpecificDay.stream()
                    .sorted(Comparator.comparing(Monitoring :: getTemp))
                    .collect(Collectors.toList());
            consumptionPerHour = this.consumptionPerHour(sortedList);
        //    consumptionPerHour = this.processingConsumptionPerHour(consumptionPerHour);
            //finalConsumptionPerHour = this.finalProcessing(finalConsumptionPerHour, consumptionPerHour);
      //}

        return consumptionPerHour;
    }

    public List<Device> getAllDevices(UUID idClient){
        Person person = personRepository.findById(idClient).get();
        return deviceRepository.findByIdClient(person);
    }

    public Sensor getSensorByDevice(Device device){
        return sensorRepository.findByDevice(device);
    }


    public void deleteMonitorings() {
        monitoringRepository.deleteAll();
    }
}
