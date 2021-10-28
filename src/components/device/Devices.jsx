import React, {Component} from 'react';
import DeviceService from "../../services/DeviceService";
import { withRouter } from 'react-router-dom';

class Devices extends Component {

    constructor(props) {
        super(props);

        this.state = {
            devices: []
        }

        this.addDevice = this.addDevice.bind(this);
        this.deleteDevice = this.deleteDevice.bind(this);
        this.updateDevice = this.updateDevice.bind(this);
        this.goBack = this.goBack.bind(this);
    }

    componentDidMount() {
       DeviceService.getDevices().then((res )=> {
           this.setState({
              devices: res.data
           });
       });
    }

    deleteDevice(id){
        DeviceService.deleteDevice(id).then(res => {
            this.setState({
                devices: this.state.devices.filter(device => device.id !== id)
            });
        });
    }

    updateDevice(id){
        this.props.history.push(`/dashboard/update-device/${id}`);
    }

    connectSensor(id){
        this.props.history.push(`/dashboard/connect-sensor/${id}`);
    }

    addDevice(){
        this.props.history.push('/dashboard/add-device');
    }

    goBack(){
        this.props.history.push('/dashboard');
    }

    render() {
        return (
            <div>
                <h2 className="text-center">Devices list</h2>
                <div className= "row">
                    <button className = "btn btn-primary" onClick={this.addDevice}>Add Device</button>
                </div>
                <br/>
                <div className="row">
                    <table className="table table-bordered table-striped ">
                        <thead>
                        <tr>
                            <th>AverageConsumption</th>
                            <th>Description</th>
                            <th>Location</th>
                            <th>MaxEnergy</th>
                            <th>Actions</th>
                        </tr>
                        </thead>

                        <tbody>
                        {
                            this.state.devices.map(
                                device =>
                                    <tr key={device.id}>
                                        <td>{device.averageConsumption}</td>
                                        <td>{device.description}</td>
                                        <td>{device.location}</td>
                                        <td>{device.maxEnergy}</td>

                                        <td>
                                            <button onClick= {()=> this.updateDevice(device.id) } className= "btn btn-info">Update</button>
                                            <button style = {{marginLeft: "10px"}} onClick= {()=> this.deleteDevice(device.id) } className= "btn btn-danger">Delete</button>
                                            <button style = {{marginLeft: "10px"}} onClick= {()=> this.connectSensor(device.id) } className= "btn btn-success">Connect sensor</button>
                                        </td>
                                    </tr>
                            )
                        }
                        </tbody>

                    </table>
                </div>
                <div className= "row">
                    <button className = "btn btn-primary" onClick={this.goBack}>Back</button>
                </div>
            </div>
        );
    }
}

export default withRouter(Devices);