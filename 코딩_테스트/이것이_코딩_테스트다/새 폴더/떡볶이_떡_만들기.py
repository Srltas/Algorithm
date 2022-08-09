
def binary_search(array, target, start, end):
    while start <= end:
        mid = (start + end) // 2

        rest = 0
        for i in array:
            if i - mid > 0:
                rest += (i - mid)
            else:
                rest += 0
        
        if rest == target:
            return mid
        elif rest > target:
            start = mid + 1
        else:
            end = mid - 1
    return None


n, m = map(int, input().split())
array = list(map(int, input().split()))

print(binary_search(array, m, 0, max(array)))


# 답안 예시

n, m = list(map(int, input().split(' ')))
array = list(map(int, input().split()))

start = 0
end = max(array)

result = 0
while start <= end:
    total = 0
    mid = (start + end) // 2
    for x in array:
        if x > mid:
            total += x - mid
    if total < m:
        end = mid - 1
    else:
        result = mid
        start = mid + 1
        
print(result)