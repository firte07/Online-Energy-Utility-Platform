import React, {Component} from 'react';
import { withRouter } from 'react-router-dom';

class User extends Component {
    render() {
        return (
            <div>
                <h1 className="text-center">Aici client</h1>
            </div>
        );
    }
}

export default withRouter(User);