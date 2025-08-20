import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

  static int N, K;
  static int[] A;
  static boolean[] visited;
  static int count;

  public static void main(String[] args) throws IOException {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      K = Integer.parseInt(st.nextToken());

      A = new int[N];
      visited = new boolean[N];

      st = new StringTokenizer(br.readLine());
      for (int i = 0; i < N; i++) {
        A[i] = Integer.parseInt(st.nextToken());
      }
    }

    dfs(0, 500);
    System.out.println(count);
  }

  static void dfs(int depth, int sum) {
    if (depth == N) {
      count++;
      return;
    }

    for (int i = 0; i < N; i++) {
      if (visited[i]) continue;
      visited[i] = true;

      int next = sum - K + A[i];
      if (next >= 500) {
        dfs(depth + 1, next);
      }
      visited[i] = false;
    }
  }
}
