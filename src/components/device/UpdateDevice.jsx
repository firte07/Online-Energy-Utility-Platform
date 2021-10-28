import React, {Component} from 'react';
import DeviceService from "../../services/DeviceService";
import { withRouter } from 'react-router-dom';

class UpdateDevice extends Component {
    constructor(props) {
        super(props);

        this.state = {
            id: this.props.match.params.id,
            averageConsumption: '',
            description: '',
            location: '',
            maxEnergy: ''
        }

        this.changeAverageConsumption = this.changeAverageConsumption.bind(this);
        this.changeDescription = this.changeDescription.bind(this);
        this.changeLocation = this.changeLocation.bind(this);
        this.changeMaxEnergy = this.changeMaxEnergy.bind(this);
        this.updateDevice = this.updateDevice.bind(this);
    }

    componentDidMount() {
        DeviceService.getDeviceById(this.state.id).then((res) => {
            let device = res.data;
            this.setState({
                averageConsumption: device.averageConsumption,
                description: device.description,
                location: device.location,
                maxEnergy: device.maxEnergy
            });
        });
    }

    updateDevice = (e) => {
        e.preventDefault();
        let device = {averageConsumption: this.state.averageConsumption,
            description: this.state.description,
            location: this.state.location,
            maxEnergy: this.state.maxEnergy};
        console.log('device => ' + JSON.stringify(device));

        DeviceService.updateDevice(device, this.state.id).then(res => {
            this.props.history.push('/dashboard/devices');
        });
    }


    changeAverageConsumption= (event) => {
        this.setState({averageConsumption: event.target.value});
    }

    changeLocation= (event) => {
        console.log('Bla bla update');
        this.setState({location: event.target.value});
    }

    changeDescription= (event) => {
        this.setState({description: event.target.value});
    }

    changeMaxEnergy= (event) => {
        this.setState({maxEnergy: event.target.value});
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
                            <h3 className="text-center">Create Client</h3>
                            <div className = "card-body">
                                <form>
                                    <div className = "form-group">
                                        <label> Average Consumption: </label>
                                        <input placeholder="AverageConsumption" name="averageConsumption" className="form-control"
                                               value={this.state.averageConsumption} onChange={this.changeAverageConsumption}/>
                                    </div>
                                    <div className = "form-group">
                                        <label> Description: </label>
                                        <input placeholder="Description" name="description" className="form-control"
                                               value={this.state.description} onChange={this.changeDescription}/>
                                    </div>
                                    <div className = "form-group">
                                        <label> Location: </label>
                                        <input placeholder="Location" name="location" className="form-control"
                                               value={this.state.location} onChange={this.changeLocation}/>
                                    </div>
                                    <div className = "form-group">
                                        <label> Max Energy: </label>
                                        <input placeholder="MaxEnergy" name="maxEnergy" className="form-control"
                                               value={this.state.maxEnergy} onChange={this.changeMaxEnergy}/>
                                    </div>

                                    <button className="btn btn-success" onClick={this.updateDevice}>Save</button>
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

export default withRouter(UpdateDevice);