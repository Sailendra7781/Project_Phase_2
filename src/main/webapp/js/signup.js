function signUp(){
    var nameValue = $("#name").val();
    if (nameValue == '') {
        alert("Please enter the Name");
        return false;
    }
    if(!alphaSpaceValidation(nameValue)){
        alert("Please enter valid Name");
        return false;
    }

    var emailValue = $("#email").val();
    if (emailValue == '') {
        alert("Please enter the E-mail ID");
        return false;
    }
    if(!emailIdValidation(emailValue)){
        alert("Please enter valid E-mail ID");
        return false;
    }

    var phoneNumberValue = $("#phonenumber").val();
    if (phoneNumberValue == '') {
        alert("Please enter the Mobile Number");
        return false;
    }
    if(!phoneValidation(phoneNumberValue)){
        alert("Please enter valid Phone Number");
        return false;
    }

    if ($("input[name='Gender']:checked").length == 0) {
        alert("Please select Gender");
        return false;
    }
    const selectedGender = $('input[name="Gender"]:checked').val();

    var countryValue = $("#country").val();
    if (countryValue == '') {
        alert("Please select the Country");
        return false;
    }

    var addressValue = $("#Address").val();
    if (addressValue == '') {
        alert("Please enter the Address");
        return false;
    }

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

    var confirmPasswordValue = $("#confirmpassword").val();
    if (confirmPasswordValue == ''){
        alert("Please Re-enter the password");
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
    if(passwordValue!=confirmPasswordValue){
        alert("Password and Confirm password must be the same");
        return false;
    }


    var passData = "switchtype=formSubmit&name=" + encodeURIComponent(nameValue)+"&emailID="+encodeURIComponent(emailValue)+"&phoneNumber="+encodeURIComponent(phoneNumberValue)+"&Gender="+encodeURIComponent(selectedGender)+"&country="+encodeURIComponent(countryValue)+"&address="+encodeURIComponent(addressValue)+"&username="+encodeURIComponent(usernameValue)+"&password="+encodeURIComponent(passwordValue)+"&confirmpassword="+encodeURIComponent(confirmPasswordValue);
    var queryString = contextPath+"/formSubmitController";

    $.ajax({type: "POST", url: queryString, data: passData, cache: false, dataType: "json",
        success: function (response, status) {

            //success code
            if(response['ResponseCode']==1){
                alert(response['ResponseMessage']);
                resetForm();
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
function resetForm(){
    $("#name,#username,#Address,#country,#confirmpassword,#email,#password,#phonenumber").val('');
    $("input[name='Gender']").prop("checked", false);
}
