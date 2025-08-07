## Trabajo Integrador II de Matemática. 
## Integrantes: Enderson Suárez, 95734136 ; Damian Eduardo Tristant, 32036674; Vega Luis, 40572834
# se importan modulos.
from datetime import datetime                  #Para identificar el año actual hemos importado la función "datetime" de la libreria "datetime"
from modulo import *

########PARTE A: Operaciones con DNIs. Obtener conjuntos de digitos únicos#######################

# Pedimos los DNIs al usuario y definimos las variables
dni_1 = int(input("Ingrese el dni del primer integrante: "))    
dni_2 = int(input("Ingrese el dni del segundo integrante: "))
dni_3 = int(input("Ingrese el dni del tercer integrante: "))

# Obtenemos los conjuntos de dígitos únicos y los guardamos en variables distintas
conjunto_1 = set(obtener_digitos_unicos(dni_1))
conjunto_2 = set(obtener_digitos_unicos(dni_2))
conjunto_3 = set(obtener_digitos_unicos(dni_3))

# Imprimimos los resultados de los conjuntos únicos 
print("\n Resultados conjuntos ùnicos ")
print(f"DNI 1: {dni_1} : conjunto de dígitos únicos: A= {conjunto_1}")
print(f"DNI 2: {dni_2} : conjunto de dígitos únicos: B= {conjunto_2}")
print(f"DNI 3: {dni_3} : conjunto de dígitos únicos: C= {conjunto_3}")
########PARTE A: Operaciones entre conjuntos ###########################################

print("\n Unión de conjuntos: ")
print(conjunto_1 | conjunto_2 | conjunto_3)

# Imprimimos los resultados de la  intersección de los conjuntos
print("\nDígitos que aparecen en ambos DNI: ")
# Generamos las intersecciones con un bucle para listas.
print(f"La interseccion del conjunto A y B: {conjunto_1 & conjunto_2}")
print(f"La interseccion del conjunto B y C: {conjunto_2 & conjunto_3}")
print(f"La interseccion del conjunto A y C: {conjunto_1 & conjunto_3}")
print(f"La interseccion del conjunto A y B y C:{conjunto_1 & conjunto_2 & conjunto_3} ")

# forma de hacerlo con diccionario:
# Imprimimos la diferencia (A-B)
print(f"\nDígitos que están en el DNI {dni_1} pero no en el DNI {dni_2}, la diferencia (A-B): ")
print( conjunto_1 - conjunto_2)
#Imprimir la diferencia (A-C)
print(f"\nDígitos que están en el DNI {dni_2} pero no en el DNI {dni_3}, la diferencia (A-C):")
print(conjunto_1 - conjunto_3)
#Imprimir la diferencia (B-A)
print(f"\nDígitos que están en el DNI {dni_2} pero no en el DNI {dni_1}, la diferencia (B-A):")
print(conjunto_2 - conjunto_1)
#Imprimir la diferencia (B-C)
print(f"\nDígitos que están en el DNI {dni_2} pero no en el DNI {dni_3}, la diferencia (B-C):")
print(conjunto_2 - conjunto_3)
#Imprimir la diferencia (C-A)
print(f"\nDígitos que están en el DNI {dni_3} pero no en el DNI {dni_1}, la diferencia (C-A):")
print(conjunto_3 - conjunto_1)
#Imprimir la diferencia (C-B)
print(f"\nDígitos que están en el DNI {dni_3} pero no en el DNI {dni_2}, la diferencia (C-B):")
print(conjunto_3 - conjunto_2)

# Imprimir la diferencia simétrica
unionABC= conjunto_1 | conjunto_2 | conjunto_3
interseccionABC= conjunto_1 & conjunto_2 & conjunto_3
print("\nElementos que no forman parte de los tres conjuntos al mismo tiempo, (diferencia simetrica = union - interseccion):")
print(unionABC-interseccionABC)

# Imprimir por pantalla la frecuencia de los digitos en cada DNI
print("\n= Frecuencia de dígitos en cada DNI =")

#Pasamos a guardarlo en una lista para usarlos en nuestro bluce
dnis = [dni_1, dni_2, dni_3]

# Recorremos la lista de DNIs (ya creada con dni_1, dni_2 y dni_3) usando un bucle for
for i in range(len(dnis)):
    dni_actual = dnis[i]  # Obtenemos el DNI en la posición i (puede ser dni_1 o dni_2)
    frecuencia = contar_frecuencia_digitos(dni_actual)              # Aplicamos la función
    print(f"DNI {i + 1} ({dni_actual}): {frecuencia}")              # Mostramos el resultado

