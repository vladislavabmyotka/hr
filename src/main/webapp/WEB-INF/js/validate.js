function validate(form) {
    var username = form.username.value;
    var password = form.password.value;

    var errors="";
    var regUsername = /[a-z][\w]{4,20}/i;
    var regPassword = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\s).*$/i;
    if(!regUsername.test(username)) {
        errors+="Wrong name! The name must begin with a Latin letter, must be at least 5 " +
            "and not more than 20 characters and can contain Latin letters, digits and '_'\n";
    }
    if(!regPassword.test(password)) {
        errors+="Wrong password! The password must contain letters and numbers and be at least 8" +
            " and not more than 16 characters\n";
    }

    if(errors === "") {
        return true;
    } else {
        alert(errors);
        return false;
    }
}