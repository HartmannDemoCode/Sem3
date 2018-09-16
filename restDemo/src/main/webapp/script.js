const root = document.getElementById("root");
const btn = document.getElementById("btnCreateNewCustomer");
const url = 'http://localhost:8084/restDemo/api/customer/';
const resource = 'allasarray';

console.log("url: ", url + 'all');
const update = () => {
    let content = '<h2>List of all customers</h2>';
    fetch(url + resource, {method: 'GET'})
            .then((response) => response.json())
            .then((data) => {
                content += '<table><tr><th>ID</th><th>Name</th><th>Age</th><th></th><th></th></tr>';
                content += data.map(el => `<tr><td>${el.id}</td><td>${el.name}</td><td>${el.age}</td><td>
                <a href="#" class="btnedit" id=${el.id}>edit</a> / 
                <a href="#" class="btndelete" id=${el.id}>delete</a>
                </td><td><span id="editform${el.id}"/></td></tr>`).join("");
                content += '</table>';
                root.innerHTML = content;
                const del = document.getElementsByClassName("btndelete");
                const edit = document.getElementsByClassName("btnedit");
                clickHandler(del);
                clickHandler(edit);
            }).catch(error => {
        console.log('get error: ', error);
    });
};
update();
btn.onclick = () => {
    const name = document.getElementById("name").value;
    const age = document.getElementById("age").value;
    const data = {name, age};

    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json; charset=utf-8'
        },
        body: JSON.stringify(data) // body data type must match "Content-Type" header
    }).then((res) => res.json()).then((data) => {
        console.log('data from post', data);
        update();
    }).catch(error => console.log(error));
};
//take a collection of clickable elements like in this case buttons of either edit og delete.
const clickHandler = (elements) => {
    [...elements].forEach(el => {
        el.onclick = (event) => { //clicking the edit button on any of the customers.
            const className = event.target.className;
            const id = event.target.id;
            if (className === 'btndelete')
                deleteData(event.target.id);
            if (className === 'btnedit') {

                const editform = document.getElementById(`editform${id}`);
                editform.innerHTML = `<input type="text" id="newName${id}" value="">
                <input type="text" id="newAge${id}" value="">
                <button class="submitEdit" id="${id}">Submit</button>`;


                [...document.getElementsByClassName("submitEdit")].forEach(el => el.onclick = (event) => {
                        const newName = document.getElementById(`newName${id}`).value;
                        const newAge = document.getElementById(`newAge${id}`).value;
                        updateData(event.target.id, newName, newAge);
                        editform.innerHTML = '';
                    });

            }
        };
    });
};
// IKKE FÆRDIG - data sendes ikke med i request body af en eller anden grund.
const updateData = (id, name, age) => {
    const cus = {name, age};
    fetch(url + id, {
        method: 'put',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(cus)
    }).then(resp => resp.json()).then((data) => {
        console.log(data);
        update();
    }).catch((err) => console.log(err));
};

const deleteData = (id) => {
//    console.log(`id: ${id}`);
    fetch(url + id, {method: 'DELETE'}).then(resp => resp.json()).then((data) => {
        console.log('data from delete: ', data);
        update();
    }).catch((error) => {
        console.log(error);
    });
};

