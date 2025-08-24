import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

  static int k;
  static char[] sign;
  static boolean[] used;
  static String max, min;

  public static void main(String[] args) throws IOException {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      k = Integer.parseInt(br.readLine());
      sign = new char[k];
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int i = 0; i < k; i++) {
        sign[i] = st.nextToken().charAt(0);
      }

      used = new boolean[10];
      dfs(0, new StringBuilder(), 0);
      System.out.println(max);
      System.out.println(min);
    }
  }

  static void dfs(int depth, StringBuilder sb, int prev) {
    if (depth == k + 1) {
      String st = sb.toString();
      if (max == null || st.compareTo(max) > 0) max = st;
      if (min == null || st.compareTo(min) < 0) min = st;
      return;
    }

    for (int i = 0; i <= 9; i++) {
      if (used[i]) continue;
      if (depth > 0) {
        char s = sign[depth - 1];
        if (s == '<' && !(prev < i)) continue;
        if (s == '>' && !(prev > i)) continue;
      }

      used[i] = true;
      dfs(depth + 1, sb.append(i), i);
      sb.deleteCharAt(sb.length() - 1);
      used[i] = false;
    }
  }
}
