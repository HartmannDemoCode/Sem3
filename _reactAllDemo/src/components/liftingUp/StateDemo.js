/**
 * Created by tha on 23-10-2017.
 */
import React from "react"
import InputComp from './InputComp';
import ShowComp from './ShowComp';
export default class StateDemo extends React.Component{
    constructor(){
        super();
        this.state = {name: ''}
    }
    update =(event)=>{
        const name = event.target.value;
        this.setState({name: name});
    }
    render(){
        return(<div>
            <InputComp update={this.update}></InputComp>
            <ShowComp stateName={this.state.name}></ShowComp>
        </div>);
    }
}