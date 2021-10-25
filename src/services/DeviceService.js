import axios from 'axios';
import {HOST} from '../commons/host';

const endpoint = {
    device: '/device'
};

class DeviceService{

    getDevices(){
        return axios.get(HOST.backend_api+ endpoint.device);
    }

    createDevice(deviceToAdd){
        return axios.post(HOST.backend_api+ endpoint.device, deviceToAdd);
    }

    getDeviceById(deviceId){
        return axios.get(HOST.backend_api + endpoint.device + '/' + deviceId);
    }

    updateDevice(newDevice, deviceId){
        return axios.put(HOST.backend_api + endpoint.device + '/' + deviceId, newDevice);
    }

    deleteDevice(deviceId){
        return axios.delete(HOST.backend_api + endpoint.device + '/' + deviceId);
    }
}

export default new DeviceService()
