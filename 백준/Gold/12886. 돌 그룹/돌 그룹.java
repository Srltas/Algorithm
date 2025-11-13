import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            if ((A + B + C) % 3 != 0) {
                System.out.println(0);
                return;
            }

            boolean[][] visited = new boolean[1501][1501];
            Queue<Node> q = new ArrayDeque<>();
            q.offer(new Node(A, B, C));
            visited[A][B] = true;

            while (!q.isEmpty()) {
                Node current = q.poll();
                int a = current.a;
                int b = current.b;
                int c = current.c;

                if (a == b && b == c) {
                    System.out.println(1);
                    return;
                }

                pair(a, b, c, q, visited);
                pair(a, c, b, q, visited);
                pair(b, c, a, q, visited);
            }
            System.out.println(0);
        }
    }

    static void pair(int n1, int n2, int n3, Queue<Node> q, boolean[][] visited) {
        if (n1 == n2) {
            return;
        }

        if (n1 < n2) {
            sort(n1 + n1, n2 - n1, n3, q, visited);
        } else {
            sort(n1 - n2, n2 + n2, n3, q, visited);
        }
    }

    static void sort(int a, int b, int c, Queue<Node> q, boolean[][] visited) {
        int[] nums = {a, b, c};
        Arrays.sort(nums);

        int na = nums[0];
        int nb = nums[1];
        int nc = nums[2];
        if (!visited[na][nb]) {
            visited[na][nb] = true;
            q.offer(new Node(na, nb, nc));
        }
    }

    static class Node {
        int a, b, c;

        public Node(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }
}
