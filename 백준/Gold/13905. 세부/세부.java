import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, S, E;
    static List<Node>[] nodes;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            S = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            nodes = new ArrayList[N + 1];
            for (int i = 1; i <= N; i++) {
                nodes[i] = new ArrayList<>();
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                nodes[a].add(new Node(b, c));
                nodes[b].add(new Node(a, c));
            }

            int[] best = new int[N + 1];
            Arrays.fill(best, -1);
            best[S] = Integer.MAX_VALUE;

            Queue<Node> q = new PriorityQueue<>();
            q.add(new Node(S, best[S]));

            while (!q.isEmpty()) {
                Node cur = q.poll();
                int u = cur.v;
                int bw = cur.w;

                if (u == E) {
                    System.out.println(bw);
                    return;
                }

                if (bw < best[u]) continue;

                for (Node nx : nodes[u]) {
                    int v = nx.v;
                    int w = nx.w;
                    int nw = Math.min(bw, w);
                    if (nw > best[v]) {
                        best[v] = nw;
                        q.add(new Node(v, nw));
                    }
                }
            }
            System.out.println(0);
        }
    }

    static class Node implements Comparable<Node> {
        int v, w;

        public Node(int v, int w) {
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(o.w, this.w);
        }
    }
}