# Imprimir los resultados de la suma de los digitos de los DNI
print("\n= Suma total de los dígitos de cada DNI =")
sumas = sumar_digitos_lista(dnis)
for i in range(len(dnis)):                                          # Recorremos los índices de la lista de DNIs
    print(f"DNI {i + 1} ({dnis[i]}): suma total = {sumas[i]}")      # Imprimimos por consola los resultados
    print()                                                         # Print para forzar un salto de linea que no pudimos obtener mas abajo por razon desconcida.

# EXPRESIONES LOGICAS A VERIFICAR CON NUESTROS CONJUNTOS:     

# EXPRESION 1: Determinar si todos los dígitos del segundo DNI también están en el primero.
incluidos = all(digito in conjunto_1 for digito in conjunto_2)
if incluidos:
    print("\nPara la primer expresión lógica: Determinar si todos los dígitos del segundo DNI también están en el primero.")
    print("Se cumple: Todos los dígitos del segundo DNI están también en el primero.")
else:
    print("Para la primer expresión lógica: Determinar si todos los dígitos del segundo DNI también están en el primero.")
    print("No se cumple: Hay al menos un dígito del segundo DNI que no está en el primero.")


# EXPRESION 2: Verificar si hay al menos un dígito que se repite en ambos DNIs.

if len(conjunto_1 & conjunto_2) > 0:
    print("\nPara la segunda expresión lógica: Verificar si hay al menos un dígito que se repite en ambos DNIs")
    print("Se cumple: Hay dígitos que se repiten en ambos DNIs.")
else:
    print("Para la segunda expresión lógica: Verificar si hay al menos un dígito que se repite en ambos DNIs")
    print("No hay dígitos en común.")

print ()

########PARTE B: Operaciones con años de nacimiento. Obtener conjuntos de digitos únicos#######################
print("Bienvenidos a la segunda parte de nuestro programa.")
print("De ahora en adelante vamos a trabajar con los años de nacimiento.")

# Pedimos los años al usuario
anio_1 = int(input("\nIngrese el primer año de nacimiento: "))    
anio_2 = int(input("Ingrese el segundo año de nacimiento: "))
anio_3 = int(input("Ingrese el tercer año de nacimiento: "))

# Guardamos los años en una lista
anios_nacimiento = [anio_1, anio_2, anio_3]

# Inicializamos los contadores de años pares e impares
pares = 0
impares = 0

for anio in anios_nacimiento:           # Recorremos la lista de años y contamos
    if anio % 2 == 0:                    # Si es divisible por 2 (operador MOD 2, resto 0), es un año par
        pares += 1                     
    else:
        impares += 1                    # Si no es divisible entre dos (con residuo) es impar

# Imprimimos los resultados los resultados: 
print("\n¿Cuántos nacieron en años pares y cuántos en años impares?")
print(f"Nacidos en años pares: {pares}")
print(f"Nacidos en años impares: {impares}")

# Si alguno nació en año bisiesto, mostrar "Tenemos un año especial".
def bisiesto(anio):
    return anio % 4 == 0 and (anio % 100 != 0 or anio % 400 == 0) 

# Pasamos a verificar si todos nacieron después del año 2000:
if anio_1 > 2000 and anio_2 > 2000 and anio_3 > 2000:         
    print("\nTodos los integrantes nacieron después del 2000.")
    print("Grupo Z")
else:
    print("\nNo todos los integrantes nacieron después del 2000.")

if bisiesto(anio_1) or bisiesto(anio_2) or bisiesto(anio_3):
    print("\nTenemos un año especial: al menos un integrante nació en un año bisiesto.")
else:
    print("\nNinguno de los integrantes nació en un año bisiesto.")


# Calcular el producto cartesiano entre el conjunto de años y el conjunto de edades actuales.

anio_actual = datetime.now().year               # Consiguiendo de esta manera el año actual

# Tenemos que calcular las edades actuales
edades_actuales = []
for anio in anios_nacimiento:
    edad = anio_actual - anio
    edades_actuales.append(edad)

producto_cartesiano = []
for i in range(len(anios_nacimiento)):
    producto_cartesiano.append((anios_nacimiento[i], edades_actuales[i]))


# Mostramos el resultado
print("\n Producto cartesiano entre años de nacimiento y edades actuales: ")
#for pares in producto_cartesiano:
print(producto_cartesiano)
