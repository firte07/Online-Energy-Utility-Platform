import React from 'react';
import { useHistory } from 'react-router-dom';
import {NavLink} from "reactstrap";

class Home extends React.Component {



        /*// ENDPOINT-ul din spring la care facem requestul
        const url = `/login/process/${email}`;

        // Executia requestului
        fetch(url, {
            method: "get",
            headers: {
                Accept: "application/json, text/plain, *!/!*",
                "Content-Type": "application/json",
            },
        })
            .then((res) => res.json()) // Primul request returnat de back end pe care il transformam in json
            .then((res) => {
                if (res.password === password && res.email === email) { // verificam daca corespunde back-end cu front-end
                    // pentru ca nu facem tot timpul acest request salvam loginul userului in localStorage de unde il putem prelua
                    localStorage.setItem("user", JSON.stringify(res));
                    // trimitem la endpointul de dashboard datele userului provenite de la backend
                    history.push({
                        pathname: "/dashboard",
                        user: res,
                    });

                } else {
                    setStateLogin(false); // daca nu corespund setam variabila de stare a aplicatiei ca esuata
                }

            });
    };*/

    routeChange=()=> {
        let path = '/dashboard';
        let history = useHistory();
        history.push(path);
    }

    render() {

        return (

            <form>
                <h3>Sign In</h3>

                <div className="form-group">
                    <label>Username</label>
                    <input type="username" className="form-control" placeholder="Enter username" />
                </div>

                <div className="form-group">
                    <label>Password</label>
                    <input type="password" className="form-control" placeholder="Enter password" />
                </div>

                <div className="form-group">
                    <div className="custom-control custom-checkbox">
                        <input type="checkbox" className="custom-control-input" id="customCheck1" />
                        <label className="custom-control-label" htmlFor="customCheck1">Remember me</label>
                    </div>
                </div>

               <NavLink className="btn btn-primary btn-block" href="/dashboard">Submit</NavLink>
                {/*<p className="forgot-password text-right">
                    Forgot <a href="#">password?</a>
                </p>*/}
            </form>
        )
    };
}

export default Home
