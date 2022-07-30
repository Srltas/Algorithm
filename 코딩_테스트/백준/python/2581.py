m = int(input())
n = int(input())

prime = []
for i in range(m, n+1):
    if i != 1:
        check = True
        for j in range(2, i):
            if i % j == 0:
                check = False
                break
        if check:
            prime.append(i)

if len(prime) == 0:
    print(-1)
else:
    print(sum(prime))
    print(prime[0])

###########################################################

prime_check = [False, False] + [True] * (n+1)
prime_list = []

for i in range(2, n+1):
    if prime_check[i]:
        prime_list.append(i)
        for j in range(2*i, n+1, i):
            prime_check[j] = False

for i in range(len(prime_list)):
    if prime_list[i] >= m:
        prime_list = prime_list[i:]
        break


if len(prime_list):
    if max(prime_list) < m:
        print(-1)
    else:
        print(sum(prime_list))
        print(min(prime_list))
else:
    print(-1)
    