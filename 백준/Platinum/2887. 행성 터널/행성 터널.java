import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(br.readLine());

            int[][] points = new int[N][4];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                points[i] = new int[] {a, b, c, i};
            }

            List<Node>[] nodes = new ArrayList[N];
            for (int i = 0; i < N; i++) {
                nodes[i] = new ArrayList<>();
            }

            // x축 정렬
            Arrays.sort(points, Comparator.comparing(n -> n[0]));
            for (int i = 0; i < N - 1; i++) {
                int u = points[i][3];
                int v = points[i + 1][3];
                int w = calW(points[i], points[i + 1]);
                nodes[u].add(new Node(v, w));
                nodes[v].add(new Node(u, w));
            }

            // y축 정렬
            Arrays.sort(points, Comparator.comparingInt(n -> n[1]));
            for (int i = 0; i < N - 1; i++) {
                int u = points[i][3];
                int v = points[i + 1][3];
                int w = calW(points[i], points[i + 1]);
                nodes[u].add(new Node(v, w));
                nodes[v].add(new Node(u, w));
            }

            // z축 정렬
            Arrays.sort(points, Comparator.comparingInt(n -> n[2]));
            for (int i = 0; i < N - 1; i++) {
                int u = points[i][3];
                int v = points[i + 1][3];
                int w = calW(points[i], points[i + 1]);
                nodes[u].add(new Node(v, w));
                nodes[v].add(new Node(u, w));
            }

            Queue<Node> q = new PriorityQueue<>();
            boolean[] visited = new boolean[N];
            q.addAll(nodes[0]);
            visited[0] = true;

            int total = 0;
            int used = 0;
            while (!q.isEmpty() && used < N - 1) {
                Node cur = q.poll();
                if (visited[cur.v]) continue;
                visited[cur.v] = true;
                total += cur.w;
                used++;
                for (Node next : nodes[cur.v]) {
                    if (!visited[next.v]) q.offer(next);
                }
            }

            System.out.println(total);
        }
    }

    static int calW (int[] n1, int[] n2) {
        int x1 = n1[0];
        int y1 = n1[1];
        int z1 = n1[2];

        int x2 = n2[0];
        int y2 = n2[1];
        int z2 = n2[2];

        int x = Math.abs(x2 - x1);
        int y = Math.abs(y2 - y1);
        int z = Math.abs(z2 - z1);

        return Math.min(x, Math.min(y, z));
    }

    static class Node implements Comparable<Node> {
        int v, w;

        public Node(int v, int w) {
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.w, o.w);
        }
    }
}
