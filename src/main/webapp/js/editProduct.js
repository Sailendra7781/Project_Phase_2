function showProductDetails(ID) {
    var ProductId = ID;
    var passData = "switchtype=getProductData&productID=" + encodeURIComponent(ProductId);
    var queryString = contextPath + "/showProductController";
    $.ajax({
        type: "POST",
        url: queryString,
        data: passData,
        cache: false,
        dataType: "json",
        success: function (response, status) {
            // Success code
            if (response['ResponseCode'] == 1) {
                $("#editProductDetails").html(response['productDetails']);
            }
        },
        error: function (jqXHR, textStatus) {
            // Error code
        }
    });
}

function updateQuantity(productID){
    var quantityValue = $("#productQuantity").val();
    if(quantityValue==''){
        alert("Please enter the Quantity to update");
        return false;
    }
    var ProductId = productID;

    var passData = "switchtype=updateQuantity&productID=" + encodeURIComponent(ProductId) + "&newProductQuantity=" + encodeURIComponent(quantityValue);
    var queryString = contextPath + "/updateQuantityController";
    $.ajax({
        type: "POST",
        url: queryString,
        data: passData,
        cache: false,
        dataType: "json",
        success: function (response, status) {
            // Success code
            if (response['ResponseCode'] == 1) {
                alert(response['ResponseMessage'])
                window.location.href = contextPath + "/products";
            } else if (response['ResponseCode'] == 2) {
                alert(response['ResponseMessage'])
            }
        },
        error: function (jqXHR, textStatus) {
            // Error code
        }
    });
}
