import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static final int[] dx = {0, 0, -1, 1};
    static final int[] dy = {-1, 1, 0, 0};
    static final int[] horseX = {-2, -2, -1, -1, 2, 2, 1, 1};
    static final int[] horseY = {-1, 1, -2, 2, -1, 1, -2, 2};

    static int K, W, H;
    static int[][] map;
    static int[][][] visited;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            K = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            map = new int[H][W];
            for (int h = 0; h < H; h++) {
                st = new StringTokenizer(br.readLine());
                for (int w = 0; w < W; w++) {
                    map[h][w] = Integer.parseInt(st.nextToken());
                }
            }

            visited = new int[H][W][K + 1];
            for (int h = 0; h < H; h++) {
                for (int w = 0; w < W; w++) {
                    Arrays.fill(visited[h][w], -1);
                }
            }

            bfs();
            int minMoves = Integer.MAX_VALUE;
            for (int k = 0; k <= K; k++) {
                if (visited[H - 1][W - 1][k] != -1) {
                    minMoves = Math.min(minMoves, visited[H - 1][W - 1][k]);
                }
            }
            System.out.println(minMoves == Integer.MAX_VALUE ? -1 : minMoves);
        }
    }

    static void bfs() {
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(0, 0, 0));
        visited[0][0][0] = 0;

        while (!q.isEmpty()) {
            Node currNode = q.poll();
            int x = currNode.x;
            int y = currNode.y;
            int k = currNode.k;
            int moves = visited[x][y][k];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (outMap(nx, ny) || map[nx][ny] == 1) continue;
                if (visited[nx][ny][k] == -1) {
                    visited[nx][ny][k] = moves + 1;
                    q.offer(new Node(nx, ny, k));
                }
            }

            if (k < K) {
                for (int i = 0; i < 8; i++) {
                    int nx = x + horseX[i];
                    int ny = y + horseY[i];
                    int nk = k + 1;

                    if (outMap(nx, ny) || map[nx][ny] == 1) continue;
                    if (visited[nx][ny][nk] == -1) {
                        visited[nx][ny][nk] = moves + 1;
                        q.offer(new Node(nx, ny, nk));
                    }
                }
            }
        }
    }

    static boolean outMap(int x, int y) {
        return x < 0 || y < 0 || x >= H || y >= W;
    }

    static class Node {
        int x, y, k;

        public Node(int x, int y, int k) {
            this.x = x;
            this.y = y;
            this.k = k;
        }
    }
}
