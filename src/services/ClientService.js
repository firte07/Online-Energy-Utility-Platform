import axios from 'axios';
import {Host} from '../commons/Host';

const endpoint = {
    client: '/person',
    connect: '/connect-device'
};

class ClientService{

    getClients(){
        return axios.get(Host.backend_api+ endpoint.client);
    }

    createClient(clientToAdd){
        return axios.post(Host.backend_api+ endpoint.client, clientToAdd);
    }

    getClientById(clientId){
        return axios.get(Host.backend_api + endpoint.client + '/' + clientId);
    }

    updateClient(newClient, clientId){
        return axios.put(Host.backend_api + endpoint.client + '/' + clientId, newClient);
    }

    deleteClient(clientId){
        return axios.delete(Host.backend_api + endpoint.client + '/' + clientId);
    }

    connectDevice(clientId, deviceId){
        return axios.post(Host.backend_api + endpoint.client + endpoint.connect + '/' + clientId, deviceId);
    }
}

export default new ClientService()
