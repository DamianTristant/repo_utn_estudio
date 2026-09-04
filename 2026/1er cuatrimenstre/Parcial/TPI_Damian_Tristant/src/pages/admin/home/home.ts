import { checkAuhtUser, logout } from "../../../utils/auth";
import type { Product } from "../../../types/product";
import { PRODUCTS, updateProducts } from "../../../utils/data";

// --- ELEMENTOS DEL DOM ---
const buttonLogout = document.getElementById("logoutButton") as HTMLButtonElement;
const tableBody = document.getElementById("admin-product-body") as HTMLTableSectionElement;

const modal = document.getElementById("product-modal") as HTMLDivElement;
const btnAddProduct = document.getElementById("btn-add-product") as HTMLButtonElement;
const btnCloseModal = document.getElementById("btn-close-modal") as HTMLButtonElement;
const btnCancel = document.getElementById("btn-cancel") as HTMLButtonElement;
const productForm = document.getElementById("product-form") as HTMLFormElement;

//Logica para eliminar
const eliminarProducto = (id: number) => {
    const nuevosProductos = PRODUCTS.filter(p => p.id !== id);
    updateProducts(nuevosProductos);
    renderAdminProducts(); 
};

//Logica para editar
const prepararEdicion = (id: number) => {
    const producto = PRODUCTS.find(p => p.id === id);
    if (!producto) return;

    (document.getElementById("p-id") as HTMLInputElement).value = producto.id.toString();
    (document.getElementById("p-nombre") as HTMLInputElement).value = producto.nombre;
    (document.getElementById("p-precio") as HTMLInputElement).value = producto.precio.toString();
    (document.getElementById("p-desc") as HTMLTextAreaElement).value = producto.descripcion;
    (document.getElementById("p-img") as HTMLInputElement).value = producto.imagen;

    const modalTitle = document.getElementById("modal-title");
    if (modalTitle) modalTitle.innerText = "Editar Producto";

    openModal();
};

//Logira para renderizar los productos
const renderAdminProducts = () => {
    if (!tableBody) return;
    tableBody.innerHTML = ""; 

    PRODUCTS.forEach(prod => {
        const tr = document.createElement("tr");
        tr.innerHTML = `
            <td><img src="/img/${prod.imagen}" alt="${prod.nombre}" class="img-admin"></td>
            <td><b>${prod.nombre}</b></td>
            <td>${prod.descripcion}</td>
            <td>$${prod.precio.toLocaleString('es-AR')}</td>
            <td>
                <button class="btn-edit" data-id="${prod.id}" title="Editar">✏️</button>
                <button class="btn-delete" data-id="${prod.id}" title="Eliminar">🗑️</button>
            </td>
        `;
        tableBody.appendChild(tr);
    });

    // Eventos de borrar
    document.querySelectorAll(".btn-delete").forEach(btn => {
        btn.addEventListener("click", (e) => {
            const target = e.currentTarget as HTMLButtonElement;
            const idABorrar = Number(target.dataset.id);
            if (confirm("¿Estás seguro de que querés eliminar este producto?")) {
                eliminarProducto(idABorrar);
            }
        });
    });
    
    // Eventos de editar
    document.querySelectorAll(".btn-edit").forEach(btn => {
        btn.addEventListener("click", (e) => {
            const target = e.currentTarget as HTMLButtonElement;
            const idAEditar = Number(target.dataset.id);
            prepararEdicion(idAEditar);
        });
    });
};

// --- LOGICA DEL MODAL ---
const openModal = () => {
    if (modal) modal.style.display = "flex";
};

const closeModal = () => {
    if (modal) {
        modal.style.display = "none";
        productForm.reset();
        // Limpiamos el ID y el título al cerrar por seguridad
        (document.getElementById("p-id") as HTMLInputElement).value = "";
        const modalTitle = document.getElementById("modal-title");
        if (modalTitle) modalTitle.innerText = "Agregar Producto";
    }
};

// --- EVENTOS ---
buttonLogout?.addEventListener("click", () => logout());
btnAddProduct?.addEventListener("click", openModal);
btnCloseModal?.addEventListener("click", closeModal);
btnCancel?.addEventListener("click", closeModal);

window.addEventListener("click", (e) => {
    if (e.target === modal) closeModal();
});

productForm?.addEventListener("submit", (e) => {
    e.preventDefault();

    const idExistente = (document.getElementById("p-id") as HTMLInputElement).value;
    const nombre = (document.getElementById("p-nombre") as HTMLInputElement).value;
    const precio = Number((document.getElementById("p-precio") as HTMLInputElement).value);
    const descripcion = (document.getElementById("p-desc") as HTMLTextAreaElement).value;
    const imagen = (document.getElementById("p-img") as HTMLInputElement).value;

    if (idExistente) {
        //Logica para editar
        const nuevosProductos = PRODUCTS.map(p => {
            if (p.id === Number(idExistente)) {
                return { ...p, nombre, precio, descripcion, imagen };
            }
            return p;
        });
        updateProducts(nuevosProductos);
    } else {
        //Logica para crear nuevo producto
        const newProduct: Product = {
            id: Date.now(),
            nombre, precio, descripcion, imagen,
            stock: 10, categorias: [], disponible: true, eliminado: false,
            createdAt: new Date().toISOString()
        };
        updateProducts([...PRODUCTS, newProduct]);
    }

    renderAdminProducts();
    closeModal();
});

//Inicializacion
const initPage = () => {
    checkAuhtUser("/src/pages/auth/login/login.html", "/src/pages/client/home/home.html", "admin");
    renderAdminProducts();
};

initPage();