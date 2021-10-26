import React, {Component} from 'react';
import RegistrationService from "../services/RegistrationService";
import { withRouter } from 'react-router-dom';

class Registration extends Component {

    constructor(props) {
        super(props);

        this.state = {
            name: '',
            address: '',
            age: '',
            username: '',
            password: '',
            role: 'client',
            confirmationPassword: ''
        }

        this.changeName = this.changeName.bind(this);
        this.changeAddress = this.changeAddress.bind(this);
        this.changeAge = this.changeAge.bind(this);
        this.changePassword =this.changePassword.bind(this);
        this.changeUsername = this.changeUsername.bind(this);
        /*this.changeConfirmationPassword = this.changeConfirmationPassword.bind(this);*/

    }

    register = (e) => {
        e.preventDefault();
        let clientCredential = {name: this.state.name, age: this.state.age, address: this.state.address,
            username: this.state.username, password: this.state.password, role: this.state.role};
        console.log('clientCredential => ' + JSON.stringify(clientCredential));

        RegistrationService.createCredentials(clientCredential).then(res => {
            this.props.history.push('/');
        });
    }

    changeName= (event) => {
        this.setState({name: event.target.value});
    }

    changeAddress= (event) => {
        this.setState({address: event.target.value});
    }

    changeAge= (event) => {
        this.setState({age: event.target.value});
    }

    changeUsername= (event) => {
        this.setState({username: event.target.value});
    }

    changePassword= (event) => {
        this.setState({password: event.target.value});
    }

    /*changeConfirmationPassword = (event) => {
        this.setState({confirmationPassword: event.target.value});
    }*/

    render() {
        return (
            <form>
                <h3>Register</h3>

                <div className="form-group">
                    <label> Name </label>
                    <input type="text" className="form-control" placeholder="Name"
                           value={this.state.name} onChange={this.changeName}/>
                </div>

                <div className="form-group">
                    <label>Address</label>
                    <input type="text" className="form-control" placeholder="Address"
                           value={this.state.address} onChange={this.changeAddress}/>
                </div>

                <div className="form-group">
                    <label>Age</label>
                    <input type="number" className="form-control" placeholder="Enter age"
                           value={this.state.age} onChange={this.changeAge}/>
                </div>

                <div className="form-group">
                    <label>Username</label>
                    <input type="username" className="form-control" placeholder="Username"
                           value={this.state.username} onChange={this.changeUsername}/>
                </div>

                <div className="form-group">
                    <label>Password</label>
                    <input type="password" className="form-control" placeholder="Enter password"
                           value={this.state.password} onChange={this.changePassword}/>
                </div>

                {/*<div className="form-group">
                    <label>Confirm password</label>
                    <input type="password" className="form-control" placeholder="Retype password"
                           value={this.state.confirmationPassword} onChange={this.changeConfirmationPassword}/>
                </div>*/}

                <button type="submit" className="btn btn-dark btn-lg btn-block" onClick={this.register}>Register</button>
                <p className="forgot-password text-right">
                    Already registered? <a href="/">Log in</a>
                </p>
            </form>
        );
    }
}

export default withRouter(Registration);