# for문 not in을 이용한 풀이
l = []
for i in range(10):
    n = int(input())
    if n%42 not in l:
        l.append(n%42)
print(len(l))

#set을 이용한 풀이

l = []
for i in range(10):
    a = int(input())
    l.append(a % 42)
print(len(set(l)))
