import axios from 'axios';
import {Host} from '../commons/Host';

const endpoint = {
    registration: '/registration',
    find: '/find-id'
};

class RegistrationService{

    createCredentials(clientCredential){
        return axios.post(Host.backend_api + endpoint.registration, clientCredential);

    }

    login(credentials){
        console.log('credential =>' + JSON.stringify(credentials));
        return axios.put(Host.backend_api, credentials);
    }

        //TODO: CHANGE
    getIdClient(username){
        console.log('username 1 =>' + JSON.stringify(username));
        return axios.post("http://localhost:8080/registration/find-id", username);
    }

}

export default new RegistrationService()