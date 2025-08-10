import java.io.*;
import java.util.StringTokenizer;

public class Main {

  static int N, M;
  static int[] path;
  static String[] num;
  static StringBuilder sb = new StringBuilder();

  public static void main(String[] args) throws IOException {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());

      path = new int[M];
      num = new String[N + 1];
      for (int i = 1; i <= N; i++) {
        num[i] = Integer.toString(i);
      }
      dfs(0);
      System.out.println(sb);
    }
  }

  static void dfs(int depth) throws IOException {
    if (depth == M) {
      for (int p : path) {
        sb.append(p).append(" ");
      }
      sb.append("\n");
      return;
    }

    for (int i = 1; i <= N; i++) {
      path[depth] = i;
      dfs(depth + 1);
    }
  }
}
