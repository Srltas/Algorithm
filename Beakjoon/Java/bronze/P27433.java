/**
 *
 * URL : https://www.acmicpc.net/problem/27433
 *
 * 팩토리얼 2
 *
 * 문제
 * 0보다 크거나 같은 정수 N이 주어진다. 이때, N!을 출력하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 정수 N(0 ≤ N ≤ 20)이 주어진다.
 *
 * 출력
 * 첫째 줄에 N!을 출력한다.
 *
 * 예제 입력 1
 * 10
 * 예제 출력 1
 * 3628800
 * 예제 입력 2
 * 0
 * 예제 출력 2
 * 1
 */

package bronze;

import java.io.*;

public class P27433 {
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

  public static void main(String[] args) throws IOException {
      new P27433().solution();
  }

  public void solution() throws IOException {
    int N = Integer.parseInt(br.readLine());

    long result = 1;
    for (int i = 1; i <= N; i++) {
      result *= i;
    }

    bw.write(result + System.lineSeparator());
    bw.flush();

    bw.close();
    br.close();
  }
}
