/**
 * Created by tha on 23-10-2017.
 */
import React from "react"
export default class ShowComp extends React.Component{
    render(){
        return <div>Show content:
            {this.props.stateName}
        </div>
    }
}