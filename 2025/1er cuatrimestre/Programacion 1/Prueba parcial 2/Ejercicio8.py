def c(vc, vs):
    m = vc[0]
    for i in range(0, vs - 2):  # (vs - 3) en PSeInt equivale a (vs - 2) en Python porque el rango es excluyente
        if vc[i] > m:
           m = vc[i]
    return m

 

# Programa principal
vs = 3
vc = [0] * vs
vc[0] = 4
vc[1] = 6
vc[2] = 1

 

print(c(vc, vs))