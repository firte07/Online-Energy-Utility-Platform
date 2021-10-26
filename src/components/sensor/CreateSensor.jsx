import React, {Component} from 'react';
import SensorService from "../../services/SensorService";
import { withRouter } from 'react-router-dom';

class CreateSensor extends Component {
    constructor(props) {
        super(props);

        this.state = {
            description: '',
            maxValue: ''
        }

        this.changeDescription = this.changeDescription.bind(this);
        this.changeMaxValue = this.changeMaxValue.bind(this);
        this.addSensor = this.addSensor.bind(this);
    }

    componentDidMount() {

    }

    addSensor = (e) => {
        e.preventDefault();
        let sensor = {description: this.state.description, maxValue: this.state.maxValue};
        console.log('sensor => ' + JSON.stringify(sensor));

        SensorService.createSensor(sensor).then(res => {
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
                            <h3 className="text-center">Create Sensor</h3>
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
                                    <button className="btn btn-success" onClick={this.addSensor}>Save</button>
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

export default withRouter(CreateSensor);