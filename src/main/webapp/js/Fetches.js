import 'bootstrap/dist/css/bootstrap.css'
import jokes from "./jokes";

document.querySelector("#getButton").addEventListener("click", getFunc);
document.querySelector("#postButton").addEventListener("click", postFunc);
document.querySelector("#putButton").addEventListener("click", putFunc);
document.querySelector("#deleteButton").addEventListener("click", deleteFunc);

function getFunc() {
    console.log("Get function");
    fetch("http://localhost:8080/the-facade-and-the-matching-endpoints/api/person/2")
        .then(res => res.json())
        .then(data => {
            document.querySelector("#div").innerHTML = "hejsa";
            let list = [];
            for(var key in data) {
                list.push(data[key]);
            }
            document.querySelector("#div").innerHTML = list;
        })
};

function postFunc() {
    console.log("post function");
    let options = {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
                "firstName": "Peter",
                "lastName": "Blonde",
                "phone": "11111111"
        })
    }

    fetch("http://localhost:8080/the-facade-and-the-matching-endpoints/api/person/", options);
};

function putFunc() {
    console.log("put function");
    let options = {
        method: "PUT",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            "id": 2,
            "firstName": "Hans",
            "lastName": "Pan",
            "phone": "545555555"
        })
    }

    fetch("http://localhost:8080/the-facade-and-the-matching-endpoints/api/person/2", options);
};

function deleteFunc() {
    console.log("delete function");
    let options = {
        method: "DELETE",
        headers: {
            'Content-Type': 'application/json'
        }
    }

    fetch("http://localhost:8080/the-facade-and-the-matching-endpoints/api/person/3", options);
};