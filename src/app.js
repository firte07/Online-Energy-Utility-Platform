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
import Sensors from "./components/sensor/Sensors";
import CreateSensor from "./components/sensor/CreateSensor";
import UpdateSensor from "./components/sensor/UpdateSensor";
import Home from "./components/home/Home";
import Dashboard from "./components/Dashboard";
import Registration from "./components/Registration";
import User from "./components/user/User";
import ConnectSensor from "./components/device/ConnectSensor";
import ConnectDevice from "./components/client/ConnectDevice";

//TODO: validari pentru crud

function App() {
  return (
    <div>
        <Router>
            <div className = "container">
                <HeaderComponent/>
                    <div className = "container">
                        <Switch>
                            <Route exact path = '/' render={() => <Home/>}/>

                            <Route exact path = '/dashboard' render={() => <Dashboard/>}/>

                            <Route path = '/dashboard/clients' render={() => <Clients/>}/>
                            <Route path = '/dashboard/add-client' render={() => <CreateClient/>}/>
                            <Route path = '/dashboard/update-client/:id' render={() => <UpdateClient/>}/>
                            <Route path = '/dashboard/connect-device/:id' render={() => <ConnectDevice/>}/>

                            <Route path = '/dashboard/devices' render={() => <Devices/>}/>
                            <Route path = '/dashboard/add-device' render={() => <CreateDevice/>}/>
                            <Route path = '/dashboard/update-device/:id' render={() => <UpdateDevice/>}/>
                            <Route path = '/dashboard/connect-sensor/:id' render={() => <ConnectSensor/>}/>


                            <Route path = '/dashboard/sensors' render={() => <Sensors/>}/>
                            <Route path = '/dashboard/add-sensor' render={() => <CreateSensor/>}/>
                            <Route path = '/dashboard/update-sensor/:id' render={() => <UpdateSensor/>}/>

                            <Route exact path = '/registration' render={() => <Registration/>}/>

                            <Route exact path = '/user' render={() => <User/>}/>
                        </Switch>
                    </div>
            </div>
        </Router>
    </div>

  );
}

export default App;
