import axios from 'axios';
import {Host} from '../commons/Host';

const endpoint = {
    sensor: 'sensor'
};

export let tokenSensor ={
  token: ''
};

class SensorService{

    getSensors(){
        const AuthStr = 'Bearer '.concat(tokenSensor.token);
        return axios.get(Host.backend_api+ endpoint.sensor,
            { headers: { Authorization: AuthStr } });
    }

    createSensor(sensorToAdd){
        const AuthStr = 'Bearer '.concat(tokenSensor.token);
        return axios.post(Host.backend_api+ endpoint.sensor, sensorToAdd,
            { headers: { Authorization: AuthStr } });
    }

    getSensorById(sensorId){
        const AuthStr = 'Bearer '.concat(tokenSensor.token);
        return axios.get(Host.backend_api + endpoint.sensor + '/' + sensorId,
            { headers: { Authorization: AuthStr } });
    }

    updateSensor(newSensor, sensorId){
        const AuthStr = 'Bearer '.concat(tokenSensor.token);
        return axios.put(Host.backend_api + endpoint.sensor + '/' + sensorId, newSensor,
            { headers: { Authorization: AuthStr } });
    }

    deleteSensor(sensorId){
        const AuthStr = 'Bearer '.concat(tokenSensor.token);
        return axios.delete(Host.backend_api + endpoint.sensor + '/' + sensorId,
            { headers: { Authorization: AuthStr } });
    }
}

export default new SensorService()
