import axios from 'axios';
import {Host} from '../commons/Host';

const endpoint = {
    client: 'person',
    monitoring: 'monitoring',
    connect: 'connect-device',
    userHistory: 'view-history',
    userToday: 'view-today',
    chart: 'chart'
};

export let tokenClient ={
    token: ''
};

export let tokenAdmin ={
    token: ''
};

class ClientService{

    getClients(){
        const AuthStr = 'Bearer '.concat(tokenAdmin.token);
        return axios.get(Host.backend_api+ endpoint.client,
            { headers: { Authorization: AuthStr } });
    }

    createClient(clientToAdd){
        const AuthStr = 'Bearer '.concat(tokenAdmin.token);
        return axios.post(Host.backend_api+ endpoint.client, clientToAdd,
            { headers: { Authorization: AuthStr } });
    }

    getClientById(clientId){
        const AuthStr = 'Bearer '.concat(tokenAdmin.token);
        return axios.get(Host.backend_api + endpoint.client + '/' + clientId,
            { headers: { Authorization: AuthStr } });
    }

    updateClient(newClient, clientId){
        const AuthStr = 'Bearer '.concat(tokenAdmin.token);
        return axios.put(Host.backend_api + endpoint.client + '/' + clientId, newClient,
            { headers: { Authorization: AuthStr } });
    }

    deleteClient(clientId){
        const AuthStr = 'Bearer '.concat(tokenAdmin.token);
        return axios.delete(Host.backend_api + endpoint.client + '/' + clientId,
            { headers: { Authorization: AuthStr } });
    }

    connectDevice(clientId, deviceId){
        const AuthStr = 'Bearer '.concat(tokenAdmin.token);
        return axios.post(Host.backend_api + endpoint.client + '/' + endpoint.connect + '/' + clientId, deviceId,
            { headers: { Authorization: AuthStr } });
    }

    deleteMonitorings(){
        const AuthStr = 'Bearer '.concat(tokenClient.token);
        return axios.delete(Host.backend_api + endpoint.client,
            { headers: { Authorization: AuthStr } });
    }

    getViewHistory(clientId){
        const AuthStr = 'Bearer '.concat(tokenClient.token);
        return axios.get(Host.backend_api + endpoint.client + '/' + endpoint.userHistory + '/' + clientId,
            { headers: { Authorization: AuthStr } });
    }

    getViewToday(clientId){
        const AuthStr = 'Bearer '.concat(tokenClient.token);
        return axios.get(Host.backend_api + endpoint.client + '/' + endpoint.userToday + '/' + clientId,
            { headers: { Authorization: AuthStr } });
    }

    getChart(clientId, date){
        const AuthStr = 'Bearer '.concat(tokenClient.token);
        console.log('date service => ' + date);
        return axios.post(Host.backend_api + endpoint.client + '/' + endpoint.chart + '/' + clientId, date,
            { headers: { Authorization: AuthStr } });
    }

    connect(clientId){
        const AuthStr = 'Bearer '.concat(tokenClient.token);
        return axios.get(Host.backend_api + endpoint.monitoring + '/start/' + clientId,
            { headers: { Authorization: AuthStr } });
    }
}

export default new ClientService()
