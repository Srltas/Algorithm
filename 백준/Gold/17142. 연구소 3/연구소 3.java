import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static final int[] dx = {0, 0, -1, 1};
    static final int[] dy = {-1, 1, 0, 0};

    static int N, M;
    static int[][] room;
    static List<int[]> virus = new ArrayList<>();
    static boolean[] visited;
    static int minTime = Integer.MAX_VALUE;
    static int emptyCount = 0;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            room = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    room[i][j] = Integer.parseInt(st.nextToken());
                    if (room[i][j] == 2) {
                        virus.add(new int[]{i,j});
                    } else if (room[i][j] == 0) {
                        emptyCount++;
                    }
                }
            }

            if (emptyCount == 0) {
                System.out.println(0);
                return;
            }

            visited = new boolean[virus.size()];
            dfs(0, 0, new ArrayList<>());

            if (minTime == Integer.MAX_VALUE) {
                System.out.println(-1);
            } else {
                System.out.println(minTime);
            }
        }
    }

    static void dfs(int depth, int index, List<int[]> start) {
        if (depth == M) {
            bfs(start);
            return;
        }

        for (int i = index; i < virus.size(); i++) {
            if (visited[i]) continue;
            visited[i] = true;
            start.add(virus.get(i));
            dfs(depth + 1, i + 1, start);
            start.remove(start.size() - 1);
            visited[i] = false;
        }
    }

    static void bfs(List<int[]> start) {
        Queue<int[]> q = new ArrayDeque<>();
        int[][] tempRoom = new int[N][N];
        int time = 0;
        int tempEmptyCount = 0;

        for (int i = 0; i < N; i++) {
            Arrays.fill(tempRoom[i], -1);
        }

        for (int[] s : start) {
            q.offer(s);
            tempRoom[s[0]][s[1]] = 0;
        }

        while (!q.isEmpty()) {
            int[] c = q.poll();
            int x = c[0];
            int y = c[1];
            int cTime = tempRoom[x][y];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || ny < 0 || nx >= N || ny >= N
                || room[nx][ny] == 1
                || tempRoom[nx][ny] != -1) continue;

                int newTime = cTime + 1;
                if (room[nx][ny] == 0 && newTime >= minTime) {
                    return;
                }

                tempRoom[nx][ny] = newTime;
                q.offer(new int[]{nx, ny});

                if (room[nx][ny] == 0) {
                    tempEmptyCount++;
                    time = tempRoom[nx][ny];
                    if (tempEmptyCount == emptyCount) {
                        q.clear();
                        break;
                    }
                }
            }
        }
        if (tempEmptyCount == emptyCount) {
            minTime = Math.min(minTime, time);
        }
    }
}
