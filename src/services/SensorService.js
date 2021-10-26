import axios from 'axios';
import {Host} from '../commons/Host';

const endpoint = {
    sensor: '/sensor'
};

class SensorService{

    getSensors(){
        return axios.get(Host.backend_api+ endpoint.sensor);
    }

    createSensor(sensorToAdd){
        return axios.post(Host.backend_api+ endpoint.sensor, sensorToAdd);
    }

    getSensorById(sensorId){
        return axios.get(Host.backend_api + endpoint.sensor + '/' + sensorId);
    }

    updateSensor(newSensor, sensorId){
        return axios.put(Host.backend_api + endpoint.sensor + '/' + sensorId, newSensor);
    }

    deleteSensor(sensorId){
        return axios.delete(Host.backend_api + endpoint.sensor + '/' + sensorId);
    }
}

export default new SensorService()
