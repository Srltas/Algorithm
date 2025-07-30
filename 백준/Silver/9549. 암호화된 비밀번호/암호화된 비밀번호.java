import java.io.*;

public class Main {
  public static void main(String[] args) throws IOException {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

      int T = Integer.parseInt(br.readLine());

      while (T-- > 0) {
        String encoding = br.readLine();
        String raw = br.readLine();

        int windowSize = raw.length();
        int[] patternCount = getCharFrequency(raw);
        int[] windowCount = getCharFrequency(encoding.substring(0, windowSize));

        boolean found = false;
        if (isSame(patternCount, windowCount)) {
          found = true;
        } else {
          for (int i = windowSize; i < encoding.length(); i++) {
            windowCount[encoding.charAt(i - windowSize) - 'a']--;
            windowCount[encoding.charAt(i) - 'a']++;
            if (isSame(patternCount, windowCount)) {
              found = true;
              break;
            }
          }
        }
        bw.write(found ? "YES" : "NO");
        bw.write(System.lineSeparator());
      }
      bw.flush();
    }
  }

  private static int[] getCharFrequency(String str) {
    int[] freq = new int[26];
    for (char c : str.toCharArray()) {
      freq[c - 'a'] += 1;
    }
    return freq;
  }

  private static boolean isSame(int[] a, int[] b) {
    for (int i = 0; i < 26; i++) {
      if (a[i] != b[i]) {
        return false;
      }
    }
    return true;
  }
}