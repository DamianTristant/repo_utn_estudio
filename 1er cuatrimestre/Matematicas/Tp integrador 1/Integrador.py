
## Trabajo Integrador II de Matemática. 
## Integrantes: Enderson Suárez, 95734136 ; Damian Eduardo Tristant, 32036674;

########PARTE A: Operaciones con DNIs. Obtener conjuntos de digitos únicos#######################

# Pedimos los DNIs al usuario y definimos las variables
dni_1 = int(input("Ingrese el dni del primer integrante: "))    
dni_2 = int(input("Ingrese el dni del segundo integrante: "))

#Pasamos a guardarlo en una lista para usarlos en nuestro bluce
dnis = [dni_1, dni_2]

# Definimos la función que vamos a utilizar para obtener los digitos únicos
def obtener_digitos_unicos(dni):            #Aquí el parámetro que va a recibir nuestra función para operar es el dni.
    digitos = []                            #Creamos una lista vacia para guardar los digitos únicos que vamos encontrando.
    for caracter in str(dni):               # Recorremos el DNI como string.
        digito = int(caracter)              # Defimos la variable digito y Convertimos cada carácter a número y 
        if digito not in digitos:           # Si no está en la lista digitos, lo agregamos. Esto evita los digitos repetidos. 
            digitos.append(digito)
    return digitos                          # Devolvemos la lista de dígitos únicos

# Generamos los conjuntos de dígitos únicos
conjuntos = [] 
for dni in dnis:
    conjuntos.append(obtener_digitos_unicos(dni))

# Obtenemos los conjuntos de dígitos únicos y los guardamos en variables distintas
conjunto_1 = obtener_digitos_unicos(dni_1)
conjunto_2 = obtener_digitos_unicos(dni_2)

# Imprimimos los resultados de los conjuntos únicos 
print("\n Resultados conjuntos ùnicos ")
print(f"DNI 1: {dni_1} : conjunto de dígitos únicos: {conjunto_1}")
print(f"DNI 2: {dni_2} : conjunto de dígitos únicos: {conjunto_2}")

########PARTE A: Operaciones entre conjuntos ###########################################

# UNION: Definimos la función que une los elementos de dos conjuntos sin repetirlos.
def union(conjunto_1, conjunto_2):
    union_result = []  # Usamos una lista vacía donde vamos a guardar la unión de conjunto_1 y conjunto_2

    # Recorremos cada elemento del primer conjunto de digitos únicos (definido en la linea 26), con un bucle for
    for digito in conjunto_1:
        if digito not in union_result:  # Si el digito evaluado en la vuelta no está en la lista lo agregamos
            union_result.append(digito) # Agregamos el digito a la lista

    # Recorremos cada elemento del segundo conjunto de digitos únicos (definido en la linea 27), con un bucle for
    for digito in conjunto_2:
        if digito not in union_result:  # Si el digito evaluado en la vuelta no está en la lista lo agregamos
            union_result.append(digito) #Agregamos el digito a la lista

    return union_result  # Devolvemos el resultado final de la unión

#Imprimos los resuldos de la unión
print("\n Unión de conjuntos: ")
print(union(conjunto_1, conjunto_2))

# INTERSECCIÓN: Definimos la función que arroja como resultado la intersección de los conjuntos.
def interseccion(conjunto_1, conjunto_2):
    interseccion_result = []                # Usamos una lista vacía donde vamos a guardar la intersección de conjunto_1 y conjunto_2

    for digito in conjunto_1:               # Recorremos cada elemento del primer conjunto   
        if digito in conjunto_2 and digito not in interseccion_result:     # Si ese dígito también está en el segundo conjunto y aún no lo agregamos
            interseccion_result.append(digito)                             # Lo agregamos a la lista interseccion_result

    return interseccion_result  # Devolvemos el resultado final de la intersección

# Imprimimos los resultados de la  intersección de los conjuntos
print("\nDígitos que aparecen en ambos DNI: ")
print(interseccion(conjunto_1, conjunto_2))

# DIFERENCIAS: Definimos la función que arroja como resultado la diferencia entre A-B y B-A
# (A-B): Función que devuelve los elementos que están en conjunto_1 pero no en conjunto_2 (A-B)
def diferenciaAB(conjunto_1, conjunto_2):
    diferenciaAB_resultado = []  # Lista vacía para guardar los elementos exclusivos del conjunto_1

    for digito in conjunto_1:                       # Recorremos cada elemento del primer conjunto
        if digito not in conjunto_2:                # Si ese dígito no está en el segundo conjunto
            diferenciaAB_resultado.append(digito)   # lo agregamos a la lista de resultado

    return diferenciaAB_resultado                   # Devolvemos el resultado de A-B

# Imprimimos la diferencia (A-B)
print(f"\nDígitos que están en el DNI {dni_1} pero no en el DNI {dni_2}: ")
print(diferenciaAB(conjunto_1, conjunto_2))

