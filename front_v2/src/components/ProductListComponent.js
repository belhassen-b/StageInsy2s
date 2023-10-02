import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import { getAllProducts } from "../services/ProductService";
import ProductCard from "./ProductCard";
import 'tailwindcss/tailwind.css';


const ProductListComponent = () => {
    const navigate = useNavigate();
    const [products, setProducts] = useState([]);


    const [isCartOpen, setIsCartOpen] = useState(false);

    const userRole ="role_admin"  // pour tester acces admin
    function listProducts() {
        getAllProducts()
            .then((response) => {
                setProducts(response.data);
            })
            .catch((error) => {
                console.log("Error:", error);
            });
    }

    useEffect(() => {
        listProducts();
    }, []);


    useEffect(() => {
    }, [products]);


    function addNewProduct(){
        navigate('/add-product')

    }
    return (
        <div className="flex mt-0 items-center justify-center bg-gray overflow-hidden">
            <div className="w-full">
                <div className="flex justify-between items-center mt-0 mb-4 border-b border-gray-300 pb-2 px-4 sm:px-0 bg-red-400">
                    <h2 className="text-2xl font-bold text-gray-600">Au petit marché </h2>
                    {userRole === 'role_admin' && (
                        <button
                            onClick={addNewProduct}
                            className="bg-green-500 hover:bg-green-600 text-black font-semibold px-4 py-2 rounded-md"
                        >
                      Ajouter un produit
                    </button>
                    )}
                </div>
                <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4">
                    {products.map((product) => (
                        <div className="col-md-4" key={product.id}>
                        <ProductCard onCartOpen={() => setIsCartOpen(true)}
                            id={product.id}
                            name={product.name}
                            description={product.description}
                            price={product.price}
                            category={product.category}
                            image={product.image}

                        />
                </div>
                    ))}

            </div>
        </div>
        </div>

    )
}

export default ProductListComponent;