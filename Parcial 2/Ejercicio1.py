def multiplicar_valor(n, m):

    r = 20

    if n % 2 == 0:

        r = n * m

    else:

        r = m * n

    return r

 

def sumar_valor(x_ref, t_ref, z_ref):

    x = x_ref[0]

    x = multiplicar_valor(x, 2)

   

    t = multiplicar_valor(x, multiplicar_valor(x, 3))

    z = multiplicar_valor(x, t)

   

    # Actualizar los valores referenciados

    x_ref[0] = x

    t_ref[0] = t

    z_ref[0] = z

 

# Programa principal (equivalente a "Algoritmo Ejercicio")

a = [3]

b = [7]

c = [4]

 

sumar_valor(a, b, c)

 

print(a[0], ",", b[0], ",", c[0])