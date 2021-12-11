import React, {Component} from 'react';
import { withRouter } from 'react-router-dom';
import RegistrationService from "../../services/RegistrationService";
import {tokenSensor} from "../../services/SensorService"
import {tokenDevice} from "../../services/DeviceService"
import {tokenClient} from "../../services/ClientService"
import {tokenAdmin} from "../../services/ClientService"
import SensorService from "../../services/SensorService";

class Home extends Component {

    constructor(props) {
        super(props);

        this.state = {
            username: '',
            password: ''
        }

        this.changePassword =this.changePassword.bind(this);
        this.changeUsername = this.changeUsername.bind(this);
        this.login = this.login.bind(this);
   }

    changeUsername= (event) => {
        this.setState({username: event.target.value});
    }

    changePassword= (event) => {
        this.setState({password: event.target.value});
    }

    login = (e) => {
        e.preventDefault();
        let credential = {username: this.state.username, password: this.state.password};

        RegistrationService.login(credential).then(res => {
            console.log("Access token: "+res.data.access_token)
            console.log("Rol: " + res.data.role);
            if(res.data.role === 'client'){
                tokenClient.token = res.data.access_token;
                console.log('Ajunge aici 1  =>' + JSON.stringify(this.state.username));
                let username = {username: this.state.username}
                RegistrationService.getIdClient(username, res.data.access_token ).then(res => {
                    this.props.history.push('/user/' + res.data);
                });
            }else if(res.data.role === 'admin'){
                tokenSensor.token = res.data.access_token;
                tokenDevice.token = res.data.access_token;
                tokenAdmin.token  = res.data.access_token;
                this.props.history.push('/dashboard');
            }else{
                console.log('Wrong username or pass => ' + JSON.stringify(res.data));
                this.props.history.push('/');
            }
        });
    }

    render() {
        return (
            <form>
                <h3 className="text-center">Sign In</h3>

                <div className="form-group">
                    <label>Username</label>
                    <input type="email" className="form-control" placeholder="Enter username"
                           value={this.state.username} onChange={this.changeUsername}/>
                </div>

                <div className="form-group">
                    <label>Password</label>
                    <input type="password" className="form-control" placeholder="Enter password"
                           value={this.state.password} onChange={this.changePassword}/>
                </div>

                <div className="form-group">
                    <div className="custom-control custom-checkbox">
                        <input type="checkbox" className="custom-control-input" id="customCheck1" />
                        <label className="custom-control-label" htmlFor="customCheck1">Remember me</label>
                    </div>
                </div>

                <button type="submit" className="btn btn-dark btn-lg btn-block" onClick={this.login}>Submit</button>
                <p className="text-right">
                      <a href="/registration">Sign up!</a>
                </p>
            </form>
        );
    }
}

export default withRouter(Home);
