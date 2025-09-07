import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static List<Edge>[] tree;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            tree = new ArrayList[N + 1];
            for (int i = 1; i < N + 1; i++) {
                tree[i] = new ArrayList<>();
            }

            for (int i = 0; i < N - 1; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                tree[s].add(new Edge(e, w));
                tree[e].add(new Edge(s, w));
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                sb.append(bfs(s, e)).append('\n');
            }
            System.out.println(sb);
        }
    }

    static int bfs(int start, int end) {
        Queue<Integer> q = new ArrayDeque<>();
        int[] dist = new int[N + 1];
        boolean[] visited = new boolean[N + 1];

        q.offer(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int c = q.poll();
            if (c == end) return dist[c];

            for (Edge edge : tree[c]) {
                int nx = edge.e;
                if (visited[nx]) continue;
                visited[nx] = true;
                dist[nx] = dist[c] + edge.w;
                q.offer(nx);
            }
        }
        return -1;
    }

    static class Edge {
        int e, w;

        public Edge(int e, int w) {
            this.e = e;
            this.w = w;
        }
    }
}
