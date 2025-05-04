function showCartDetails(){
    var passData = "switchtype=showCartDetails";
    var queryString = contextPath+"/loadCartDetails";

    $.ajax({type: "POST", url: queryString, data: passData, cache: false, dataType: "json",
        success: function (response, status) {
            //success code
            if(response['ResponseCode']==1){
                $("#cart").html(response['Result']);
            }
            else if(response['ResponseCode']==2){
                alert(response['Result']);
            }
        },
        error: function (jqXHR, textStatus) {
            //error code
        }
    });
}

function ProceedToPayment(){
    $("#payment").attr("action",contextPath+"/payment").submit();
}