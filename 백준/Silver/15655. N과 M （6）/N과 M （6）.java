import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

  static int N, M;
  static int[] path;
  static int[] numbers;
  static StringBuilder sb = new StringBuilder();

  public static void main(String[] args) throws IOException {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());

      path = new int[M];
      numbers = new int[N];
      st = new StringTokenizer(br.readLine());
      for (int i = 0; i < N; i++) numbers[i] = Integer.parseInt(st.nextToken());
      Arrays.sort(numbers);
      dfs(0, 0);
      System.out.println(sb);
    }
  }

  static void dfs(int depth, int start) throws IOException {
    if (depth == M) {
      for (int p : path) {
        sb.append(p).append(" ");
      }
      sb.append("\n");
      return;
    }

    int limit = N - (M - depth) + 1;
    for (int i = start; i < limit; i++) {
      path[depth] = numbers[i];
      dfs(depth + 1, i + 1);
    }
  }
}
