import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static Node[] tree;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(br.readLine());
            int[] arr = new int[N + 1];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            tree = new Node[4 * N];
            init(1, N, 1, arr);

            int M = Integer.parseInt(br.readLine());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                if (a == 1) {
                    int b = Integer.parseInt(st.nextToken());
                    int c = Integer.parseInt(st.nextToken());
                    update(1, N, 1, b, c);
                } else if (a == 2) {
                    sb.append(tree[1].index).append('\n');
                }
            }
            System.out.println(sb);
        }
    }

    static Node update(int start, int end, int node, int index, int value) {
        if (index < start || index > end) return tree[node];
        if (start == end) {
            Node cur = tree[node];
            if (cur == null) tree[node] = new Node(index, value);
            else { cur.index = index; cur.value = value; }
            return tree[node];
        }

        int mid = (start + end) / 2;
        return tree[node] = merge(
            update(start, mid, node * 2, index, value),
            update(mid + 1, end, node * 2 + 1, index, value));
    }

    static Node init(int start, int end, int node, int[] arr) {
        if (start == end) {
            return tree[node] = new Node(start, arr[start]);
        }

        int mid = (start + end) / 2;
        return tree[node] = merge(
            init(start, mid, node * 2, arr),
            init(mid + 1, end, node * 2 + 1, arr));
    }

    static Node merge(Node a, Node b) {
        if (a.value == b.value) return a.index <= b.index ? a : b;
        return a.value < b.value ? a : b;
    }

    static class Node {
        int index, value;

        public Node(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }
}
