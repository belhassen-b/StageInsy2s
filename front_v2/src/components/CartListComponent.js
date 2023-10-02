import React, {useEffect} from 'react';
import {useNavigate} from "react-router-dom";
import {Fragment, useState} from 'react'
import {Dialog, Transition} from '@headlessui/react'
import {XMarkIcon} from '@heroicons/react/24/outline'


export default function CartListComponent({isCartOpen, setIsCartOpen}) {
    const [cartItems, setCartItems] = useState([]);

    const navigate = useNavigate();


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
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({date, clientId})
        };
        fetch('http://localhost:8080/api/cards/save/', requestOptions)
            .then(response => response.json())
            .then(data => console.log(data));

        console.log("Cart created" + date + clientId)
    }



    // console.log("Cart items:", cartItems)

        return (<Transition.Root show={isCartOpen} as={Fragment}>
                <Dialog as="div" className="relative z-10" onClose={setIsCartOpen}>
                    <Transition.Child
                        as={Fragment}
                        enter="ease-in-out duration-500"
                        enterFrom="opacity-0"
                        enterTo="opacity-100"
                        leave="ease-in-out duration-500"
                        leaveFrom="opacity-100"
                        leaveTo="opacity-0"
                    >
                        <div className="fixed inset-0 bg-gray-500 bg-opacity-75 transition-opacity"/>
                    </Transition.Child>

                    <div className="fixed inset-0 overflow-hidden">
                        <div className="absolute inset-0 overflow-hidden">
                            <div className="pointer-events-none fixed inset-y-0 right-0 flex max-w-full pl-10">
                                <Transition.Child
                                    as={Fragment}
                                    enter="transform transition ease-in-out duration-500 sm:duration-700"
                                    enterFrom="translate-x-full"
                                    enterTo="translate-x-0"
                                    leave="transform transition ease-in-out duration-500 sm:duration-700"
                                    leaveFrom="translate-x-0"
                                    leaveTo="translate-x-full"
                                >
                                    <Dialog.Panel className="pointer-events-auto w-screen max-w-md">
                                        <div className="flex h-full flex-col overflow-y-scroll bg-white shadow-xl">
                                            <div className="flex-1 overflow-y-auto px-4 py-6 sm:px-6">
                                                <div className="flex items-start justify-between">
                                                    <Dialog.Title className="text-lg font-medium text-gray-900">Shopping
                                                        cart</Dialog.Title>
                                                <div className="ml-3 flex h-7 items-center">
                                                    <button
                                                        type="button"
                                                        className="relative -m-2 p-2 text-gray-400 hover:text-gray-500"
                                                        onClick={() => setIsCartOpen(false)}
                                                    >
                                                        <span className="absolute -inset-0.5"/>
                                                        <span className="sr-only">Close panel</span>
                                                        <XMarkIcon className="h-6 w-6" aria-hidden="true"/>
                                                    </button>
                                                </div>
                                            </div>

                                            <div className="mt-8">
                                                <div className="flow-root">
                                                    {/* eslint-disable-next-line jsx-a11y/no-redundant-roles */}
                                                    <ul role="list" className="-my-6 divide-y divide-gray-200">
                                                        {cartItems.map((product) => (
                                                            <li key={product.id} className="flex py-6">
                                                                <div
                                                                    className="h-24 w-24 flex-shrink-0 overflow-hidden rounded-md border border-gray-200">
                                                                    <img
                                                                        src={product.image}
                                                                        alt={product.name}
                                                                        className="h-full w-full object-cover object-center"
                                                                    />
                                                                </div>

                                                                <div className="ml-4 flex flex-1 flex-col">
                                                                    <div>
                                                                        <div
                                                                            className="flex justify-between text-base font-medium text-gray-900">
                                                                            <h3>
                                                                                <a href={product.href}>{product.name}</a>
                                                                            </h3>
                                                                            <p className="ml-4">{product.price}€</p>
                                                                        </div>
                                                                    </div>
                                                                    <div
                                                                        className="flex-1 flex items-end justify-between text-sm">
                                                                        <p className="text-gray-500">Qty {product.quantity}</p>
                                                                        <div
                                                                            className={`flex items-center justify-center px-2 py-2 rounded-md text-xs font-medium ${product.quantity > 0 ? 'bg-red-100 text-red-800' : 'bg-gray-100 text-gray-800'}`}>
                                                                            {product.quantity > 0 ? 'In stock' : 'Out of stock'}
                                                                        </div>
                                                                        <div className="flex">
                                                                            <button
                                                                                type="button"
                                                                                className="font-medium text-indigo-600 hover:text-indigo-500"
                                                                                onClick={() => removeItem(product.id)} // Call removeItem with the item's id

                                                                            >
                                                                                Remove
                                                                            </button>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </li>))}
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>

                                        <div className="border-t border-gray-200 px-4 py-6 sm:px-6">
                                            <div className="flex justify-between text-base font-medium text-gray-900">
                                                <p>Sous-Total</p>
                                                <p> {cartItems.reduce((acc, item) => acc + item.price * item.quantity, 0)}</p>
                                            </div>
                                            <p className="mt-0.5 text-sm text-gray-500">Shipping and taxes calculated at
                                                checkout.</p>
                                            <div className="mt-6">
                                                <button
                                                    type="button"
                                                    className="flex justify-center items-center px-6 py-3 border border-transparent rounded-md shadow-sm text-base font-medium text-white bg-indigo-600 hover:bg-indigo-700"
                                                    onClick={() => {
                                                        setIsCartOpen(false);
                                                        createCart(new Date(), 1);
                                                        navigate('/products');
                                                    }
                                                    }
                                                >
                                                    Enregistrer
                                                </button>
                                            </div>
                                            <div className="mt-6 flex justify-center text-center text-sm text-gray-500">
                                                <p>
                                                    or
                                                    <button
                                                        type="button"
                                                        className="font-medium text-indigo-600 hover:text-indigo-500"
                                                        onClick={() => {
                                                            setIsCartOpen(false);
                                                            navigate('/products');
                                                        }}
                                                    >
                                                        Continue Shopping
                                                        <span aria-hidden="true"> &rarr;</span>
                                                    </button>
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                </Dialog.Panel>
                            </Transition.Child>
                        </div>
                    </div>
                </div>
            </Dialog>
        </Transition.Root>)
}
