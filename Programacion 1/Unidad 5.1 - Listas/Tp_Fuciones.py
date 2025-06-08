'''
Ejercicio 1

def imprimir_hola_mundo():
    print("Hola mundo!")


imprimir_hola_mundo()

'''

'''
Ejercicio 2

def saludo_usuario(nombre):
    return f"Hola {nombre}!"

nombre = input("Indique su nombre: ")
saludo = saludo_usuario(nombre)
print(saludo)

'''

'''
Ejercicio 3

def info_personal(nombre,apellido,edad,residencia):
    return f"Soy {nombre}{apellido}, tengo {edad} años y vivo en {residencia}"

nombre = input("Indique su nombre: ")
apellido = input("Indique su apellido: ")
edad = int(input("Indique su edad: "))
residencia = input("Indique su residencia: ")

info = info_personal(nombre,apellido,edad,residencia)
print(info)

'''

'''
Ejercicio 4

import math

def calcular_area_circulo(radio):
    return math.pi * radio ** 2

def calcular_perimetro_circulo(radio):
    return math.pi * 2 * radio

radio = float(input("Ingrese el radio del círculo: "))
area = calcular_area_circulo(radio)
perimetro = calcular_perimetro_circulo(radio)

print(f"El área del círculo es: {area:.2f}")
print(f"El perímetro del círculo es: {perimetro:.2f}") #Con .2f imprimo el resultado solamente con 2 decimales

'''
'''
Ejercicio 5

def segundos_a_horas(segundos):
    return segundos / 3600

segundos = float(input("Ingrese la cantidad de segundos: "))
horas = segundos_a_horas(segundos)
print(f"{segundos} segundos equivalen a {horas:.2f} horas.")

'''

'''
Ejercicio 6

def tabla_multiplicar(numero):
    print(f"Tabla de multiplicar de {numero}:")
    for i in range(1, 11):
        print(f"{numero} x {i} = {numero * i}")

numero = int(input("Ingrese un número para mostrar su tabla de multiplicar: "))
tabla_multiplicar(numero)

'''

'''
Ejercicio 7

def operaciones_basicas(a, b):
    suma = a + b
    resta = a - b
    multiplicacion = a * b
    division = a / b
    return (suma, resta, multiplicacion, division)

a = int(input("Ingrese el primer número: "))
b = int(input("Ingrese el segundo número: "))
suma, resta, multiplicacion, division = operaciones_basicas(a, b)

print("Suma:", suma)
print("Resta:", resta)
print("Multiplicación:", multiplicacion)
print("División:", division)

'''

'''
Ejercicio 8

def calcular_imc(peso, altura):
    return peso / (altura ** 2)

peso = float(input("Ingrese su peso en kilogramos: "))
altura = float(input("Ingrese su altura en metros: "))

imc = calcular_imc(peso, altura)
print(f"Tu índice de masa corporal (IMC) es: {imc:.2f}")

'''

'''
Ejercicio 9

def celsius_a_fahrenheit(celsius):
    return (celsius * 9/5) + 32

celsius = float(input("Ingrese la temperatura en grados Celsius: "))
fahrenheit = celsius_a_fahrenheit(celsius)
print(f"{celsius}°C equivalen a {fahrenheit:.2f}°F")

'''

'''
Ejercicio 10

def calcular_promedio(a, b, c):
    return (a + b + c) / 3

num1 = float(input("Ingrese el primer número: "))
num2 = float(input("Ingrese el segundo número: "))
num3 = float(input("Ingrese el tercer número: "))

promedio = calcular_promedio(num1, num2, num3)
print(f"El promedio de {num1}, {num2} y {num3} es: {promedio:.2f}")

'''