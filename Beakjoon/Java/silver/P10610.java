/**
 *
 * URL : https://www.acmicpc.net/problem/10610
 *
 * 30
 *
 * 문제
 * 어느 날, 미르코는 우연히 길거리에서 양수 N을 보았다. 미르코는 30이란 수를 존경하기 때문에, 그는 길거리에서 찾은 수에 포함된 숫자들을 섞어 30의 배수가 되는 가장 큰 수를 만들고 싶어한다.
 *
 * 미르코를 도와 그가 만들고 싶어하는 수를 계산하는 프로그램을 작성하라.
 *
 * 입력
 * N을 입력받는다. N는 최대 105개의 숫자로 구성되어 있으며, 0으로 시작하지 않는다.
 *
 * 출력
 * 미르코가 만들고 싶어하는 수가 존재한다면 그 수를 출력하라. 그 수가 존재하지 않는다면, -1을 출력하라.
 *
 * 예제 입력 1
 * 30
 * 예제 출력 1
 * 30
 * 예제 입력 2
 * 102
 * 예제 출력 2
 * 210
 * 예제 입력 3
 * 2931
 * 예제 출력 3
 * -1
 * 예제 입력 4
 * 80875542
 * 예제 출력 4
 * 88755420
 */

package silver;

import java.io.*;
import java.util.Arrays;

public class P10610 {
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

  public static void main(String[] args) throws IOException {
      new P10610().solution();
  }

  public void solution() throws IOException {
    String st = br.readLine();
    char[] numArray = st.toCharArray();
    StringBuffer sb = new StringBuffer();

    Arrays.sort(numArray);

    int total = 0;
    for (int i = numArray.length - 1; i >= 0; i--) {
      int num = numArray[i] - 48;
      total += num;
      sb.append(num);
    }

    if (numArray[0] != '0' || total % 3 != 0) {
      bw.write("-1");
    } else {
      bw.write(sb.toString());
    }

    bw.close();
    br.close();
  }
}
