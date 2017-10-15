/**
 * Created by tha on 12-10-2017.
 */
import React, { Component } from 'react';
import DataStore from '../data/FacadeWithCallback';
export default class CarApp extends Component {
    constructor(){
        super();

        this.store = new DataStore();
        this.state={_data: [], car:{id:'',make:'',model:'',year:''}};
        this.store.loadData((data)=>{this.setState({_data:data});}); //the callback is called from within the fetch().then() method in the DataStore whenever data is provided from the server

        this.createCar = this.createCar.bind(this);
        this.deleteCar = this.deleteCar.bind(this);
        this.editCar = this.editCar.bind(this);
        this.update = this.update.bind(this);
        this.chooseCar = this.chooseCar.bind(this);
    }
    createCar() {
        this.store.createCar(this.state.car, (data)=>{this.setState({_data: data})});
    }
    editCar(){
        this.store.editCar(this.state.car, (data)=>{this.setState({_data:data})});
    }
    deleteCar(){
        this.store.deleteCar(this.state.car, (data)=>{this.setState({_data:data})});
    }
    update(event){
        let car = {...this.state.car}; //shallow copy
        switch(event.target.id){
            case('id'): car.id = event.target.value; break;
            case('make'):car.make = event.target.value; break;
            case('model'):car.model = event.target.value; break;
            case('year'):car.year = event.target.value; break;
            default:break;
        }
        this.setState({car: car}, ()=>console.log(this.state.car));
    }
    chooseCar(car){
        console.log(typeof car);
        this.setState({car:car});
    }
    render(){
        //console.log(this.state._data);
        const cars = this.state._data.map((car)=>{

            return <div key={car.id}><a id={car} onClick={()=>{this.chooseCar(car)}} >{car.id}:{car.make} {car.model} from {car.year}</a></div>;
        });
        return <div>
            <input value={this.state.car.make} onChange={this.update} id="make" placeholder="car make"/>
            <input value={this.state.car.model} onChange={this.update} id="model" placeholder="car model"/>
            <input value={this.state.car.year} onChange={this.update} id="year" placeholder="car year"/>
            <button onClick={this.createCar}>Create a car</button><br/>
            <input value={this.state.car.id} onChange={this.update} id="id" placeholder="car id"/>
            <button onClick={this.editCar}>Edit a car</button>
            <button onClick={this.deleteCar}>Delete a car</button><br/>

            <ul>
                {cars}
            </ul>
            <CarDetail car={this.state.car}/>
            </div>
    }
}
const CarDetail = (props)=>{
    return(
        <div id="cardetails">
            <h2>Details on car no {props.car.id}</h2>
            {props.car.make} {props.car.model} form {props.car.year}
        </div>
    );
};