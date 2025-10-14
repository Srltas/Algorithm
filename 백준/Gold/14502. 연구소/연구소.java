import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static final int WALL = 3;
    static final int[] dx = {0, 0, -1, 1};
    static final int[] dy = {-1, 1, 0, 0};

    static int N, M, maxSafeZone, totalEmpty;
    static int[][] map;
    static List<int[]> virus = new ArrayList<>();
    static List<int[]> empties = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            map = new int[N][M];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    int n = Integer.parseInt(st.nextToken());
                    if (n == 2) virus.add(new int[]{i, j});
                    if (n == 0) {
                        empties.add(new int[]{i, j});
                        totalEmpty++;
                    }
                    map[i][j] = n;
                }
            }

            dfs(0,0);
            System.out.println(maxSafeZone);
        }
    }

    static void dfs(int start, int depth) {
        if (depth == WALL) {
            bfs();
            return;
        }
        for (int idx = start; idx < empties.size(); idx++) {
            int[] p = empties.get(idx);
            int x = p[0], y = p[1];
            if (map[x][y] != 0) continue;
            map[x][y] = 1;
            dfs(idx + 1, depth + 1);
            map[x][y] = 0;
        }
    }

    static void bfs() {
        boolean[][] visited = new boolean[N][M];
        Queue<int[]> q = new ArrayDeque<>();
        for (int[] v : virus) {
            q.offer(v);
            visited[v[0]][v[1]] = true;
        }

        int countVirus = 0;
        while (!q.isEmpty()) {
            int[] n = q.poll();
            int x = n[0];
            int y = n[1];
            visited[x][y] = true;
            for (int i = 0; i < 4; i++) {
                int nx = n[0] + dx[i];
                int ny = n[1] + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if (visited[nx][ny] || map[nx][ny] != 0) continue;
                visited[nx][ny] = true;
                countVirus++;
                q.offer(new int[]{nx, ny});
            }
        }
        maxSafeZone = Math.max(maxSafeZone, totalEmpty - WALL - countVirus);
    }
}
