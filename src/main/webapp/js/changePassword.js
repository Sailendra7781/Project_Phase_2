function changePassword(){
    var currentPasswordValue = $("#currentPassword").val();
    if(currentPasswordValue == ''){
        alert("Please enter your current password");
        return false;
    }


    var newPasswordValue = $("#newPassword").val();
    if (newPasswordValue == ''){
        alert("Please enter the New Password");
        return false;
    }
    if(currentPasswordValue == newPasswordValue){
        alert("Current Password and New Password should not be the same\n" +
            "Please enter the New Password again")
        return false;
    }

    var reEnterPasswordValue = $("#reEnterPassword").val();
    if (reEnterPasswordValue == ''){
        alert("Please Re-enter the password");
        return false;
    }
    if(reEnterPasswordValue != newPasswordValue){
        alert("Both New Password and Re-enter password must be same\n" +
            "Please enter the Password again")
        return false;
    }

    var passData = "switchtype=changePasswordSubmit&CurrentPassword=" + encodeURIComponent(currentPasswordValue)+"&NewPassword="+encodeURIComponent(newPasswordValue);
    var queryString = contextPath+"/changePasswordController";
    $.ajax({type: "POST", url: queryString, data: passData, cache: false, dataType: "json",
        success: function (response, status) {
            //success code
            if(response['ResponseCode']==1){
                window.location.href = contextPath + "/login"
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