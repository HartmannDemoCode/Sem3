/**
 * Created by tha on 05-10-2017.
 */
import React, { Component } from 'react';
export default class KiloPoundConverter extends Component{
    constructor(){
        super();
        this.state = { kilo: '', pound: ''};
        this.convert = this.convert.bind(this);
        this.empty = this.empty.bind(this);
    }
    convert(event){
        const target = event.target;

        if(target.id === 'kilo'){
            this.setState({kilo: target.value ,pound: target.value*2.20462262185});
        } else {
            this.setState({kilo: target.value/2.20462262185, pound: target.value});
        }
    }
    getConvertion(booleanValue, factorValue, number2convert){
        let a; let b;
        if(booleanValue){
            a = number2convert;
            b = number2convert*factorValue;
        }
        else {
            b = number2convert;
            a = number2convert/factorValue;
        }
        return {a: a, b: b};
    }
    empty(){
        this.setState({kilo: '', pound: ''});
    }

    render(){
        return (<div>
            Kilo: <input id="kilo" value={this.state.kilo} onChange={this.convert} onClick={this.empty}/><br/>
            Pound: <input id="pound" value={this.state.pound} onChange={this.convert} onClick={this.empty}/><br/>
        </div>)
    }
}
