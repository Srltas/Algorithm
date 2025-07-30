import java.io.*;

public class Main {
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

  boolean isChange = false;

  public static void main(String[] args) throws IOException {
      new Main().solution();
  }

  public void solution() throws IOException {
    String S = br.readLine();
    StringBuffer T = new StringBuffer(br.readLine());

    check(S, T);

    bw.write(isChange ? "1" : "0");
    bw.flush();

    bw.close();
    br.close();
  }

  private void check(String S, StringBuffer T) {
    if (S.length() >= T.length()) {
      if (S.equals(T.toString())) {
        isChange = true;
      }
      return;
    }

    StringBuffer aT = new StringBuffer(T);
    if (aT.charAt(aT.length() - 1) == 'A') {
      check(S, aT.deleteCharAt(aT.length() - 1));
    }

    StringBuffer bT = new StringBuffer(T);
    if (bT.reverse().charAt(bT.length() - 1) == 'B') {
      check(S, bT.deleteCharAt(bT.length() - 1));
    }
  }
}
