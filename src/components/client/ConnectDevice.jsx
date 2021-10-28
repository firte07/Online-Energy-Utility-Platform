import React, {Component} from 'react';
import ClientService from "../../services/ClientService";
import { withRouter } from 'react-router-dom';

class ConnectDevice extends Component {
    constructor(props) {
        super(props);

        this.state = {
            id: this.props.match.params.id,
            idDevice: ''
        }

        this.connect = this.connect.bind(this);
        this.changeIdDevice = this.changeIdDevice.bind(this);
    }

    changeIdDevice= (event) => {
        this.setState({idDevice: event.target.value});
    }

    connect = (e) =>{
        e.preventDefault();
        console.log('Client id => ' + JSON.stringify(this.state.id));
        console.log('Device id => ' + JSON.stringify(this.state.idDevice));

        ClientService.connectDevice(this.state.id, this.state.idDevice).then(res => {
            this.props.history.push('/dashboard/clients');
        });
    }

    cancel(){
        this.props.history.push('/dashboard/clients');
    }

    render() {
        return (
            <div>
                <div className = "container">
                    <div className = "row">
                        <div className = "card col-md-6 offset-md-3 offset-md-3">
                            <h3 className="text-center">Connect client to device</h3>
                            <div className = "card-body">
                                <form>
                                    <div className = "form-group">
                                        <label> Unique identifier for device: </label>
                                        <input placeholder="Identifier" name="identifier" className="form-control"
                                               value={this.state.idDevice} onChange={this.changeIdDevice}/>
                                    </div>

                                    <button className="btn btn-success" onClick={this.connect}>Connect</button>
                                    <button className="btn btn-danger" onClick={this.cancel.bind(this)} style={{marginLeft: "10px"}}>Cancel</button>
                                </form>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        );
    }
}

export default withRouter(ConnectDevice);