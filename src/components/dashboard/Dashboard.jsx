import React, {Component} from 'react';
import { withRouter } from 'react-router-dom';

class Dashboard extends Component {
    constructor(props) {
        super(props);

        this.state = {

        }

        this.viewClients = this.viewClients.bind(this);
        this.viewDevices = this.viewDevices.bind(this);
        this.viewSensors = this.viewSensors.bind(this);

    }


    viewClients(){
        this.props.history.push('/dashboard/clients');
    }

    viewSensors(){
        this.props.history.push('/dashboard/sensors');
    }

    viewDevices(){
        this.props.history.push('/dashboard/devices');
    }

    render() {
        return (
            <div>
                <div className = "container">
                    <h2 className="text-center">Dashboard</h2>
                    <div className = "card-body">
                        <button className="btn btn-info" onClick={this.viewClients}>Clients</button>
                        <button className="btn btn-info" onClick={this.viewSensors} style={{marginLeft: "10px"}}>Sensors</button>
                        <button className="btn btn-info" onClick={this.viewDevices} style={{marginLeft: "10px"}}>Devices</button>
                    </div>
                </div>
            </div>
        );
    }
}

export default withRouter(Dashboard);
