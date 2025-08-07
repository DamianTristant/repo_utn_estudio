#Se crea la "Agenda" y se cargan los contactos
agenda = [
    {"nombre": "Luis", "telefono": "123", "email": "luis@mail.com"},
    {"nombre": "Ana", "telefono": "456", "email": "ana@mail.com"},
    {"nombre": "Carlos", "telefono": "789", "email": "carlos@mail.com"},
    {"nombre": "Bruno", "telefono": "321", "email": "bruno@mail.com"},
    {"nombre": "Sofía", "telefono": "654", "email": "sofia@mail.com"},
    {"nombre": "Valentina", "telefono": "987", "email": "valentina@mail.com"},
    {"nombre": "Diego", "telefono": "159", "email": "diego@mail.com"},
    {"nombre": "Martina", "telefono": "753", "email": "martina@mail.com"},
    {"nombre": "Pedro", "telefono": "852", "email": "pedro@mail.com"},
    {"nombre": "Elena", "telefono": "951", "email": "elena@mail.com"}
]

# Se crea una función extraer el nombre de cada contacto
# Se usa para ordenar la agenda por nombre alfabeticamente
def obtener_nombre(contacto):
    return contacto["nombre"]

# Búsqueda lineal
def busqueda_lineal(agenda, nombre):
    #Recorre con ciclo for. 
    #Por cada contacto en la agenda, si coincide con el nombre pasado por parametro retorna el contacto
    for contacto in agenda:
        #Para simplicar la prueba utilizamos lower para que no distinga entre mayusculas o minusculas
        if contacto["nombre"].lower() == nombre.lower(): 
            return contacto
    return None

# Ordena la lista por nombre usando "sort" y la funcion obtener_nombre
agenda.sort(key=obtener_nombre)

#Imprimo la agenda completa ordenada alfabeticamente
print("Agenda ordenada:\n")
for contacto in agenda:
    print(f"Nombre: {contacto['nombre']}, Teléfono: {contacto['telefono']}, Email: {contacto['email']}")

# Búsqueda binaria con lista ya ordenada
def buscar_binaria(agenda, nombre):

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

#Medicion tiempo busqueda lineal
import time
inicio_lineal = time.time()
resultado_lineal = busqueda_lineal(agenda,"Carlos")
fin_lineal = time.time()
tiempo_lineal = fin_lineal - inicio_lineal

#Medicion tiempo busqueda binaria
inicio_binaria = time.time()
resultado_binaria = buscar_binaria(agenda,"Carlos")
fin_binaria = time.time()
tiempo_binaria = fin_binaria - inicio_binaria

# Prueba búsqueda lineal y resultado por pantalla
resultado_lineal = busqueda_lineal(agenda, "Carlos")
print("Resultado búsqueda lineal:")
print(f"Nombre: {resultado_lineal['nombre']}, Teléfono: {resultado_lineal['telefono']}, Email: {resultado_lineal['email']}")
print(f"Tiempo de busqueda: {tiempo_lineal:.6f} segundos")

print("")
# Prueba búsqueda binaria y resultado por pantalla
resultado_binaria = buscar_binaria(agenda, "Carlos")
print("Resultado búsqueda binaria:")
print(f"Nombre: {resultado_binaria['nombre']}, Teléfono: {resultado_binaria['telefono']}, Email: {resultado_binaria['email']}")
print(f"Tiempo de busqueda: {tiempo_binaria:.6f} segundos")