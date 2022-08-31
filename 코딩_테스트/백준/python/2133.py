"""
URL : https://www.acmicpc.net/problem/2133

문제
3×N 크기의 벽을 2×1, 1×2 크기의 타일로 채우는 경우의 수를 구해보자.

입력
첫째 줄에 N(1 ≤ N ≤ 30)이 주어진다.

출력
첫째 줄에 경우의 수를 출력한다.

예제 입력 1 
2
예제 출력 1 
3
"""

import sys

n = int(sys.stdin.readline())
d = [0] * (31)

d[2] = 3
d[4] = 11
for i in range(5, n+1):
    d[i] = d[i-2] * 3 + d[i-4] * 8

print(d[n])