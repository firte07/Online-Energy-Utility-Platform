import React, {Component} from 'react';
import DeviceService from "../../services/DeviceService";
import { withRouter } from 'react-router-dom';

class ConnectSensor extends Component {
    constructor(props) {
        super(props);

        this.state = {
            id: this.props.match.params.id,
            idSensor: ''
        }

        this.connect = this.connect.bind(this);
        this.changeIdSensor = this.changeIdSensor.bind(this);
    }

    changeIdSensor= (event) => {
        console.log('Bla bla connect');
        this.setState({idSensor: event.target.value});
    }

    connect = (e) =>{
        e.preventDefault();
        console.log('Device id => ' + JSON.stringify(this.state.id));
        console.log('Sensor id => ' + JSON.stringify(this.state.idSensor));

        DeviceService.connectSensor(this.state.id, this.state.idSensor).then(res => {
            this.props.history.push('/dashboard/devices');
        });
    }

    cancel(){
        this.props.history.push('/dashboard/devices');
    }

    render() {
        return (
            <div>
                <div className = "container">
                    <div className = "row">
                        <div className = "card col-md-6 offset-md-3 offset-md-3">
                            <h3 className="text-center">Connect device to sensor</h3>
                            <div className = "card-body">
                                <form>
                                    <div className = "form-group">
                                        <label> Unique identifier for sensor: </label>
                                        <input placeholder="Identifier" name="identifier" className="form-control"
                                               value={this.state.idSensor} onChange={this.changeIdSensor}/>
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

export default withRouter(ConnectSensor);