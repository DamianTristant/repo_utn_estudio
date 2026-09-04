import type { Product } from "../types/product";

export interface CartItem extends Product {
    quantity: number;
}

export const getCart = (): CartItem[] => {
    const cart = localStorage.getItem("cart");
    return cart ? JSON.parse(cart) : [];
};

export const saveCart = (cart: CartItem[]) => {
    localStorage.setItem("cart", JSON.stringify(cart));
};

export const addToCart = (product: Product) => {
    const cart = getCart();
    const existing = cart.find(item => item.id === product.id);
    if (existing) {
        existing.quantity += 1;
    } else {
        cart.push({ ...product, quantity: 1 });
    }
    saveCart(cart);
};

export const updateQuantity = (productId: number, amount: number) => {
    const cart = getCart();
    const productIndex = cart.findIndex(item => item.id === productId);
    
    if (productIndex !== -1) {
        cart[productIndex].quantity += amount;
        
        // Si la cantidad llega a 0 o menos, lo eliminamos
        if (cart[productIndex].quantity <= 0) {
            cart.splice(productIndex, 1);
        }
        saveCart(cart);
    }
};

export const deleteItem = (productId: number) => {
    const cart = getCart().filter(item => item.id !== productId);
    saveCart(cart);
};