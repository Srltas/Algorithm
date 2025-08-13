import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

  static int N, S;
  static int[] path;
  static int[] numbers;
  static int count;

  public static void main(String[] args) throws IOException {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      S = Integer.parseInt(st.nextToken());

      path = new int[N];
      numbers = new int[N];

      st = new StringTokenizer(br.readLine());
      for (int i = 0; i < N; i++) numbers[i] = Integer.parseInt(st.nextToken());

      dfs(0, 0);
      if (S == 0) count--; // 공집합 제외가 중요한 포인트
      System.out.println(count);
    }
  }

  static void dfs(int index, int sum) {
    if (index == N) {
      if (sum == S) count++;
      return;
    }
    dfs(index + 1, sum + numbers[index]);
    dfs(index + 1, sum);
  }
}
