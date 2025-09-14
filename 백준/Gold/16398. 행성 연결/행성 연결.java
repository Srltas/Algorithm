import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    static int[] parent, rank;
    static List<Edge> edges;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(br.readLine());

            parent = new int[N + 1];
            rank = new int[N + 1];
            for (int i = 1; i < N + 1; i++) {
                parent[i] = i;
            }

            edges = new ArrayList<>((N * N) - N);
            for (int i = 1; i <= N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 1; j <= N; j++) {
                    int w = Integer.parseInt(st.nextToken());
                    if (i > j) {
                        edges.add(new Edge(i, j, w));
                    }
                }
            }

            Collections.sort(edges);

            long total = 0L;
            int used = 0;
            for (Edge e : edges) {
                if (union(e.s, e.t)) {
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

    static class Edge implements Comparable<Edge> {
        int s, t, w;

        public Edge(int s, int t, int w) {
            this.s = s;
            this.t = t;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.w, o.w);
        }
    }
}
