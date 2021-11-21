import React, {Component} from 'react';
import { withRouter } from 'react-router-dom';
import ClientService from "../../services/ClientService";

class User extends Component {
    constructor(props) {
        super(props);

        this.state = {
            id: this.props.match.params.id
        }

        this.viewHistoryConsumption = this.viewHistoryConsumption.bind(this);
        this.viewTodayConsumption = this.viewTodayConsumption.bind(this);
        this.viewChart = this.viewChart.bind(this);
        this.goBack = this.goBack.bind(this);
        this.deleteMonitorings = this.deleteMonitorings.bind(this);
    }

    viewHistoryConsumption(){
        this.props.history.push('/user/view-history-consumption/' + this.state.id);
    }

    viewTodayConsumption(){
        this.props.history.push('/user/view-today-consumption/' + this.state.id);
    }

    viewChart(){
        this.props.history.push('/user/chart/' + this.state.id);
    }

    deleteMonitorings(){
        ClientService.deleteMonitorings().then(res => {
            console.log("Delete successfully");
        });
    }

    goBack(){
        this.props.history.push('/');
    }

    render() {
        return (
            <div>
                <div className = "container">
                    <h2 className="text-center">User</h2>
                    <div className = "card-body">
                        <button className="btn btn-info" onClick={this.viewHistoryConsumption}>View history consumption</button>
                        <button className="btn btn-info" onClick={this.viewTodayConsumption} style={{marginLeft: "10px"}}>View today consumption</button>
                        <button className="btn btn-info" onClick={this.viewChart} style={{marginLeft: "10px"}}>View chart</button>
                        <button className="btn btn-danger" onClick={this.deleteMonitorings} style={{marginLeft: "10px"}}>Delete all monitorings</button>
                        <br/>
                        <br/>
                        <button className="btn btn-danger" onClick={this.goBack}>Log out</button>
                    </div>
                </div>
            </div>
        );
    }
}

export default withRouter(User);