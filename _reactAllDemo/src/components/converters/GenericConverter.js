/**
 * Created by tha on 05-10-2017.
 */
import React, { Component } from 'react';
export default class GenericConverter extends Component{
    constructor(){
        super(props);
        this.state = { a: '', b: ''};
        this.name = this.props.converterName;
        this.convertMethod = this.props.convertMethod;
        this.convert = this.convert.bind(this);
        this.empty = this.empty.bind(this);
    }
    convert(event){
        const result = getConvertion(event.id === 'a', 2.02, event.target.value)
        this.setState(getConvertion(tru));
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
            Kilo: <input id="a" value={this.state.a} onChange={this.convert} onClick={this.empty}/><br/>
            Pound: <input id="b" value={this.state.b} onChange={this.convert} onClick={this.empty}/><br/>
        </div>)
    }
}
