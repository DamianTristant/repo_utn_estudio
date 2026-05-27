import { Rol } from "../../../types/rol";
import { Iuser } from "./../../../types/IUser";

const fromRegistro = document.querySelector('#form-registro') as HTMLFormElement;




fromRegistro.addEventListener('submit', (e) => {
    e.preventDefault();

    //captura los datos del usuario
    const email = (document.querySelector('#email') as HTMLInputElement).value;
    const password = (document.querySelector('#password') as HTMLInputElement).value;
    const rolInput = document.getElementById('rol') as HTMLSelectElement;
    const rolValue = rolInput.value as Rol; // Esto va a ser "admin" o "client"


    //traigo los datos existentes en localStorage o un array vacio si no hay
    const usuariosGuardados : Iuser[] = JSON.parse(localStorage.getItem('users') || '[]');

    const nuevoUsuario = {
    email: email,
    password: password,
    rol: rolValue // <-- Ahora se guarda lo que elegiste
};

    //agrego el usuario creado
    usuariosGuardados.push(nuevoUsuario);

    //guardo el array actualizado
    localStorage.setItem('users', JSON.stringify(usuariosGuardados));

    //alerta para que cliente sepa que se cargo con exito
    alert('Registro exitoso, puede iniciar secion.');
    window.location.href = '../login/login.html';
});