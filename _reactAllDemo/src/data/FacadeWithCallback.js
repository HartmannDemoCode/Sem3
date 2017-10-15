/**
 * Created by tha on 12-10-2017.
 */
const URL = 'http://localhost:4000/cars';
class DataStore{
    constructor(){
        this.cars = [];
        this.createCar = this.createCar.bind(this);
        this.loadData = this.loadData.bind(this);
    }
    loadData(callback){
        fetch(URL, {method: 'GET'}).then(function(data){
            return data.json();
        }).then(function(data){
            //console.log(data);
            const filtered = data.filter((e,idx)=>idx>10 && idx<=15);
            console.log('filtered: '+filtered.length);
            callback(filtered);
        }).catch((err)=>{
            console.log("Could not fetch data from server. Is the backend running?")
            callback(null);
        });
    }
    createCar(car, callback){
        /*const copyPart = ({make,model,year})=>{return {make,model,year}}; //destructuring object
        const carMinusId = copyPart(car);*/ //An easier way to remove a property from an object is using: delete
        const carMinusId = {...car};
        delete carMinusId.id;
        fetch(URL,
            {
            method: 'POST',
            headers: new Headers({'Content-Type': 'application/json'}),
            body: JSON.stringify(carMinusId)
            }
        ).then(function(data){
            console.log(data)
            return data.json();
        }).then(()=>this.loadData(callback));
    }
    editCar(car, callback){
        fetch(URL+'/'+car.id, {
            method: 'PUT',
            headers: new Headers({'Content-Type': 'application/json'}),
            body: JSON.stringify(car)
        })
            .then(data=>{return data.json();})
            .then(()=>this.loadData(callback));
    }
    deleteCar(car, callback){
        fetch(URL+'/'+car.id, {
            method: 'DELETE',
        }).then(()=>{this.loadData(callback);});
    };

}
export default DataStore;