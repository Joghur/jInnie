/**
 * table utils
 */

/**
 * 
 */
function getAllItemTypes(ev) {
    ev.preventDefault();
    console.log(ev.target.innerText);

    fetch("/jinnie/api/itemtype/all")
        .then(res => res.json())
        .then(data => {

            console.log("iiwerdwedweuj");
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
                list2Table(data, "#content");
                linking("#content");
            } else {
                var tableElement = CONTENTDIV.querySelector("table");
                if (tableElement !== null) {
                    CONTENTDIV.querySelector("table").remove();
                }
                alert("\n\nNo data left!");
            }

            // For small screens
            // CONTENTDIV.classList.add("table-responsive");

            //Events needs to be added after the table has been written
            //also changes headliners
            //                addEvents();

            insertNewModal(); // new or edit overlay
        });
};

/**
 * new or edit overlay
 */
function insertNewModal() {

    $.get("newitemtype.html", {}, function (results) {
        $("#content").append(results);
    });
};


//Cars button eventlistener and other DOM manipulations
console.log("oidhfgskjh)");
document.querySelector("#itemTypePage").addEventListener("click", getAllItemTypes);


