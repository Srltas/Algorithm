import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

  static int N;
  static int[][] S;
  static boolean[] visited;
  static int best = Integer.MAX_VALUE;

  public static void main(String[] args) throws IOException {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      N = Integer.parseInt(br.readLine());

      S = new int[N][N];
      for (int i = 0; i < N; i++) {
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int j = 0; j < N; j++) {
          S[i][j] = Integer.parseInt(st.nextToken());
        }
      }

      visited = new boolean[N];
      visited[0] = true;
      dfs(1, 1);

      System.out.println(best);
    }
  }

  static void dfs(int index, int startIndex) {
    if (index == N / 2) {
      int diff = diffOfTeams();
      best = Math.min(best, diff);
      return;
    }

    int limited = N - ((N / 2) - index) + 1;
    for (int i = startIndex; i < limited; i++) {
      visited[i] = true;
      dfs(index + 1, i + 1);
      visited[i] = false;

      if (best == 0) return;
    }
  }

  static int diffOfTeams() {
    int A = 0, B = 0;
    for (int i = 0; i < N; i++) {
      for (int j = i + 1; j < N; j++) {
        int v = S[i][j] + S[j][i];
        if (visited[i] && visited[j]) A += v;
        else if (!visited[i] && !visited[j]) B += v;
      }
    }
    return Math.abs(A - B);
  }
}
