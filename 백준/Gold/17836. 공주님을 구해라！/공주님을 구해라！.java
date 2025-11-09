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

    static int N, M, T;
    static int[][] map, timeMap;
    static int swordX, swordY;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            T = Integer.parseInt(st.nextToken());

            map = new int[N][M];
            timeMap = new int[N][M];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] == 2) {
                        swordX = i;
                        swordY = j;
                    }
                }
            }

            int timeNoSword = bfs(0, 0, N - 1, M - 1);
            int timeToSword = bfs(0, 0, swordX, swordY);

            int timeWithSword = Integer.MAX_VALUE;
            if (timeToSword != Integer.MAX_VALUE) {
                int timeFromSword = (N - 1 - swordX) + (M - 1 - swordY);
                timeWithSword = timeToSword + timeFromSword;
            }

            int minTime = Math.min(timeNoSword, timeWithSword);
            System.out.println(minTime <= T ? minTime : "Fail");
        }
    }

    static int bfs(int startX, int startY, int endX, int endY) {
        Queue<Node> q = new ArrayDeque<>();
        int[][] timeMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(timeMap[i], - 1);
        }

        q.offer(new Node(startX, startY));
        timeMap[startX][startY] = 0;

        while (!q.isEmpty()) {
            Node currNode = q.poll();
            int x = currNode.x;
            int y = currNode.y;

            if (x == endX && y == endY) {
                return timeMap[x][y];
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if (timeMap[nx][ny] != -1) continue;
                if (map[nx][ny] == 1) continue;
                timeMap[nx][ny] = timeMap[x][y] + 1;
                q.offer(new Node(nx, ny));
            }
        }
        return Integer.MAX_VALUE;
    }


    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
