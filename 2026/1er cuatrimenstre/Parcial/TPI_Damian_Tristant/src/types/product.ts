import type { ICategory } from "../types/category";

export interface Product {
    id: number;
    nombre: string;
    precio: number;
    descripcion: string;
    imagen: string;
    stock: number;
    categorias: ICategory[];
    disponible: boolean;
    eliminado: boolean;
    createdAt: string;
}