import React, {Component} from 'react';
import SensorService from "../../services/SensorService";
import { withRouter } from 'react-router-dom';

class UpdateSensor extends Component {
    constructor(props) {
        super(props);

        this.state = {
            id: this.props.match.params.id,
            description: '',
            maxValue: ''
        }

        this.changeDescription = this.changeDescription.bind(this);
        this.changeMaxValue = this.changeMaxValue.bind(this);
        this.updateSensor = this.updateSensor.bind(this);
    }

    componentDidMount() {
        SensorService.getSensorById(this.state.id).then((res) => {
            let sensor = res.data;
            this.setState({
                description: sensor.description,
                maxValue: sensor.maxValue
            });
        });
    }

    updateSensor = (e) => {
        e.preventDefault();
        let sensor = {description: this.state.description, maxValue: this.state.maxValue};
        console.log('sensor => ' + JSON.stringify(sensor));

        SensorService.updateSensor(sensor, this.state.id).then(res => {
            this.props.history.push('/dashboard/sensors');
        });
    }


    changeDescription= (event) => {
        this.setState({description: event.target.value});
    }

    changeMaxValue= (event) => {
        this.setState({maxValue: event.target.value});
    }

    cancel(){
        this.props.history.push('/dashboard/sensors');
    }

    render() {
        return (
            <div>
                <div className = "container">
                    <div className = "row">
                        <div className = "card col-md-6 offset-md-3 offset-md-3">
                            <h3 className="text-center">Update Sensor</h3>
                            <div className = "card-body">
                                <form>
                                    <div className = "form-group">
                                        <label> Description: </label>
                                        <input placeholder="Description" name="description" className="form-control"
                                               value={this.state.description} onChange={this.changeDescription}/>
                                    </div>
                                    <div className = "form-group">
                                        <label> Max Value: </label>
                                        <input placeholder="MaxValue" name="maxValue" className="form-control"
                                               value={this.state.maxValue} onChange={this.changeMaxValue}/>
                                    </div>
                                    <button className="btn btn-success" onClick={this.updateSensor}>Save</button>
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

export default withRouter(UpdateSensor);