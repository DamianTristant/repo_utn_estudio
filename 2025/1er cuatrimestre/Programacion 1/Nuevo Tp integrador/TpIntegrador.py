# ----------------- MÓDULO: Agenda -----------------
from datos import agenda
# ----------------- MÓDULO: Funciones -----------------
from funciones import obtener_nombre, busqueda_lineal, busqueda_binaria

# ----------------- MÓDULO: Aplicación principal -----------------
def main():
    # Importamos la libreria time
    import time

    # Ordena la lista por nombre usando "sort" y la funcion obtener_nombre
    agenda.sort(key=obtener_nombre)

    # Imprimo la agenda completa ordenada alfabeticamente
    print("Agenda ordenada:\n")
    for contacto in agenda:
        print(f"Nombre: {contacto['nombre']}, Teléfono: {contacto['telefono']}, Email: {contacto['email']}")

    #Medicion tiempo busqueda lineal
    inicio_lineal = time.time()
    resultado_lineal = busqueda_lineal(agenda,"Carlos")
    fin_lineal = time.time()
    tiempo_lineal = fin_lineal - inicio_lineal

    #Medicion tiempo busqueda binaria
    inicio_binaria = time.time()
    resultado_binaria = busqueda_binaria(agenda,"Carlos")
    fin_binaria = time.time()
    tiempo_binaria = fin_binaria - inicio_binaria

    # Prueba búsqueda lineal y resultado por pantalla
    print("Resultado búsqueda lineal:")
    print(f"Nombre: {resultado_lineal['nombre']}, Teléfono: {resultado_lineal['telefono']}, Email: {resultado_lineal['email']}")
    print(f"Tiempo de busqueda: {tiempo_lineal:.6f} segundos")

    print("")
    # Prueba búsqueda binaria y resultado por pantalla
    print("Resultado búsqueda binaria:")
    print(f"Nombre: {resultado_binaria['nombre']}, Teléfono: {resultado_binaria['telefono']}, Email: {resultado_binaria['email']}")
    print(f"Tiempo de busqueda: {tiempo_binaria:.6f} segundos")

if __name__ == "__main__":
    main()