import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

  static int N;
  static int[] S, B;
  static int answer = Integer.MAX_VALUE;

  public static void main(String[] args) throws IOException {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      N = Integer.parseInt(br.readLine());
      S = new int[N];
      B = new int[N];
      for (int i = 0; i < N; i++) {
        StringTokenizer st = new StringTokenizer(br.readLine());
        S[i] = Integer.parseInt(st.nextToken());
        B[i] = Integer.parseInt(st.nextToken());
      }

      dfs(0, 1, 0, 0);
      System.out.println(answer);
    }
  }

  static void dfs(int index, long s, long b, int count) {
    if (index == N) {
      if (count > 0) {
        answer = Math.min(answer, (int)Math.abs(s - b));
      }
      return;
    }

    dfs(index + 1, s * S[index], b + B[index], count + 1);
    dfs(index + 1, s, b, count);
  }
}
