import sys 
input = sys.stdin.readline
a, b, v = map(int, input().split())

temp = v - b 
diff = a - b 

ans = temp // diff 
remain = temp % diff 
if remain > 0 :
  print(ans + 1)
else:
  print(ans)