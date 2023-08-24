/**
 *
 * URL : https://www.acmicpc.net/problem/10953
 *
 * A+B - 6
 *
 * 문제
 * 두 정수 A와 B를 입력받은 다음, A+B를 출력하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 테스트 케이스의 개수 T가 주어진다.
 *
 * 각 테스트 케이스는 한 줄로 이루어져 있으며, 각 줄에 A와 B가 주어진다. A와 B는 콤마(,)로 구분되어 있다. (0 < A, B < 10)
 *
 * 출력
 * 각 테스트 케이스마다 A+B를 출력한다.
 *
 * 예제 입력 1
 * 5
 * 1,1
 * 2,3
 * 3,4
 * 9,8
 * 5,2
 * 예제 출력 1
 * 2
 * 5
 * 7
 * 17
 * 7
 */

package bronze;

import java.io.*;

public class P10953 {
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

  public static void main(String[] args) throws IOException {
      new P10953().solution();
  }

  public void solution() throws IOException {
    int T = Integer.parseInt(br.readLine());
    while (T-- > 0) {
      String[] str = br.readLine().split(",");
      bw.write((Integer.parseInt(str[0]) + Integer.parseInt(str[1])) + System.lineSeparator());
    }
    bw.flush();

    bw.close();
    br.close();
  }
}
