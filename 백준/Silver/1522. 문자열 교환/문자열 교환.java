import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  public static void main(String[] args) throws IOException {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      String st = br.readLine();
      int n = st.length();

      int windows = 0;
      for (int i = 0; i < n; i++) {
        if (st.charAt(i) == 'a') {
          windows++;
        }
      }

      if (windows == 0 || windows == n) {
        System.out.println(0);
        return;
      }

      String doubled = st + st;
      int bCount = 0;
      for (int i = 0; i < windows; i++) {
        if (doubled.charAt(i) == 'b') {
          bCount++;
        }
      }

      int minChange = bCount;
      for (int i = windows; i < n + windows; i++) {
        if (doubled.charAt(i - windows) == 'b') {
          bCount--;
        }
        if (doubled.charAt(i) == 'b') {
          bCount++;
        }
        minChange = Math.min(minChange, bCount);
      }
      System.out.println(minChange);
    }
  }
}
