import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

  static int N;
  static int[] path;
  static boolean[] visited;
  static StringBuilder sb = new StringBuilder();

  public static void main(String[] args) throws IOException {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      N = Integer.parseInt(br.readLine());

      path = new int[N];
      visited = new boolean[N];

      dfs(0);
      System.out.println(sb);
    }
  }

  static void dfs(int depth) {
    if (depth == N) {
      for (int p : path) {
        sb.append(p).append(' ');
      }
      sb.append("\n");
      return;
    }

    for (int i = 0; i < N; i++) {
      if (visited[i]) continue;
      visited[i] = true;
      path[depth] = i + 1;
      dfs(depth + 1);
      visited[i] = false;
    }
  }
}
