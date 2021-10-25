import axios from 'axios';
import {HOST} from '../commons/hosts';

const endpoint = {
    person: '/person'
};

class ClientService{

    getClients(){
        return axios.get(HOST.backend_api + endpoint.person);
    }

    getClientById(clientId){
        return axios.get(HOST.backend_api + endpoint.person + '/' + clientId);
    }

    updateClient(){

    }
}

export default new ClientService()
