import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            List<Edge>[] nodes = new ArrayList[N + 1];
            for (int i = 1; i <= N; i++) {
                nodes[i] = new ArrayList<>();
            }

            long allLoad = 0L;
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                nodes[a].add(new Edge(b, c));
                nodes[b].add(new Edge(a, c));
                allLoad += c;
            }

            Queue<Edge> q = new PriorityQueue<>();
            boolean[] visited = new boolean[N + 1];
            q.addAll(nodes[1]);
            visited[1] = true;

            long total = 0L;
            int used = 0;
            while (!q.isEmpty() && used < N - 1) {
                Edge e = q.poll();
                if (visited[e.b]) continue;
                visited[e.b] = true;
                total += e.c;
                used++;
                for (Edge ne : nodes[e.b]) {
                    if (!visited[ne.b]) q.offer(ne);
                }
            }

            long result = used == N -1 ? allLoad - total : -1;
            System.out.println(result);
        }
    }

    static class Edge implements Comparable<Edge> {
        int b, c;

        public Edge(int b, int c) {
            this.b = b;
            this.c = c;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.c, o.c);
        }
    }
}
