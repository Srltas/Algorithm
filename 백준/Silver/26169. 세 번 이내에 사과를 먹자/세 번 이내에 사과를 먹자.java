import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

  private static final int N = 5;
  private static int[][] board;
  private static boolean [][] visited;

  private static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

  public static void main(String[] args) throws IOException {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      board = readGrid(br);
      visited = new boolean[N][N];

      int[] start = readPair(br);
      boolean ok = dfs(start[0], start[1], 0, 0);
      System.out.println(ok ? 1 : 0);
    }
  }

  public static boolean dfs(int r, int c, int moveCount, int appleCount) {
    if (moveCount > 3) return false;
    if (board[r][c] == 1) appleCount++;
    if (appleCount >= 2) return true;
    if (moveCount == 3) return false;

    visited[r][c] = true;

    try {
      for (int[] d: DIRS) {
        int nr = r + d[0];
        int nc = c + d[1];

        if (inBounds(nr, nc) && !visited[nr][nc] && !isBlocked(nr, nc)) {
          if (dfs(nr, nc, moveCount + 1, appleCount)) {
            return true;
          }
        }
      }
      return false;
    } finally {
      visited[r][c] = false;
    }
  }

  private static boolean inBounds(int r, int c) {
    return 0 <= r && r < N && 0 <= c && c < N;
  }

  private static boolean isBlocked(int r, int c) {
    return board[r][c] == -1;
  }

  private static int[][] readGrid(BufferedReader br) throws IOException {
    int[][] g = new int[N][N];
    for (int i = 0; i < N; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        g[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    return g;
  }

  private static int[] readPair(BufferedReader br) throws IOException {
    StringTokenizer st = new StringTokenizer(br.readLine());
    return new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
  }
}
