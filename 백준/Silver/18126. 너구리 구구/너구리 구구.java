import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static List<Node>[] graph;
    static boolean[] visited;
    static long[] dist;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(br.readLine());
            graph = new ArrayList[N + 1];
            for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();
            for (int i = 0; i < N - 1; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                graph[a].add(new Node(b, c));
                graph[b].add(new Node(a, c));
            }

            visited = new boolean[N + 1];
            dist = new long[N + 1];
            Arrays.fill(dist, -1);

            bfs(new Node(1, 0));

            long max = 0;
            for (int i = 1; i <= N; i++) {
                max = Math.max(max, dist[i]);
            }
            System.out.println(max);
        }
    }

    static void bfs(Node start) {
        Queue<Node> q = new ArrayDeque<>();
        q.offer(start);
        visited[start.v] = true;
        dist[start.v] = 0;

        while (!q.isEmpty()) {
            Node current = q.poll();
            for (Node nextNode : graph[current.v]) {
                if (!visited[nextNode.v]) {
                    visited[nextNode.v] = true;
                    dist[nextNode.v] = dist[current.v] + nextNode.d;
                    q.offer(nextNode);
                }
            }
        }
    }

    static class Node {
        int v, d;

        public Node(int v, int d) {
            this.v = v;
            this.d = d;
        }
    }
}
