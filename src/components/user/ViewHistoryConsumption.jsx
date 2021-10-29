import React, {Component} from 'react';
import { withRouter } from 'react-router-dom';
import ClientService from "../../services/ClientService";

class ViewHistoryConsumption extends Component {
    constructor(props) {
        super(props);

        this.state = {
            id: this.props.match.params.id,
            view: []
        }

        this.goBack = this.goBack.bind(this);
    }

    componentDidMount() {
        ClientService.getViewHistory(this.state.id).then((res) => {
            this.setState({view: res.data});
        });
    }

    goBack(){
        this.props.history.push('/user/' + this.state.id);
    }

    render() {
        return (
            <div>
                <h2 className="text-center">Consumption history</h2>
                <br/>
                <div className="row">
                    <table className="table table-bordered table-striped ">
                        <thead>
                        <tr>
                            <th>Device</th>
                            <th>Location</th>
                            <th>Sensor</th>
                            <th>Max value of sensor</th>
                            <th>Average consumption</th>
                            <th>Max energy</th>
                            <th>Total consumption</th>
                        </tr>
                        </thead>

                        <tbody>
                        {
                            this.state.view.map(
                                view =>
                                    <tr key={view.id}>
                                        <td>{view.deviceDescription}</td>
                                        <td>{view.location}</td>
                                        <td>{view.sensorDescription}</td>
                                        <td>{view.maxValueSensor}</td>
                                        <td>{view.averageConsumption}</td>
                                        <td>{view.maxEnergy}</td>
                                        <td>{view.totalConsumption}</td>
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

export default withRouter(ViewHistoryConsumption);