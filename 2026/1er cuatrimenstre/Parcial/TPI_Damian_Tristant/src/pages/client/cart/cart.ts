import { getCart, saveCart, updateQuantity, deleteItem } from "../../../utils/cart";

const renderCart = () => {
    const cart = getCart();
    const itemsContainer = document.getElementById("cart-items") as HTMLDivElement;
    const totalElement = document.getElementById("cart-total") as HTMLSpanElement;
    const totalFinal = document.getElementById("cart-total-final") as HTMLSpanElement;
    const checkoutBtn = document.getElementById("checkout-btn") as HTMLButtonElement | null;

    if (!itemsContainer || !totalElement) return;

    itemsContainer.innerHTML = "";
    let total = 0;

    // Carrito Vacío
    if (cart.length === 0) {
        itemsContainer.innerHTML = `<div class="empty-cart-msg"><p>Tu carrito está vacío 🍕</p></div>`;
        totalElement.textContent = "$0";
        if (totalFinal) totalFinal.textContent = "$0";
        if (checkoutBtn) {
            checkoutBtn.disabled = true;
            checkoutBtn.style.opacity = "0.5";
        }
        return;
    }

    //Carrito con productos
    if (checkoutBtn) {
        checkoutBtn.disabled = false;
        checkoutBtn.style.opacity = "1";
    }

    cart.forEach(item => {
        total += item.precio * item.quantity;
        const itemDiv = document.createElement("div");
        itemDiv.className = "cart-item";
        itemDiv.innerHTML = `
            <img src="/img/${item.imagen}" alt="${item.nombre}">
            <div class="item-info">
                <h3>${item.nombre}</h3>
                <p>$${item.precio.toLocaleString('es-AR')}</p>
            </div>
            <div class="item-controls">
                <button class="btn-qty minus" data-id="${item.id}">-</button>
                <span class="qty-number">${item.quantity}</span>
                <button class="btn-qty plus" data-id="${item.id}">+</button>
                <button class="btn-delete" data-id="${item.id}">🗑️</button>
            </div>
        `;
        itemsContainer.appendChild(itemDiv);
    });

    // --- Botones del carrito para sumar y restar productos ---
    itemsContainer.querySelectorAll(".btn-qty").forEach(btn => {
        btn.addEventListener("click", (e) => {
            const target = e.target as HTMLButtonElement;
            const id = Number(target.dataset.id);
            const isPlus = target.classList.contains("plus");
            updateQuantity(id, isPlus ? 1 : -1);
            renderCart(); // Refrescamos la vista
        });
    });

    itemsContainer.querySelectorAll(".btn-delete").forEach(btn => {
        btn.addEventListener("click", (e) => {
            const target = e.currentTarget as HTMLButtonElement;
            const id = Number(target.dataset.id);
            deleteItem(id);
            renderCart(); // Refrescamos la vista
        });
    });

    totalElement.textContent = `$${total.toLocaleString('es-AR')}`;
    if (totalFinal) totalFinal.textContent = `$${total.toLocaleString('es-AR')}`; 
};

// Eventos de botones principales
//Borrar carrito
document.getElementById("clear-cart-btn")?.addEventListener("click", () => {
    if (confirm("¿Estás seguro de que querés vaciar el carrito?")) {
        saveCart([]); 
        renderCart();
    }
});
//Realizar compra
document.getElementById("checkout-btn")?.addEventListener("click", () => {
    alert("¡Gracias por tu compra!");
    saveCart([]); 
    window.location.href = "../home/home.html"; 
});

// Inicializamos
renderCart();