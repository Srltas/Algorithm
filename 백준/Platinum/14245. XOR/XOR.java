import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] arr, lazy;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(br.readLine());
            arr = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            lazy = new int[4 * N];

            int M = Integer.parseInt(br.readLine());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int t = Integer.parseInt(st.nextToken());
                if (t == 1) {
                    int a = Integer.parseInt(st.nextToken());
                    int b = Integer.parseInt(st.nextToken());
                    int c = Integer.parseInt(st.nextToken());
                    update(0, N - 1, 1, a, b, c);
                } else if (t == 2) {
                    int a = Integer.parseInt(st.nextToken());
                    sb.append(point(0, N - 1, 1, a, 0)).append('\n');
                }
            }
            System.out.println(sb);
        }
    }

    static int point(int start, int end, int node, int index, int acc) {
        acc ^= lazy[node];
        if (start == end) {
            return arr[index] ^ acc;
        }
        int mid = (start + end) >>> 1;
        if (index <= mid) return point(start, mid, node << 1, index, acc);
        else return point(mid + 1, end, (node << 1) + 1, index, acc);
    }

    static void update(int start, int end, int node, int left, int right, int value) {
        if (start > right || end < left) {
            return;
        }

        if (start >= left && end <= right) {
            lazy[node] ^= value;
            return;
        }

        int mid = (start + end) >>> 1;
        update(start, mid, node << 1, left, right, value);
        update(mid + 1, end, (node << 1) + 1, left, right, value);
    }
}
