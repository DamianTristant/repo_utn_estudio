# Programa principal
print("Introduce el valor N°1:")
d1 = int(input())
print("Introduce el valor N°2:")
d2 = int(input())
print("Introduce el valor N°3:")
d3 = int(input())

dd = 0

# Determinación de la cantidad de días según el mes
if d2 in [1, 3, 5, 7, 8, 10, 12]:
    dd = 31
elif d2 in [4, 6, 9, 11]:
    dd = 30
elif d2 == 2:
    if (d3 % 4 == 0 and d3 % 100 != 0) or (d3 % 400 == 0):
        dd = 29
    else:
        dd = 28
else:
    print("A")

# Validaciones de día y mes
if (d1 < 0) or (d1 > dd):
    print("B")
else:
    if (d2 < 0) or (d2 > dd):
        print("C")
    else:
        print("D")