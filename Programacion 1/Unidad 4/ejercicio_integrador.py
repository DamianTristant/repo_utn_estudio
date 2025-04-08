numero = int(input("Ingrese un numero entero positivo: "))

if numero > 0 :
    if numero % 2 != 0 :
        numero -= 1
    cont = numero
    while cont >= 0 :
        print(str(cont) + " ", end="")
        cont -= 2
else:
    print("El numero debia ser positivo")