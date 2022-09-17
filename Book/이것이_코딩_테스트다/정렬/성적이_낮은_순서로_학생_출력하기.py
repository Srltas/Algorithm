n = int(input())

l = []
for i in range(n):
    input_data = input().split()
    l.append((input_data[0], int(input_data[1])))

l.sort(key=lambda x : x[1])

for i in l:
    print(i[0], end=' ')