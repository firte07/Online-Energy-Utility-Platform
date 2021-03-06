import React, {Component} from 'react';
import { withRouter } from 'react-router-dom';
import Calendar from "react-calendar";
import {Bar} from 'react-chartjs-2';
import ClientService from "../../services/ClientService";
import SockJsClient from 'react-stomp';
import { Notifications } from 'react-push-notification';
import addNotification from 'react-push-notification';

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
        this.setState({date});

        ClientService.getChart(this.state.id, date).then(res => {
            for(let i = 0; i<24;i++){
                console.log('state => ' + JSON.stringify(res.data[i].value));
            }
            for(let i = 0; i<24;i++){
                this.state.datasets[0].data[i] = 0;
            }

            for(let i = 0; i<24;i++){

                this.state.datasets[0].data[i] = res.data[i].value;
            }
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
                <SockJsClient url='https://firte-catalin-backend-1.herokuapp.com/websocket' topics={['/topic/notifications']}
                              onMessage={(msg) => {
                                  console.log(msg);
                                  addNotification({
                                      title: 'Warning',
                                      message: 'Maximum value exceeded!!',
                                      theme: 'red',
                                      native: true // when using native, your OS will handle theming.
                                  });
                              }}
                              ref={(client) => {
                                  this.clientRef = client
                              }}/>
            </div>
        );
    }
}

export default withRouter(Chart);