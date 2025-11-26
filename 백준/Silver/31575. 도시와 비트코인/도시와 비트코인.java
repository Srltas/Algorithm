import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[] dy = {1, 0};
    static int[] dx = {0, 1};

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int[][] map = new int[M][N];
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            boolean[][] visited = new boolean[M][N];
            Queue<int[]> q = new ArrayDeque<>();
            q.offer(new int[] {0, 0});
            visited[0][0] = true;
            while (!q.isEmpty()) {
                int[] current = q.poll();
                for (int i = 0; i < 2; i++) {
                    int ny = current[0] + dy[i];
                    int nx = current[1] + dx[i];
                    if (ny < 0 || nx < 0 || ny >= M || nx >= N) continue;
                    if (visited[ny][nx] || map[ny][nx] == 0) continue;
                    visited[ny][nx] = true;
                    q.offer(new int[]{ny, nx});
                }
            }

            System.out.println(visited[M - 1][N - 1] ? "Yes" : "No");
        }
    }
}
