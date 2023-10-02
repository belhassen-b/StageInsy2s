import React, {useState} from 'react';
import { Formik, Field, Form, ErrorMessage } from 'formik';
import * as Yup from 'yup';
import { useNavigate } from 'react-router-dom';
import {loginAPICall, saveLoggedInUser, storeToken} from '../services/AuthService';
import "../styles/form.css";
import {getAllProducts} from "../services/ProductService";



const LoginForm = () => {
    const initialValues = {
        usernameOrEmail: '',
        password: '',
    };

     const [usernameOrEmail, setUsernameOrEmail] = useState('')
     const [password, setPassword] = useState('')

   const navigator = useNavigate();
    const validationSchema = Yup.object({
        usernameOrEmail: Yup.string().required('Nom d\'utilisateur ou Email est requis'),
        password: Yup.string().required('Mot de passe est requis'),
    });



    async function handleSubmit(e) {
        e.preventDefault();
        try {
            const response = await loginAPICall(usernameOrEmail, password);
            if (response.status === 200) {
                storeToken(response.data.access_token);
                saveLoggedInUser(usernameOrEmail);
                // Check if there are any products available
                const productsResponse = await getAllProducts();

                if (productsResponse.status === 200 && productsResponse.data.length > 0) {
                    // If there are products available, navigate to the add-product route
                    navigator('/products');
                } else {
                    // If no products are available, navigate to the products route
                    navigator('/add-product');
                }
            } else {
                alert('Invalid credentials');
            }
        } catch (error) {
            console.log(error);
        }
    }

    return (
        <div className="main-container">
            <div className="login-form-container">
                <h2 className="form-title">Connectez-vous : </h2>
                <Formik
                    initialValues={initialValues}
                    validationSchema={validationSchema}
                    onSubmit={handleSubmit}
                >
                    {({ isSubmitting }) => (
                        <Form autoComplete="off">
                            <div className="mb-4">
                                <label htmlFor="usernameOrEmail" className="text-sm font-medium text-gray-600">
                                   Nom d'utilisateur ou Email
                                </label>
                                <Field
                                    type="text"
                                    id="usernameOrEmail"
                                    name="usernameOrEmail"
                                    placeholder="Your username or email"
                                    className="mt-1 p-2 w-full rounded-md border border-gray-300 focus:ring focus:ring-purple-400 focus:outline-none"
                                    value={usernameOrEmail}
                                    onChange={(e) => setUsernameOrEmail(e.target.value)}
                                />
                                <ErrorMessage name="usernameOrEmail" component="div" className="text-red-500 text-sm" />
                            </div>
                            <div className="mb-4">
                                <label htmlFor="password" className="text-sm font-medium text-gray-600">
                                    Mot de passe
                                </label>
                                <Field
                                    type="password"
                                    id="password"
                                    name="password"
                                    placeholder="Your password"
                                    className="mt-1 p-2 w-full rounded-md border border-gray-300 focus:ring focus:ring-purple-400 focus:outline-none"
                                    value={password}
                                    onChange={(e) => setPassword(e.target.value)}
                                />
                                <ErrorMessage name="password" component="div" className="text-red-500 text-sm" />
                            </div>
                            {/*<div className="mb-4 flex justify-end">*/}
                            {/*    /!* eslint-disable-next-line jsx-a11y/anchor-is-valid *!/*/}
                            {/*    <a*/}
                            {/*        href="#"*/}
                            {/*        className="text-sm text-gray-600 hover:text-gray-800"*/}
                            {/*    >*/}
                            {/*        Forgot Your Password?*/}
                            {/*    </a>*/}
                            {/*</div>*/}
                            <div>
                                <button
                                    type="submit"
                                    disabled={isSubmitting}
                                    className="submit-button"
                                    onClick={handleSubmit}
                                >
                                    {isSubmitting ? 'Connexion en cours...' : 'Se connecter'}
                                </button>
                            </div>
                        </Form>
                    )}
                </Formik>
                <div className="mt-4 text-sm text-gray-600 text-center">
                    Vous n'avez pas de compte ?{' '}
                    <a
                        href= "/register"
                        className="text-green-600 hover:text-green-800"
                    >
                        Créer un compte
                    </a>
                </div>
            </div>
        </div>
    );
};
export default LoginForm;
