import { checkAuhtUser, logout } from "../../../utils/auth";
import { getCategories, PRODUCTS } from "../../../utils/data";
import type { Product } from "../../../types/product";
import { addToCart } from "../../../utils/cart";

const buttonLogout = document.getElementById("logoutButton") as HTMLButtonElement;
const container = document.getElementById("productos-container") as HTMLDivElement;
const searchInput = document.getElementById("search-input") as HTMLInputElement;
const categoriesContainer = document.getElementById("categories-container") as HTMLDivElement;

// Limpia el contenedor y genera las tarjetas de productos dinámicamente
const renderProducts = (productos: Product[]) => {
    if (!container) return;
    container.innerHTML = ""; 

    // Crea la estructura visual de cada producto (Card)
    productos.forEach(prod => {
        const card = document.createElement("article");
        card.className = "product-card";
        //Inyecta imagen, nombre, descripción y precio
        card.innerHTML = ` 
            <div class="card-img">
                <img src="/img/${prod.imagen}" alt="${prod.nombre}" style="width: 100%; height: 150px; object-fit: cover;">
            </div>
            <div class="card-info">
                <h3>${prod.nombre}</h3>
                <p>${prod.descripcion}</p>
                <div class="card-footer">
                    <span class="price"><b>$${prod.precio.toLocaleString('es-AR')}</b></span>
                    <button class="btn-add">Agregar</button>
                </div>
            </div>
        `;
        container.appendChild(card);

        //Logica para agregar al carrito
        const btnAdd = card.querySelector(".btn-add") as HTMLButtonElement;
        btnAdd.addEventListener("click", () => {
            addToCart(prod);
            alert(`${prod.nombre} se agregó al carrito`);
        });
    });
};

// Genera los botones de categorías y filtra los productos al hacer clic
const renderCategoryButtons = () => {
    if (!categoriesContainer) return;
    categoriesContainer.innerHTML = "";

    //El boton "todos" Reinicia la vista y muestra el catálogo completo
    const allBtn = document.createElement("button");
    allBtn.className = "cat-btn active";
    allBtn.textContent = "Todos";
    allBtn.addEventListener("click", () => {
        document.querySelectorAll(".cat-btn").forEach(b => b.classList.remove("active"));
        allBtn.classList.add("active");
        renderProducts(PRODUCTS);
    });
    categoriesContainer.appendChild(allBtn);

    //Filtra productos comparando su categoría con la seleccionada
    const categories = getCategories();
    categories.forEach(cat => {
        const btn = document.createElement("button");
        btn.className = "cat-btn";
        btn.textContent = cat.nombre;
        btn.addEventListener("click", () => {
            document.querySelectorAll(".cat-btn").forEach(b => b.classList.remove("active"));
            btn.classList.add("active");
            const filtered = PRODUCTS.filter(p => 
                p.categorias.some(c => c.nombre === cat.nombre)
            );
            renderProducts(filtered);
        });
        categoriesContainer.appendChild(btn);
    });
};

//La barra para filtrar productos por nombre o descripción en tiempo real
searchInput?.addEventListener("input", (e) => {
    const term = (e.target as HTMLInputElement).value.toLowerCase().trim();
    const filtered = PRODUCTS.filter(p => 
        p.nombre.toLowerCase().includes(term) || 
        p.descripcion.toLowerCase().includes(term)
    );
    renderProducts(filtered);
});

buttonLogout?.addEventListener("click", () => logout());

const initPage = () => {
    // Verifico que sea cliente, sino lo hecha
    checkAuhtUser("/src/pages/auth/login/login.html", "/src/pages/admin/home/home.html", "client");
    
    renderCategoryButtons();
    renderProducts(PRODUCTS);
};

initPage();