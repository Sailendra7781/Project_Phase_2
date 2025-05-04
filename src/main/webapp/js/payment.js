function pay(){
    var cardNumber = $("#cdCard").val();
    if(cardNumber === ""){
        alert("Please enter the card number");
        return false;
    }
    if(!integerValidationWithLimit(cardNumber,12)){
        alert("Please enter valid card number");
        return false;
    }

    var expiryDate = $("#expDate").val();
    if(expiryDate === ""){
        alert("Please enter the expiry date");
        return false;
    }
    if(!integerValidationWithLimit(expiryDate,4)){
        alert("Please enter valid Expiry Date");
        return false;
    }

    var cvv = $("#cvv").val();
    if(cvv === ""){
        alert("Please enter the CVV");
        return false;
    }
    if(!integerValidationWithLimit(cvv,3)){
        alert("Please enter valid CVV");
        return false;
    }

    var passData = "switchtype=paymentDetails";
    var queryString = contextPath+"/paymentDetails";

    $.ajax({type: "POST", url: queryString, data: passData, cache: false, dataType: "json",
        success: function (response, status) {
            //success code
            if(response['ResponseCode']==1){
                window.location.href = contextPath + "/";
            }
            else if(response['ResponseCode']==2){

            }
        },
        error: function (jqXHR, textStatus) {
            //error code
        }
    });

}