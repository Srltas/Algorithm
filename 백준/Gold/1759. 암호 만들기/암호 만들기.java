import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

  static int L, C;
  static String[] alphabet;
  static StringBuilder sb = new StringBuilder();

  public static void main(String[] args) throws IOException {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      L = Integer.parseInt(st.nextToken());
      C = Integer.parseInt(st.nextToken());

      alphabet = new String[C];
      st = new StringTokenizer(br.readLine());
      for (int i = 0; i < C; i++) {
        alphabet[i] = st.nextToken();
      }

      Arrays.sort(alphabet);
      dfs(0,0,new StringBuilder(L));
      System.out.println(sb);
    }
  }

  static void dfs(int depth, int start, StringBuilder path) {
    if (depth == L) {
      if (isValid(path)) sb.append(path).append('\n');
      return;
    }

    for (int i = start; i < C; i++) {
      path.append(alphabet[i]);
      dfs(depth + 1, i + 1, path);
      path.deleteCharAt(path.length() - 1);
    }
  }

  static boolean isValid(StringBuilder str) {
    int v = 0, c = 0;
    for (int i = 0; i < str.length(); i++) {
      char ch = str.charAt(i);
      if (isVowel(ch)) v++;
      else c++;
    }
    return v >= 1 && c >= 2;
  }

  static boolean isVowel(char ch) {
    return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
  }
}
