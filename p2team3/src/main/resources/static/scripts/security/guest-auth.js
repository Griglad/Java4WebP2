if (sessionStorage.getItem(SESSION_STORAGE_LOGIN_TOKEN_ROLE)) {
    let roleToken = sessionStorage.getItem(SESSION_STORAGE_LOGIN_TOKEN_ROLE);
    if(roleToken=='p'){
        window.location.replace(ROOT_PATH + "/pages/patient/index.html");
    }else if(roleToken=='d'){
        window.location.replace(ROOT_PATH + "/pages/doctor/index.html");
    }
}