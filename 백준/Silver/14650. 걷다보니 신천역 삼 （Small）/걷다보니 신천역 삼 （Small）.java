import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

  static int N;
  static int[] number = {0, 1, 2};
  static int count;

  public static void main(String[] args) throws IOException {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      N = Integer.parseInt(br.readLine());
      dfs(0, 0);
      System.out.println(count);
    }
  }

  static void dfs(int depth, int sum) {
    if (depth == N) {
      if (sum % 3 == 0) {
        count++;
      }
      return;
    }

    for (int i = 0; i < 3; i++) {
      if (depth == 0 && number[i] == 0) continue;
      dfs(depth + 1, sum * 10 + number[i]);
    }
  }
}
