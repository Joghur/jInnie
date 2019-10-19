/**
 * GET
 */
function getAllItemTypes(ev) {
//    ev.preventDefault();
    console.log("get function");
//    console.log(ev.target.innerText);

    fetch("/jinnie/api/itemtype/all")
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
                var keyList = ["itemTypeID", "name", "description", "price"];
                if (typeof data !== 'undefined' && data.length > 0) {
                    list2Table(data, "#content", keyList);
                    linking("#content");
                } else {
                    if (document.querySelector("#content table") != null) {
                        document.querySelector("#content table").remove();
                    }
                    alert("\n\nNo data left!");
                }
                insertNewItemtypeModal(); // new overlay
            });
}


/**
 * POST
 */
function postNewItemtypeFunc(e) {
    console.log("post function");
    var name = document.querySelector("#newItemtypeName").value;
    var desc = document.querySelector("#newItemtypeDescription").value;
    var number = document.querySelector("#newItemtypePrice").value;
    number = parseFloat(number);
    console.log("name", name);
    console.log("desc", desc);
    console.log("number", number);
    let options = {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            "name": name,
            "description": desc,
            "price": number
        })
    };
    console.log("options", options);
    fetch("/jinnie/api/itemtype/new", options);
    getAllItemTypes();
}

/**
 * DELETE
 */
function deleteItemtypeFunc(id) {
    console.log("delete function");
    let options = {
        method: "DELETE",
        headers: {
            'Content-Type': 'application/json'
        }
    };
    fetch("/jinnie/api/itemtype/" + id, options);
    getAllItemTypes();
}

/**
 * new or edit overlay
 */
function insertNewItemtypeModal() {

    $.get("html/newitemtype.html", {}, function (results) {
        $("#newButton").html(results);
    });
}

/**
 * Page decorations
 */
function itemtypePageDecos() {
    $("#h1content").html("Itemtypes");
    $("#h3content").html("");
}

document.querySelector("#itemTypePage").addEventListener("click", itemtypePageDecos);
document.querySelector("#itemTypePage").addEventListener("click", getAllItemTypes);


