import { IProducto } from "../types/IProducto";

export const productos : IProducto[] = [
    {
        id: 1, 
        nombre: "Hamburguesa Triple", 
        descripcion: "Triple carne, cheddar y bacon", 
        precio: 25000, 
        imagen: "/img/hamburguesa.jpg",
        categoria: "Hamburguesas"
    },
    {
        id: 2, 
        nombre: "Pizza Muzzarella", 
        descripcion: "Salsa casera y orégano", 
        precio: 18000, 
        imagen: "/img/pizza.jpg", 
        categoria: "Pizzas" 
   },
   { 
        id: 3, 
        nombre: "Papas Cheddar & Bacon", 
        descripcion: "Papas grandes con mucho cheddar y bacon picado", 
        precio: 12000, 
        imagen: "/img/papas-fritas.jpg", 
        categoria: "Papas Fritas"
   },
   { 
        id: 4, 
        nombre: "Gaseosa 500ml", 
        descripcion: "Línea Coca-Cola, bien fría", 
        precio: 4500, 
        imagen: "/img/bebida-cola.jpg", 
        categoria: "Bebidas"
   }
];