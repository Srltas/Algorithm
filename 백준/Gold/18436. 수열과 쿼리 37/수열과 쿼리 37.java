import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] arr;
    static Num[] tree;

    public static void main(String[] args) throws IOException {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(br.readLine());

            arr = new int[N + 1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            tree = new Num[4 * N];
            init(1, N, 1);

            int M = Integer.parseInt(br.readLine());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                if (a == 1) {
                    if((arr[b] & 1) != (c & 1)){
                        update(1, N, 1, b, c);
                    } else {
                        arr[b] = c;
                    }
                } else if (a == 2) {
                    sb.append(findEven(1, N, 1, b, c)).append('\n');
                } else if (a == 3) {
                    sb.append(findOdd(1, N, 1, b, c)).append('\n');
                }
            }
            System.out.println(sb);
        }
    }

    static int findOdd(int start, int end, int node, int left, int right) {
        if (start > right || end < left) {
            return 0;
        }

        if (start >= left && end <= right) {
            return tree[node].odd;
        }

        int mid = (start + end) / 2;
        return findOdd(start, mid, node * 2, left, right) + findOdd(mid + 1, end, node * 2 + 1, left, right);
    }

    static int findEven(int start, int end, int node, int left, int right) {
        if (start > right || end < left) {
            return 0;
        }

        if (start >= left && end <= right) {
            return tree[node].even;
        }

        int mid = (start + end) / 2;
        return findEven(start, mid, node * 2, left, right) + findEven(mid + 1, end, node * 2 + 1, left, right);
    }

    static void update(int start, int end, int node, int index, int value) {
        if (index < start || index > end) return;

        if (start == end) {
            arr[index] = value;
            tree[node] = ((value & 1) == 0) ? new Num(1, 0) : new Num(0, 1);
            return;
        }

        int mid = (start + end) / 2;
        update(start, mid, node * 2, index, value);
        update(mid + 1, end, node * 2 + 1, index, value);
        tree[node] = new Num(tree[node * 2].even + tree[node * 2 + 1].even,
            tree[node * 2].odd + tree[node * 2 + 1].odd);
    }

    static Num init(int start, int end, int node) {
        if (start == end) {
            return tree[node] = arr[start] % 2 == 0 ? new Num(1, 0) : new Num(0, 1);
        }

        int mid = (start + end) / 2;
        Num leftChild = init(start, mid, node * 2);
        Num rightChild = init(mid + 1, end, node * 2 + 1);
        return tree[node] = new Num(leftChild.even + rightChild.even, leftChild.odd + rightChild.odd);
    }

    static class Num {
        int even, odd;

        public Num(int even, int odd) {
            this.even = even;
            this.odd = odd;
        }
    }
}
