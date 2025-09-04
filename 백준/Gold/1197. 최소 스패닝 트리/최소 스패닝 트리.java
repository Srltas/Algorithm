import java.io.IOException;
import java.util.Arrays;

public class Main {

    static int[] parent, rank;
    static Edge[] edges;

    public static void main(String[] args) throws IOException {
        FastScanner fs = new FastScanner();
        int V = fs.nextInt();
        int E = fs.nextInt();

        parent = new int[V + 1];
        rank = new int[V + 1];
        edges = new Edge[E];

        for (int i = 1; i <= V; i++) parent[i] = i;

        for (int i = 0; i < E; i++) {
            int a = fs.nextInt();
            int b = fs.nextInt();
            int c = fs.nextInt();
            edges[i] = new Edge(a, b, c);
        }

        Arrays.sort(edges);

        long total = 0L;
        int used = 0;

        for (Edge e : edges) {
            int ra = find(e.a);
            int rb = find(e.b);
            if (ra == rb) continue;
            union(ra, rb);
            total += e.c;
            if (++used == V - 1) break;
        }
        System.out.println(total);
    }

    static void union(int x, int y) {
        if (x == y) return;
        if (rank[x] < rank[y]) {
            parent[x] = y;
        } else if (rank[x] > rank[y]) {
            parent[y] = x;
        } else {
            parent[y] = x;
            rank[x]++;
        }
        x = find(x);
        y = find(y);
        if (x != y) {
            parent[y] = x;
        }
    }

    static int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }

    static class Edge implements Comparable<Edge> {
        int a, b, c;
        public Edge(int a, int b, int c) { this.a = a; this.b = b; this.c = c; }
        @Override
        public int compareTo(Edge o) { return Integer.compare(this.c, o.c); }
    }

    static class FastScanner {
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        private int read() throws IOException {
            if (ptr >= len) {
                len = System.in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        int nextInt() throws IOException {
            int c, sign = 1, val = 0;
            do { c= read(); } while (c <= ' ');
            if (c == '-') { sign = -1; c = read(); }
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = read();
            }
            return val * sign;
        }
    }
}
