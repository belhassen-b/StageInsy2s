import React, {useState} from 'react';
import {NavLink, useNavigate} from 'react-router-dom';
import {getToken, isUserLoggedIn, logout} from '../services/AuthService';

const NavbarComponent = () => {
    const isAuth = isUserLoggedIn();
    const navigator = useNavigate();

    const [searchText, setSearchText] = useState({
        name: "",
    });

    function handleLogout() {
        logout();
        navigator('/login');
    }

    function getClientId() {
          const token = getToken();
          const base64Url = token.split('.')[1];
          const base64 = base64Url.replace('-', '+').replace('_', '/');
        return JSON.parse(window.atob(base64)).sub;

    }

    return (
        <header className="header sticky top-0 bg-orange-500 shadow-md flex flex-col md:flex-row items-center justify-between px-4 md:px-8 py-2" style={{ zIndex: 1 }}>
            <div className="flex items-center">
                <h1 className="w-full md:w-3/12 text-center md:text-left mb-2 md:mb-0">
                    <a href="/">
                        <svg xmlns="http://www.w3.org/2000/svg" height="5em" viewBox="0 0 576 512" fill="red"><path d="M0 24C0 10.7 10.7 0 24 0H69.5c22 0 41.5 12.8 50.6 32h411c26.3 0 45.5 25 38.6 50.4l-41 152.3c-8.5 31.4-37 53.3-69.5 53.3H170.7l5.4 28.5c2.2 11.3 12.1 19.5 23.6 19.5H488c13.3 0 24 10.7 24 24s-10.7 24-24 24H199.7c-34.6 0-64.3-24.6-70.7-58.5L77.4 54.5c-.7-3.8-4-6.5-7.9-6.5H24C10.7 48 0 37.3 0 24zM128 464a48 48 0 1 1 96 0 48 48 0 1 1 -96 0zm336-48a48 48 0 1 1 0 96 48 48 0 1 1 0-96z"/></svg>
                    </a>
                </h1>
                <div className="flex items-center md:hidden ml-auto">
                    <button
                        className="bg-green-500 hover:bg-green-600 text-black font-semibold px-4 py-2 rounded-md mr-2"
                        onClick={handleLogout}
                    >
                        Logout
                    </button>
                    <NavLink
                        to="/login"
                        className="bg-green-500 hover:bg-green-600 text-black font-semibold px-4 py-2 rounded-md"
                    >
                        Login
                    </NavLink>
                </div>
            </div>
            <nav className="nav font-semibold text-lg flex-grow md:flex-grow-0 md:ml-4">
                <ul className="flex flex-col md:flex-row items-center justify-center md:justify-start">
                    <li className="p-2 md:p-4 border-b-2 border-green-500 border-opacity-0 hover:border-opacity-100 hover:text-green-500 duration-200 cursor-pointer active">
                        <a href="/">Accueil</a>
                    </li>
                    <li className="p-2 md:p-4 border-b-2 border-green-500 border-opacity-0 hover:border-opacity-100 hover:text-green-500 duration-200 cursor-pointer">
                        <a href="/products">Produits</a>
                    </li>
                    <li className="p-2 md:p-4 border-b-2 border-green-500 border-opacity-0 hover:border-opacity-100 hover:text-green-500 duration-200 cursor-pointer">
                        <button
                            onClick={() => {
                                const clientId = getClientId();
                                navigator(`/cart?clientId=${clientId}`);
                            }}
                        >
                            Panier
                        </button>
                    </li>
                    <li className="p-2 md:p-4 border-b-2 border-green-500 border-opacity-0 hover:border-opacity-100 hover:text-green-500 duration-200 cursor-pointer">
                        <a href="/">Contact</a>
                    </li>
                </ul>
            </nav>
            <div className="flex items-center mt-2 md:mt-0">
                <input
                    className="border-2 border-gray-300 bg-white h-10 px-4 md:px-5 pr-8 rounded-lg text-sm focus:outline-none"
                    type="search"
                    name="search"
                    placeholder="Search"
                    value={searchText.name}
                    onChange={(e) => setSearchText({ name: e.target.value })}
                />
                 <button   className="ml-2 md:ml-4 text-black"
                          type="button"
                          onClick={() => {
                                navigator(`/search/${searchText.name}`);
                          }
                            }
                >
                    <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 512 512"><path d="M505 442.7L405.3 343c-4.5-4.5-10.6-7-17-7H372c27.6-35.3 44-79.7 44-128C416 93.1 322.9 0 208 0S0 93.1 0 208s93.1 208 208 208c48.3 0 92.7-16.4 128-44v16.3c0 6.4 2.5 12.5 7 17l99.7 99.7c9.4 9.4 24.6 9.4 33.9 0l28.3-28.3c9.4-9.4 9.4-24.6.1-34zM208 336c-70.7 0-128-57.2-128-128 0-70.7 57.2-128 128-128 70.7 0 128 57.2 128 128 0 70.7-57.2 128-128 128z"/></svg>                </button>
                <div className="hidden md:flex ml-4">
                    {isAuth ? (
                        <button
                            onClick={handleLogout}
                            className="bg-green-500 hover:bg-green-600 text-black font-semibold px-4 py-2 rounded-md"
                        >
                            Logout
                        </button>
                    ) : (
                        <NavLink
                            to="/login"
                            className="bg-green-500 hover:bg-green-600 text-black font-semibold px-4 py-2 rounded-md"
                        >
                            Login
                        </NavLink>
                    )}
                </div>
            </div>
        </header>
    );
};

export default NavbarComponent;
