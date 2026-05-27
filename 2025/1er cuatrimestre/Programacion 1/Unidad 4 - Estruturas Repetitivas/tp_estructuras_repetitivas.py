'''Ejercicio 1

#Imprimo los numeros del 0 a 100 por pantalla
for i in range(101):
    print(i)

'''

'''Ejercicio 2

#Pido un numero al usuario
numero = int(input("Ingrese un numero entero: "))
#Convierto,cuento e imprimo numero ingresado
cant_digitos = len(str(abs(numero)))
print(f"El numero {numero} tiene {cant_digitos} digitos")

'''

'''Ejercicio 3

#Ingreso los numeros para sumar
num1 = int(input("Ingrese el primero numero: "))
num2 = int(input("Ingrese el segundo numero: "))

inicio = num1 + 1
suma = 0

#Realizo la suma e imprimo por pantalla
for i in range(inicio,num2):
    suma += i

print(f"La sumatoria de todos los numeros comprendidos entre {num1} y {num2} sin incluirlos da: {suma}")

'''

'''Ejercicio 4

#Le pido al usuario que ingrese numero y se van sumando hasta que ingrese un 0
total = 0
cont = 0
salida = False

while salida == False:
    numeros = int(input("Ingrese numeros para ir sumando, para finalizar ingrese '0' : "))
    if numeros < 0:
        print("A ingresado un numero negativo, ingrese solo numeros positivos")
    elif numeros > 0:
        total += numeros
        cont += 1
    else:
        numeros == 0
        salida = True

print(f"A ingresado un total de {cont} numeros para sumar y su total es: {total}")        

'''

'''Ejercicio 5

#Creo un numero aleatorio entre 0 y 9
import random
num_aleatorio = random.randint(0, 9)

cont = 1
acerto = False

#Pido al usuario y hago la comparacion
while acerto == False :
    num_usuario = int(input("Elija un numero entre el 0 y 9 : "))
    if num_usuario == num_aleatorio:
        print("Ah acertado!")
        acerto = True
    else:
        print("Estuvo cerca, intentelo nuevamente!")
        cont+=1
print(f"A acertado el intento numero: {cont}")

'''

'''Ejercicio 6

for i in range(100,-1,-2):
    print(i)

'''

'''Ejercicio 7

#Le pido al usuario un numero hasta donde desea sumar desde 0
num_usu = int(input("Ingrese el numero hasta el cual desea sumar: "))
suma = 0
for i in range(num_usu + 1):
    suma +=i
print(suma)

'''

'''Ejercicio 8

# Cantidad maxima modificable
cantidad_numeros = 10

# Contadores
pares = 0
impares = 0
positivos = 0
negativos = 0

# Pido al usuario que ingrese números
print(f"Ingresa {cantidad_numeros} números enteros:")

for i in range(cantidad_numeros):
    numero = int(input(f"Ingresa el número {i + 1}: "))

    # Verifico si es positivo o negativo
    if numero > 0:
        positivos += 1
    elif numero < 0:
        negativos += 1

    # Verifico si es par o impar
    if numero % 2 == 0:
        pares += 1
    else:
        impares += 1

# Resultados
print(f"\nResultados:")
print(f"Cantidad de números pares: {pares}")
print(f"Cantidad de números impares: {impares}")
print(f"Cantidad de números positivos: {positivos}")
print(f"Cantidad de números negativos: {negativos}")

'''


'''Ejercicio 9

# Cantidad maxima modificable
cantidad_numeros = 10
suma = 0

# Pedo al usuario que ingrese números
print(f"Ingresa {cantidad_numeros} números enteros: ")

for i in range(cantidad_numeros):
    numero = int(input(f"Ingresa el número {i + 1}: "))
    suma += numero

# Calculo la media
media = suma / cantidad_numeros
print(f"La media de los números ingresados es: {media}")

'''



'''Ejercicio 10

#Variable para almacenar el numero invertido
numero_invertido = 0

num_normal = int(input("Ingrese un numero: "))

while num_normal > 0 :
    digito = num_normal % 10  # Obtengo el último dígito
    numero_invertido = numero_invertido * 10 + digito  # Agrego el dígito al número invertido
    num_normal //= 10  # Elimino el último dígito     

# Muestro el número invertido
print(f"El número invertido es: {numero_invertido}")

'''
