import React, {Component} from 'react';
import ClientService from '../services/client-service'
import {
    Button,
    Card,
    CardHeader,
    Col,
    Modal,
    ModalBody,
    ModalHeader,
    Row
} from 'reactstrap';
import PersonForm from "../person/components/person-form";
import * as API_USERS from "../person/api/person-api";

class ClientContainer extends Component {
    constructor(props) {
        super(props)

        this.state = {
            clients: [],
            isLoaded: false
        }

        this.toggleForm = this.toggleForm.bind(this);
        this.componentDidMount = this.componentDidMount.bind(this);
        this.fetchClients = this.fetchClients.bind(this);
        this.updateClient = this.updateClient.bind(this);
    }

    componentDidMount() {
        this.fetchClients();
    }

    fetchClients(){
        ClientService.getClients().then((res) =>{
                this.setState({clients: res.data, isLoaded: true});
            }
        );
        /*return API_USERS.getPersons((result, status, err) => {
            if (result !== null && status === 200) {
                this.setState({
                    clients: result.data,
                    isLoaded: true
                });
            } else {
                this.setState(({
                    errorStatus: status,
                    error: err
                }));
            }
        });*/
    }

    updateClient(id){
        this.props.history.push(`/dashboard/person/update/${id}`);
    }

    toggleForm() {
        this.setState({selected: !this.state.selected});
    }

    reload() {
        this.setState({
            isLoaded: false
        });
        this.toggleForm();
  //      this.fetchClients();
    }

    render() {
        return (
            <div>
                <CardHeader>
                    <h2 className="text-center">Clients list</h2>
                </CardHeader>
                <Card>
                    <br/>
                    <Row>
                        <Col sm={{size: '8', offset: 1}}>
                            <Button color="primary" onClick={this.toggleForm}>Add Client</Button>
                        </Col>
                    </Row>
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
                                            </td>
                                        </tr>
                                )
                            }
                            </tbody>

                        </table>
                    </div>
                </Card>

                <Modal isOpen={this.state.selected} toggle={this.toggleForm}
                       className={this.props.className} size="lg">
                    <ModalHeader toggle={this.toggleForm}> Add client: </ModalHeader>
                    <ModalBody>
                        <PersonForm reloadHandler={this.reload}/>
                    </ModalBody>
                </Modal>

            </div>
        );
    }
}

export default ClientContainer;
