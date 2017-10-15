/**
 * Created by tha on 14-10-2017.
 */
import React, { Component } from 'react';
import FacadeWithAwait from '../data/FacadeWithAwait';
import FacadeWithCallback from '../data/FacadeWithCallback';
import FacadeWithObservable from '../data/ObservableDemo';
class Data3Ways extends Component{
    constructor(){
        super();
        this.facadeA = new FacadeWithAwait();
        this.facadeC = new FacadeWithCallback();
        this.facadeO = new FacadeWithObservable();

        this.state = {loadMethod: 'callback', data:[]};
    }
    componentDidMount = ()=>{
        this.load();
    }
    load = ()=>{
        console.log("Data was loaded");
        this.facadeO.stop(); //stop getting data form observable every 3 second.
        if(this.loadMethod === 'async'){this.getDataAsync();}
        if(this.loadMethod === 'callback'){this.getDataWithCallback();}
        if(this.loadMethod === 'observable'){this.getDataFromObservable(); this.facadeO.start();}
    };
    getDataAsync = async ()=>{
        const data = await this.facadeA.loadData();
        this.setState({data});
    }
    getDataWithCallback = ()=>{
        this.facadeC.loadData((data)=>{this.setState({data: data});});
    }
    getDataFromObservable = () =>{
        this.facadeO.addObserver(this);
        this.facadeO.loadData(); //this will make the facade class call this class´s notify method.
    }
    notify = (data)=>{ this.setState({data: data});}

    render(){
        const data = this.state.data.map(car=><li key={car.id}>{car.id+':'+car.make+' '+car.model+' from '+car.year}</li>);
        // console.log(data);
        return (
            <div>
                <button onClick={()=>{this.loadMethod = 'callback'; this.load();}}>Use callback to load data</button>
                <button onClick={()=>{this.loadMethod = 'async'; this.load();}}>Use Await to load data</button>
                <button onClick={()=>{this.loadMethod = 'observable'; this.load();}}>Use observer to load data</button>
            <ul>{data}</ul>
            </div>
            )
    }
}
export default Data3Ways;

