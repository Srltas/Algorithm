import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int[] parent, rank;
    static List<Edge> edges;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int T = Integer.parseInt(br.readLine());

            while (T-- > 0) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int R = Integer.parseInt(st.nextToken());
                int C = Integer.parseInt(st.nextToken());

                edges = new ArrayList<>();
                parent = new int[R * C];
                rank = new int[R * C];
                for (int i = 0; i < R * C; i++) {
                    parent[i] = i;
                }

                for (int i = 0; i < R; i++) {
                    st = new StringTokenizer(br.readLine());
                    for (int j = 0; j < C - 1; j++) {
                        int w = Integer.parseInt(st.nextToken());
                        int u = id(i, j, C);
                        int v = id(i, j + 1, C);
                        edges.add(new Edge(u, v, w));
                    }
                }

                for (int i = 0; i < R - 1; i++) {
                    st = new StringTokenizer(br.readLine());
                    for (int j = 0; j < C; j++) {
                        int w = Integer.parseInt(st.nextToken());
                        int u = id(i, j, C);
                        int v = id(i + 1, j, C);
                        edges.add(new Edge(u, v, w));
                    }
                }

                Collections.sort(edges);

                int total = 0;
                int used = 0;
                int N = R * C;
                for (Edge e : edges) {
                    if (union(e.u, e.v)) {
                        total += e.w;
                        if (++used == N - 1) break;
                    }
                }
                System.out.println(total);
            }
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

    static int id(int i , int j, int C) {
        return i * C + j;
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
