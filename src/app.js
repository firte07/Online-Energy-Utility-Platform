import React from 'react'
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom'
import NavigationBar from './navigation-bar'
import Home from './home/home';
import PersonContainer from './person/person-container'

import ErrorPage from './commons/errorhandling/error-page';
import styles from './commons/styles/project-style.css';
import Dashboard from "./dashboard/dashboard";
import UpdatePerson from "./person/components/update-person";
import ClientContainer from "./client/client-container";
import UpdateClient from "./client/update-client";

class App extends React.Component {


    render() {

        return (
            <div className={styles.back}>
            <Router>
                <div>
                    <NavigationBar />
                    <Switch>

                        <Route
                            exact
                            path='/'
                            render={() => <Home/>}
                        />


                        <Route
                            exact
                            path='/dashboard'
                            render={() => <Dashboard/>}
                        />



                        <Route
                            exact
                            path='/dashboard/person'
                            render={() => <ClientContainer/>}
                        />


                        <Route
                            exact
                            path='/dashboard/person/update'
                            render={() => <UpdateClient/>}
                        />


                        <Route
                            exact
                            path='/dashboard/update-person/:id'
                            render={() => <UpdatePerson/>}
                        />

                        <Route
                            exact
                            path='/person'
                            render={() => <PersonContainer/>}
                        />

                        {/*Error*/}
                        <Route
                            exact
                            path='/error'
                            render={() => <ErrorPage/>}
                        />

                        <Route render={() =><ErrorPage/>} />
                    </Switch>
                </div>
            </Router>
            </div>
        )
    };
}

export default App