# (B-A): Función que devuelve los elementos que están en conjunto_2 pero no en conjunto_1 (B-A)
def diferenciaBA(conjunto_2, conjunto_1):
    diferenciaBA_resultado = []

    for digito in conjunto_2:                       # Recorremos cada elemento del primer conjunto
        if digito not in conjunto_1:                # Si ese dígito no está en el segundo conjunto
            diferenciaBA_resultado.append(digito)   # lo agregamos a la lista de resultado

    return diferenciaBA_resultado                   # Devolvemos el resultado de B-A

#Imprimir la diferencia (B-A)
print(f"\nDígitos que están en el DNI {dni_2} pero no en el DNI {dni_1}:")
print(diferenciaBA(conjunto_2, conjunto_1))

# DIFERENCIA SIMETRICA:  Definimos la función que define aquellos elementos que forman parte del conjunto A o del conjunto B, pero no se ambos. 

def diferencia_simetrica(conjunto_1, conjunto_2):
    diferencia_AB = diferenciaAB(conjunto_1, conjunto_2)
    diferencia_BA = diferenciaBA(conjunto_2, conjunto_1)
    return diferencia_AB + diferencia_BA

# Imprimir la diferencia simétrica
print("\nDígitos que están en uno de los dos DNI, pero no en ambos:")
print(diferencia_simetrica(conjunto_1, conjunto_2))

#  CONTEO DE FRECUENCIA de cada dígito en cada DNI utilizando estructuras repetitivas. Revisar.
def contar_frecuencia_digitos(dni):
    frecuencias = {}  # Creamos un diccionario vacío para guardar el dígito y cuántas veces aparece. En pares definidos. 

    for caracter in str(dni):                           # Recorremos el DNI como cadena, carácter por carácter
        digito = int(caracter)                          # Convertimos el carácter en número
        if digito in frecuencias:
            frecuencias[digito] += 1                    # Si ya existe el dígito en el diccionario, sumamos 1
        else:
            frecuencias[digito] = 1                     # Si no existe, lo agregamos y ponemos 1

    return frecuencias  # Devolvemos el diccionario con los conteos

# Imprimir por pantalla la frecuencia de los digitos en cada DNI
print("\n= Frecuencia de dígitos en cada DNI =")
# Recorremos la lista de DNIs (ya creada con dni_1 y dni_2) usando un bucle for
for i in range(len(dnis)):
    dni_actual = dnis[i]  # Obtenemos el DNI en la posición i (puede ser dni_1 o dni_2)
    frecuencia = contar_frecuencia_digitos(dni_actual)              # Aplicamos la función
    print(f"DNI {i + 1} ({dni_actual}): {frecuencia}")              # Mostramos el resultado

#  SUMA TOTAL de los digitos de cada DNI. Suma los dígitos de todos los DNIs de una lista y devuelve una lista de resultados
def sumar_digitos_lista(dnis):
    resultados_suma = []                                # Lista vacía para guardar la suma de cada DNI
    for dni in dnis:                                    # Recorremos cada DNI que está dentro de la lista 'dnis'
        suma = 0                                        # Inicializamos el contador para el dni en 0
        for caracter in str(dni):                       # Convertimos el DNI en string y recorremos cada uno de sus caracteres (que son los dígitos)
            suma += int(caracter)                       # Convertimos el carácter a número y lo sumamos a la variable 'suma'
        resultados_suma.append(suma)                    # Guardamos la suma en la lista
    return resultados_suma                              # # Devolvemos la lista completa con las sumas de los dígitos de cada DNI

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

if len(interseccion(conjunto_1, conjunto_2)) > 0:
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

# Guardamos los años en una lista
anios_nacimiento = [anio_1, anio_2]

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

# Pasamos a verificar si todos nacieron después del año 2000:
if anio_1 > 2000 and anio_2 > 2000:         
    print("\nTodos los integrantes nacieron después del 2000.")
    print("Grupo Z")
else:
    print("\nNo todos los integrantes nacieron después del 2000.")

# Si alguno nació en año bisiesto, mostrar "Tenemos un año especial".
def bisiesto(anio):
    return anio % 4 == 0 and (anio % 100 != 0 or anio % 400 == 0) 

if bisiesto(anio_1) or bisiesto(anio_2):
    print("\nTenemos un año especial: al menos un integrante nació en un año bisiesto.")
else:
    print("\nNinguno de los integrantes nació en un año bisiesto.")


# Calcular el producto cartesiano entre el conjunto de años y el conjunto de edades actuales.

from datetime import datetime                  #Para identificar el año actual hemos importado la función "datetime" de la libreria "datetime"

anio_actual = datetime.now().year               # Consiguiendo de esta manera el año actual

# Tenemos que calcular las edades actuales
edades_actuales = []
for anio in anios_nacimiento:
    edad = anio_actual - anio
    edades_actuales.append(edad)

# Pasamos a culcular el producto cartesiano
producto_cartesiano = []
for anio in anios_nacimiento:
    for edad in edades_actuales:
        producto_cartesiano.append((anio, edad))

# Mostramos el resultado
print("\n Producto cartesiano entre años de nacimiento y edades actuales: ")
for pares in producto_cartesiano:
    print(pares)
