import axios from 'axios';
import {Host} from '../commons/Host';

const endpoint = {
    registration: 'registration',
    find: '/find-id'
};

class RegistrationService{

    createCredentials(clientCredential){
        return axios.post(Host.backend_api + endpoint.registration, clientCredential);

    }

    //with security
    login(credentials){
        var qs = require('qs');
        var data = qs.stringify({
            'username': credentials.username,
            'password': credentials.password
        });

        console.log('credential =>' + JSON.stringify(credentials));
        return axios.post(Host.backend_api + 'login', data);
    }

    getIdClient(username, token){
        console.log('Token =>' + token);
        console.log('Username ' + username);
        const AuthStr = 'Bearer '.concat(token);
        return axios.post(Host.backend_api + endpoint.registration + endpoint.find, username,
            { headers: { Authorization: AuthStr } });
    }

}

export default new RegistrationService()