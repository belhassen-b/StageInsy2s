import axios from 'axios';
import {getToken} from "./AuthService";



const apiBackEnd = axios.create({
    baseURL: 'http://localhost:8080/api',
});

export default apiBackEnd;

apiBackEnd.interceptors.request.use((request) => {
    const token = getToken();
    if (token) request.headers['Authorization'] = `Bearer ${token}`;
    return request;
});

apiBackEnd.interceptors.response.use(
    (response) => {
        console.log(response.status);
        return response;
    },
    (error) => {
        return error;
    },
);