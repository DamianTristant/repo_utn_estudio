import type { IUser } from "../../../types/IUser";
import type { Rol } from "../../../types/Rol";

// Formulario de registro
const fromRegistro = document.querySelector('#register-form') as HTMLFormElement;

fromRegistro?.addEventListener('submit', (e) => {
    e.preventDefault();

    // captura los id: email,password y rol desde los id del html
    const emailInput = document.querySelector('#email') as HTMLInputElement;
    const passwordInput = document.querySelector('#password') as HTMLInputElement;
    const rolInput = document.querySelector('#rol') as HTMLSelectElement;
    
    if (!emailInput || !passwordInput || !rolInput) {
        console.error("No se encontraron los campos del formulario");
        return;
    }

    const email = emailInput.value.trim();
    const password = passwordInput.value;
    const rolValue = rolInput.value as Rol;

    const rawUsers = localStorage.getItem('users');
    const usuariosGuardados: IUser[] = rawUsers ? JSON.parse(rawUsers) : [];

    //variable para comparar si el usuario existe
    const existe = usuariosGuardados.some(u => u.email.toLowerCase() === email.toLowerCase());
    if (existe) {
        alert("Este correo ya está registrado.");
        return;
    }
    //Nuevo usuario
    const nuevoUsuario: IUser = {
        email: email,
        password: password as any, 
        rol: rolValue
    };
    //Agrega el usuario
    usuariosGuardados.push(nuevoUsuario);
    localStorage.setItem('users', JSON.stringify(usuariosGuardados));

    alert('¡Registro exitoso! Ya podés entrar a tu cuenta.');
    
    // Redirige al login
    window.location.href = '../login/login.html';
});