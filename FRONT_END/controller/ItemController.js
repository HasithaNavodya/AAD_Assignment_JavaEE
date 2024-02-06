//load all Item
getAllItem();

//Save Item
$("#btnSaveItem").click(function () {
    if (checkAllItem()){
        saveItem();
    }else{
        alert("Error");
    }
});

// Save Item
function saveItem() {
    let id = $("#ItemtxtID").val();
    let description = $("#ItemtxtDescription").val();
    let price = $("#ItemtxtPrice").val();
    let quantity = $("#ItemtxtQuantity").val();

    let itemObj ={
        id: id,
        description: description,
        price: price,
        quantity: quantity
    }

    $.ajax({
        url: "http://localhost:8080/app/item",
        method: "post",
        contentType: "application/json",
        data: JSON.stringify(itemObj),
        success: function (resp, textStatus, jqxhr) {
            alert("customer saved successfully");
            // getAllCustomer();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            if(jqXHR.status==409){
                alert("Duplicate values. Please check again");
                return;
            }else{
                alert("Customer not added");
            }
        }
    });
}

//delete Item
$("#btnItemDelete").click(function () {
    let id = $("#ItemtxtID").val();
    let formData = $('#CustomerForm').serialize()
    let consent = confirm("Do you want to delete.?");
    if (consent) {
        $.ajax({
            url: "http://localhost:8080/app/item?id="+id,
            method: "delete",
            data:formData,
            success: function (res) {
                alert("customer remove");
                getAllCustomer()
                console.log(res)
            },
            error: function (error) {
                let message = JSON.parse(error.responseText).message
                alert(message)
            },
        });
    }
});

//update  Item
$("#btnItemUpdate").click(function () {
    let id = $("#ItemtxtID").val();
    let description = $("#ItemtxtDescription").val();
    let price = $("#ItemtxtPrice").val();
    let quantity = $("#ItemtxtQuantity").val();

    var ItemOB = {
        id:id,
        description:description,
        price:price,
        quantity:quantity
    }
    $.ajax({
        url: "http://localhost:8080/app/item",
        method:"put",
        contentType:"application/json",
        data:JSON.stringify(ItemOB),
        dataType:"json",
        success: function (res) {
            getAllCustomer()
            alert("customer update");
        },
        error: function (error) {
            let message = JSON.parse(error.responseText).message;
            alert("customer not update");
        },
    });
});

//clear textField
$("#btnItemClear").click(function () {
    clearItemInputFields();

});

$(document).on('click', '#ItemTbl > tr', function() {
    let code = $(this).children().eq(0).text();
    let desc = $(this).children().eq(1).text();
    let salary = $(this).children().eq(2).text();
    let qty = $(this).children().eq(3).text();
    $("#ItemtxtID").val(code);
    $("#ItemtxtDescription").val(desc);
    $("#ItemtxtPrice").val(salary);
    $("#ItemtxtQuantity").val(qty);
});


function getAllItem() {
    $("#ItemTbl").empty();

    $.ajax({
        url: "http://localhost:8080/app/item",
        method: "GET",
        dataType: "json",
        success: function (res) {
            var rows = "";
            $.each(res.data, function (index, c) {
                let code = c.code;
                let description = c.description;
                let salary = c.salary;
                let qty = c.qty;
                let row = "<tr><td>" + code + "</td><td>" + description + "</td><td>" + salary + "</td><td>" + qty + "</td></tr>";
                rows += row;
            });
            $("#ItemTbl").append(rows);
        },
        error: function (xhr, status, error) {
            console.error("AJAX request failed:", status, error);
        }
    });
}



