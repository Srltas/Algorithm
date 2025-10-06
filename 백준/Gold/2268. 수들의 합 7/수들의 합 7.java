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
            int M = Integer.parseInt(st.nextToken());

            arr = new long[N + 1];

            tree = new long[4 * N];

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if (a == 0) {
                    int c = Integer.parseInt(st.nextToken());
                    if (b > c) {
                        int tmp = b;
                        b = c;
                        c = tmp;
                    }
                    sb.append(sum(1 ,N, 1, b, c)).append('\n');
                } else if (a == 1) {
                    long c = Long.parseLong(st.nextToken());
                    long diff = c - arr[b];
                    arr[b] = c;
                    update(1, N, 1, b, diff);
                }
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
}
