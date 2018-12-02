if (!sessionStorage.getItem(SESSION_STORAGE_LOGIN_TOKEN_NAME)) {
    window.location.replace(ROOT_PATH + "/index.html");
}
