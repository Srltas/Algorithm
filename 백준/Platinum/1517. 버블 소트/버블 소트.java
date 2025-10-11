import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] compressArr;
    static long[] tree;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(br.readLine());
            int[] arr = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            Map<Integer, Integer> map = new HashMap<>();
            int[] sortedArr = Arrays.stream(arr).distinct().sorted().toArray();
            for (int i = 0; i < sortedArr.length; i++) {
                map.put(sortedArr[i], i);
            }

            compressArr = new int[N];
            for (int i = 0; i < N; i++) {
                compressArr[i] = map.get(arr[i]);
            }

            tree = new long[4 * N];

            long totalSwap = 0;
            for (int val : compressArr) {
                totalSwap += query(0, N - 1, 1, val + 1, N - 1);
                update(0, N - 1, 1, val, 1);
            }
            System.out.println(totalSwap);
        }
    }

    static long query(int start, int end, int node, int left, int right) {
        if (start > right || end < left) return 0L;
        if (start >= left && end <= right) return tree[node];
        int mid = (start + end) / 2;
        return query(start, mid, node * 2, left, right) + query(mid + 1, end, node * 2 + 1, left, right);
    }

    static void update(int start, int end, int node, int index, int value) {
        if (index < start || index > end) return;
        tree[node] += value;
        if (start == end) return;
        int mid = (start + end) / 2;
        if (index <= mid) update(start, mid, node * 2, index, value);
        else update(mid + 1, end, node * 2 + 1, index, value);
    }
}
