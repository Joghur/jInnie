/**
 * jsonList2Table converts a json-list into a html table
 * and places it at the html tag position
 * arguments: a jsonlist and the tag identifier
 */
function jsonList2Table(jsonList, htmlTag) {

    //Create a HTML Table element.
    var table = document.createElement("TABLE");

    // insert table class for bootstrap goodies
    table.setAttribute("class", "table table-hover table-condensed");

    //insert empty row.
    var row = table.insertRow(-1);

    //insert header cell elements
    // it uses the first entry to get object properties
    Object.keys(jsonList[0]).forEach(function (item) {
//        console.log("item: " + item);
        var headerCell = document.createElement("TH");

        //create id for header. ID is the same as the content
        headerCell.setAttribute("id", item);
        var text = document.createTextNode(item);
        headerCell.appendChild(text);
        row.appendChild(headerCell);
    });

    //Add the data rows.
    jsonList.forEach(function (listItem, index) {
//        console.log("listItem.carID: " + listItem.carID);

        row = table.insertRow(-1);
        Object.keys(listItem).forEach(function (parts) {
//            console.log("listItem[parts]: " + listItem[parts]);
            var cell = row.insertCell(-1);
            var text = document.createTextNode(listItem[parts]);
            cell.appendChild(text);
        });
    });

    var divTable = document.querySelector(htmlTag);
    if (divTable.contains(document.querySelector("table"))) {
        document.querySelector("table").remove();
        divTable.appendChild(table);
    } else {
        divTable.appendChild(table);
    }
}
