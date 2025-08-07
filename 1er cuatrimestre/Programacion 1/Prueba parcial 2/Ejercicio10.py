def s(v, t):
    c = 1
    if t == 1:
        c = v[3] + c
    else:
        c = s(v, t - 2)
    return c

 

# Programa principal
print("Ingresar valor:")
N = int(input())
v = [0] * N  # Inicializa el vector con N elementos

for i in range(2, N):
    v[i] = i * 4
r = s(v, N)

print(r)