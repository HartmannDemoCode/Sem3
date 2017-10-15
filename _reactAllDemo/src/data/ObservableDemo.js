/**
 * Created by tha on 12-10-2017.
 */
const URL = "http://localhost:4000/cars";
class CarStore{
    constructor(){
        this._cars = [];
        //this.interval = this.start();

    }
    addObserver = (observer)=>{this.observer = observer;} //We can add only one observer to this observable
    start = ()=>{
        this.interval = setInterval(()=>{this.loadData();},3000);

    }
    stop = ()=>{
        clearInterval(this.interval);
    }
    get cars() {
        return this._cars;
    }

    loadData () {
        console.log("loading.....")
        fetch(URL).
        then(res => res.json()).
        then(data => {
            this._cars = data;
            //const filtered = data.filter((e,idx)=>idx<=5);
            this.observer.notify(data); //Just to show that we can send some data back to the observer: CarApp.
        })
        //     .catch((err)=>{
        //     console.log("Could not fetch data from server. Is the backend running?");
        // });
    }
}

export default CarStore;

