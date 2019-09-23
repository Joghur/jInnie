/**
 * table utils
 */

//constants
//const CONTENTDIV = document.querySelector("#content");

/**
 * Making table of all cars. Sorts and filters after which choices has been made
 */
function getAllCustomers(ev) {
    ev.preventDefault();
    console.log(ev.target.innerText);

    fetch("/jinnie/api/customer/all")
            .then(res => res.json())
            .then(data => {

                //sorting arrays
//                sorting(ev, data);

                // filtering
//                data = filtering(data);

                /**
                 * jsonList2Table() is in tables.js
                 * It converts a json-list into a html table
                 * arguments: a jsonlist and the tag identifier
                 */
                console.log("data before table.js: " + data +
                        "\ntypeof data: " + typeof data);
                if (typeof data !== 'undefined' && data.length > 0) {
                    jsonList2Table(data, "#content");
                } else {
                    var tableElement = CONTENTDIV.querySelector("table");
                    if (tableElement !== null) {
                        CONTENTDIV.querySelector("table").remove();
                    }
                    alert("\n\nNo data left!");
                }

                //For small screens
                CONTENTDIV.classList.add("table-responsive");

                //Events needs to be added after the table has been written
                //also changes headliners
//                addEvents();
            });
}

/**
 * Sort the data array after which column has been pressed
 * 
 * @param {type} event
 * @param {type} data
 */
function sorting(event, data) {
    switch (event.target.innerText) {
        case "price":
            data.sort((a, b) => parseFloat(a.price) - parseFloat(b.price));
            break;
        case "make":
            data.sort((a, b) => a.make.localeCompare(b.make));
            break;
        case "carID":
        case "Cars": //e.target = Cars first time table is made
            data.sort((a, b) => parseInt(a.carID) - parseInt(b.carID));
            break;
        case "year":
            data.sort((a, b) => parseInt(a.year) - parseInt(b.year));
            break;
    }
}

/**
 * Takes input and filters the data array after these inputs
 * Also makes some minor input checks
 * 
 * @param {Array} data
 * @return {filtering.data|Array}
 */
function filtering(data) {
    console.log("data in filtering: " + data);
    var makeInput = document.querySelector("#makeInput").value;
    var beforeYearInput = document.querySelector("#beforeYearInput").value;
    var afterYearInput = document.querySelector("#afterYearInput").value;
    var priceLessInput = document.querySelector("#priceLessInput").value;
    var priceMoreInput = document.querySelector("#priceMoreInput").value;

    //input checks
    if (beforeYearInput < afterYearInput
            && afterYearInput !== "" && beforeYearInput !== "") {
        alert("'Before year' has to be higher than 'After year'!");
        return;
    }

    if (priceLessInput < priceMoreInput
            && priceMoreInput !== "" && priceLessInput !== "") {
        alert("'Price less..' has to be higher than 'Price more..'!");
        return;
    }

    if (makeInput !== "") {
        let isWhatMake = (car) => {
            return car.make === document.querySelector("#makeInput").value;
        };
        var data = data.filter(isWhatMake);
    }

    if (beforeYearInput !== "") {
        let isBefore = (car) => {
            return car.year < document.querySelector("#beforeYearInput").value;
        };
        var data = data.filter(isBefore);
    }

    if (afterYearInput !== "") {
        let isAfter = (car) => {
            return car.year > document.querySelector("#afterYearInput").value;
        };
        var data = data.filter(isAfter);
    }

    if (priceLessInput !== "") {
        let isLowerPrice = (car) => {
            return car.price < document.querySelector("#priceLessInput").value;
        };
        var data = data.filter(isLowerPrice);
    }

    if (priceMoreInput !== "") {
        let isHigherPrice = (car) => {
            return car.price > document.querySelector("#priceMoreInput").value;
        };
        var data = data.filter(isHigherPrice);
    }
    console.log("data before returning - filtering: " + data);
    return data;
}

/**
 * events added and some html changes
 */
function addEvents() {
    console.log("Adding events");
    document.querySelector("#make").addEventListener("click", getAllCars);
    document.querySelector("#year").addEventListener("click", getAllCars);
    document.querySelector("#carID").addEventListener("click", getAllCars);
    document.querySelector("#price").addEventListener("click", getAllCars);
    document.querySelector("#beforeYearInput").addEventListener("change", getAllCars);
    document.querySelector("#afterYearInput").addEventListener("change", getAllCars);
    document.querySelector("#makeInput").addEventListener("change", getAllCars);
    document.querySelector("#priceLessInput").addEventListener("change", getAllCars);
    document.querySelector("#priceMoreInput").addEventListener("change", getAllCars);
}

/**
 * inserting forms and text on page
 * and updating H1 and H3
 */
function insertForms() {

    CONTENTDIV.innerHTML = "";
    document.querySelector("#h1content").innerHTML = "Cars for sale";
    document.querySelector("#h3content").innerHTML = "Press columns for sorting";
    document.querySelector("#jokeButtons").style = "display: none;";

    // making div to contain all ( for CSS )
    var inputDiv = document.createElement("DIV");
    inputDiv.id = "inputDiv";
    CONTENTDIV.appendChild(inputDiv);

    // inserting text and input fields ( named for CSS )
    inputDiv.appendChild(document.createTextNode("After year:"));
    var input = document.createElement("input");
    input.type = "text";
    input.id = "afterYearInput";
    inputDiv.appendChild(input);

    inputDiv.appendChild(document.createTextNode("Before year:"));
    var input = document.createElement("input");
    input.type = "text";
    input.id = "beforeYearInput";
    inputDiv.appendChild(input);

    inputDiv.appendChild(document.createTextNode("Make:"));
    var input = document.createElement("input");
    input.type = "text";
    input.id = "makeInput";
    inputDiv.appendChild(input);

    inputDiv.appendChild(document.createTextNode("Price more than:"));
    var input = document.createElement("input");
    input.type = "text";
    input.id = "priceMoreInput";
    inputDiv.appendChild(input);

    inputDiv.appendChild(document.createTextNode("Price less than:"));
    var input = document.createElement("input");
    input.type = "text";
    input.id = "priceLessInput";
    inputDiv.appendChild(input);
}

//Cars button eventlistener and other DOM manipulations
//document.querySelector("#carPage").addEventListener("click", insertForms);
document.querySelector("#customerPage").addEventListener("click", getAllCustomers);


