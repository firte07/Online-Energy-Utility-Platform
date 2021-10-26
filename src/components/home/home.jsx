import React, {Component} from 'react';
import { withRouter } from 'react-router-dom';

class Home extends Component {
    render() {
        return (
            <div>
                <h2 className="text-center">Welcome to the Online Energy Utility Platform</h2>
            </div>
        );
    }
}

export default withRouter(Home);
