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
                    if (document.querySelector("#content table") != null) {
                        document.querySelector("#content table").remove();
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
    var selTable = document.querySelector("#output");
    console.log("selTable", selTable);
    var count = document.querySelector("#output").childElementCount;
    var c = selTable.childNodes;
    console.log("selTable c", c);
    console.log("selTable count", count);
    var itemTypeID;
    var quantity;
    for (var i = 0; i < count; i++) {
        itemTypeID = Number(c[i].rows[0].cells[0].innerHTML);
        quantity = Number(c[i].rows[0].cells[1].getElementsByTagName('input')[0].value);
        console.log("itemTypeID", itemTypeID);
        console.log("quantity", quantity);
        console.log("orderLines before push", orderLines);
        orderLines.push(
                {
                    "itemTypeID": itemTypeID,
                    "quantity": quantity,
                    "orderLineDoneDate": quantity
                }
        );
        console.log("orderLines after push", orderLines);
    }

    var workDoneDate = document.querySelector("#datepicker").value;
    console.log("customerID", customerID);
    console.log("orderLines", orderLines);
    console.log("quantity", quantity);
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
        $("#datepicker").datepicker({
            container: '#divDate',
            closeText: "Luk",
            prevText: "&#x3C;Forrige",
            nextText: "Næste&#x3E;",
            currentText: "Idag",
            monthNames: ["januar", "februar", "marts", "april", "maj", "juni",
                "juli", "august", "september", "oktober", "november", "december"],
            monthNamesShort: ["jan", "feb", "mar", "apr", "maj", "jun",
                "jul", "aug", "Sep", "Okt", "nov", "Dec"],
            dayNames: ["Søndag", "Mandag", "Tirsdag", "Onsdag", "Torsdag", "Fredag", "Lørdag"],
            dayNamesShort: ["Søn", "Man", "Tir", "Ons", "Tor", "Fre", "Lør"],
            dayNamesMin: ["Sø", "Ma", "Ti", "On", "To", "Fr", "Lø"],
            weekHeader: "Uge",
            dateFormat: "dd/Mmm-yy",
            firstDay: 1,
            isRTL: false,
            showMonthAfterYear: false,
            changeMonth: true,
            changeYear: true,
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
    selections = document.querySelector("#selItemtypes");
    var result = "<h2>Orderlines selected</h2>";
    output = document.querySelector("#output");
    var table = document.createElement("TABLE");
    table.setAttribute("id", "selectionTable");

    for (i = 0; i < selItemtypes.length; i++) {
        currentOption = selItemtypes[i];
        if (currentOption.selected === true) {
            var row = table.insertRow(-1);
            var cell = document.createElement("TH");
            var text = document.createTextNode(currentOption.value.split(" ")[0]);
            cell.appendChild(text);
            row.appendChild(cell);
            var cellDate = document.createElement("TH");
            var textDate = document.createTextNode(document.querySelector("#datepicker").value);
            cellDate.appendChild(textDate);
            row.appendChild(cellDate);

            var inputCell = document.createElement("TH");
            text = document.createElement("input");
            text.setAttribute("type", "number");
            text.setAttribute("value", 1);
            inputCell.appendChild(text);
            row.appendChild(inputCell);
        }
    }
    output.appendChild(table);
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


