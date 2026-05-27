def s(i_ref, j_val):
    i_ref[0] = i_ref[0] + 10  # Simula paso por referencia
    j_val = j_val + 10        # j es por valor
    print(f" i = {i_ref[0]} / j = {j_val}", end="")

 

# Programa principal
i = [2]  # Usamos una lista para simular referencia
j = 3
s(i, j)

print(f"\ni = {i[0]} / j = {j}")