import { productos } from '../../data/productos'; // Ajustá la ruta a tus datos
import { IProducto } from '../../types/IProducto'; // Ajustá a tu interfaz

const contenedorAdmin = document.getElementById("contenedor-productos-admin");

const cargarProductosAdmin = () => {
    if (!contenedorAdmin) return;

    contenedorAdmin.innerHTML = ""; // Limpiamos

    productos.forEach((prod: IProducto) => {
    const div = document.createElement("div");
    div.className = "producto-admin"; // Clase para el contenedor blanco
    div.innerHTML = `
        <div class="info-prod">
            <strong>${prod.nombre}</strong>
            <p>$${prod.precio} | <span>${prod.categoria}</span></p>
        </div>
        <div class="botones-acciones">
            <button class="btn-editar" onclick="alert('Editar ${prod.nombre}')">✏️ Editar</button>
            <button class="btn-eliminar" onclick="alert('Eliminar ${prod.nombre}')">🗑️ Eliminar</button>
        </div>
    `;
    contenedorAdmin.appendChild(div);
});
};

// Ejecutamos la carga al abrir la página
cargarProductosAdmin();

const btnLogout = document.getElementById('btn-logout');
if (btnLogout) {
    btnLogout.addEventListener('click', () => {
        localStorage.removeItem('userData'); // Borramos la sesión
        window.location.href = '/src/pages/auth/login/login.html'; // Al login
    });
}