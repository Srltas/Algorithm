count = int(input())

result = []
for i in range(count):
    num1, num2 = map(int, input().split())
    result.append(num1 + num2)

for i in range(len(result)):
    print(result[i])