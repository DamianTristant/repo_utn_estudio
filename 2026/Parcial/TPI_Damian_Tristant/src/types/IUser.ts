import type { Rol } from "./Rol";

export interface IUser {
  email: string;
  password: boolean;
  rol: Rol;
}
