"""
문제
크기가 N인 수열 A = A1, A2, ..., AN이 있다. 수열의 각 원소 Ai에 대해서 오큰수 NGE(i)를 구하려고 한다. Ai의 오큰수는 오른쪽에 있으면서 Ai보다 큰 수 중에서 가장 왼쪽에 있는 수를 의미한다. 그러한 수가 없는 경우에 오큰수는 -1이다.

예를 들어, A = [3, 5, 2, 7]인 경우 NGE(1) = 5, NGE(2) = 7, NGE(3) = 7, NGE(4) = -1이다. A = [9, 5, 4, 8]인 경우에는 NGE(1) = -1, NGE(2) = 8, NGE(3) = 8, NGE(4) = -1이다.

입력
첫째 줄에 수열 A의 크기 N (1 ≤ N ≤ 1,000,000)이 주어진다. 둘째 줄에 수열 A의 원소 A1, A2, ..., AN (1 ≤ Ai ≤ 1,000,000)이 주어진다.

출력
총 N개의 수 NGE(1), NGE(2), ..., NGE(N)을 공백으로 구분해 출력한다.

예제 입력 1 
4
3 5 2 7
예제 출력 1 
5 7 7 -1
예제 입력 2 
4
9 5 4 8
예제 출력 2 
-1 8 8 -1
"""

"""
n = int(input())
array = list(map(int, input().split()))

for _ in range(len(array)):
    first_n = array.pop(0)
    for i in array:
        if first_n < i:
            print(i, end=' ')
            break
    else:
        print(-1, end=' ')
시간 초과... 시간 복잡도가 O(n^2) 
"""

n = int(input())
array = list(map(int, input().split()))
answer = [-1] * n
stack = []

stack.append(0)
for i in range(1, n):
    while stack and array[stack[-1]] < array[i]:
        answer[stack.pop()] = array[i]
    stack.append(i)

print(*answer)

# 고민 끝에 다른 사람의 풀이를 참고하여 해결
# 인덱스를 stack에 넣는 것이 문제 해결에 핵심