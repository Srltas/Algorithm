import java.io.*;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int N = Integer.parseInt(st.nextToken());
      int K = Integer.parseInt(st.nextToken());

      int[] array = new int[N];
      st = new StringTokenizer(br.readLine());
      for (int i = 0; i < N; i++) {
        array[i] = Integer.parseInt(st.nextToken());
      }

      int sum = 0;
      for (int i = 0; i < K; i++) {
        sum += array[i];
      }
      
      int result = sum;
      for (int i = 1; i < N; i++) {
        sum += array[(i + K - 1) % N] - array[(i - 1) % N];
        result = Math.max(result, sum);
      }
      System.out.println(result);
    }
  }
}