import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static long[] arr;
    static long[] tree;
    static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            arr = new long[N + 1];
            for (int i = 1; i <= N; i++) {
                arr[i] = Long.parseLong(br.readLine());
            }

            tree = new long[4 * N];
            init(1, N, 1);

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < M + K; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                long c = Long.parseLong(st.nextToken());

                if (a == 1) {
                    arr[b] = c;
                    update(1, N, 1, b, c);
                } else if (a == 2) {
                    sb.append(multiply(1, N, 1, b, (int) c)).append('\n');
                }
            }
            System.out.println(sb);
        }
    }

    static long multiply(int start, int end, int node, int left, int right) {
        if (left > end || right < start) {
            return 1;
        }
        if (left <= start && right >= end) {
            return tree[node];
        }
        int mid = (start + end) / 2;
        return (multiply(start, mid, node * 2, left, right) * multiply(mid + 1, end, node * 2 + 1, left, right)) % MOD;
    }

    static long update(int start, int end, int node, int index, long value) {
        if (index < start || index > end) {
            return tree[node];
        }
        if (start == end) {
            return tree[node] = value;
        }

        int mid = (start + end) / 2;
        long leftChild = update(start, mid, node * 2, index, value);
        long rightChild = update(mid + 1, end, node * 2 + 1, index, value);
        return tree[node] = (leftChild * rightChild) % MOD;
    }

    static long init(int start, int end, int node) {
        if (start == end) {
            return tree[node] = arr[start];
        }
        int mid = (start + end) / 2;
        return tree[node] = (init(start, mid, node * 2) * init(mid + 1, end, node * 2 + 1)) % MOD;
    }
}
