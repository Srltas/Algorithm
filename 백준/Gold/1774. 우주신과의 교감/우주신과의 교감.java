import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int[] parent, rank;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            int[][] p = new int[N + 1][2];
            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                p[i][0] = Integer.parseInt(st.nextToken());
                p[i][1] = Integer.parseInt(st.nextToken());
            }

            parent = new int[N + 1];
            rank = new int[N + 1];
            for (int i = 1; i <= N; i++) { parent[i] = i; }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                union(a, b);
            }

            List<Edge> edges = new ArrayList<>();
            for (int u = 1; u <= N - 1; u++) {
                for (int v = u + 1; v <= N; v++) {
                    double w = Math.hypot(p[u][0] - p[v][0], p[u][1] - p[v][1]);
                    edges.add(new Edge(u, v, w));
                }
            }

            Collections.sort(edges);

            Set<Integer> roots = new HashSet<>();
            for (int i = 1; i <= N; i++) { roots.add(find(i)); }
            int need = roots.size() - 1;

            double total = 0.0;
            int used = 0;
            for (Edge e : edges) {
                if (union(e.u, e.v)) {
                    total += e.w;
                    if (++used == need) break;
                }
            }
            System.out.printf("%.2f%n", total);
        }
    }

    static boolean union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y) return false;
        if (rank[x] < rank[y]) { int t = x; x = y; y = t; }
        parent[y] = x;
        if (rank[x] == rank[y]) rank[x]++;
        return true;
    }

    static int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }

    static class Edge implements Comparable<Edge> {
        int u ,v;
        double w;

        public Edge(int u, int v, double w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }


        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.w, o.w);
        }
    }
}
