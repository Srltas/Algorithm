import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static long[] arr, tree, lazy;

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
            lazy = new long[4 * N];
            init(1, N, 1);

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < M + K; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                if (a == 1) {
                    int b = Integer.parseInt(st.nextToken());
                    int c = Integer.parseInt(st.nextToken());
                    long d = Long.parseLong(st.nextToken());
                    update(1, N, 1, d, b, c);
                } else if (a == 2) {
                    int b = Integer.parseInt(st.nextToken());
                    int c = Integer.parseInt(st.nextToken());
                    sb.append(sum(1, N, 1, b, c)).append('\n');
                }
            }
            System.out.println(sb);
        }
    }

    static void propagate(int start, int end, int node) {
        if (lazy[node] != 0) {
            tree[node] += (end - start + 1) * lazy[node];
            if (start != end) {
                lazy[node * 2] += lazy[node];
                lazy[node * 2 + 1] += lazy[node];
            }
            lazy[node] = 0;
        }
    }

    static long sum(int start, int end, int node, int left, int right) {
        propagate(start, end, node);

        if (left > end || right < start) {
            return 0L;
        }

        if (left <= start && right >= end ) {
            return tree[node];
        }

        int mid = (start + end) / 2;
        return sum(start, mid, node * 2, left, right) + sum(mid + 1, end, node * 2 + 1, left, right);
    }

    static void update(int start, int end, int node, long value, int left, int right) {
        propagate(start, end, node);

        if (left > end || right < start) {
            return;
        }

        if (left <= start && right >= end) {
            tree[node] += (end - start + 1) * value;
            if (start != end) {
                lazy[node * 2] += value;
                lazy[node * 2 + 1] += value;
            }
            return;
        }

        int mid = (start + end) / 2;
        update(start, mid, node * 2, value, left, right);
        update(mid + 1, end, node * 2 + 1, value, left, right);
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    static long init(int start, int end, int node) {
        if (start == end) {
            return tree[node] = arr[start];
        }

        int mid = (start + end) / 2;
        return tree[node] =
            init(start, mid, node * 2)
                + init(mid + 1, end, node * 2 + 1);
    }
}
