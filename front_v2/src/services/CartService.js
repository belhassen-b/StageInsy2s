import apiBackEnd from "./api.backend";

export const addProductToCart = (product) => apiBackEnd.get('cart/add', product)

export const getAllProductsInCart = () => apiBackEnd.get('cart/get-all')


export const deleteProductFromCart = (id) => apiBackEnd.get( 'cart/delete/' + id)

export const updateProductInCart = (id, product) => apiBackEnd.get( 'cart/update/' + id, product)

export const deleteAllProductsFromCart = () => apiBackEnd.get( 'cart/delete-all')

export const checkout = () => apiBackEnd.get( 'cart/checkout')




