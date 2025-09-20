import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] parent, rank;
    static Edge[] edges;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringBuilder sb = new StringBuilder();
            while (true) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                M = Integer.parseInt(st.nextToken());
                N = Integer.parseInt(st.nextToken());
                if (M == 0 && N == 0) break;

                rank = new int[M];
                parent = new int[M];
                for (int i = 0; i < M; i++) {
                    parent[i] = i;
                }

                edges = new Edge[N];
                int total = 0;
                for (int i = 0; i < N; i++) {
                    st = new StringTokenizer(br.readLine());
                    int a = Integer.parseInt(st.nextToken());
                    int b = Integer.parseInt(st.nextToken());
                    int c = Integer.parseInt(st.nextToken());
                    total += c;
                    edges[i] = new Edge(a, b, c);
                }

                Arrays.sort(edges);

                int save = 0;
                int used = 0;
                for (Edge e : edges) {
                    if (union(e.u, e.v)) {
                        save += e.w;
                        if (++used == M - 1) break;
                    }
                }
                sb.append(total - save).append("\n");
            }
            System.out.println(sb);
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
