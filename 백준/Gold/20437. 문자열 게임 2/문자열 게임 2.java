import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
  public static void main(String[] args) throws IOException {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      int T = Integer.parseInt(br.readLine());

      while (T-- > 0) {
        String str = br.readLine();
        int K = Integer.parseInt(br.readLine());

        int minLen = Integer.MAX_VALUE;
        int maxLen = -1;

        List<Integer>[] charIndexes = new ArrayList[26];
        for (int i = 0; i < 26; i++) {
          charIndexes[i] = new ArrayList<>();
        }

        for (int i = 0; i < str.length(); i++) {
          char c = str.charAt(i);
          charIndexes[c - 'a'].add(i);
        }

        for (int i = 0; i < 26; i++) {
          List<Integer> list = charIndexes[i];
          if (list.size() < K) {
            continue;
          }

          for (int j = 0; j <= list.size() - K; j++) {
            int start = list.get(j);
            int end = list.get(j + K - 1);
            int length = end - start + 1;

            minLen = Math.min(minLen, length);
            if (str.charAt(start) == str.charAt(end)) {
              maxLen = Math.max(maxLen, length);
            }
          }
        }

        if (minLen == Integer.MAX_VALUE || maxLen == -1) {
          System.out.println("-1");
        } else {
          System.out.println(minLen + " " + maxLen);
        }
      }
    }
  }
}
