def b(div_count):
    if div_count <= 2:
        return True
    else:
        return False

def a(num):
    r = False
    div_count = 0
    for c in range(1,num+1):
        if num % c == 0:
            div_count += 1
            r = b(div_count)
    return r

#Programa principal
for num in range(1,11):
    if a(num):
        print(num,end="")