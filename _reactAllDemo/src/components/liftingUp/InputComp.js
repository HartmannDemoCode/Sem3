/**
 * Created by tha on 23-10-2017.
 */
import React from "react"
export default class InputComp extends React.Component{
    constructor(){
        super();

    }
    componentWillMount(){

    }
    render(){
        return (<div>
            <input type="text" onChange={this.props.update}/>

        </div>);
    }
}