const listaCategorias = document.getElementById("lista-categorias");

const cargarCategorias = () => {
    categorias.forEach((categoria) => {
        const li = document.createElement('li')
        li.innerHTML = `<a href="#">${categoria}</a>`
        listaCategorias.appendChild(li)
    });
};

const contenedorProductos = document.getElementById("contenedor-productos");

const cargarProductos = () => {
    productos.forEach((producto) =>{
        const article = document.createElement("article");
        article.classList.add("producto-card");
        article.innerHTML = `
        <img src="${producto.imagen}" alt="${producto.nombre}">
        <h3>${producto.nombre}</h3>
        <p>${producto.descripcion}</p>
        <span class="precio">$${producto.precio}</span>
        <button class="btn-agregar">Agregar</button>       
        `;

        const boton = article.querySelector(".btn-agregar");
        boton.addEventListener("click", () => {
            alert(`Has seleccionado: ${producto.nombre}`);
        });

        contenedorProductos.appendChild(article);
    })
}

cargarCategorias();
cargarProductos();