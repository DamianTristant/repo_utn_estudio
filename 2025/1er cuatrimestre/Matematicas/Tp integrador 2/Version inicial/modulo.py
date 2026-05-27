######## Funciones ########

########PARTE A: Operaciones con DNIs. Obtener conjuntos de digitos únicos#######################

# Definimos la función que vamos a utilizar para obtener los digitos únicos
def obtener_digitos_unicos(dni):            #Aquí el parámetro que va a recibir nuestra función para operar es el dni.
    digitos = []                            #Creamos una lista vacia para guardar los digitos únicos que vamos encontrando.
    for caracter in str(dni):               # Recorremos el DNI como string.
        digito = int(caracter)              # Defimos la variable digito y Convertimos cada carácter a número y 
        if digito not in digitos:           # Si no está en la lista digitos, lo agregamos. Esto evita los digitos repetidos. 
            digitos.append(digito)
    return digitos                          # Devolvemos la lista de dígitos únicos

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

#  SUMA TOTAL de los digitos de cada DNI. Suma los dígitos de todos los DNIs de una lista y devuelve una lista de resultados
def sumar_digitos_lista(dnis):
    resultados_suma = []                                # Lista vacía para guardar la suma de cada DNI
    for dni in dnis:                                    # Recorremos cada DNI que está dentro de la lista 'dnis'
        suma = 0                                        # Inicializamos el contador para el dni en 0
        for caracter in str(dni):                       # Convertimos el DNI en string y recorremos cada uno de sus caracteres (que son los dígitos)
            suma += int(caracter)                       # Convertimos el carácter a número y lo sumamos a la variable 'suma'
        resultados_suma.append(suma)                    # Guardamos la suma en la lista
    return resultados_suma                              # # Devolvemos la lista completa con las sumas de los dígitos de cada DNI

