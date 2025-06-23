# ----------------- MÓDULO: Funciones -----------------
# 1. Se crea una función extraer el nombre de cada contacto
# Se usa para ordenar la agenda por nombre alfabéticamente
def obtener_nombre(contacto):
    return contacto["nombre"]

# 2. Búsqueda lineal
def busqueda_lineal(agenda, nombre):
    #Recorre con ciclo for. 
    #Por cada contacto en la agenda, si coincide con el nombre pasado por parametro retorna el contacto
    for contacto in agenda:
        #Para simplicar la prueba utilizamos lower para que no distinga entre mayusculas o minusculas
        if contacto["nombre"].lower() == nombre.lower(): 
            return contacto
    return None

# 3. Búsqueda binaria con lista ya ordenada
def busqueda_binaria(agenda, nombre):
    #Declaro "Las puntas del indice como izquierda y derecha"
    izquierda = 0
    derecha = len(agenda) - 1
    
    #Mientras izquierda sea menor que derecha
    while izquierda <= derecha:
        #calcula el medio y guarda en variale medio
        medio = (izquierda + derecha) // 2
        #variable nombre_medio es igual a la agenda [con el medio calculado arriba] y el nombre que este en ese indice
        nombre_medio = agenda[medio]["nombre"]

        #Si el nombre en ese indice medio es igual al nombre traido como parametro devuelve el mismo valor.
        if nombre_medio.lower() == nombre.lower():
            return agenda[medio]
        #Si el nombre como parametro es menor al nombre medio. Derecha va a ser igual a medio - 1
        elif nombre.lower() < nombre_medio.lower():
            derecha = medio - 1
            #Sino izquierda va a ser igual a medio + 1
        else:
            izquierda = medio + 1
            #En resumen divide la lista en 2 y busca en cada mitad por separado comparando con un elemento central.
            #Si en menor se descarta la mitad izquierda y si es mayor la mitad derecha.

    return None
