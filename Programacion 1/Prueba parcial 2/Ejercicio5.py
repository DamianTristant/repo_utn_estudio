ct = 1
t = 0
s = True

c = int(input("Ingrese un valor n1: "))

while s != False:
    p = int(input("Ingrese un valor n2: "))
    t = t + p
    ct = ct + 1

    while ct <= c:
        print(t, end=",")
        s = False
        if ct == c:
            s = True