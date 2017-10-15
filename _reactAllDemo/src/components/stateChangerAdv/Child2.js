/**
 * Created by tha on 22-03-2017.
 */
import React from 'react';
class Child2 extends React.Component{
    render(){
        return (
            <input onChange={this.props.update} type="text"/>
        );
    }
}
export default Child2;
