import React, {Component} from 'react';
import { withRouter } from 'react-router-dom';
import Calendar from "react-calendar";
import {Bar} from 'react-chartjs-2';
import ClientService from "../../services/ClientService";

class Chart extends Component {
    constructor(props) {
        super(props);

        this.state ={
            id: this.props.match.params.id,

            date: new Date(),

            labels: ['0', '1', '2', '3', '4', '5',
                '6', '7', '8', '9', '10',
                '11', '12', '13', '14', '15',
                '16', '17', '18', '19', '20',
                '21', '22', '23'],

            datasets: [
                {
                    label: 'Consumption',
                    backgroundColor: 'rgba(75,192,192,1)',
                    borderColor: 'rgba(0,0,0,1)',
                    borderWidth: 2,
                    data: [0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0, 0, 0]
                }
            ]
        }

        this.onChange = this.onChange.bind(this);
    }

    componentDidMount() {

    }

    onChange = date => {
        //console.log('state => ' + JSON.stringify(this.state.datasets.data[1]));
        console.log('state => ' + JSON.stringify(this.state.datasets[0].data[0]));
        this.setState({date});

        console.log('date => ' + JSON.stringify(date.toString()));
        console.log('date 2 => ' + JSON.stringify(this.state.date));

        let time = {date: this.state.date.toString()}

        const currentDayOfMonth = date.getDate();
        const currentMonth = date.getMonth(); // Be careful! January is 0, not 1
        const currentYear = date.getFullYear();

        const dateString = currentDayOfMonth + "-" + (currentMonth + 1) + "-" + currentYear;

        let time2 = {date: dateString}

        console.log('date 3 => ' + JSON.stringify(time));

        ClientService.getChart(this.state.id, time).then(res => {
           // console.log('Rezult => ' + JSON.stringify(res));
        });
    }


    render() {
        return (
            <div>
                <br/>
                <Calendar
                    onChange={this.onChange}
                    value={this.state.date}
                />
                <br/>
                <Bar
                    type={"bar"}
                    data={this.state}
                    options={{
                        title:{
                            display:true,
                            text:'Consumption per h',
                            fontSize:20
                        },
                        legend:{
                            display:true,
                            position:'right'
                        },
                        /*scales: {
                            yAxes: [
                                {
                                    ticks: {
                                        min: 0,
                                        max: 1200,
                                        stepSize: 1
                                    }
                                }
                            ]
                        }*/
                    }}
                />
            </div>
        );
    }
}

export default withRouter(Chart);