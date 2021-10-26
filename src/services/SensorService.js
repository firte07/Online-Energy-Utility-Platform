import axios from 'axios';
import {HOST} from '../commons/host';

const endpoint = {
    sensor: '/sensor'
};

class SensorService{

    getSensors(){
        return axios.get(HOST.backend_api+ endpoint.sensor);
    }

    createSensor(sensorToAdd){
        return axios.post(HOST.backend_api+ endpoint.sensor, sensorToAdd);
    }

    getSensorById(sensorId){
        return axios.get(HOST.backend_api + endpoint.sensor + '/' + sensorId);
    }

    updateSensor(newSensor, sensorId){
        return axios.put(HOST.backend_api + endpoint.sensor + '/' + sensorId, newSensor);
    }

    deleteSensor(sensorId){
        return axios.delete(HOST.backend_api + endpoint.sensor + '/' + sensorId);
    }
}

export default new SensorService()
