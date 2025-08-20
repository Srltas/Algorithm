import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

  static int N;
  static int[][] board;
  static boolean[][] visited;
  static int min = Integer.MAX_VALUE;

  static int[] dr = {0, 0, 0, 1, -1};
  static int[] dc = {0, 1, -1, 0, 0};

  public static void main(String[] args) throws IOException {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      N = Integer.parseInt(br.readLine());
      board = new int[N][N];
      visited = new boolean[N][N];

      for (int i = 0; i < N; i++) {
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int j = 0; j < N; j++) {
          board[i][j] = Integer.parseInt(st.nextToken());
        }
      }

      dfs(0, 0, 0);
      System.out.println(min);
    }
  }

  static void dfs(int count, int start, int sum) {
    if (count == 3) {
      min = Math.min(min, sum);
      return;
    }

    int W = N - 2;
    for (int idx = start; idx < W * W; idx++) {
      int r = idx / W + 1;
      int c = idx % W + 1;

      int cost = costIfPlace(r, c);
      if (cost < 0) continue;

      mark(r, c, true);
      dfs(count + 1, idx + 1, sum + cost);
      mark(r, c, false);
    }
  }

  static int costIfPlace(int r, int c) {
    int total = 0;
    for (int k = 0; k < 5; k++) {
      int nr = r + dr[k];
      int nc = c + dc[k];
      if (nr < 0 || nr >= N || nc < 0 || nc >= N) return -1;
      if (visited[nr][nc]) return -1;
      total += board[nr][nc];
    }
    return total;
  }

  static void mark(int r, int c, boolean flag) {
    for (int k = 0; k < 5; k++) {
      int nr = r + dr[k];
      int nc = c + dc[k];
      visited[nr][nc] = flag;
    }
  }
}
