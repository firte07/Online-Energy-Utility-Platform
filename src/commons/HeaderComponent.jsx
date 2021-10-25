import React, {Component} from 'react';
import icon from '../icon.png'

class HeaderComponent extends Component {
    constructor(props) {
        super(props);

        this.state = {

        }
    }
    render() {
        return (
            <div>
                <header>
                    <nav className="navbar navbar-expand-md navbar-dark bg-dark">
                        <img src={icon} width={"50"}
                             height={"35"} />
                        <div>
                            <h3 className = 'online-title'>Online energy utility platform</h3>
                        </div>
                    </nav>
                </header>
            </div>
        );
    }
}

export default HeaderComponent;