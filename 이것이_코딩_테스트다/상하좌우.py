n = int(input())
plans = input().split()

x,y = 1,1

for p in plans:
    if p == "L" and y - 1 > 0:
        y -= 1
    elif p == "R" and y + 1 < n + 1:
        y += 1
    elif p == "U" and x - 1 > 0:
        x -= 1
    elif p == "D" and x + 1 < n + 1:
        x += 1

print(x, y)


x, y = 1, 1
# L, R, U, D에 따른 이동 방향
dx = [0, 0, -1, 1]
dy = [-1, 1, 0, 0]
move_types = ['L', 'R', 'U', 'D']

for plan in plans:
    for i in range(len(move_types)):
        if plan == move_types[i]:
            nx = x + dx[i]
            ny = y + dy[i]
    if nx < 1 or ny < 1 or nx > n or ny > n:
        continue
    x, y = nx, ny
print(nx, ny)
