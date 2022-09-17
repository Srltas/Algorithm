n = int(input())
l = list(map(int, input().split()))


primary_list = []
primary_check = [False, False] + [True] * 999

for i in range(2, 1001):
    if primary_check[i]:
        primary_list.append(i)
        for j in range(2*i, 1001, i):
            primary_check[j] = False

count = 0
for i in l:
    if primary_check[i]:
        count += 1
print(count)


