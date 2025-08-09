import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

  private static int N, M;
  private static int[] path;
  private static StringBuilder sb = new StringBuilder();

  public static void main(String[] args) throws IOException {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());

      path = new int[M];
      dfs(0, 1);
      System.out.println(sb);
    }
  }

  private static void dfs(int depth, int start) {
    if (depth == M) {
      for (int i = 0; i < M; i++) {
        if (i > 0) sb.append(' ');
        sb.append(path[i]);
      }
      sb.append('\n');
      return;
    }

    int limit = N - (M - depth) + 1;
    for (int i = start; i <= limit; i++) {
      path[depth] = i;
      dfs(depth + 1, i + 1);
    }
  }
}
