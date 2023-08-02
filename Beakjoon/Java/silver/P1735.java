/**
 *
 * URL : https://www.acmicpc.net/problem/1735
 * 
 * 분수 합
 *
 * 문제
 * 분수 A/B는 분자가 A, 분모가 B인 분수를 의미한다. A와 B는 모두 자연수라고 하자.
 *
 * 두 분수의 합 또한 분수로 표현할 수 있다. 두 분수가 주어졌을 때, 그 합을 기약분수의 형태로 구하는 프로그램을 작성하시오. 기약분수란 더 이상 약분되지 않는 분수를 의미한다.
 *
 * 입력
 * 첫째 줄과 둘째 줄에, 각 분수의 분자와 분모를 뜻하는 두 개의 자연수가 순서대로 주어진다. 입력되는 네 자연수는 모두 30,000 이하이다.
 *
 * 출력
 * 첫째 줄에 구하고자 하는 기약분수의 분자와 분모를 뜻하는 두 개의 자연수를 빈 칸을 사이에 두고 순서대로 출력한다.
 *
 * 예제 입력 1
 * 2 7
 * 3 5
 * 예제 출력 1
 * 31 35
 */

package silver;

import java.io.*;
import java.util.StringTokenizer;

public class P1735 {
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  StringTokenizer st;

  public static void main(String[] args) throws IOException {
      new P1735().solution();
  }

  public void solution() throws IOException {
    st = new StringTokenizer(br.readLine());
    int A = Integer.parseInt(st.nextToken());
    int B = Integer.parseInt(st.nextToken());

    st = new StringTokenizer(br.readLine());
    int C = Integer.parseInt(st.nextToken());
    int D = Integer.parseInt(st.nextToken());

    int molecular = A * D + B * C;
    int denominator = B * D;

    int mod = gcd(molecular, denominator);
    molecular /= mod;
    denominator /= mod;

    bw.write(molecular + " " + denominator + System.lineSeparator());
    bw.flush();

    bw.close();
    br.close();
  }

  private int gcd(int a, int b) {
    if (a <= b) {
      int temp = a;
      a = b;
      b = temp;
    }

    if (b == 0) {
      return a;
    }
    return gcd(b, a % b);
  }
}
