import React, {Component} from 'react';
import { withRouter } from 'react-router-dom';

class User extends Component {
    constructor(props) {
        super(props);

        this.state = {
            id: this.props.match.params.id
        }

        this.viewHistoryConsumption = this.viewHistoryConsumption.bind(this);
        this.viewTodayConsumption = this.viewTodayConsumption.bind(this);
        this.viewChart = this.viewChart.bind(this);
    }


    viewHistoryConsumption(){
        this.props.history.push('/user/view-history-consumption/' + this.state.id);
    }

    viewTodayConsumption(){
        this.props.history.push('/user/view-today-consumption/' + this.state.id);
    }

    viewChart(){
        //this.props.history.push('/dashboard/devices');
    }

    render() {
        return (
            <div>
                <div className = "container">
                    <h2 className="text-center">User</h2>
                    <div className = "card-body">
                        <button className="btn btn-info" onClick={this.viewHistoryConsumption}>View history consumption</button>
                        <button className="btn btn-info" onClick={this.viewTodayConsumption} style={{marginLeft: "10px"}}>View today consumption</button>
                        <button className="btn btn-info" onClick={this.viewChart} style={{marginLeft: "10px"}}>View today</button>
                    </div>
                </div>
            </div>
        );
    }
}

export default withRouter(User);