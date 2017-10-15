/**
 * Created by tha on 14-10-2017.
 */
const URL = 'http://localhost:4000/cars';

export default class FacadeWithAwait{
    async loadData(){
        const data = await fetch(URL);
        const jsonData = await data.json();
        const filtered = jsonData.filter((e,idx)=>idx>5 && idx <= 10);
        return filtered;
    }
}