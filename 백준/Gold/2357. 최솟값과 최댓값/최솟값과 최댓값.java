import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] arr;
    static MinMax[] tree;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            arr = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                arr[i] = Integer.parseInt(br.readLine());
            }

            tree = new MinMax[4 * N];
            init(1, N ,1);

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                MinMax result = find(1, N, 1, a, b);
                sb.append(result.min).append(" ").append(result.max).append('\n');
            }
            System.out.println(sb);
        }
    }

    static MinMax find(int start, int end, int node, int left, int right) {
        if (left > end || right < start) {
            return new MinMax(Integer.MAX_VALUE, Integer.MIN_VALUE);
        }

        if (left <= start && right >= end) {
            return tree[node];
        }

        int mid = (start + end) / 2;
        MinMax leftResult = find(start, mid, node * 2, left, right);
        MinMax rightResult = find(mid + 1, end, node * 2 + 1, left, right);

        int min = Math.min(leftResult.min, rightResult.min);
        int max = Math.max(leftResult.max, rightResult.max);

        return new MinMax(min, max);
    }

    static MinMax init(int start, int end, int node) {
        if (start == end) {
            return tree[node] = new MinMax(arr[start], arr[start]);
        }

        int mid = (start + end) / 2;
        MinMax leftChild = init(start, mid, node * 2);
        MinMax rightChild = init(mid + 1, end, node * 2 + 1);

        int min = Math.min(leftChild.min, rightChild.min);
        int max = Math.max(leftChild.max, rightChild.max);

        return tree[node] = new MinMax(min, max);
    }

    static class MinMax {
        int min, max;

        public MinMax(int min, int max) {
            this.min = min;
            this.max = max;
        }
    }
}
