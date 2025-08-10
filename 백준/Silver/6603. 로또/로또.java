import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

  static final int PATH_RANGE = 6;

  static int N;
  static int[] array, path;
  static StringBuilder sb = new StringBuilder();

  public static void main(String[] args) throws IOException {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());

      while (N != 0) {
        array = new int[N];
        path = new int[PATH_RANGE];
        for (int i = 0; i < N; i++) array[i] = Integer.parseInt(st.nextToken());
        dfs(0, 0);
        sb.append("\n");

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
      }
      System.out.println(sb);
    }
  }

  static void dfs(int depth, int start) {
    if (depth == PATH_RANGE) {
      for (int p : path) {
        sb.append(p).append(" ");
      }
      sb.append("\n");
      return;
    }

    int limited = N - (PATH_RANGE - depth) + 1;
    for (int i = start; i < limited; i++) {
      path[depth] = array[i];
      dfs(depth + 1, i + 1);
    }
  }
}
