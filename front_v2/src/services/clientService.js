import apiBackEnd from "./api.backend";



export const getCurrentUser = (client) => apiBackEnd.get('/clients/current/' + client)

