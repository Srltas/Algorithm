import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] arr, tree;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(br.readLine());
            arr = new int[N + 1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            tree = new int[4 * N];
            init(1, N, 1);

            int M = Integer.parseInt(br.readLine());

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                if (a == 1) {
                    update(1, N, 1, b, c);
                } else if (a == 2) {
                    sb.append(find(1, N, 1, b, c)).append('\n');
                }
            }
            System.out.println(sb);
        }
    }

    static int find(int start, int end, int node, int left, int right) {
        if (start > right || end < left) {
            return Integer.MAX_VALUE;
        }

        if (start >= left && end <= right) {
            return tree[node];
        }

        int mid = (start + end) / 2;
        int leftChild = find(start, mid, node * 2, left, right);
        int rightChild = find(mid + 1, end, node * 2 + 1, left, right);
        return Math.min(leftChild, rightChild);
    }

    static void update(int start, int end, int node, int index, int value) {
        if (index < start || index > end) return;

        if (start == end) {
            tree[node] = value;
            return;
        }

        int mid = (start + end) / 2;
        update(start, mid, node * 2, index, value);
        update(mid + 1, end, node * 2 + 1, index, value);
        tree[node] = Math.min(tree[node * 2], tree[node * 2 + 1]);
    }

    static int init(int start, int end, int node) {
        if (start == end) {
            return tree[node] = arr[start];
        }

        int mid = (start + end) / 2;
        return tree[node] = Math.min(init(start, mid, node * 2), init(mid + 1, end, node * 2 + 1));
    }
}
