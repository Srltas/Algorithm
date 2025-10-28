import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static final int TOP = 6, BOTTOM = 1, NORTH = 2, SOUTH = 5, EAST = 3, WEST = 4;
    static final int DIR_EAST = 0, DIR_SOUTH = 1, DIR_WEST = 2, DIR_NORTH = 3;
    static final int[] dx = {0, 1, 0, -1};
    static final int[] dy = {1, 0, -1, 0};
    
    static int N, M, K;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            map = new int[N + 1][M + 1];
            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 1; j <= M; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            Dice dice = new Dice(DIR_EAST);
            int score = 0;
            int x = 1, y = 1;
            while (K-- > 0) {
                int nx = x + dx[dice.dir];
                int ny = y + dy[dice.dir];
                if (nx < 1 || ny < 1 || nx > N || ny > M) {
                    dice.dir = (dice.dir + 2) % 4;
                    nx = x + dx[dice.dir];
                    ny = y + dy[dice.dir];
                }

                dice.roll(dice.dir);
                x = nx; y = ny;

                score += (map[x][y] * bfs(x, y));

                int A = dice.getBottom();
                int B = map[x][y];
                if (A > B) dice.dir = (dice.dir + 1) % 4;
                else if (A < B) dice.dir = (dice.dir + 3) % 4;
            }
            System.out.println(score);
        }
    }

    static int bfs(int x, int y) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{x, y});
        boolean[][] visited = new boolean[N + 1][M + 1];
        visited[x][y] = true;
        int count = 1;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if (nx < 1 || ny < 1 || nx > N || ny > M
                    || visited[nx][ny]
                    || map[x][y] != map[nx][ny]) continue;
                q.offer(new int[]{nx, ny});
                visited[nx][ny] = true;
                count++;
            }
        }
        return count;
    }
    
    static class Dice {
        int dir;
        int[] f = new int[7];

        public Dice(int dir) {
            this.dir = dir;
            f[TOP] = 1; f[NORTH] = 2; f[EAST] = 3; f[WEST] = 4; f[SOUTH] = 5; f[BOTTOM] = 6;
        }

        public int getBottom() {
            return f[BOTTOM];
        }

        public void roll(int d) {
            if (d == DIR_EAST) rollEast();
            else if (d == DIR_SOUTH) rollSouth();
            else if (d == DIR_WEST) rollWest();
            else if (d == DIR_NORTH) rollNorth();
        }

        private void rollEast() {
            int t = f[TOP], b = f[BOTTOM], e = f[EAST], w = f[WEST];
            f[TOP] = w; f[BOTTOM] = e; f[EAST] = t; f[WEST] = b;
        }

        private void rollWest() {
            int t = f[TOP], b = f[BOTTOM], e = f[EAST], w = f[WEST];
            f[TOP] = e; f[BOTTOM] = w; f[EAST] = b; f[WEST] = t;
        }

        private void rollNorth() {
            int t = f[TOP], b = f[BOTTOM], n = f[NORTH], s = f[SOUTH];
            f[TOP] = s; f[BOTTOM] = n; f[NORTH] = t; f[SOUTH] = b;
        }

        private void rollSouth() {
            int t = f[TOP], b = f[BOTTOM], n = f[NORTH], s = f[SOUTH];
            f[TOP] = n; f[BOTTOM] = s; f[NORTH] = b; f[SOUTH] = t;
        }
    }
}
