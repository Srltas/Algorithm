/**
 *
 * URL : https://www.acmicpc.net/problem/1026
 *
 * 보물
 *
 * 문제
 * 옛날 옛적에 수학이 항상 큰 골칫거리였던 나라가 있었다. 이 나라의 국왕 김지민은 다음과 같은 문제를 내고 큰 상금을 걸었다.
 *
 * 길이가 N인 정수 배열 A와 B가 있다. 다음과 같이 함수 S를 정의하자.
 *
 * S = A[0] × B[0] + ... + A[N-1] × B[N-1]
 *
 * S의 값을 가장 작게 만들기 위해 A의 수를 재배열하자. 단, B에 있는 수는 재배열하면 안 된다.
 *
 * S의 최솟값을 출력하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 N이 주어진다. 둘째 줄에는 A에 있는 N개의 수가 순서대로 주어지고, 셋째 줄에는 B에 있는 수가 순서대로 주어진다. N은 50보다 작거나 같은 자연수이고, A와 B의 각 원소는 100보다 작거나 같은 음이 아닌 정수이다.
 *
 * 출력
 * 첫째 줄에 S의 최솟값을 출력한다.
 *
 * 예제 입력 1
 * 5
 * 1 1 1 6 0
 * 2 7 8 3 1
 * 예제 출력 1
 * 18
 * 예제 입력 2
 * 3
 * 1 1 3
 * 10 30 20
 * 예제 출력 2
 * 80
 * 예제 입력 3
 * 9
 * 5 15 100 31 39 0 0 3 26
 * 11 12 13 2 3 4 5 9 1
 * 예제 출력 3
 * 528
 */

package silver;

import java.io.*;
import java.util.*;

public class P1026 {
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  StringTokenizer st;

  public static void main(String[] args) throws IOException {
      new P1026().solution();
  }

  public void solution() throws IOException {
    int N = Integer.parseInt(br.readLine());
    Integer[] A = new Integer[N];
    Integer[] B = new Integer[N];

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      A[i] = Integer.parseInt(st.nextToken());
    }

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      B[i] = Integer.parseInt(st.nextToken());
    }

    Arrays.sort(A);
    Arrays.sort(B, Collections.reverseOrder());

    int result = 0;
    for (int i = 0; i < N; i++) {
      result += A[i] * B[i];
    }
    bw.write(result + System.lineSeparator());
    bw.flush();

    bw.close();
    br.close();
  }
}
