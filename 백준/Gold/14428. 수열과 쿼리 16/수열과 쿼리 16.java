import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static final A INF = new A(0, Integer.MAX_VALUE);

    static int N;
    static int[] arr;
    static A[] tree;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(br.readLine());
            arr = new int[N + 1];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            tree = new A[4 * N];
            init(1, N, 1);

            int M = Integer.parseInt(br.readLine());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                if (a == 1) {
                    arr[b] = c;
                    update(1, N, 1, b);
                } else if (a== 2) {
                    sb.append(find(1, N, 1, b, c).index).append('\n');
                }
            }
            System.out.println(sb);
        }
    }

    static A find(int start, int end, int node, int left, int right) {
        if (left > end || right < start) {
            return INF;
        }

        if (left <= start && right >= end) {
            return tree[node];
        }

        int mid = (start + end) / 2;
        A leftChild = find(start, mid, node * 2, left, right);
        A rightChild = find(mid + 1, end, node * 2 + 1, left, right);
        return combine(leftChild, rightChild);
    }

    static A update(int start, int end, int node, int index) {
        if (index < start || index > end) {
            return tree[node];
        }
        if (start == end) {
            return tree[node] = new A(index, arr[index]);
        }

        int mid = (start + end) / 2;
        A leftChild = update(start, mid, node * 2, index);
        A rightChild = update(mid + 1, end, node * 2 + 1, index);
        return tree[node] = combine(leftChild, rightChild);
    }

    static A init(int start, int end, int node) {
        if (start == end) {
            return tree[node] = new A(start, arr[start]);
        }

        int mid = (start + end) / 2;
        A leftChild = init(start, mid, node * 2);
        A rightChild = init(mid + 1, end, node * 2 + 1);

        return tree[node] = combine(leftChild, rightChild);
    }

    static A combine(A l, A r) {
        return l.compareTo(r) > 0 ? r : l;
    }

    static final class A implements Comparable<A> {
        final int index, value;

        public A(int index, int value) {
            this.index = index;
            this.value = value;
        }

        @Override
        public int compareTo(A o) {
            if (this.value == o.value) return Integer.compare(this.index, o.index);
            return Integer.compare(this.value, o.value);
        }
    }
}
