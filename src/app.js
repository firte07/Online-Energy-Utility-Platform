import './App.css';
import React from "react";
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom'
import HeaderComponent from "./commons/HeaderComponent";
import Clients from "./components/client/Clients";
import CreateClient from "./components/client/CreateClient";
import UpdateClient from "./components/client/UpdateClient";
import Devices from "./components/device/Devices";
import CreateDevice from "./components/device/CreateDevice";
import UpdateDevice from "./components/device/UpdateDevice";

function App() {
  return (
    <div>
        <Router>
            <div className = "container">
                <HeaderComponent/>
                    <div className = "container">
                        <Switch>
                            <Route exact path = '/' render={() => <Clients/>}/>

                            <Route path = '/clients' render={() => <Clients/>}/>
                            <Route path = '/add-client' render={() => <CreateClient/>}/>
                            <Route path = '/update-client/:id' render={() => <UpdateClient/>}/>

                            <Route path = '/devices' render={() => <Devices/>}/>
                            <Route path = '/add-device' render={() => <CreateDevice/>}/>
                            <Route path = '/update-device/:id' render={() => <UpdateDevice/>}/>
                        </Switch>
                    </div>
            </div>
        </Router>
    </div>

  );
}

export default App;
