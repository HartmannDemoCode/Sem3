// @author Thomas Hartmann - tha@cphbusiness.dk created on Oct 24, 2017 
var form = document.getElementsByTagName("form")[0];
var URL = "api/image/upload";
form.onsubmit = function (event) {
    var data = new FormData(form)
    //data.append('fileToUpload', file);
    
    fetch(URL, {
        method: 'POST',
        body: data
    });
    event.preventDefault();

};



