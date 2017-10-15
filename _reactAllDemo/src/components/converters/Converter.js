/**
 * Created by tha on 10-10-2017.
 */
import React from 'react';

class Converter extends React.Component {
    constructor(){
        super();
        this.state = {kilo: 0, pound: 0};
        this.convert = this.convert.bind(this);
    }
    convert(event){
        //console.log(event.target.id);
        const who = event.target.id;
        const value = event.target.value;
        if(who === 'kilo'){
            const pound = value * 2.20462;
            this.setState({kilo: value, pound: pound});
        } else if(who === 'pound'){
            const kilo = value / 2.20462;
            this.setState({kilo: kilo, pound: value});
        }
    }
    render(){
        return (<div>
            <h1>Kilo to Pound Converter</h1>
            <span>Kilo:</span><Input id="kilo"  onChange={this.convert} value={this.state.kilo}/><br/>
            <span>Pound:</span><Input id="pound"  onChange={this.convert} value={this.state.pound}/>
        </div>);
    }
}
export default Converter;
const Input = (props)=>{return <input type="number" id={props.id} onChange={props.onChange} value={props.value}/>};