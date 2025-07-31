import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      int N = Integer.parseInt(br.readLine());

      int[] prevMax = new int[3];
      int[] prevMin = new int[3];
      int[] currMax = new int[3];
      int[] currMin = new int[3];

      int[] input = new int[3];
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int i = 0; i < 3; i++) {
        input[i] = Integer.parseInt(st.nextToken());
        prevMax[i] = input[i];
        prevMin[i] = input[i];
      }

      for (int line = 1; line < N; line++) {
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 3; i++) {
          input[i] = Integer.parseInt(st.nextToken());
        }

        currMax[0] = input[0] + Math.max(prevMax[0], prevMax[1]);
        currMin[0] = input[0] + Math.min(prevMin[0], prevMin[1]);

        currMax[1] = input[1] + Math.max(prevMax[0], Math.max(prevMax[1], prevMax[2]));
        currMin[1] = input[1] + Math.min(prevMin[0], Math.min(prevMin[1], prevMin[2]));

        currMax[2] = input[2] + Math.max(prevMax[1], prevMax[2]);
        currMin[2] = input[2] + Math.min(prevMin[1], prevMin[2]);

        System.arraycopy(currMax, 0, prevMax, 0, 3);
        System.arraycopy(currMin, 0, prevMin, 0, 3);
      }

      int max = Math.max(prevMax[0], Math.max(prevMax[1], prevMax[2]));
      int min = Math.min(prevMin[0], Math.min(prevMin[1], prevMin[2]));

      System.out.println(max + " " + min);
    }
  }
}
