import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(br.readLine());

            List<Edge>[] nodes = new ArrayList[N + 1];
            for (int i = 1; i < N + 1; i++) {
                nodes[i] = new ArrayList<>();
            }

            for (int i = 1; i <= N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 1; j <= N; j++) {
                    int v = Integer.parseInt(st.nextToken());
                    if (i > j) {
                        nodes[i].add(new Edge(j, v));
                        nodes[j].add(new Edge(i, v));
                    }
                }
            }

            Queue<Edge> q = new PriorityQueue<>();
            boolean[] visited = new boolean[N + 1];
            q.addAll(nodes[1]);
            visited[1] = true;

            long total = 0L;
            int used = 0;
            while (!q.isEmpty() && used < N - 1) {
                Edge e = q.poll();
                if (visited[e.e]) continue;
                visited[e.e] = true;
                total += e.v;
                used++;
                for (Edge ne : nodes[e.e]) {
                    if (!visited[ne.e]) q.offer(ne);
                }
            }
            System.out.println(total);
        }
    }

    static class Edge implements Comparable<Edge> {
        int e, v;

        public Edge(int e, int v) {
            this.e = e;
            this.v = v;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.v, o.v);
        }
    }
}
