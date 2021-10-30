import React, {Component} from 'react';
import { withRouter } from 'react-router-dom';
import Calendar from "react-calendar";

class Chart extends Component {
    constructor(props) {
        super(props);

        this.state ={
            date: new Date()
        }

        this.onChange = this.onChange.bind(this);
    }

    onChange = (event) =>{
        {console.log(this.state.date)}
        this.setState({date: event.target.value});
    }


    render() {
        return (
            <div>
                <Calendar
                    onChange={this.onChange}
                    value={this.state.date}

                />
                <br/>

            </div>
        );
    }
}

export default withRouter(Chart);