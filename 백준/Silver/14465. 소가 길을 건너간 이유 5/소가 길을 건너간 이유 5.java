import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int N = Integer.parseInt(st.nextToken());
      int K = Integer.parseInt(st.nextToken());
      int B = Integer.parseInt(st.nextToken());

      boolean[] broken = new boolean[N + 1];
      for (int i = 0; i < B; i++) {
        int index = Integer.parseInt(br.readLine());
        broken[index] = true;
      }

      int brokenCount = 0;
      for (int i = 1; i <= K; i++) {
        if (broken[i]) {
          brokenCount++;
        }
      }

      int minFix = brokenCount;
      for (int i = 2; i <= N - K + 1; i++) {
        if (broken[i + K - 1]) {
          brokenCount++;
        }
        if (broken[i - 1]) {
          brokenCount--;
        }
        minFix = Math.min(minFix, brokenCount);
      }
      System.out.println(minFix);
    }
  }
}