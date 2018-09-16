//Lille test fil for at holde det simpelt.
const root = document.getElementById("root");
const btn = document.getElementById("btnCreateNewCustomer");
const url = 'http://localhost:8084/restDemo/api/customer/2';
btn.onclick = ()=>{
    console.log("HHHHHH");
    const cus = {name:"hohi", age:33};
    fetch(url, {
        method: 'put',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(cus)
    }).then(resp=>resp.json()).then((data)=>{
        console.log(data);
    }).catch((err)=>console.log(err));
};

