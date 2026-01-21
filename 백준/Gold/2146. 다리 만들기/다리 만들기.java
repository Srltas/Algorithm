import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static final int[] dx = {0, 0, -1, 1};
    static final int[] dy = {-1, 1, 0, 0};

    static int N;
    static int[][] map;
    static int minBridge = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int islandId = 2;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1) {
                    labelIsland(i, j, islandId++);
                }
            }
        }

        for (int id = 2; id < islandId; id++) {
            bfs(id);
        }

        System.out.println(minBridge);
    }

    static void labelIsland(int x, int y, int id) {
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(x, y, 0));
        map[x][y] = id;

        while (!q.isEmpty()) {
            Node cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (isIn(nx, ny) && map[nx][ny] == 1) {
                    map[nx][ny] = id;
                    q.offer(new Node(nx, ny, 0));
                }
            }
        }
    }

    static void bfs(int islandId) {
        Queue<Node> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == islandId) {
                    q.offer(new Node(i, j, 0));
                    visited[i][j] = true;
                }
            }
        }

        while (!q.isEmpty()) {
            Node cur = q.poll();
            if (cur.dist >= minBridge) continue;

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (!isIn(nx, ny)) continue;
                if (map[nx][ny] == islandId) continue;

                if (map[nx][ny] != 0) {
                    minBridge = Math.min(minBridge, cur.dist);
                    return;
                }

                if (!visited[nx][ny]) {
                    visited[nx][ny] = true;
                    q.offer(new Node(nx, ny, cur.dist + 1));
                }
            }
        }
    }

    static boolean isIn(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < N;
    }

    static class Node {
        int x;
        int y;
        int dist;

        public Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
}
