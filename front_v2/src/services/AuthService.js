import axios from "axios";

const AUTH_REST_API_BASE_URL =  "http://localhost:8080/api/auth";

export const registerAPICall = (registerObj) => axios.post(AUTH_REST_API_BASE_URL + '/register', registerObj);

export const loginAPICall = (usernameOrEmail, password) => axios.post(AUTH_REST_API_BASE_URL + '/authenticate', { usernameOrEmail, password });
export const storeToken = (token) => localStorage.setItem("token", token);

export const getToken = () => localStorage.getItem("token");

export const saveLoggedInUser = (username) => sessionStorage.setItem("authenticatedUser", username);

export const isUserLoggedIn = () => {
    const username = sessionStorage.getItem("authenticatedUser");
    return username != null;
}

export const getLoggedInUser = () => {
    const username = sessionStorage.getItem("authenticatedUser");
    if (username == null) {
        return "";
    }
    return username;
}

export const logout = () => {
    localStorage.clear();
    sessionStorage.clear();
}