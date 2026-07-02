import type { IUser } from "../../../types/IUser";
import type { Rol } from "../../../types/Rol";
import { navigate } from "../../../utils/navigate";

//Variable del login
const form = document.getElementById("form") as HTMLFormElement;
const inputEmail = document.getElementById("email") as HTMLInputElement;
const inputPassword = document.getElementById("password") as HTMLInputElement;
//Selector de rol para validar
const selectRol = document.getElementById("rol") as HTMLSelectElement;

form.addEventListener("submit", (e: SubmitEvent) => {
  e.preventDefault();
  
  const valueEmail = inputEmail.value.trim();
  const valuePassword = inputPassword.value.trim();
  const valueRol = selectRol.value as Rol;

  // 1. Buscamos la lista de usuarios que se registraron
  const rawUsers = localStorage.getItem('users'); // 'users' es la clave que usamos en registro.ts
  const usuariosGuardados: IUser[] = rawUsers ? JSON.parse(rawUsers) : [];

  // 2. Buscamos si existe alguien con ese mail y esa contraseña
  // Importante: usamos "as any" en password porque en la lista está como string
  const usuarioEncontrado = usuariosGuardados.find(u => 
    u.email.toLowerCase() === valueEmail.toLowerCase() && 
    (u.password as any) === valuePassword &&
    u.rol === valueRol
  );

  if (usuarioEncontrado) {
    // 3. Si lo encontramos, creamos la sesión activa
    // Guardamos 'userData' para que los Guards (checkAuhtUser) funcionen
    const sessionUser: IUser = {
      email: usuarioEncontrado.email,
      rol: usuarioEncontrado.rol,
      password: true // Esto le dice a los Guards que la sesión está OK
    };

    localStorage.setItem("userData", JSON.stringify(sessionUser));

    alert(`¡Hola de nuevo! Entrando como ${usuarioEncontrado.rol}...`);

    // 4. Redirección según el rol
    if (usuarioEncontrado.rol === "admin") {
      navigate("/src/pages/admin/home/home.html");
    } else {
      navigate("/src/pages/client/home/home.html");
    }
  } else {
    // Si no lo encuentra, no lo deja pasar
    alert("Datos incorrectos. Revisá el email, la contraseña o el rol seleccionado.");
  }
});
