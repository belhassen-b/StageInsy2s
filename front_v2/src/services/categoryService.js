import apiBackEnd from "./api.backend";


export const getAllCategories = () => apiBackEnd.get('/categories')