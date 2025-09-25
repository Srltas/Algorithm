import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] parent, rank;
    static Edge[] edges;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            parent = new int[N + 1];
            rank = new int[N + 1];
            for (int i = 0; i <= N; i++) {
                parent[i] = i;
            }

            edges = new Edge[M];
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());
                edges[i] = new Edge(u, v, w, t);
            }

            Arrays.sort(edges);

            long total = 0;
            int used = 0;
            long maxTime = 0;
            for (Edge e : edges) {
                if (union(e.u, e.v)) {
                    total += e.w;
                    maxTime = Math.max(maxTime, e.t);
                    if (++used == N - 1) break;
                }
            }
            if (used != N - 1) {
                System.out.println(-1);
            } else {
                System.out.println(maxTime + " " + total);
            }
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
        int u, v, w, t;

        public Edge(int u, int v, int w, int t) {
            this.u = u;
            this.v = v;
            this.w = w;
            this.t = t;
        }


        @Override
        public int compareTo(Edge o) {
            int c = Integer.compare(this.w, o.w);
            return (c != 0) ? c : Integer.compare(this.t, o.t);
        }
    }
}
