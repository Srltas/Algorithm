import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int N = Integer.parseInt(st.nextToken());
      int K = Integer.parseInt(st.nextToken());

      List<Integer> lions = new ArrayList<>();
      st = new StringTokenizer(br.readLine());
      for (int i = 0; i < N; i++) {
        String doll = st.nextToken();
        if (doll.equals("1")) {
          lions.add(i);
        }
      }

      if (lions.size() < K) {
        System.out.println(-1);
        return;
      }

      int minLength = Integer.MAX_VALUE;
      for (int i = 0; i <= lions.size() - K; i++) {
        int start = lions.get(i);
        int end = lions.get(i + K - 1);
        minLength = Math.min(minLength, end - start + 1);
      }
      System.out.println(minLength);
    }
  }
}
