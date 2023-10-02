import { BrowserRouter, Navigate, Route, Routes } from "react-router-dom";
import NavbarComponent from "./components/NavbarComponent";
import LoginForm from "./components/LoginForm";
import RegisterForm from "./components/RegisterForm";
import { isUserLoggedIn } from "./services/AuthService";
import ProductListComponent from "./components/ProductListComponent";
import './styles/global.css';
import ProductAdd from "./components/ProductAdd";
import CartListComponent from "./components/CartListComponent";
import Cart from "./components/Cart";

function App() {

    function AuthenticatedRoute({ children }) {
        const isAuth = isUserLoggedIn();
        if(isAuth) {
            return children;
        }
        return <Navigate to="/products" />

    }
    return (
        <>
            <BrowserRouter>
                <Routes>
                <Route path="/" element={<LoginForm />} />
                <Route path="/register" element={<RegisterForm />} />
                </Routes>
                <NavbarComponent />
                <Routes>
                    <Route path="/" element={<LoginForm />} />
                    <Route path="/register" element={<RegisterForm />} />
                    <Route path="/products"  element={
                        <AuthenticatedRoute>
                            <ProductListComponent />
                        </AuthenticatedRoute>
                    } />
                    <Route path="/add-product" element={
                        <AuthenticatedRoute>
                            <ProductAdd />
                        </AuthenticatedRoute>
                    } />
                    <Route path="/cartListComponent" element={
                        <AuthenticatedRoute>
                            <CartListComponent />
                        </AuthenticatedRoute>
                    } />
                    <Route path="/cart" element={
                        <AuthenticatedRoute>
                            <Cart />
                        </AuthenticatedRoute>
                    } />
                </Routes>
            </BrowserRouter>

        </>

    );
}

export default App;
