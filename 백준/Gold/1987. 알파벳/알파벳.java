import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static final int[] dx = {0, 0, 1, -1};
    static final int[] dy = {1, -1, 0, 0};

    static int R, C;
    static int answer;
    static char[][] board;
    static boolean[] visited = new boolean[26];

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            board = new char[R][C];
            for (int i = 0; i < R; i++) {
                char[] row = br.readLine().toCharArray();
                for (int j = 0; j < C; j++) {
                    board[i][j] = row[j];
                }
            }
            dfs(0, 0, 1);
            System.out.println(answer);
        }
    }

    static void dfs(int x, int y, int depth) {
        int index = board[x][y] - 'A';
        visited[index] = true;
        answer = Math.max(answer, depth);

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if ((nx >= R || nx < 0 || ny >= C || ny < 0)
                || visited[board[nx][ny] - 'A']) continue;
            dfs(nx, ny, depth + 1);
            visited[board[nx][ny] - 'A'] = false;
        }
    }
}
