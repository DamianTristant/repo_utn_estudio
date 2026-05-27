def c(f):
    r = f
    l = len(f)
    for i in range(0, l, 5):
        r = f[i:l]
    return r

 

# Programa principal
f = "HolaMundoCruel"

print(c(f))