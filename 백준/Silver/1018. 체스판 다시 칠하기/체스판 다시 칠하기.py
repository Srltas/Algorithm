n, m = map(int, input().split())

board = []
for i in range(n):
    board.append(input())

result = []
for i in range(n-7):
    for j in range(m-7):
        first_w = 0
        first_b = 0
        for k in range(i, i+8):
            for o in range(j, j+8):
                if (k + o) % 2 == 0:
                    if board[k][o] != 'W':
                        first_w += 1
                    if board[k][o] != 'B':
                        first_b += 1
                else:
                    if board[k][o] != 'B':
                        first_w += 1
                    if board[k][o] != 'W':
                        first_b += 1
        result.append(min(first_w, first_b))
print(min(result))