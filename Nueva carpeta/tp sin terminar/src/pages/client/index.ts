import { productos } from '../../data/productos'; 
import { IProducto } from '../../types/IProducto';


const categorias = ["Hamburguesas", "Papas", "Bebidas"];

const btnLogout = document.getElementById("btn-logout");

btnLogout?.addEventListener("click", () => {
    localStorage.removeItem("userData"); // Borra la sesión
    window.location.href = "../auth/login/login.html"; // Vuelve al inicio
});

const listaCategorias = document.getElementById("lista-categorias") as HTMLUListElement;
const contenedorProductos = document.getElementById("contenedor-productos") as HTMLElement;

const cargarCategorias = () : void => {
    //verificamos que el elemento exista antes de usarlo
    if(!listaCategorias) return;

    categorias.forEach((categoria: String) => {
        const li = document.createElement('li')
        li.innerHTML = `<a href="#">${categoria}</a>`
        listaCategorias.appendChild(li)
    });
};

const cargarProductos = (): void => {
    if(!contenedorProductos) return;

    productos.forEach((producto : IProducto) =>{
        const article = document.createElement("article");
        article.classList.add("producto-card");
        article.innerHTML = `
        <img src="${producto.imagen}" alt="${producto.nombre}">
        <h3>${producto.nombre}</h3>
        <p>${producto.descripcion}</p>
        <span class="precio">$${producto.precio}</span>
        <button class="btn-agregar">Agregar</button>       
        `;

        const boton = article.querySelector(".btn-agregar") as HTMLButtonElement;
        boton.addEventListener("click", () => {
            alert(`Has seleccionado: ${producto.nombre}`);
        });

        contenedorProductos.appendChild(article);
    })
}

cargarCategorias();
cargarProductos();