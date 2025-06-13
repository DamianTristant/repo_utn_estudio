# Definir variables
d1 = int(input("Introduce el valor N°1 (día): "))
d2 = int(input("Introduce el valor N°2 (mes): "))
d3 = int(input("Introduce el valor N°3 (año): "))

dd = 100  # valor inicial inválido


# Determinar los días del mes
if d2 in [1, 3, 5, 7, 8, 10, 12]:
    dd = 31
elif d2 in [4, 6, 9, 11]:
    dd = 30
elif d2 == 2:
    if (d3 % 4 == 0 and not (d3 % 100 == 0)) or (d3 % 400 == 0):
        dd = 29
    else:
        dd = 28
else:
    print("A")  

 
if d1 < 0 or d1 > dd:
    print("B")  
else:
    if d2 < 0 or d2 > 12:
        print("C")  
    else:
        print("D")  