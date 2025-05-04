function addProduct(){
    var productCategoryValue = $("#ProductCategory").val();
    if(productCategoryValue == ''){
        alert("Please select the Product Category");
        return false;
    }

    var productNameValue = $("#productName").val();
    if(productNameValue == ''){
        alert("Please enter the Product Name");
        return false;
    }
    if(!alphaNumericSpaceValidation(productNameValue)){
        alert("Please enter valid Product Name");
        return false;
    }

    var productPriceValue = $("#productPrice").val();
    if(productPriceValue == ''){
        alert("Please enter the Product Price");
        return false;
    }
    if(productPriceValue>999999){
        alert("Please enter Product Price below 7 digits");
        return false;
    }

    var productQuantityValue = $("#productQuantity").val();
    if(productQuantityValue == ''){
        alert("Please enter the Product Quantity");
        return false;
    }
    if(productQuantityValue>999){
        alert("Please enter Product Quantity below 4 digits");
        return false;
    }


    var productDescriptionValue = $("#productDescription").val();
    if(productDescriptionValue == ''){
        alert("Please enter the Product Description");
        return false;
    }

    var filename = $("#fileUpload").val();
    var fileExtension = filename.split('.').pop();
    if (!["jpg", "jpeg", "png"].includes(fileExtension.toLowerCase())) {
        alert("Please select only PNG, JPG, or JPEG files");
        return false;
    }
    let fileValue = document.getElementById('fileUpload').files[0];
    let filesize = Math.round(fileValue.size/1024);
    if(filesize>5120){
        alert("Image file size should be less than 5mb");
        return false;
    }
    let formData = new FormData();
    formData.append('ProductName',productNameValue);
    formData.append('ProductPrice',productPriceValue);
    formData.append('ProductQuantity',productQuantityValue);
    formData.append('ProductDescription',productDescriptionValue);
    formData.append('AttachedFileValue',fileValue);
    formData.append('ProductCategory',productCategoryValue);

    // alert(formData);return ;

    $.ajax({
        type:"POST",
        url:contextPath+"/uploadFile",
        data:formData,
        enctype:'multipart/form-data',
        dataType:"json",
        processData: false,
        contentType: false,
        cache: false,
        success: function (response, status) {
            //success code
            if(response['ResponseCode']==1){
                alert(response['ResponseMessage']);
                addProductResetForm();
            }
            else if(response['ResponseCode']==2){
                alert(response['ResponseMessage']);
            }
        },
        error: function (jqXHR, textStatus) {
            //error code
        }
    });
}

function addProductResetForm(){
    $("#productName,#productPrice,#productQuantity,#productDescription,#fileUpload").val('');
}