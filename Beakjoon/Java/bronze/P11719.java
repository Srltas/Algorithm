/**
 *
 * URL : https://www.acmicpc.net/problem/11719
 *
 * 그대로 출력하기 2
 *
 * 문제
 * 입력 받은 대로 출력하는 프로그램을 작성하시오.
 *
 * 입력
 * 입력이 주어진다. 입력은 최대 100줄로 이루어져 있고, 알파벳 소문자, 대문자, 공백, 숫자로만 이루어져 있다. 각 줄은 100글자를 넘지 않으며, 빈 줄이 주어질 수도 있고, 각 줄의 앞 뒤에 공백이 있을 수도 있다.
 *
 * 출력
 * 입력받은 그대로 출력한다.
 *
 * 예제 입력 1
 *     Hello
 *
 * Baekjoon
 *    Online Judge
 * 예제 출력 1
 *     Hello
 *
 * Baekjoon
 *    Online Judge
 */

package bronze;

import java.io.*;

public class P11719 {
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

  public static void main(String[] args) throws IOException {
      new P11719().solution();
  }

  public void solution() throws IOException {
    StringBuffer sb = new StringBuffer();
    String str = null;
    while ((str = br.readLine()) != null) {
      sb.append(str);
      sb.append(System.lineSeparator());
    }
    bw.write(sb.toString());
    bw.flush();

    bw.close();
    br.close();
  }
}
