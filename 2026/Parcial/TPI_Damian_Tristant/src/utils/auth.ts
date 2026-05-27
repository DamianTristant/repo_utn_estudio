import type { IUser } from "../types/IUser";
import type { Rol } from "../types/Rol";
import { getUSer, removeUser } from "./localStorage";
import { navigate } from "./navigate";

export const checkAuhtUser = (
  redireccionLogin: string, // Si no está logueado
  redireccionOtroRol: string, // Si el rol es incorrecto
  rolEsperado: Rol
) => {
  const user = getUSer();

  // 1. Si no hay nada en el LocalStorage
  if (!user) {
    navigate(redireccionLogin);
    return;
  }

  const parseUser: IUser = JSON.parse(user);

  // 2. Verificamos si está logueado 
  // Y verificamos si el rol coincide
  if (parseUser.password !== true) {
    console.log("Sesión no activa");
    navigate(redireccionLogin);
    return;
  }

  if (parseUser.rol !== rolEsperado) {
    console.log("Rol incorrecto, redirigiendo para evitar bucle");
    navigate(redireccionOtroRol);
    return;
  }
  
  console.log("Acceso concedido para: ", rolEsperado);
};

export const logout = () => {
  removeUser();
  // Limpiamos todo el rastro para que no quede basura en la sesión
  localStorage.removeItem("userData"); 
  navigate("/src/pages/auth/login/login.html");
};
