import React, {Component} from 'react';
import ClientService from "../../services/ClientService";
import { withRouter } from 'react-router-dom';

class UpdateClient extends Component {
    constructor(props) {
        super(props);

        this.state = {
            id: this.props.match.params.id,
            name: '',
            address: '',
            age: ''
        }

        this.changeName = this.changeName.bind(this);
        this.changeAddress = this.changeAddress.bind(this);
        this.changeAge = this.changeAge.bind(this);
        this.updateClient = this.updateClient.bind(this);
    }

    componentDidMount() {
        ClientService.getClientById(this.state.id).then((res) => {
           let client = res.data;
           this.setState({
              name: client.name,
              address: client.address,
              age: client.age
           });
        });
    }

    updateClient = (e) => {
        e.preventDefault();
        let client = {name: this.state.name, age: this.state.age, address: this.state.address};
        console.log('client => ' + JSON.stringify(client));

        ClientService.updateClient(client, this.state.id).then(res => {
            this.props.history.push('/dashboard/clients');
        });
    }

    changeName= (event) => {
        this.setState({name: event.target.value});
    }

    changeAddress= (event) => {
        this.setState({address: event.target.value});
    }

    changeAge= (event) => {
        this.setState({age: event.target.value});
    }

    cancel(){
        this.props.history.push('/dashboard/clients');
    }

    render() {
        return (
            <div>
                <div className = "container">
                    <div className = "row">
                        <div className = "card col-md-6 offset-md-3 offset-md-3">
                            <h3 className="text-center">Update Client</h3>
                            <div className = "card-body">
                                <form>
                                    <div className = "form-group">
                                        <label> Name: </label>
                                        <input placeholder="Name" name="name" className="form-control"
                                               value={this.state.name} onChange={this.changeName}/>
                                    </div>
                                    <div className = "form-group">
                                        <label> Age: </label>
                                        <input placeholder="Age" name="age" className="form-control"
                                               value={this.state.age} onChange={this.changeAge}/>
                                    </div>
                                    <div className = "form-group">
                                        <label> Address: </label>
                                        <input placeholder="Address" name="address" className="form-control"
                                               value={this.state.address} onChange={this.changeAddress}/>
                                    </div>

                                    <button className="btn btn-success" onClick={this.updateClient}>Save</button>
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

export default withRouter(UpdateClient);