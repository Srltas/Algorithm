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
            int R = Integer.parseInt(st.nextToken());

            List<Integer>[] graph = new ArrayList[N + 1];
            for (int i = 1; i <= N; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                graph[u].add(v);
                graph[v].add(u);
            }

            Queue<Integer> q = new ArrayDeque<>();
            q.offer(R);
            int[] depth = new int[N + 1];
            Arrays.fill(depth, -1);
            depth[R] = 0;

            while (!q.isEmpty()) {
                int current = q.poll();
                for (int next : graph[current]) {
                    if (depth[next] != -1) continue;
                    q.offer(next);
                    depth[next] = depth[current] + 1;
                }
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 1; i <= N; i++) {
                sb.append(depth[i]).append('\n');
            }
            System.out.println(sb);
        }
    }
}
