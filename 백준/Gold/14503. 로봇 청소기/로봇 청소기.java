import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            map = new int[N][M];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            Robot robot = new Robot(r, c, d);
            robot.clean();
        }
    }

    static class Robot {
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        int x, y;
        int dir, cleaned;

        public Robot(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }

        public void clean() {
            while (true) {
                if (map[x][y] == 0) {
                    map[x][y] = 2;
                    cleaned++;
                }
                if (tryMoveLeftFirst()) continue;
                if (!back()) break;
            }
            System.out.println(cleaned);
        }

        private boolean tryMoveLeftFirst() {
            for (int i = 0; i < 4; i++) {
                dir = (dir + 3) % 4;
                int nx = x + dx[dir];
                int ny = y + dy[dir];
                if (outMapArea(nx, ny)) continue;
                if (map[nx][ny] == 0) {
                    x = nx; y = ny;
                    return true;
                }
            }
            return false;
        }

        private boolean back() {
            int backDir = (dir + 2) % 4;
            int nx = x + dx[backDir];
            int ny = y + dy[backDir];
            if (outMapArea(nx, ny)) return false;
            if (map[nx][ny] == 1) return false;
            x = nx; y = ny;
            return true;
        }

        private boolean outMapArea(int x, int y) {
            return x < 0 || x >= N || y < 0 || y >= M;
        }
    }
}
