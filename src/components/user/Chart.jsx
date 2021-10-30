import React, {Component} from 'react';
import { withRouter } from 'react-router-dom';
import Calendar from "react-calendar";
import {Bar} from 'react-chartjs-2';

class Chart extends Component {
    constructor(props) {
        super(props);

        this.state ={
            date: new Date(),

            labels: ['00', '1', '2', '3', '4', '5',
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
                    data: [65, 59, 80, 81, 56, 0, 12]
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