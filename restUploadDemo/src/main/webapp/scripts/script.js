// @author Thomas Hartmann - tha@cphbusiness.dk created on Oct 24, 2017 
var form = document.getElementsByTagName("form")[0];
var URL = "api/image/upload";
form.onsubmit = function (event) {
    console.log("HEHEHE");
    var fileElement = document.getElementById("file");
    if (fileElement.value) {
        console.log("There is a file");
        var file = fileElement.value;
        
    }
    var data = new FormData(form)
    //data.append('fileToUpload', file);
    
    fetch(URL, {
        method: 'POST',
//        headers: {
//            //'Accept': 'application/json, text/plain, */*',
//            'Content-Type': 'application/json'
//        },
        body: data
    });
    event.preventDefault();

};



