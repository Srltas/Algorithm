"""
URL : https://www.acmicpc.net/problem/11051

문제
자연수 N과 정수 K가 주어졌을 때 이항 계수 
binom{N}{K}를 10,007로 나눈 나머지를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 N)과 K가 주어진다. (1 ≤ N ≤ 1,000, 0 ≤ K ≤ N)

출력
 
binom{N}{K}를 10,007로 나눈 나머지를 출력한다.

예제 입력 1 
5 2
예제 출력 1 
10
"""

n, k = map(int, input().split())

if k == 0 or k == n:
    print(1)
else:
    d = [[0] * (n+1) for _ in range(k+1)]

    for i in range(1, n+1):
        d[1][i] = i

    for i in range(2, k+1):
        for j in range(2, n+1):
            d[i][j] = (d[i-1][j-1] + d[i][j-1]) % 10007

    print(d[k][n])
