import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static long[] arr, lazy;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(br.readLine());
            arr = new long[N + 1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            lazy = new long[4 * N];

            int M = Integer.parseInt(br.readLine());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                if (a == 1) {
                    int b = Integer.parseInt(st.nextToken());
                    int c = Integer.parseInt(st.nextToken());
                    int k = Integer.parseInt(st.nextToken());
                    update(1, N, 1, b, c, k);
                } else if (a == 2) {
                    int b = Integer.parseInt(st.nextToken());
                    sb.append(point(1, N, 1, b, 0L)).append('\n');
                }
            }
            System.out.println(sb);
        }
    }

    static long point(int start, int end, int node, int index, long acc) {
        acc += lazy[node];
        if (start == end) {
            return arr[index] + acc;
        }
        int mid = (start + end) / 2;
        if (index <= mid) return point(start, mid, node * 2, index, acc);
        else return point(mid + 1, end, node * 2 + 1, index, acc);
    }

    static void update(int start, int end, int node, int left, int right, long value) {
        if (start > right || end < left) return;

        if (start >= left && end <= right) {
            lazy[node] += value;
            return;
        }
        int mid = (start + end) / 2;
        update(start, mid, node * 2, left, right, value);
        update(mid + 1, end, node * 2 + 1, left, right, value);
    }
}
