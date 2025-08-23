import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

  static int N;
  static int[] numbers;
  static int[] operator = new int[4]; // +, -, *, /
  static int max = Integer.MIN_VALUE;
  static int min = Integer.MAX_VALUE;

  public static void main(String[] args) throws IOException {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      N = Integer.parseInt(br.readLine());
      numbers = new int[N];

      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int i = 0; i < N; i++) {
        numbers[i] = Integer.parseInt(st.nextToken());
      }

      st = new StringTokenizer(br.readLine());
      for (int i = 0; i < 4; i++) {
        operator[i] = Integer.parseInt(st.nextToken());
      }

      dfs(1, numbers[0]);
      System.out.println(max);
      System.out.println(min);
    }
  }

  static void dfs(int idx, int acc) {
    if (idx == N) {
      if (acc > max) max = acc;
      if (acc < min) min = acc;
      return;
    }

    int next = numbers[idx];

    if (operator[0] > 0) {
      operator[0]--;
      dfs(idx + 1, acc + next);
      operator[0]++;
    }

    if (operator[1] > 0) {
      operator[1]--;
      dfs(idx + 1, acc - next);
      operator[1]++;
    }

    if (operator[2] > 0) {
      operator[2]--;
      dfs(idx + 1, acc * next);
      operator[2]++;
    }

    if (operator[3] > 0) {
      operator[3]--;
      dfs(idx + 1, acc / next);
      operator[3]++;
    }
  }
}
