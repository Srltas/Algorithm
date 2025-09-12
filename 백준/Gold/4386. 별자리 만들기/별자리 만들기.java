import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] parent, rank;
    static double[][] point;
    static List<Edge> edges = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(br.readLine());

            parent = new int[N];
            for (int i = 0; i < N; i++) {
                parent[i] = i;
            }
            rank = new int[N];
            point = new double[N][2];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                double x = Double.parseDouble(st.nextToken());
                double y = Double.parseDouble(st.nextToken());

                point[i][0] = x;
                point[i][1] = y;
            }

            for (int i = 0; i < point.length - 1; i++) {
                for (int j = i + 1; j < point.length; j++) {
                    double x1 = point[i][0];
                    double y1 = point[i][1];
                    double x2 = point[j][0];
                    double y2 = point[j][1];

                    edges.add(new Edge(i, j, Math.hypot(x2 - x1, y2 - y1)));
                }
            }

            Collections.sort(edges);

            double total = 0;
            int used = 0;
            for (Edge e : edges) {
                int n1 = find(e.node1);
                int n2 = find(e.node2);
                if (n1 == n2) continue;
                union(n1, n2);
                total += e.d;
                if (++used == N - 1) break;
            }
            System.out.printf("%.2f", total);
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
        int node1, node2;
        double d;

        public Edge(int node1, int node2, double d) {
            this.node1 = node1;
            this.node2 = node2;
            this.d = d;
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.d, o.d);
        }
    }
}
