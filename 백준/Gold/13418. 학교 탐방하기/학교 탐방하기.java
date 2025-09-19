import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()) + 1;
            int M = Integer.parseInt(st.nextToken());

            List<Node>[] nodes = new ArrayList[N + 1];
            for (int i = 0; i <= N; i++) {
                nodes[i] = new ArrayList<>();
            }

            for (int i = 0; i <= M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                nodes[a].add(new Node(b, c));
                nodes[b].add(new Node(a, c));
            }

            Queue<Node> q = new PriorityQueue<>(Comparator.comparingInt(o -> o.w));
            boolean[] vis = new boolean[N + 1];
            q.addAll(nodes[0]);
            vis[0] = true;

            int worst = 0;
            int used = 0;
            while (!q.isEmpty() && used < N - 1) {
                Node cur = q.poll();
                if (vis[cur.v]) continue;
                vis[cur.v] = true;
                if (cur.w == 0) worst++;
                used++;
                for (Node next : nodes[cur.v]) {
                    if (!vis[next.v]) q.offer(next);
                }
            }

            q = new PriorityQueue<>((o1, o2) -> o2.w - o1.w);
            vis = new boolean[N + 1];
            q.addAll(nodes[0]);
            vis[0] = true;

            int best = 0;
            used = 0;
            while (!q.isEmpty() && used < N - 1) {
                Node cur = q.poll();
                if (vis[cur.v]) continue;
                vis[cur.v] = true;
                if (cur.w == 0) best++;
                used++;
                for (Node next : nodes[cur.v]) {
                    if (!vis[next.v]) q.offer(next);
                }
            }

            System.out.println((worst * worst) - (best * best));
        }
    }

    static class Node {
        int v, w;

        public Node(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
}
