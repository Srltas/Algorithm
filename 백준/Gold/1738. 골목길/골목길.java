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

            int[] distance = new int[N + 1];
            Arrays.fill(distance, Integer.MIN_VALUE);

            Edge[] edges = new Edge[M];
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                edges[i] = new Edge(s, e, w);
            }

            int[] prev = new int[N + 1];
            distance[1] = 0;
            for (int i = 0; i < N - 1; i++) {
                boolean updated = false;
                for (Edge edge : edges) {
                    if (distance[edge.s] != Integer.MIN_VALUE &&
                        distance[edge.e] < distance[edge.s] + edge.w) {
                        distance[edge.e] = distance[edge.s] + edge.w;
                        prev[edge.e] = edge.s;
                        updated = true;
                    }
                }
                if (!updated) break;
            }

            if (distance[N] == Integer.MIN_VALUE) {
                System.out.println(-1);
                return;
            }

            List<Integer>[] reverse = new ArrayList[N + 1];
            for (int i = 1; i <= N; i++) { reverse[i] = new ArrayList<>(); }
            for (Edge edge : edges) { reverse[edge.e].add(edge.s); }

            boolean[] canReach = new boolean[N + 1];
            Deque<Integer> q = new ArrayDeque<>();
            q.add(N);
            canReach[N] = true;

            while (!q.isEmpty()) {
                int current = q.poll();
                for (int prevNode : reverse[current]) {
                    if (!canReach[prevNode]) {
                        canReach[prevNode] = true;
                        q.add(prevNode);
                    }
                }
            }

            for (Edge edge : edges) {
                if (distance[edge.s] != Integer.MIN_VALUE &&
                    distance[edge.e] < distance[edge.s] + edge.w) {
                    if (canReach[edge.e]) {
                        System.out.println(-1);
                        return;
                    }
                }
            }

            Deque<Integer> path = new ArrayDeque<>();
            int cur = N;
            int hop = 0;
            boolean ok = true;
            while (true) {
                path.addFirst(cur);
                if (cur == 1) break;
                if (prev[cur] == 0) {
                    ok = false;
                    break;
                }
                cur = prev[cur];
                if (++hop > N) {
                    ok = false;
                    break;
                }
            }

            if (ok) {
                StringJoiner sj = new StringJoiner(" ");
                for (int v : path) sj.add(Integer.toString(v));
                System.out.println(sj);
            } else {
                System.out.println(-1);
            }
        }
    }

    static class Edge {
        int s, e, w;
        public Edge(int s, int e, int w) { this.s = s; this.e = e; this.w = w; }
    }
}
