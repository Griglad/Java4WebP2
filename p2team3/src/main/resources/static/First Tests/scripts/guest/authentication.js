
function login(usernameElement, passwordElement) {
    let username = usernameElement && usernameElement.value ? usernameElement.value : "";
    let password = passwordElement && passwordElement.value ? passwordElement.value : "";

    var fd = new FormData();
    fd.append('username', username);
    fd.append('password', password);

    $.ajax({
        url: ROOT_PATH + '/login',
        data: fd,
        processData: false,
        contentType: false,
        type: 'POST',
        success: function (data) {
            sessionStorage.setItem(SESSION_STORAGE_LOGIN_TOKEN_NAME, username);
            window.location.replace(ROOT_PATH + "/pages/user/index.html");
        },
        statusCode: {
            401: function () {
                alert("Invalid username or password!");
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

    var json = JSON.stringify(newUser );

    $.ajax({
        url: 'http://localhost:8080/patients',
        data: json,
        processData: false,
        contentType: 'application/json',
        type: 'POST',

    }
    );

}