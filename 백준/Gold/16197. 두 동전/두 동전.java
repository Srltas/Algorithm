import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final char COIN = 'o';
    static final char WALL = '#';

    static final int[] dx = {1, 0, 0, -1};
    static final int[] dy = {0, 1, -1, 0};

    static int N, M;
    static char[][] map;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            map = new char[N][M];
            List<int[]> coins = new ArrayList<>();
            for (int n = 0; n < N; n++) {
                char[] line = br.readLine().toCharArray();
                for (int m = 0; m < M; m++) {
                    map[n][m] = line[m];
                    if (map[n][m] == COIN) coins.add(new int[] {n, m});
                }
            }

            System.out.println(bfs(coins));
        }
    }

    static int bfs(List<int[]> coins) {
        boolean[][][][] visited = new boolean[N][M][N][M];
        Queue<Status> q = new ArrayDeque<>();
        int[] coin1 = coins.get(0);
        int[] coin2 = coins.get(1);
        q.offer(new Status(coin1[0], coin1[1], coin2[0], coin2[1], 0));
        visited[coin1[0]][coin1[1]][coin2[0]][coin2[1]] = true;

        while(!q.isEmpty()) {
            Status current = q.poll();
            int x1 = current.x1;
            int y1 = current.y1;
            int x2 = current.x2;
            int y2 = current.y2;
            int count = current.count;
            if (count == 10) continue;
            for (int i = 0; i < 4; i++) {
                int nx1 = x1 + dx[i];
                int ny1 = y1 + dy[i];
                int nx2 = x2 + dx[i];
                int ny2 = y2 + dy[i];

                boolean fell1 = false, fell2 = false;
                if (outMap(nx1, ny1)) fell1 = true;
                if (outMap(nx2, ny2)) fell2 = true;

                if (fell1 != fell2) return count + 1;
                if (fell1) continue;

                if (map[nx1][ny1] == WALL) { nx1 = x1; ny1 = y1; }
                if (map[nx2][ny2] == WALL) { nx2 = x2; ny2 = y2; }

                if (visited[nx1][ny1][nx2][ny2]) continue;
                visited[nx1][ny1][nx2][ny2] = true;
                q.offer(new Status(nx1, ny1, nx2, ny2, count + 1));
            }
        }
        return -1;
    }

    static boolean outMap(int x, int y) {
        return x < 0 || y < 0 || x >= N || y >= M;
    }

    static class Status {
        int x1, y1, x2, y2, count;

        public Status(int x1, int y1, int x2, int y2, int count) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.count = count;
        }
    }
}
