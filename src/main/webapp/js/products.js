function getProductDetails()
{
    var passData = "switchtype=getProductDetails";
    var queryString = contextPath+"/loadProductDetails";

    $.ajax({type: "POST", url: queryString, data: passData, cache: false, dataType: "json",
        success: function (response, status) {
            //success code
            if(response['ResponseCode']==1){
                $("#productDetails").html(response['productDetails']);
            }
            else if(response['ResponseCode']==2){
                $("#productDetails").html(response['productDetails']);
            }
        },
        error: function (jqXHR, textStatus) {
            //error code
        }
    });
}

function saveProductId(Id){
    $("#productID").val(Id);
    $("#SubmitForm").attr("action",contextPath + "/editproduct");
    $("#SubmitForm").submit();
}

function addToCart(productID){
    var quantityValue = $("#productQuantity"+productID).val();
    if(quantityValue==''){
        alert("Please enter the Quantity to update");
        return false;
    }
    if(quantityValue<=0){
        alert("Quantity should be greater than zero. Please enter again");
        return false;
    }
    var passData = "switchtype=addToCart&productID=" + encodeURIComponent(productID) + "&ProductQuantity=" + encodeURIComponent(quantityValue);
    var queryString = contextPath + "/addToCartController";
    $.ajax({
        type: "POST",
        url: queryString,
        data: passData,
        cache: false,
        dataType: "json",
        success: function (response, status) {
            // Success code
            if (response['ResponseCode']==1) {
                alert(response['ResponseMessage'])
                $("#productQuantity"+productID).val('');
            }
            else if (response['ResponseCode']==2) {
                alert(response['ResponseMessage'])
            }
        },
        error: function (jqXHR, textStatus) {
            // Error code
        }
    });
}

function gotToCart(){
    window.location.href = contextPath + "/cart";
}
