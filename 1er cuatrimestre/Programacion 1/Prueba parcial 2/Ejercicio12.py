def b(d):
    if d <= 2:
        return True
    else:
        return False



def a(num):
    r = False
    d = 0
    for c in range(1, num + 1):
        if num % c == 0:
            d += 1
            r = b(d)
    return r



# Programa principal
for num in range(1, 12):
    if a(num):
        print(num, end=" ")