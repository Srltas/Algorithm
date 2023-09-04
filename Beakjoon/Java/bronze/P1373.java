/**
 *
 * URL : https://www.acmicpc.net/problem/1373
 *
 * 2진수 8진수
 *
 * 문제
 * 2진수가 주어졌을 때, 8진수로 변환하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 2진수가 주어진다. 주어지는 수의 길이는 1,000,000을 넘지 않는다.
 *
 * 출력
 * 첫째 줄에 주어진 수를 8진수로 변환하여 출력한다.
 *
 * 예제 입력 1
 * 11001100
 * 예제 출력 1
 * 314
 */

package bronze;

import java.io.*;
import java.math.BigInteger;

public class P1373 {
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

  public static void main(String[] args) throws IOException {
      new P1373().solution();
  }

  public void solution() throws IOException {
    String S = br.readLine();
    bw.write(new BigInteger(S, 2).toString(8) + System.lineSeparator());
    bw.flush();

    bw.close();
    br.close();
  }
}
