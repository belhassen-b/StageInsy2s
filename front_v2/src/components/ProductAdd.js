import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { saveProduct } from '../services/ProductService';
import { Formik, Field, Form } from 'formik';

import 'tailwindcss/tailwind.css';
import * as Yup from 'yup';
import { getAllCategories } from '../services/categoryService';

function ProductAdd() {
    const [category, setCategory] = useState([]);

    const initialValues = {
        name: '',
        description: '',
        category: '',
        price: '',
        image: '',
        stock:''
    };

    const validationSchema = Yup.object({
        name: Yup.string().required('Un nom est requis'),
        description: Yup.string().required('Une description est requise'),
        category: Yup.string().required('Une catégorie est requise'),
        price: Yup.string().required('Un prix est requis'),
        image: Yup.string().required('Une image est requise'),

    });

    useEffect(() => {
        getAllCategories()
            .then((response) => {
                setCategory(response.data);
                console.log(response)
            })
            .catch((error) => {
                console.log('Error:', error);
            });
    }, []);

    const [product, setProduct] = useState(initialValues);

    const navigate = useNavigate();

    const handleInputChange = (event) => {
        const { name, value } = event.target;
        setProduct({ ...product, [name]: value });
        console.log(product)
    };

    const handleSubmit = async ( values, { setSubmitting }) => {
        setSubmitting(true);

        const selectedCategory = values.category;

        const newProduct = {
            ...values,
            category: selectedCategory,
        };



        const response = await saveProduct(newProduct);
        console.log(response)
        if (response.status === 200) {
            alert('Le produit a bien été ajouté');
            navigate('/products');
        } else {
            console.log(newProduct)
            alert('Error occurred');
        }
    };

    return (
        <div className="flex items-center justify-center h-screen bg-gray-200">
            <div className="w-auto">
                <div className="flex justify-between items-center mt-0 mb-4 border-b border-gray-300 pb-2 px-4 sm:px-0 bg-red-400">
                    <h2 className="text-2xl font-bold text-gray-600">Ajouter un produit </h2>
                </div>

                <Formik
                    initialValues={initialValues}
                    validationSchema={validationSchema}
                    onSubmit={handleSubmit}
                >
                    {({ isSubmitting }) => (
                        <Form autoComplete="off">
                            <div className="mb-4">
                                <label htmlFor="name" className="block text-gray-700 text-sm font-bold mb-2">
                                    Nom du produit
                                </label>
                                <Field
                                    type="text"
                                    id="name"
                                    name="name"
                                    placeholder="Nom du produit"
                                    className="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring focus:border-green-600"
                                />
                            </div>
                            <div className="mb-4">
                                <label htmlFor="description" className="block text-gray-700 text-sm font-bold mb-2">
                                    Description
                                </label>
                                <Field
                                    type="text"
                                    id="description"
                                    name="description"
                                    placeholder="Description"
                                    className="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring focus:border-green-600"
                                />
                            </div>
                            <div className="mb-4">
                                <label htmlFor="category" className="block text-gray-700 text-sm font-bold mb-2">
                                    Categorie
                                </label>
                                <Field
                                    as="select"
                                    id="category"
                                    name="category" // Updated name attribute to "category"
                                    className="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring focus:border-green-600"
                                >
                                    <option value="">Select a category</option>
                                    {category.map(item => (
                                        <option key={item} value={item}>
                                            {item}
                                        </option>
                                    ))}
                                </Field>
                            </div>
                            <div className="mb-4">
                                <label htmlFor="price" className="block text-gray-700 text-sm font-bold mb-2">
                                    Prix
                                </label>
                                <Field
                                    type="text"
                                    id="price"
                                    name="price"
                                    placeholder="Prix"
                                    className="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring focus:border-green-600"
                                />
                            </div>
                            <div className="mb-4">
                                <label htmlFor="image" className="block text-gray-700 text-sm font-bold mb-2">
                                    Image
                                </label>
                                <Field
                                    type="text"
                                    id="image"
                                    name="image"
                                    placeholder="Image"
                                    className="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring focus:border-green-600"
                                />
                            </div>
                            <div className="mb-4">
                                <label htmlFor="stock" className="block text-gray-700 text-sm font-bold mb-2">
                                    Stock
                                </label>
                                <Field
                                    type="number"
                                    id="stock"
                                    name="stock"
                                    placeholder="Stock"
                                    className="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring focus:border-green-600"
                                />
                            </div>

                            <div className="mb-4">
                                <button
                                    type="submit"
                                    disabled={isSubmitting}
                                    className="submit-button btn btn-primary bg-red-400 hover:bg-red-600 text-black font-semibold px-4 py-2 rounded-md"
                                >
                                 Ajouter un produit
                                </button>
                            </div>
                        </Form>
                    )}
                </Formik>
            </div>
        </div>
    );
}

export default ProductAdd;
