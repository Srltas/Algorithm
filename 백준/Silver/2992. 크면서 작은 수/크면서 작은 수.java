import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

  static int x;
  static int n;
  static int[] cnt = new int[10];
  static int max = Integer.MAX_VALUE;

  public static void main(String[] args) throws IOException {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      String str = br.readLine();
      x = Integer.parseInt(str);
      n = str.length();

      for (int i = 0; i < str.length(); i++) {
        cnt[str.charAt(i) - '0']++;
      }

      dfs(0, 0);
      System.out.println(max == Integer.MAX_VALUE ? 0 : max);
    }
  }

  static void dfs(int depth, int cur) {
    if (depth == n) {
      if (cur > x && cur < max) max = cur;
      return;
    }
    for (int i = 0; i <= 9; i++) {
      if (cnt[i] == 0) continue;
      if (depth == 0 && i == 0) continue;

      cnt[i]--;
      dfs(depth + 1, cur * 10 + i);
      cnt[i]++;
    }
  }
}
