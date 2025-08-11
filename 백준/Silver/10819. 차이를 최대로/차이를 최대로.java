import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

  static int N;
  static int[] path;
  static int[] numbers;
  static boolean[] visited;
  static int max = Integer.MIN_VALUE;

  public static void main(String[] args) throws IOException {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      N = Integer.parseInt(br.readLine());

      path = new int[N];
      numbers = new int[N];
      visited = new boolean[N];

      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int i = 0; i < N ; i++) numbers[i] = Integer.parseInt(st.nextToken());
      dfs(0);
      System.out.println(max);
    }
  }

  static void dfs(int depth) {
    if (depth == N) {
      max = Math.max(max, sum());
      return;
    }

    for (int i = 0; i < N; i++) {
      if (visited[i]) continue;
      visited[i] = true;
      path[depth] = numbers[i];
      dfs(depth + 1);
      visited[i]= false;
    }
  }

  static int sum() {
    int num = 0;
    for (int i = 1; i < N; i++) {
      num += Math.abs(path[i - 1] - path[i]);
    }
    return num;
  }
}
