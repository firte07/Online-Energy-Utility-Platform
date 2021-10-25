import axios from 'axios';
import {HOST} from '../commons/host';

const endpoint = {
    client: '/person'
};

class ClientService{

    getClients(){
        return axios.get(HOST.backend_api+ endpoint.client);
    }

    createClient(clientToAdd){
        return axios.post(HOST.backend_api+ endpoint.client, clientToAdd);
    }

    getClientById(clientId){
        return axios.get(HOST.backend_api + endpoint.client + '/' + clientId);
    }

    updateClient(newClient, clientId){
        return axios.put(HOST.backend_api + endpoint.client + '/' + clientId, newClient);
    }

    deleteClient(clientId){
        return axios.delete(HOST.backend_api + endpoint.client + '/' + clientId);
    }
}

export default new ClientService()
