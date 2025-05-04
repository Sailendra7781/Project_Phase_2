function searchProduct(){
    var productCategory = $("#productCategory").val();
    if(productCategory==""){
        alert("Please select a category to continue ");
        return false;
    }
    var searchText = $("#search").val()

    var passData = "switchtype=searchDetails&productCategory=" + encodeURIComponent(productCategory) + "&searchText=" + encodeURIComponent(searchText);
    var queryString = contextPath+"/searchDetails";

    $.ajax({type: "POST", url: queryString, data: passData, cache: false, dataType: "json",
        success: function (response, status) {
            //success code
            if(response['ResponseCode']==1){
                $("#searchInsert").html(response['Result']);
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

function showProducts(){
    var passData = "switchtype=showProducts";
    var queryString = contextPath+"/showProducts";

    $.ajax({type: "POST", url: queryString, data: passData, cache: false, dataType: "json",
        success: function (response, status) {
            //success code
            if(response['ResponseCode']==1){
                $("#searchInsert").html(response['Result']);
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