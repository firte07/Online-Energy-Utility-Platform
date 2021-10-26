import React, {Component} from 'react';
import ClientService from "../../services/ClientService";
import { withRouter } from 'react-router-dom';

class Clients extends Component {

    constructor(props) {
        super(props);

        this.state = {
            clients: []
        }

        this.addClient = this.addClient.bind(this);
        this.deleteClient = this.deleteClient.bind(this);
        this.updateClient = this.updateClient.bind(this);
        this.goBack = this.goBack.bind(this);
    }

    componentDidMount() {
        ClientService.getClients().then((res) => {
            this.setState({clients: res.data});
        });
    }

    deleteClient(id){
        ClientService.deleteClient(id).then(res => {
            this.setState({
                clients: this.state.clients.filter(client => client.id !== id)
            });
        });
    }

    updateClient(id){
        this.props.history.push(`/dashboard/update-client/${id}`);
    }

    addClient(){
        this.props.history.push('/dashboard/add-client');
    }

    goBack(){
        this.props.history.push('/dashboard');
    }

    render() {
        return (
            <div>
                <h2 className="text-center">Clients list</h2>
                <div className= "row">
                    <button className = "btn btn-primary" onClick={this.addClient}>Add Client</button>
                </div>
                <br/>
                <div className="row">
                    <table className="table table-bordered table-striped ">
                        <thead>
                        <tr>
                            <th>Name</th>
                            <th>Address</th>
                            <th>Age</th>
                            <th>Actions</th>
                        </tr>
                        </thead>

                        <tbody>
                        {
                            this.state.clients.map(
                                client =>
                                    <tr key={client.id}>
                                        <td>{client.name}</td>
                                        <td>{client.address}</td>
                                        <td>{client.age}</td>
                                        <td>
                                            <button onClick= {()=> this.updateClient(client.id) } className= "btn btn-info">Update</button>
                                            <button style = {{marginLeft: "10px"}} onClick= {()=> this.deleteClient(client.id) } className= "btn btn-danger">Delete</button>
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

export default withRouter(Clients);