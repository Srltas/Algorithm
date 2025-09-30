import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] arr, tree;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            arr = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                arr[i] = Integer.parseInt(br.readLine());
            }

            tree = new int[4 * N];
            init(1, N, 1);

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                sb.append(find(1, N, 1, a, b)).append('\n');
            }
            System.out.println(sb);
        }
    }

    static int find(int start, int end, int node, int left, int right) {
        if (left > end || right < start) {
            return Integer.MAX_VALUE;
        }
        if (left <= start && right >= end) {
            return tree[node];
        }
        int mid = (start + end) / 2;
        int leftChild = find(start, mid, node * 2, left, right);
        int rightChild = find(mid + 1, end, node * 2 + 1, left, right);
        return Math.min(leftChild, rightChild);
    }

    static int update(int start, int end, int node, int index) {
        if (index < start || index > end) {
            return tree[node];
        }

        int mid = (start + end) / 2;
        int leftChild = update(start, mid, node * 2, index);
        int rightChild = update(mid + 1, end, node * 2 + 1, index);
        return tree[node] = Math.min(leftChild, rightChild);
    }

    static int init(int start, int end, int node) {
        if (start == end) {
            return tree[node] = arr[start];
        }
        int mid = (start + end) / 2;
        int leftChild = init(start, mid, node * 2);
        int rightChild = init(mid + 1, end, node * 2 + 1);
        return tree[node] = Math.min(leftChild, rightChild);
    }
}
