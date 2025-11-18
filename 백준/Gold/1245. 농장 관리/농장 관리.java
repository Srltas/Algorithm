import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static final int[] dy = {1, 0, -1, 0, 1, 1, -1, -1};
    static final int[] dx = {0, 1, 0, -1, 1, -1, 1, -1};

    static int N, M;
    static int[][] farm;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            farm = new int[N][M];
            for (int n = 0; n < N; n++) {
                st = new StringTokenizer(br.readLine());
                for (int m = 0; m < M; m++) {
                    farm[n][m] = Integer.parseInt(st.nextToken());
                }
            }

            int result = 0;
            visited = new boolean[N][M];
            for (int n = 0; n < N; n++) {
                for (int m = 0; m < M; m++) {
                    if (!visited[n][m] && farm[n][m] > 0) {
                        if (bfs(n,m)) {
                            result++;
                        }
                    }
                }
            }

            System.out.println(result);
        }
    }

    static boolean bfs(int startY, int startX) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{startY, startX});
        visited[startY][startX] = true;

        int height = farm[startY][startX];
        boolean tallest = true;

        while (!q.isEmpty()) {
            int[] current = q.poll();
            int y = current[0];
            int x = current[1];
            for (int i = 0; i < 8; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                if (ny < 0 || nx < 0 || ny >= N || nx >= M) continue;
                if (farm[ny][nx] > height) tallest = false;
                if (height == farm[ny][nx] && !visited[ny][nx]) {
                    q.offer(new int[] {ny, nx});
                    visited[ny][nx] = true;
                }

            }
        }

        return tallest;
    }
}
