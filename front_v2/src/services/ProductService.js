import apiBackEnd from "./api.backend";


export const getAllProducts = () => apiBackEnd.get('/products')
export const saveProduct = (product) => apiBackEnd.post('/products/save-product', product)

export const searchProduct = (product) => apiBackEnd.get( '/products/search/' + product)
export const getProduct = (id) => apiBackEnd.get( '/products/get/' + id)

export const updateProduct = (id, product) => apiBackEnd.get(  '/products/update_product' + id, product)

export const deleteProduct = (id) => apiBackEnd.get(  '/products/delete' + id)

