import React, {Component} from 'react';
import SensorService from "../../services/SensorService";
import { withRouter } from 'react-router-dom';

class Sensors extends Component {

    constructor(props) {
        super(props);

        this.state = {
            sensors: []
        }

        this.addSensor = this.addSensor.bind(this);
        this.deleteSensor = this.deleteSensor.bind(this);
        this.updateSensor = this.updateSensor.bind(this);
        this.goBack = this.goBack.bind(this);
    }

    componentDidMount() {
        SensorService.getSensors().then((res) => {
            this.setState({sensors: res.data});
        });
    }

    deleteSensor(id){
        SensorService.deleteSensor(id).then(res => {
            this.setState({
                sensors: this.state.sensors.filter(sensor => sensor.id !== id)
            });
        });
    }

    updateSensor(id){
        this.props.history.push(`/dashboard/update-sensor/${id}`);
    }

    addSensor(){
        this.props.history.push('/dashboard/add-sensor');
    }

    goBack(){
        this.props.history.push('/dashboard');
    }

    render() {
        return (
            <div>
                <h2 className="text-center">Sensors list</h2>
                <div className= "row">
                    <button className = "btn btn-primary" onClick={this.addSensor}>Add Sensor</button>
                </div>
                <br/>
                <div className="row">
                    <table className="table table-bordered table-striped ">
                        <thead>
                        <tr>
                            <th>Description</th>
                            <th>MaxValue</th>
                        </tr>
                        </thead>

                        <tbody>
                        {
                            this.state.sensors.map(
                                sensor =>
                                    <tr key={sensor.id}>
                                        <td>{sensor.description}</td>
                                        <td>{sensor.maxValue}</td>
                                        <td>
                                            <button onClick= {()=> this.updateSensor(sensor.id) } className= "btn btn-info">Update</button>
                                            <button style = {{marginLeft: "10px"}} onClick= {()=> this.deleteSensor(sensor.id) } className= "btn btn-danger">Delete</button>
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

export default withRouter(Sensors);