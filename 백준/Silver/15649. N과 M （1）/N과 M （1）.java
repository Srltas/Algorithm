import javax.swing.plaf.ViewportUI;
import java.io.*;
import java.util.StringTokenizer;

public class Main {

  private static int N, M;
  private static int[] array;
  private static boolean[] visited;
  private static StringBuilder out = new StringBuilder();

  public static void main(String[] args) throws IOException {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());

      array = new int[M];
      visited = new boolean[N + 1];
      
      dfs(0);

      System.out.println(out);
    }
  }

  private static void dfs(int depth) {
    if (depth == M) {
      for (int i = 0; i < M; i++) {
        out.append(array[i]).append(' ');
      }
      out.append('\n');
      return;
    }

    for (int i = 1; i <= N; i++) {
      if (visited[i]) continue;
      visited[i] = true;
      array[depth] = i;
      dfs(depth + 1);
      visited[i] = false;
    }
  }
}
