from collections import Counter
import sys

n = int(sys.stdin.readline())

l = []
for _ in range(n):
    l.append(int(sys.stdin.readline()))

l.sort()

print(round(sum(l)/n))
print(l[(n//2)])

cnt = Counter(l).most_common(2)
if len(l) > 1:
    if cnt[0][1] == cnt[1][1]:
        print(cnt[1][0])
    else:
        print(cnt[0][0])
else:
    print(cnt[0][0])

print(max(l) - min(l))