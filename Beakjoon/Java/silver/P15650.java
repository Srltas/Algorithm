/**
 *
 * URL : https://www.acmicpc.net/problem/15650
 *
 * N과 M (2)
 *
 * 문제
 * 자연수 N과 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.
 *
 * 1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열
 * 고른 수열은 오름차순이어야 한다.
 * 입력
 * 첫째 줄에 자연수 N과 M이 주어진다. (1 ≤ M ≤ N ≤ 8)
 *
 * 출력
 * 한 줄에 하나씩 문제의 조건을 만족하는 수열을 출력한다. 중복되는 수열을 여러 번 출력하면 안되며, 각 수열은 공백으로 구분해서 출력해야 한다.
 *
 * 수열은 사전 순으로 증가하는 순서로 출력해야 한다.
 *
 * 예제 입력 1
 * 3 1
 * 예제 출력 1
 * 1
 * 2
 * 3
 * 예제 입력 2
 * 4 2
 * 예제 출력 2
 * 1 2
 * 1 3
 * 1 4
 * 2 3
 * 2 4
 * 3 4
 * 예제 입력 3
 * 4 4
 * 예제 출력 3
 * 1 2 3 4
 */
package silver;

import java.io.*;
import java.util.StringTokenizer;

public class P15650 {
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  StringTokenizer st;

  int N, M;
  int[] array;

  public static void main(String[] args) throws IOException {
      new P15650().solution();
  }

  public void solution() throws IOException {
    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    array = new int[M];
    DFS(1,0);
    bw.flush();

    bw.close();
    br.close();
  }

  private void DFS(int start, int depth) throws IOException {
    if (depth == M) {
      for (int value : array) {
        bw.write(value + " ");
      }
      bw.write(System.lineSeparator());
      return;
    }

    for (int i = start; i <= N; i++) {
      array[depth] = i;
      DFS(i + 1, depth + 1);
    }
  }
}
