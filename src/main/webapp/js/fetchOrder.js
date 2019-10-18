/**
 * GET
 */
function getAllOrders() {
    console.log("get function");

    fetch("/jinnie/api/order/all")
            .then(res => res.json())
            .then(data => {
                console.log("data: ", data)
                /**
                 * jsonList2Table() is in tables.js
                 * It converts a json-list into a html table
                 * arguments: a jsonlist and the tag identifier
                 */
                console.log("data before table.js: " + data +
                        "\ntypeof data: " + typeof data);
                var keyList = [
                    "ordrerID",
                    "invoiceID",
                    "customerID",
                    "invoiceDate",
                    "workDoneDate",
                    "totalPrice",
                    "orderState"
                ];
                if (typeof data !== 'undefined' && data.length > 0) {
                    list2Table(data, "#content", keyList);
                    linking("#content");
                } else {
                    var tableElement = CONTENTDIV.querySelector("table");
                    if (tableElement !== null) {
                        CONTENTDIV.querySelector("table").remove();
                    }
                    alert("\n\nNo data left!");
                }
                insertNewOrderModal(); // new overlay
            });
}

/**
 * POST
 */
function postNewOrderFunc(e) {
    console.log("post function");

    var customerID;
    for (i = 0; i < selCustomer.length; i++) {
        currentOption = selCustomer[i];
        if (currentOption.selected === true) {
            console.log(currentOption.value);
            customerID = Number(currentOption.value.split(" ")[0]);
        }
    }

    var orderLines = [];
    for (i = 0; i < selItemtypes.length; i++) {
        currentOption = selItemtypes[i];
        if (currentOption.selected === true) {
            orderLines.push(
                    {
                        "itemTypeID": currentOption.value.split(" ")[0],
                        "quantity": 1
                    }
            );
        }
    }

    var workDoneDate = document.querySelector("#datepicker").value;
    console.log("customerID", customerID);
    console.log("orderLines", orderLines);
//    console.log("quantity", quantity);
    console.log("workDoneDate", workDoneDate);

    let options = {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            "customerID": customerID,
            "workDoneDate": workDoneDate,
            "orderLines": orderLines
        })
    };

    console.log("options", options);
    fetch("/jinnie/api/order/new", options);
    getAllOrders();
}

/**
 * DELETE
 */
function deleteOrderFunc(id) {
    console.log("delete function");
    let options = {
        method: "DELETE",
        headers: {
            'Content-Type': 'application/json'
        }
    };
    fetch("/jinnie/api/order/" + id, options);
    getAllOrders();
}

/**
 * 
 */
function fillSelectionLists() {

    $(function () {

//        $("#datepicker").setDefaults(datepicker.regional['dk']);
//        $("#datepicker").datepicker("option", "dateFormat", "dd/M-yy");
//        $("#datepicker").datepicker.setDefaults( $.datepicker.regional[ "fr" ] );
        $("#datepicker").datepicker({
            closeText: "Luk",
            prevText: "&#x3C;Forrige",
            nextText: "Næste&#x3E;",
            currentText: "Idag",
            monthNames: ["januar", "februar", "marts", "april", "maj", "juni",
                "juli", "august", "september", "oktober", "november", "december"],
            monthNamesShort: ["jan", "feb", "mar", "apr", "maj", "jun",
                "jul", "aug", "sep", "okt", "nov", "dec"],
            dayNames: ["Søndag", "Mandag", "Tirsdag", "Onsdag", "Torsdag", "Fredag", "Lørdag"],
            dayNamesShort: ["Søn", "Man", "Tir", "Ons", "Tor", "Fre", "Lør"],
            dayNamesMin: ["Sø", "Ma", "Ti", "On", "To", "Fr", "Lø"],
            weekHeader: "Uge",
            dateFormat: "dd/M-yy",
            firstDay: 1,
            isRTL: false,
            showMonthAfterYear: false,
            yearSuffix: ""});
    });


    fetch("/jinnie/api/customer/all")
            .then(res => res.json())
            .then(data => {
                console.log("data: ", data);
                var x = document.getElementById("selCustomer");
                for (var i = 0; i < data.length; i++) {
                    console.log(data[i].customerContactName);
                    var option = document.createElement("option");
                    option.text = data[i].customerID + " " +    
                            data[i].customerContactName;
                    x.add(option);
                }
            });
    fetch("/jinnie/api/itemtype/all")
            .then(res => res.json())
            .then(data => {
                console.log("data: ", data);
                var x = document.getElementById("selItemtypes");
                for (var i = 0; i < data.length; i++) {
                    var option = document.createElement("option");
                    option.text = data[i].itemTypeID + " " +
                            data[i].description;
                    x.add(option);
                }
            });
}

function getSelections() {
    //Date selection
    var selects = [];
    var result = "<h2>Date selected</h2>";
    result += "<ul>";
    result += document.querySelector("#datepicker").value;
    selects.push("'workDoneDate':" + document.querySelector("#datepicker").value + "'");
    result += "</ul>";
    //Customer selection
    var selCustomer = document.getElementById("selCustomer");
    result += "<h2>Customer selected</h2>";
    result += "<ul>";
    var customerid;
    for (i = 0; i < selCustomer.length; i++) {
        currentOption = selCustomer[i];
        if (currentOption.selected === true) {
            result += " <li>" + currentOption.value + "</li>";
            customerid = currentOption.value.split(" ")[0];
        }
    }
    selects.push("'customerID':" + customerid)
    result += "</ul>";
    //Itemtypes selections
    var selItemtypes = document.getElementById("selItemtypes");
    result += "<h2>Itemtypes selected</h2>";
    result += "<ul>";
    var items = [];
    for (i = 0; i < selItemtypes.length; i++) {
        currentOption = selItemtypes[i];
        if (currentOption.selected === true) {
            result += " <li>" + currentOption.value + "</li>";
            items.push(
                    "'itemTypeID':" + currentOption.value.split(" ")[0] +
                    ",'quantity':1"
                    );
        }
    }
    items = "{" + items.join(",") + "}";
    selects.push(items);
    result += "</ul>";
    output = document.getElementById("output");
    output.innerHTML = result;
    console.log(selects);
    selects = "{" + selects.join(",") + "}";

    console.log(selects);
    return selects;
}


/**
 * new or edit overlay
 */
function insertNewOrderModal() {

    $.get("html/neworder.html", {}, function (results) {
        $("#newButton").html(results);
        document.querySelector("#newButtonOrderPage").addEventListener("click", fillSelectionLists);
    });
}

/**
 * Page decorations
 */
function orderPageDecos() {
    $("#h1content").html("Order");
    $("#h3content").html("");
}

document.querySelector("#orderPage").addEventListener("click", orderPageDecos);
document.querySelector("#orderPage").addEventListener("click", getAllOrders);


