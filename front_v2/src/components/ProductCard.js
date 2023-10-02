import React, {useState} from 'react';
import CartListComponent from "./CartListComponent";


const ProductCard = ({
                         id,
                         name,
                         description,
                         category,
                         price,
                         image,
                     }) => {
    const [quantity, setQuantity] = useState(0);

    const [isCartOpen, setIsCartOpen] = useState(false);



    const incrementQuantity = () => {
        setQuantity(quantity + 1);
    };

    const decrementQuantity = () => {
        if (quantity > 0) {
            setQuantity(quantity - 1);
        }
    };

    function onCartOpen() {
        setIsCartOpen(true)
    }


    function addTocart(id, quantity) {
        if (quantity > 0) {
            const cartItem = {
                id,
                name,
                description,
                category,
                price,
                quantity,
                image,
            };
            const existingCartItems = JSON.parse(localStorage.getItem('cartItems')) || [];
            const existingCartItem = existingCartItems.find((item) => item.id === id);
            if (existingCartItem) {
                existingCartItem.quantity += quantity;
            } else {existingCartItems.push(cartItem);}
            localStorage.setItem('cartItems', JSON.stringify(existingCartItems));
            setQuantity(0);
            onCartOpen();

        }
    }

    return (
        <div className="w-full max-w-sm mx-auto rounded-md shadow-md overflow-hidden border-2">
            <img className="object-cover object-center w-full h-56" src={image} alt="avatar"/>
            <div className="mt-4 px-5 pb-5">
                <a href="#">
                    <h5 className="text-xl tracking-tight text-slate-900 font-semibold hover:underline">{name}</h5>
                    <p className="mt-1 text-sm text-gray-500">{id}</p>
                </a>
                <span className="text-sm text-gray-500">{category}</span>
                <div className="mt-2 flex items-center">
                    <span className="text-sm font-medium text-gray-500">{description}</span>
                </div>
                <div className="mt-2 mb-5 flex items-center justify-between">
                    <p>
                        <span className="text-3xl font-bold text-slate-900">{price}€</span>
                    </p>
                    <div className="flex items-center">
                        <input
                            className="w-10 h-10 border border-gray-300 rounded-lg text-sm text-center text-gray-600 focus:outline-none focus:border-blue-400"
                            value={quantity}
                            readOnly
                        />
                        <>
                            <button
                                className="ml-2 flex items-center justify-center rounded-md bg-slate-900
                  px-5 py-2.5 text-center text-sm font-medium text-black bg-red-400 hover:bg-gray-700 focus:outline-none focus:ring-4 focus:ring-blue-300"
                                onClick={incrementQuantity}
                            >
                                <svg xmlns="http://www.w3.org/2000/svg" className="h-3 w-3" fill="none"
                                     viewBox="0 0 24 24" stroke="currentColor" strokeWidth="2">
                                    <path strokeLinecap="round" strokeLinejoin="round" d="M12 6v6m0 0v6m0-6h6m-6 0H6"/>
                                </svg>
                            </button>
                            <button
                                className="ml-1 flex items-center justify-center rounded-md bg-slate-900
                  px-5 py-2.5 text-center text-sm font-medium text-black bg-red-400 hover:bg-gray-700 focus:outline-none focus:ring-4 focus:ring-blue-300"
                                onClick={decrementQuantity}
                            >
                                <svg xmlns="http://www.w3.org/2000/svg" className="h-3 w-3" fill="none"
                                     viewBox="0 0 24 24" stroke="currentColor" strokeWidth="2">
                                    <path strokeLinecap="round" strokeLinejoin="round" d="M20 12H4"/>
                                </svg>
                            </button>
                        </>
                    </div>
                </div>
                <button
                    className="flex items-center justify-center rounded-md bg-slate-900 px-5 py-2.5 text-center text-sm font-medium text-black bg-red-400 hover:bg-gray-700 focus:outline-none focus:ring-4 focus:ring-blue-300"
                    onClick={() => {
                        addTocart(id, quantity);
                    }}
                >
                    <svg xmlns="http://www.w3.org/2000/svg" className="mr-2 h-6 w-6" fill="none" viewBox="0 0 24 24"
                         stroke="currentColor" strokeWidth="2">
                        <path strokeLinecap="round" strokeLinejoin="round"
                              d="M3 3h2l.4 2M7 13h10l4-8H5.4M7 13L5.4 5M7 13l-2.293 2.293c-.63.63-.184 1.707.707 1.707H17m0 0a2 2 0 100 4 2 2 0 000-4zm-8 2a2 2 0 11-4 0 2 2 0 014 0z"/>
                    </svg>
                    Ajouter au panier
                </button>
            </div>
            {isCartOpen && (
                <CartListComponent setIsCartOpen={setIsCartOpen} isCartOpen={isCartOpen}/>
            )}
        </div>


    );
};

export default ProductCard;
