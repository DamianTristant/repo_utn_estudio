import {Iuser} from '../../../types/IUser';

const formLogin = document.querySelector('#form-login') as HTMLFormElement;

const dataOriginal = localStorage.getItem('users');
console.log("Dato crudo en LocalStorage:", dataOriginal);

const usuarios = JSON.parse(dataOriginal || '[]');

formLogin.addEventListener('submit', (e) => {
    e.preventDefault();
    console.log("¡El botón funciona!");

    const emailInput = document.querySelector('#email') as HTMLInputElement;
    const passwordInput = document.querySelector('#password') as HTMLInputElement;

    const emailValue = emailInput.value.trim(); 
    const passwordValue = passwordInput.value.trim();

    // Busca en el array de users
    const usuarios = JSON.parse(localStorage.getItem('users') || '[]');

    const coincidencia = usuarios.find((u : any) => 
    u.email === emailValue && u.password === passwordValue
);

if (coincidencia) {
    console.log("Usuario encontrado:", coincidencia);
    // ✅ SI EXISTE: Guardamos y redirigimos
    localStorage.setItem('userData', JSON.stringify(coincidencia));
    alert('¡Bienvenido!');

    if (coincidencia.rol === 'admin') {
        window.location.href = '/src/pages/admin/admin.html';
    } else {
        window.location.href = '/index.html';
    }
} else {
    // ❌ NO EXISTE: Aquí es donde evitamos que el código explote
    alert('Credenciales inválidas. Verificá tu mail y contraseña.');
    console.error("Error: No se encontró el usuario en la lista.");
}
});
