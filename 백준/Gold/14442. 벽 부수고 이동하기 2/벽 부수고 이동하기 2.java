import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static final int[] dr = {0, 0, -1, 1};
    static final int[] dc = {-1, 1, 0, 0};

    static int N, M, K;
    static int[][] arr;
    static int[][][] dist;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            arr = new int[N][M];
            for (int i = 0; i < N; i++) {
                char[] c = br.readLine().toCharArray();
                for (int j = 0; j < M; j++) {
                    arr[i][j] = c[j] - '0';
                }
            }

            dist = new int[N][M][K + 1];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    Arrays.fill(dist[i][j], -1);
                }
            }

            Queue<Node> q = new ArrayDeque<>();
            q.offer(new Node(0, 0, 0, 1));
            dist[0][0][0] = 1;
            while (!q.isEmpty()) {
                Node cur = q.poll();
                for (int i = 0; i < 4; i++) {
                    int nr = cur.r + dr[i];
                    int nc = cur.c + dc[i];

                    if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
                    if (arr[nr][nc] == 1 && cur.k + 1> K) continue;

                    if (arr[nr][nc] == 0 && dist[nr][nc][cur.k] == -1) {
                        dist[nr][nc][cur.k] = cur.d + 1;
                        q.offer(new Node(nr, nc, cur.k, cur.d + 1));
                    } else if (arr[nr][nc] == 1 && dist[nr][nc][cur.k + 1] == -1) {
                        dist[nr][nc][cur.k + 1] = cur.d + 1;
                        q.offer(new Node(nr, nc, cur.k + 1, cur.d + 1));
                    }
                }
            }

            int result = Integer.MAX_VALUE;
            for (int k = 0; k <= K; k++) {
                int d =  dist[N - 1][M - 1][k];
                result = Math.min(result, d == -1 ? Integer.MAX_VALUE : d);
            }
            System.out.println(result == Integer.MAX_VALUE ? -1 : result);
        }
    }

    static class Node {
        int r, c, k, d;
        public Node (int r, int c, int k, int d) {
            this.r = r;
            this.c = c;
            this.k = k;
            this.d = d;
        }
    }
}
