import axios from 'axios';
import {Host} from '../commons/Host';

const endpoint = {
    client: '/person',
    connect: '/connect-device',
    userHistory: '/view-history',
    userToday: '/view-today',
    chart: '/chart'
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

    getViewHistory(clientId){
        return axios.get(Host.backend_api + endpoint.client + endpoint.userHistory + '/' + clientId);
    }

    getViewToday(clientId){
        return axios.get(Host.backend_api + endpoint.client + endpoint.userToday + '/' + clientId);
    }

    getChart(clientId, date){
        console.log('date service => ' + date);
        return axios.post(Host.backend_api + endpoint.client + endpoint.chart + '/' + clientId, date);
    }
}

export default new ClientService()
