import React from 'react';
import {
    registerAPICall,
    saveLoggedInUser,
    storeToken,
} from '../services/AuthService';
import { Formik, Field, Form } from 'formik';
import * as Yup from 'yup';
import "../styles/form.css";
import {useNavigate} from "react-router-dom";


function RegisterForm() {
    const initialValues = {
        username: '',
        firstname: '',
        lastname: '',
        email: '',
        password: '',
    };

    const navigate = useNavigate();

    const validationSchema = Yup.object({
        username: Yup.string().required('Nom d\'utilisateur est requis'),
        firstname: Yup.string().required('Prénom est requis'),
        lastname: Yup.string().required('Nom est requis'),
        email: Yup.string().email('Format Email Invalide').required('Email est requis'),
        password: Yup.string()
            .required('Mot de passe est requis')
            .min(3, 'Le mot de passe doit contenir au moins 3 caractères'),
    });

    const handleSubmit = async (values, { setSubmitting }) => {
        console.log(values);
        setSubmitting(true);
        const response = await registerAPICall(values);
        console.log(response);
        if (response.status === 200) {
            const data = response.data;
            const token = data.token;
            const user = data.user;
            storeToken(token);
            saveLoggedInUser(user);
            console.log(user);
            alert('Enregistrement réussi');
            navigate('/login');
        } else {
            alert('Erreur lors de l\'enregistrement');
        }
        setSubmitting(false);
    };

    return (
        <div className="flex items-center justify-center h-screen bg-gray-200">
            <div className="register-form-container">
                <div className="mb-6 text-2xl font-bold text-gray-600 ">Register</div>
                <Formik
                    initialValues={initialValues}
                    validationSchema={validationSchema}
                    onSubmit={handleSubmit}
                >
                    {({ isSubmitting }) => (
                        <Form autoComplete="off">
                            <div className="mb-4">
                                <label htmlFor="username" className="block text-gray-700 text-sm font-bold mb-2">
                                    Nom d'utilisateur
                                </label>
                                <Field
                                    type="text"
                                    id="username"
                                    name="username"
                                    placeholder="Nom d'utilisateur"
                                    className="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring focus:border-green-600"
                                />
                            </div>
                            <div className="mb-4">
                                <label htmlFor="firstname" className="block text-gray-700 text-sm font-bold mb-2">
                                    Prénom
                                </label>
                                <Field
                                    type="text"
                                    id="firstname"
                                    name="firstname"
                                    placeholder="Prénom"
                                    className="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring focus:border-green-600"
                                />
                            </div>
                            <div className="mb-4">
                                <label htmlFor="lastname" className="block text-gray-700 text-sm font-bold mb-2">
                                    Nom
                                </label>
                                <Field
                                    type="text"
                                    id="lastname"
                                    name="lastname"
                                    placeholder="Nom"
                                    className="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring focus:border-green-600"
                                />
                            </div>
                            <div className="mb-4">
                                <label htmlFor="email" className="block text-gray-700 text-sm font-bold mb-2">
                                    Email
                                </label>
                                <Field
                                    type="email"
                                    id="email"
                                    name="email"
                                    placeholder="Email"
                                    className="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring focus:border-green-600"
                                />
                            </div>
                            <div className="mb-4">
                                <label htmlFor="password" className="block text-gray text-sm font-bold mb-2">
                                    Mot de passe
                                </label>
                                <Field
                                    type="password"
                                    id="password"
                                    name="password"
                                    placeholder="Password"
                                    className="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring focus:border-green-600"
                                />
                            </div>
                            <div className="mb-4">
                                <button
                                    type="submit"
                                    className="submit-button"
                                    disabled={isSubmitting}
                                >
                                    Enregistrer
                                </button>
                            </div>
                            {/*<div className="flex items-center -mt-4">*/}
                            {/*    <div className="ml-auto">*/}
                            {/*        <a*/}
                            {/*            href="#"*/}
                            {/*            className="text-xs font-thin text-black hover:text-red-500"*/}
                            {/*        >*/}
                            {/*            Forgot Your Password?*/}
                            {/*        </a>*/}
                            {/*    </div>*/}
                            {/*</div>*/}
                        </Form>
                    )}
                </Formik>
            </div>
        </div>
    );
}

export default RegisterForm;
