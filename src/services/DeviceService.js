import axios from 'axios';
import {Host} from '../commons/Host';

const endpoint = {
    device: '/device',
    connect: '/connect-sensor'
};

class DeviceService{

    getDevices(){
        return axios.get(Host.backend_api+ endpoint.device);
    }

    createDevice(deviceToAdd){
        return axios.post(Host.backend_api+ endpoint.device, deviceToAdd);
    }

    getDeviceById(deviceId){
        return axios.get(Host.backend_api + endpoint.device + '/' + deviceId);
    }

    updateDevice(newDevice, deviceId){
        return axios.put(Host.backend_api + endpoint.device + '/' + deviceId, newDevice);
    }

    deleteDevice(deviceId){
         return axios.delete(Host.backend_api + endpoint.device + '/' + deviceId);
    }

    connectSensor(deviceId, idSensor){
        return axios.post(Host.backend_api + endpoint.device +  endpoint.connect + '/' + deviceId, idSensor);
    }
}

export default new DeviceService()
