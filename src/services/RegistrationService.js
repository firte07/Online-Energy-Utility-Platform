import axios from 'axios';
import {Host} from '../commons/Host';

const endpoint = {
    registration: '/registration'
};

class RegistrationService{

    createCredentials(clientCredential){
        return axios.post(Host.backend_api + endpoint.registration, clientCredential);

    }

    login(credentials){
        console.log('credential =>' + JSON.stringify(credentials));
        return axios.put(Host.backend_api, credentials);
    }

}

export default new RegistrationService()