import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

  static int N;
  static int[][] city;
  static boolean[] visited;
  static int min = Integer.MAX_VALUE;

  public static void main(String[] args) throws IOException {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      N = Integer.parseInt(br.readLine());
      city = new int[N][N];
      for (int i = 0; i < N; i++) {
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int j = 0; j < N; j++) {
          city[i][j] = Integer.parseInt(st.nextToken());
        }
      }

      visited = new boolean[N];
      visited[0] = true;
      dfs(1, 0, 0);
      System.out.println(min);
    }
  }

  static void dfs(int depth, int cost, int curr) {
    if (cost >= min) return;

    if (depth == N) {
      if (city[curr][0] > 0) {
        min = Math.min(min, cost + city[curr][0]);
      }
      return;
    }

    for (int next = 1; next < N; next++) {
      if (visited[next]) continue;
      if (city[curr][next] == 0) continue;

      visited[next] = true;
      dfs(depth + 1, cost + city[curr][next], next);
      visited[next] = false;
    }
  }
}
