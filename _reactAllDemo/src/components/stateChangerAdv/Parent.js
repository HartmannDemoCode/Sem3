/**
 * Created by tha on 22-03-2017.
 */
import React, {Component} from 'react';
import Child1 from './Child1';
import Child2 from './Child2';
class Parent extends Component {
    constructor() {
        super();
        this.state = {txt: 'Besked fra parent'};
        this.update = this.update.bind(this);
    }

    update(e) {
        const txt = e.target.value;
        console.log(txt);
        this.setState({txt: txt});

    }

    render() {
        const txt = this.state.txt;
        return <div>
            <Child1 message={txt}/>

            {this.props.children}
        </div>

    }
}
export default Parent;




