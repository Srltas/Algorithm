/**
 *
 * URL : https://www.acmicpc.net/problem/2864
 *
 * 5와 6의 차이
 *
 * 문제
 * 상근이는 2863번에서 표를 너무 열심히 돌린 나머지 5와 6을 헷갈리기 시작했다.
 *
 * 상근이가 숫자 5를 볼 때, 5로 볼 때도 있지만, 6으로 잘못 볼 수도 있고, 6을 볼 때는, 6으로 볼 때도 있지만, 5로 잘못 볼 수도 있다.
 *
 * 두 수 A와 B가 주어졌을 때, 상근이는 이 두 수를 더하려고 한다. 이때, 상근이가 구할 수 있는 두 수의 가능한 합 중, 최솟값과 최댓값을 구해 출력하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 두 정수 A와 B가 주어진다. (1 <= A,B <= 1,000,000)
 *
 * 출력
 * 첫째 줄에 상근이가 구할 수 있는 두 수의 합 중 최솟값과 최댓값을 출력한다.
 *
 * 예제 입력 1
 * 11 25
 * 예제 출력 1
 * 36 37
 * 예제 입력 2
 * 1430 4862
 * 예제 출력 2
 * 6282 6292
 * 예제 입력 3
 * 16796 58786
 * 예제 출력 3
 * 74580 85582
 */

package bronze;

import java.io.*;
import java.util.StringTokenizer;

public class P2864 {
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  StringTokenizer st;

  public static void main(String[] args) throws IOException {
      new P2864().solution();
  }

  public void solution() throws IOException {
    st = new StringTokenizer(br.readLine());
    String strA = st.nextToken();
    String strB = st.nextToken();

    int minA = Integer.parseInt(strA.replaceAll("6", "5"));
    int minB = Integer.parseInt(strB.replaceAll("6", "5"));

    int maxA = Integer.parseInt(strA.replaceAll("5", "6"));
    int maxB = Integer.parseInt(strB.replaceAll("5", "6"));

    bw.write((minA + minB) + " " + (maxA + maxB));
    bw.flush();

    bw.close();
    br.close();
  }
}
