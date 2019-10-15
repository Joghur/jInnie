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
                    "orderState",
                    "orderLines"
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
    var customerFirmName = document.querySelector("#newCustomerFirmName").value;
    var customerFirmAddress = document.querySelector("#newCustomerFirmAddress").value;
    var customerContactName = document.querySelector("#newCustomerContactName").value;
    var customerContactEmail = document.querySelector("#newCustomerContactEmail").value;
    var customerContactPhone = document.querySelector("#newCustomerContactPhone").value;

    console.log("customerFirmName", customerFirmName);
    console.log("customerFirmAddress", customerFirmAddress);
    console.log("customerContactName", customerContactName);
    console.log("customerContactEmail", customerContactEmail);
    console.log("customerContactPhone", customerContactPhone);
    let options = {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            "customerFirmName": customerFirmName,
            "customerFirmAddress": customerFirmAddress,
            "customerContactName": customerContactName,
            "customerContactEmail": customerContactEmail,
            "customerContactPhone": customerContactPhone
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
 * new or edit overlay
 */
function insertNewOrderModal() {

    $.get("html/neworder.html", {}, function (results) {
        $("#newButton").html(results);
    });
}

document.querySelector("#orderPage").addEventListener("click", getAllOrders);


