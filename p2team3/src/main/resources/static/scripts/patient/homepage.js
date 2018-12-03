function logout() {
    sessionStorage.removeItem(SESSION_STORAGE_LOGIN_TOKEN_NAME);
    sessionStorage.removeItem(SESSION_STORAGE_LOGIN_TOKEN_ROLE);
    window.location.replace(ROOT_PATH + "/logout")
    return false;
}