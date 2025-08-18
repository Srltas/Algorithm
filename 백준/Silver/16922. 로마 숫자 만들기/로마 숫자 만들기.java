import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

  static int[] number = {1, 5, 10, 50};
  static int N;
  static boolean[] seen;

  public static void main(String[] args) throws IOException {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      N = Integer.parseInt(br.readLine());
      seen = new boolean[50 * N + 1];

      dfs(0 , 0, 0);

      int count = 0;
      for (boolean b : seen) if (b) count++;
      System.out.println(count);
    }
  }

  static void dfs(int depth, int start, int sum) {
    if (depth == N) {
      seen[sum] = true;
      return;
    }

    for (int i = start; i < 4; i++) {
      dfs(depth + 1, i, sum + number[i]);
    }
  }
}
