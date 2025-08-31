import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringBuilder sb = new StringBuilder();
            int TC = Integer.parseInt(br.readLine());
            while (TC-- > 0) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int N = Integer.parseInt(st.nextToken());
                int M = Integer.parseInt(st.nextToken());
                int W = Integer.parseInt(st.nextToken());

                Edge[] edges = new Edge[(2 * M) + W];
                int[] distance = new int[N + 1];

                for (int i = 0; i < 2 * M; i += 2) {
                    st = new StringTokenizer(br.readLine());
                    int s = Integer.parseInt(st.nextToken());
                    int e = Integer.parseInt(st.nextToken());
                    int t = Integer.parseInt(st.nextToken());
                    edges[i] = new Edge(s, e, t);
                    edges[i + 1] = new Edge(e, s, t);
                }

                for (int i = 2 * M; i < (2 * M) + W; i++) {
                    st = new StringTokenizer(br.readLine());
                    int s = Integer.parseInt(st.nextToken());
                    int e = Integer.parseInt(st.nextToken());
                    int t = Integer.parseInt(st.nextToken());
                    edges[i] = new Edge(s, e, -t);
                }

                Arrays.fill(distance, 0);
                for (int j = 0; j < N - 1; j++) {
                    boolean updated = false;
                    for (int k = 0; k < (2 * M) + W; k++) {
                        Edge edge = edges[k];
                        if (distance[edge.e] > distance[edge.s] + edge.t) {
                            distance[edge.e] = distance[edge.s] + edge.t;
                            updated = true;
                        }
                    }
                    if (!updated) break;
                }

                boolean cycle = false;
                for (int k = 0; k < (2 * M) + W; k++) {
                    Edge edge = edges[k];
                    if (distance[edge.e] > distance[edge.s] + edge.t) {
                        distance[edge.e] = distance[edge.s] + edge.t;
                        cycle = true;
                        break;
                    }
                }
                sb.append(cycle ? "YES" : "NO").append("\n");
            }
            System.out.println(sb);
        }
    }

    static class Edge {
        int s, e, t;
        public Edge(int s, int e, int t) { this.s = s; this.e = e; this.t = t; }
    }
}
