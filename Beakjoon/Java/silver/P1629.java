/**
 *
 * URL : https://www.acmicpc.net/problem/1629
 *
 * 곱셈
 *
 * 문제
 * 자연수 A를 B번 곱한 수를 알고 싶다. 단 구하려는 수가 매우 커질 수 있으므로 이를 C로 나눈 나머지를 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 A, B, C가 빈 칸을 사이에 두고 순서대로 주어진다. A, B, C는 모두 2,147,483,647 이하의 자연수이다.
 *
 * 출력
 * 첫째 줄에 A를 B번 곱한 수를 C로 나눈 나머지를 출력한다.
 *
 * 예제 입력 1
 * 10 11 12
 * 예제 출력 1
 * 4
 */

package silver;

import java.io.*;
import java.util.StringTokenizer;

public class P1629 {
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  StringTokenizer st;

  public static void main(String[] args) throws IOException {
      new P1629().solution();
  }

  public void solution() throws IOException {
    st = new StringTokenizer(br.readLine());
    long A = Long.parseLong(st.nextToken());
    long B = Long.parseLong(st.nextToken());
    long C = Long.parseLong(st.nextToken());

    bw.write(DFS(A, B, C) + System.lineSeparator());
    bw.flush();

    bw.close();
    br.close();
  }

  private long DFS(long A, long B, long C) {
    if (B == 0) {
      return 1;
    }

    long value = DFS(A, B / 2, C);

    if (B % 2 == 0) {
      return value * value % C;
    } else {
      return (value * value % C) * A % C;
    }
  }
}
