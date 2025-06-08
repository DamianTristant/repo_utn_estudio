'''
#Ejercicio 1 - 2 - 3

#Creo el diccionario con los datos
precios_frutas = {'Banana': 1200, 'Ananá': 2500, 'Melón': 3000, 'Uva': 
1450}

#Agrego los items requeridos
precios_frutas["Naranja"] = 1200
precios_frutas["Manzana"] = 1500
precios_frutas["Pera"] = 2300

print("Lista con los items agregados:")
print(precios_frutas)

#Modificacion de valores a items
precios_frutas['Banana'] = 1330
precios_frutas['Manzana'] = 1700
precios_frutas['Melón'] = 2800

print("------------------------------------------")
print("Lista con los precios modificados:")
print(precios_frutas)

#Lista de frutas sin los precios
frutas_solas = precios_frutas.keys()
frutas_solas = list(frutas_solas)
print("------------------------------------------")
print(frutas_solas)

'''

'''
#Ejercicio 4

class Persona :
    def __init__(self, nombre, pais, edad):
        self.nombre = nombre
        self.pais = pais
        self.edad = edad

    def saludar(self):
        print(f"Hola! Soy {self.nombre}, vivo en el pais {self.pais} y tengo {self.edad} años")

#Creo un objeto persona y llamo al metodo saludar
persona_creada = Persona("Damian","Argentina","39")
persona_creada.saludar()

'''

'''
#Ejercicio 5

from math import pi

class Circulo :
    def __init__(self, radio):
        self.radio = radio

    def calcular_area(self):
        area_circulo = round((self.radio * self.radio) * pi)
        return area_circulo
    
    def calcular_perimetro(self):
        perimetro_circulo = round((self.radio * 2) * pi)
        return perimetro_circulo

circulo = Circulo(5)
area = circulo.calcular_area()
perimetro = circulo.calcular_perimetro()
print(f"Segun el radio entregado igual a: {circulo.radio} , el perimetro es: {perimetro} y el area: {area}")

'''

'''
#Ejercicio 6

def balance (expresion):
    pila = [] #Creo una pila vacia
    pares = {')': '(','}': '{', ']': '['} #Creo un diccionario con cada clave - valor

    for caracter in expresion:
        if caracter in "({[":
            pila.append(caracter)
        elif caracter in "}])":
            if not pila or pila.pop() != pares[caracter]:
                return False

    return len(pila) == 0

#Imprimo la salida para ver el resultado
print(balance("({[]})"))  # True
print(balance("({[})"))   # Fallucas

'''

'''
#Ejercicio 7

from collections import deque

class FilaBanco:
    def __init__(self):
        self.fila = deque()

    def encolar(self,cliente):
        self.fila.append(cliente)

    def fila_vacia(self):
        return len(self.fila) == 0
    
    def desencolar(self):
        return self.fila.popleft() if not self.fila_vacia() else "La fila esta vacia"
    
    def proximo_cliente(self):
        return self.fila[0] if not self.fila_vacia() else "La cola esta vacia"
    
banco = FilaBanco()
banco.encolar("1er cliente")
banco.encolar("2do cliente")
print(banco.proximo_cliente())
print(banco.desencolar())
print(banco.desencolar())
print(banco.desencolar())

'''

'''
#Ejercicio 8 y 9

class Nodo:
    def __init__(self, dato):
        self.dato = dato
        self.siguiente = None

class ListaEnlazada:
    def __init__(self):
        self.cabeza = None

    def insertar(self, dato):
        nuevo_nodo = Nodo(dato)
        nuevo_nodo.siguiente = self.cabeza
        self.cabeza = nuevo_nodo

    def mostrar(self):
        actual = self.cabeza
        while actual:
            print(actual.dato, end=" -> ")
            actual = actual.siguiente
        print("None")

    def invertir(self):
        prev = None
        actual = self.cabeza
        while actual:
            siguiente = actual.siguiente
            actual.siguiente = prev
            prev = actual
            actual = siguiente
        self.cabeza = prev    

lista = ListaEnlazada()
lista.insertar(3)
lista.insertar(2)
lista.insertar(1)
print("Lista original:")
lista.mostrar()
lista.invertir()
print("Lista invertida:")
lista.mostrar()

'''
