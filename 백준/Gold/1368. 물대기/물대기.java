import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    static int[] parent, rank;
    static List<Edge> edges = new ArrayList();

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(br.readLine());

            for (int i = 1; i <= N; i++) {
               edges.add(new Edge(0, i, Integer.parseInt(br.readLine())));
            }

            for (int i = 1; i <= N; i++) {
                String[] st = br.readLine().split(" ");
                for (int j = i + 1; j <= N; j++) {
                    edges.add(new Edge(i, j, Integer.parseInt(st[j - 1])));
                }
            }

            parent = new int[N + 1];
            rank = new int[N + 1];
            for (int i = 0; i <= N; i++) {
                parent[i] = i;
            }

            Collections.sort(edges);

            int total = 0;
            int used = 0;
            for (Edge e : edges) {
                if (union(e.u, e.v)) {
                    total += e.w;
                    if (++used == N) break;
                }
            }
            System.out.println(total);
        }
    }

    static int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
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
