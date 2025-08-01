import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int N = Integer.parseInt(st.nextToken());
      int d = Integer.parseInt(st.nextToken());
      int k = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());

      int[] belt = new int[N];
      for (int i = 0; i < N; i++) {
        belt[i] = Integer.parseInt(br.readLine());
      }

      int[] count = new int[d + 1];
      int unique = 0;
      for (int i = 0; i < k; i++) {
        if (count[belt[i]] == 0) {
          unique++;
        }
        count[belt[i]]++;
      }
      int max = unique;
      if (count[c] == 0) {
        max++;
      }

      for (int i = k; i < N + k; i++) {
        int remove = belt[(i - k) % N];
        int add = belt[i % N];

        count[remove]--;
        if (count[remove] == 0) {
          unique--;
        }
        if (count[add] == 0) {
          unique++;
        }
        count[add]++;

        int current = unique;
        if (count[c] == 0) {
          current++;
        }
        max = Math.max(max, current);
      }
      System.out.println(max);
    }
  }
}
