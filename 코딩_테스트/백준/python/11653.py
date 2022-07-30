n = int(input())

c = 2
while n != 0:
    if n == 1:
        break
    
    if n % c == 0:
        print(c)
        n = n // c
    else:
        c += 1
