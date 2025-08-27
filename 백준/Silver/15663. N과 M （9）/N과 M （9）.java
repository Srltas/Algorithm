import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

  static int N, M;
  static int[] path;
  static int[] numbers;
  static boolean[] visited;
  static StringBuilder sb = new StringBuilder();

  public static void main(String[] args) throws IOException {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());

      path = new int[M];
      numbers = new int[N];
      visited = new boolean[N];
      st = new StringTokenizer(br.readLine());
      for (int i = 0; i < N; i++) numbers[i] = Integer.parseInt(st.nextToken());
      Arrays.sort(numbers);
      dfs(0);
      System.out.println(sb);
    }
  }

  static void dfs(int depth) {
    if (depth == M) {
      for (int p : path) {
        sb.append(p).append(" ");
      }
      sb.append("\n");
      return;
    }

    int prev = Integer.MIN_VALUE;
    for (int i = 0; i < N; i++) {
      if (visited[i]) continue;
      if (numbers[i] == prev) continue;

      visited[i] = true;
      path[depth] = numbers[i];
      prev = numbers[i];
      dfs(depth + 1);
      visited[i] = false;
    }
  }
}
