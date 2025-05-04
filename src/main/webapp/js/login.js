function login(){
    var usernameValue = $("#username").val();
    if(usernameValue == ''){
        alert("Please enter the Username");
        return false;
    }
    if(!usernameValidation(usernameValue)){
        alert("Please enter valid Password\n" +
            "It should satisfy the following conditions:\n" +
            "1.It should have Minimum 7 characters\n" +
            "2.It should have Maximum 10 characters\n" +
            "3.Must have one capital letter and one special character");
        return false;
    }

    var passwordValue = $("#password").val();
    if (passwordValue == ''){
        alert("Please enter the password");
        return false;
    }
    if(!passwordValidation(passwordValue)){
        alert("Please enter valid Password\n" +
            "It should satisfy the following conditions:\n" +
            "1.It should have Minimum 6 characters\n" +
            "2.It should have Maximum 10 characters\n" +
            "3.Must have one capital letter and one special character");
        return false;
    }

    var passData = "switchtype=loginSubmit&username=" + encodeURIComponent(usernameValue)+"&password="+encodeURIComponent(passwordValue);
    var queryString = contextPath + "/loginSubmitController";
    $.ajax({type: "POST", url: queryString, data: passData, cache: false, dataType: "json",
        success: function (response, status) {
            //success code
            if(response['ResponseCode']==1){
                window.location.href = contextPath + "/dashboard";
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