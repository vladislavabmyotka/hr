function validate(form) {
    var login = form.login.value;
    var password = form.password.value;

    var errors="";
    var regPassword = /^(?=.*\d)(?=.*[a-z])[0-9a-z]{8,12}$/i;
    var regLogin = /[a-z][\w]{4,20}/i;
    if(!regLogin.test(login)) {
        errors+="Wrong login! The login must begin with a Latin letter, must be at least 5 " +
            "and not more than 20 characters and can contain Latin letters, digits and '_'\n";
    }
    if(!regPassword.test(password)) {
        errors+="Wrong password! The password must contain letters and numbers and be at least 8" +
            " and not more than 12 characters\n";
    }

    if(errors === "") {
        return true;
    } else {
        alert(errors);
        return false;
    }
}

function checkPasswordsMatch(form) {
    var password = form.password.value;
    var repeatPassword = form.repeatPassword.value;

    if (password === repeatPassword) {
        return true;
    } else {
        alert("Entered passwords do not match!");
        return false;
    }
}