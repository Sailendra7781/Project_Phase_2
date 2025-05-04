function alphaSpaceValidation(value){
    var regex = /^[a-zA-Z ]*$/;
    var valid = regex.test(value);
    if(valid){
        return true;
    }
    else{
        return false;
    }
}

function emailIdValidation(value){
    var regex = /^[A-Z0-9\._%-]+@[A-Z0-9\.-]+\.[A-Z]{2,8}(?:(?:[,;][A-Z0-9\._%-]+@[A-Z0-9\.-]+))*$/i;
    var valid = regex.test(value);
    if(valid){
        return true;
    }
    else{
        return false;
    }
}

function phoneValidation(value){
    var regex = /^\d{10}$/;
    var valid = regex.test(value);
    if(valid){
        return true;
    }
    else{
        return false;
    }
}

function cardValidation(value){
    var regex = /^\d{12}$/;
    var valid = regex.test(value);
    if(valid){
        return true;
    }
    else{
        return false;
    }
}


function usernameValidation(value){
    var regex = /^(?=(.*[A-Z]))(?=(.*[\W_]))[A-Za-z0-9\W_]{7,10}$/;
    return regex.test(value);
}
function passwordValidation(password) {
    var regex = /^(?=.*[A-Z])(?=.*[^a-zA-Z0-9]).{6,10}$/;
    return regex.test(password);
}

function alphaNumericSpaceValidation(value){
    var regex = /^[A-Za-z0-9\s]+$/;
    var valid = regex.test(value);
    if(valid){
        return true;
    }
    else{
        return false;
    }
}

function integerValidationWithLimit(value, size) {
    const regex = new RegExp(`^\\d{${size}}$`);
    return regex.test(value);
}

