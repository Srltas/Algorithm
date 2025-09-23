import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

    static Edge[] edges;
    static int[] parent, rank;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(br.readLine());

            int[][] nodes = new int[N][4];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                nodes[i] = new int[] {a, b, c, i};
            }

            edges = new Edge[3 * (N - 1)];
            int index = 0;
            // x 기준 정렬 후 간선
            Arrays.sort(nodes, Comparator.comparingInt(n -> n[0]));
            for (int i = 0; i < N - 1; i++) {
                edges[index++] = new Edge(nodes[i][3], nodes[i + 1][3], calW(nodes[i], nodes[i + 1]));
            }

            // y 기준 정렬 후 간선
            Arrays.sort(nodes, Comparator.comparingInt(n -> n[1]));
            for (int i = 0; i < N - 1; i++) {

                edges[index++] = new Edge(nodes[i][3], nodes[i + 1][3], calW(nodes[i], nodes[i + 1]));
            }

            // z 기준 정렬 후 간선
            Arrays.sort(nodes, Comparator.comparingInt(n -> n[2]));
            for (int i = 0; i < N - 1; i++) {
                edges[index++] = new Edge(nodes[i][3], nodes[i + 1][3], calW(nodes[i], nodes[i + 1]));
            }

            Arrays.sort(edges);

            parent = new int[N];
            rank = new int[N];
            for (int i = 0; i < N; i++) {
                parent[i] = i;
            }

            int total = 0;
            int used = 0;
            for (Edge e : edges) {
                if (union(e.u, e.v)) {
                    total += e.w;
                    if (++used == N - 1) break;
                }
            }
            System.out.println(total);
        }
    }

    static boolean union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x == y) return false;
        if (rank[x] < rank[y]) {
            int t = x;
            x = y;
            y = t;
        }
        parent[y] = x;
        if (rank[x] == rank[y]) rank[x]++;
        return true;
    }

    static int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
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

    static class Edge implements Comparable<Edge> {
        int u, v, w;

        public Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.w, o.w);
        }
    }
}
