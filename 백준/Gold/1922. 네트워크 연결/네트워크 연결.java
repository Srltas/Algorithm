import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(br.readLine());
            int M = Integer.parseInt(br.readLine());

            List<Edge>[] nodes = new ArrayList[N + 1];
            for (int i = 1; i < N + 1; i++) nodes[i] = new ArrayList<>();

            for (int i = 0; i < M; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                nodes[a].add(new Edge(b, c));
                nodes[b].add(new Edge(a, c));
            }

            Queue<Edge> q = new PriorityQueue<>();
            boolean[] visited = new boolean[N + 1];
            q.addAll(nodes[1]);
            visited[1] = true;

            int total = 0;
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
            System.out.println(total);
        }
    }

    static class Edge implements Comparable<Edge> {
        int b, c;
        public Edge(int b, int c) { this.b = b; this.c = c; }
        @Override
        public int compareTo(Edge o) {
            return this.c - o.c;
        }
    }
}
