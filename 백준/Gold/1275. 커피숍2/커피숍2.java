import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static long[] arr, tree;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int Q = Integer.parseInt(st.nextToken());

            arr = new long[N + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                arr[i] = Long.parseLong(st.nextToken());
            }

            tree = new long[4 * N];
            init(1, N ,1);

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < Q; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int a = Integer.parseInt(st.nextToken());
                long b = Long.parseLong(st.nextToken());

                if (x > y) {
                    int tmp = x;
                    x = y;
                    y = tmp;
                }
                sb.append(sum(1, N, 1, x, y)).append('\n');

                long diff = b - arr[a];
                arr[a] = b;
                update(1, N, 1, a, diff);
            }
            System.out.println(sb);
        }
    }

    static long sum(int start, int end, int node, int left, int right) {
        if (start > right || end < left) {
            return 0;
        }

        if (start >= left && end <= right) {
            return tree[node];
        }

        int mid = (start + end) / 2;
        return sum(start, mid, node * 2, left, right) + sum(mid + 1, end, node * 2 + 1, left, right);
    }

    static void update(int start, int end, int node, int index, long diff) {
        if (index < start || index > end) {
            return;
        }

        tree[node] += diff;

        if (start != end) {
            int mid = (start + end) / 2;
            update(start, mid, node * 2, index, diff);
            update(mid + 1, end, node * 2 + 1, index, diff);
        }
    }

    static long init(int start, int end, int node) {
        if (start == end) {
            return tree[node] = arr[start];
        }

        int mid = (start + end) / 2;
        return tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
    }
}
