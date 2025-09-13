import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int power[] = new int[K];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < K; i++) {
                power[i] = Integer.parseInt(st.nextToken());
            }

            List<Edge>[] nodes = new ArrayList[N + 1];
            for (int i = 1; i < N + 1; i++) {
                nodes[i] = new ArrayList<>();
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                nodes[u].add(new Edge(v, w));
                nodes[v].add(new Edge(u, w));
            }

            Queue<Edge> q = new PriorityQueue<>();
            boolean[] visited = new boolean[N + 1];
            for (int p : power) {
                q.addAll(nodes[p]);
                visited[p] = true;
            }

            int total = 0;
            int used = 0;
            while (!q.isEmpty() && used < N - K) {
                Edge e = q.poll();
                if (visited[e.v]) continue;
                visited[e.v] = true;
                total += e.w;
                used++;
                for (Edge ne : nodes[e.v]) {
                    if (!visited[ne.v]) q.offer(ne);
                }
            }
            System.out.println(total);
        }
    }

    static class Edge implements Comparable<Edge> {
        int v, w;

        public Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.w, o.w);
        }
    }
}
