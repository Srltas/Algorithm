import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static Edge[] edges;
    static int[] parent, rank;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(br.readLine());
            M = Integer.parseInt(br.readLine());

            parent = new int[N + 1];
            rank = new int[N + 1];
            edges = new Edge[M];

            for (int i = 1; i < N + 1; i++) parent[i] = i;

            for (int i = 0; i < M; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                edges[i] = new Edge(a, b, c);
            }

            Arrays.sort(edges);

            int total = 0;
            int used = 0;
            for (Edge edge : edges) {
                int na = find(edge.a);
                int nb = find(edge.b);
                if (na == nb) continue;
                union(na, nb);
                total += edge.c;
                if (++used > N - 1) break;
            }
            System.out.println(total);
        }
    }

    static void union(int x, int y) {
        if (x == y) return;
        if (rank[x] > rank[y]) {
            parent[y] = x;
        } else if (rank[y] > rank[x]) {
            parent[x] = y;
        } else {
            parent[y] = x;
            rank[x]++;
        }
    }

    static int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }

    static class Edge implements Comparable<Edge> {
        int a, b, c;

        public Edge(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        @Override
        public int compareTo(Edge o) {
            return this.c - o.c;
        }
    }
}