c = int(input())
h = []
w = []
t = []

# 동 1, 서 2, 남 3, 북 4
for _ in range(6):
    dir, length = map(int, input().split())
    if dir == 1 or dir == 2:
        w.append(length)
        t.append(length)
    else:
        h.append(length)
        t.append(length)

big_box = max(w) * max(h)

max_w_index = t.index(max(w))
max_h_index = t.index(max(h))

min_w = abs(t[max_w_index-1] - t[max_w_index - 5 if max_w_index == 5 else max_w_index + 1])
min_h = abs(t[max_h_index-1] - t[max_h_index - 5 if max_h_index == 5 else max_h_index + 1])

small_box = min_w * min_h

print(c * (big_box - small_box))