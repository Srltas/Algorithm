import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      StringTokenizer st = new StringTokenizer(br.readLine());

      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());

      int b = n - (m - 2);
      StringBuilder sb = new StringBuilder();

      for (int i = 0; i < b - 1; i++) {
        sb.append(i).append(' ').append(i + 1).append('\n');
      }

      for (int i = b; i < n; i++) {
        sb.append(1).append(' ').append(i).append('\n');
      }
      System.out.println(sb);
    }
  }
}
