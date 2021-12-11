import axios from 'axios';
import {Host} from '../commons/Host';

const endpoint = {
    device: 'device',
    connect: 'connect-sensor/'
};

export let tokenDevice ={
    token: ''
};

class DeviceService{

    getDevices(){
        const AuthStr = 'Bearer '.concat(tokenDevice.token);
        return axios.get(Host.backend_api+ endpoint.device,
            { headers: { Authorization: AuthStr } });
    }

    createDevice(deviceToAdd){
        const AuthStr = 'Bearer '.concat(tokenDevice.token);
        return axios.post(Host.backend_api+ endpoint.device, deviceToAdd,
            { headers: { Authorization: AuthStr } });
    }

    getDeviceById(deviceId){
        const AuthStr = 'Bearer '.concat(tokenDevice.token);
        return axios.get(Host.backend_api + endpoint.device + '/' + deviceId,
            { headers: { Authorization: AuthStr } });
    }

    updateDevice(newDevice, deviceId){
        const AuthStr = 'Bearer '.concat(tokenDevice.token);
        return axios.put(Host.backend_api + endpoint.device + '/' + deviceId, newDevice,
            { headers: { Authorization: AuthStr } });
    }

    deleteDevice(deviceId){
        const AuthStr = 'Bearer '.concat(tokenDevice.token);
         return axios.delete(Host.backend_api + endpoint.device + '/' + deviceId,
             { headers: { Authorization: AuthStr } });
    }

    connectSensor(deviceId, idSensor){
        const AuthStr = 'Bearer '.concat(tokenDevice.token);
        return axios.post(Host.backend_api + endpoint.device +  '/' +endpoint.connect  + deviceId, idSensor,
            { headers: { Authorization: AuthStr } });
    }
}

export default new DeviceService()
