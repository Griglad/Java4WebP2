function login(usernameElement, passwordElement, ddRoleElement) {
    let username = usernameElement;
    let password = passwordElement;
    let role =  ROLE_PATIENT_PREFIX;
    if(ddRoleElement=='Γιατρός'){
        role = ROLE_DOCTOR_PREFIX;
    }
    var fd = new FormData();
    fd.append( 'username', role+username);
    fd.append( 'password', password);

    $.ajax({
      url: ROOT_PATH + '/login',
      data: fd,
      processData: false,
      contentType: false,
      type: 'POST',
      success: function(data){
        sessionStorage.setItem(SESSION_STORAGE_LOGIN_TOKEN_NAME, username);
        sessionStorage.setItem(SESSION_STORAGE_LOGIN_TOKEN_ROLE, role);
        if(role == ROLE_DOCTOR_PREFIX){
            window.location.replace(ROOT_PATH + "/pages/doctor/index.html");
        }
        else if(role == ROLE_PATIENT_PREFIX){
            window.location.replace(ROOT_PATH + "/pages/patient/index.html");
        }
      },
      statusCode: {
        401 : function() {
            $('.alert-danger').removeAttr('hidden');
            }
        }
    });

}

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
            alert("Η Εγγραφή ολοκληρώθηκε!");
            location.replace("../../index.html");
        },
        error: function(resultData){
        var data=resultData.responseText;
        var jsonResponse = JSON.parse(data);
        alert(jsonResponse["message"]);
            }

    }
    );
}