    import React, { useState, useEffect } from 'react';
    import {useLocation, useNavigate} from 'react-router-dom';

    export default function Cart() {
        const [cartItems, setCartItems] = useState([]);

        const navigate = useNavigate();
        const location = useLocation();
        const queryParams = new URLSearchParams(location.search);
        const clientId = queryParams.get('clientId');

        useEffect(() => {
            const initialCartItems = JSON.parse(localStorage.getItem('cartItems')) || [];
            setCartItems(initialCartItems);
        }, []);


        useEffect(() => {
            const initialCartItems = JSON.parse(localStorage.getItem('cartItems')) || [];
            setCartItems(initialCartItems);
        }, []);

        const removeItem = (itemId) => {
            const updatedCartItems = cartItems.filter((item) => item.id !== itemId);
            setCartItems(updatedCartItems);
            localStorage.setItem('cartItems', JSON.stringify(updatedCartItems));
        };

        function createCart(date, clientId) {
            const requestOptions = {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ date, clientId }),
            };
            fetch('http://localhost:8080/api/cards/save/', requestOptions)
                .then((response) => response.json())
                .then((data) => console.log(data));

            console.log('Cart created' + date + clientId);
        }

        return (
            <div className="flex mt-0 items-center justify-center bg-gray overflow-hidden">
                <div className="w-full">
                    <div className="flex justify-between items-center mt-0 mb-4 border-b border-gray-300 pb-2 px-4 sm:px-0 bg-red-400">
                        <h2 className="text-2xl font-bold text-gray-600">Cart</h2>
                    </div>
                    <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4">
                        {cartItems.map((item) => (
                            <div className="col-md-4" key={item.id}>
                                <div className="bg-white rounded-lg shadow-lg overflow-hidden">
                                    <div className="bg-cover bg-center h-56 p-4" style={{ backgroundImage: `url(${item.image})` }}></div>
                                    <div className="p-4">
                                        <p className="uppercase tracking-wide text-sm font-bold text-gray-700">{item.name}</p>
                                        <p className="text-3xl text-gray-900">{item.price} €</p>
                                        <p className="text-gray-700">{item.description}</p>
                                        <p className="text-gray-700">{item.category}</p>
                                        <p className="text-gray-700">{item.quantity}</p>
                                    </div>
                                    <div className="flex p-4 border-t border-gray-300 text-gray-700">
                                        <div className="flex-1 inline-flex items-center">
                                            <button
                                                onClick={() => removeItem(item.id)}
                                                className="bg-red-500 hover:bg-red-600 text-white font-semibold px-4 py-2 rounded-md"
                                            >
                                                Remove
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        ))}
                    </div>
                    <div className="flex justify-between items-center mt-0 mb-4 border-b border-gray-300 pb-2 px-4 sm:px-0 bg-red-400">
                        <button
                            onClick={() => createCart(new Date(), clientId)}
                            className="bg-green-500 hover:bg-green-600 text-black font-semibold px-4 py-2 rounded-md"
                        >
                            Create Cart
                        </button>
                    </div>
                </div>
            </div>
        );
    }

