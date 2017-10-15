/**
 * Created by tha on 09-10-2017.
 */
import React, {Component} from 'react';

const imgs = ['img/boat1.jpg', 'img/boat2.jpg'];
export default class StateChanger extends Component{
    constructor(){
        super(); //a child class constructor cannot make use of 'this' until super() has been called
        this.state = { img: imgs[0] }; //initial state is setup in the constructor
        // this.changeImg1 = this.changeImg1.bind(this); //Bind 'this' to the function: Now inside the function 'this' refers to the class
        // this.changeImg2 = this.changeImg2.bind(this);

    }
    changeImg1(){
        this.setState({img: imgs[0]});
    }
    changeImg2(){
        this.setState({img: imgs[1]});
    }
    render(){
        return(
            <div>
                <h1>The state changer: </h1>
                <h2 style={{color: `${this.state.status}`}}>{this.props.name} is now: {this.state.status}</h2>
                <img src={this.state.img}/>
                <Image path={this.state.img} text="bla vlalblablalblabla"/>
                {/*<img src={ require(this.state.imgs[0]) } />*/}
                <button onClick={this.changeImg1.bind(this)}>Change to image 1</button><span> </span>
                <button onClick={this.changeImg2.bind(this)}>Change to image 2</button>
            </div>
        );
    }
}
const Image = (props)=>{return <div id="imageholder"><img src={props.path} /><p>{props.text}</p></div>}