function register(usernameElement, passwordElement, firstnameElement, lastnameElement, amkaElement, emailElement, mobileElement) {
    let username = usernameElement && usernameElement.value ? usernameElement.value : "";
    let password = passwordElement && passwordElement.value ? passwordElement.value : "";
    let amka = amkaElement && amkaElement.value ? amkaElement.value : "";
    let mobile = mobileElement && mobileElement.value ? mobileElement.value : "";
    let firstname = firstnameElement && firstnameElement.value ? firstnameElement.value : "";
    let lastname = lastnameElement && lastnameElement.value ? lastnameElement.value : "";
    let email = emailElement && emailElement.value ? emailElement.value : "";


    var newUser = {
        "firstName": firstname,
        "lastName": lastname,
        "amka": amka,
        "email": email,
        "mobilePhone": mobile,
        "username": username,
        "password": password
    };

    var json = JSON.stringify(newUser);

    $.ajax({
        url: 'http://localhost:8080/patients',
        data: json,
        processData: false,
        contentType: 'application/json',
        type: 'POST',
        success: function(resultData){
            alert("Η Εγγραφή σου ολοκληρώθηκε!");
            location.replace("loginpage/login.html");
        },
        error: function(resultData){
            alert("Ουπς! Προσπάθησε ξανά με τα σωστά στοιχεία!")
            location.reload();
            }

    }
    );

}