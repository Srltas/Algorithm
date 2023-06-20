/**
 *
 * URL : https://www.acmicpc.net/problem/1189
 *
 * 컴백홈
 *
 * 문제
 * 한수는 캠프를 마치고 집에 돌아가려 한다. 한수는 현재 왼쪽 아래점에 있고 집은 오른쪽 위에 있다. 그리고 한수는 집에 돌아가는 방법이 다양하다. 단, 한수는 똑똑하여 한번 지나친 곳을 다시 방문하지는 않는다.
 *
 *       cdef  ...f  ..ef  ..gh  cdeh  cdej  ...f
 *       bT..  .T.e  .Td.  .Tfe  bTfg  bTfi  .Tde
 *       a...  abcd  abc.  abcd  a...  a.gh  abc.
 * 거리 :  6     6     6     8     8    10    6
 * 위 예제는 한수가 집에 돌아갈 수 있는 모든 경우를 나타낸 것이다. T로 표시된 부분은 가지 못하는 부분이다. 문제는 R x C 맵에 못가는 부분이 주어지고 거리 K가 주어지면 한수가 집까지도 도착하는 경우 중 거리가 K인 가짓수를 구하는 것이다.
 *
 * 입력
 * 첫 줄에 정수 R(1 ≤ R ≤ 5), C(1 ≤ C ≤ 5), K(1 ≤ K ≤ R×C)가 공백으로 구분되어 주어진다. 두 번째부터 R+1번째 줄까지는 R×C 맵의 정보를 나타내는 '.'과 'T'로 구성된 길이가 C인 문자열이 주어진다.
 *
 * 출력
 * 첫 줄에 거리가 K인 가짓수를 출력한다.
 *
 * 예제 입력 1
 * 3 4 6
 * ....
 * .T..
 * ....
 * 예제 출력 1
 * 4
 */

package silver;

import java.io.*;
import java.util.StringTokenizer;

public class P1189 {
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  StringTokenizer st;

  int[] dR = {0, 0, -1, 1};
  int[] dC = {-1, 1, 0, 0};

  int R, C, K;
  int totalCount;
  char[][] array;
  boolean[][] visited;

  public static void main(String[] args) throws IOException {
      new P1189().solution();
  }

  public void solution() throws IOException {
    st = new StringTokenizer(br.readLine());
    R = Integer.parseInt(st.nextToken());
    C = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());

    array = new char[R][C];
    visited = new boolean[R][C];
    for (int i = 0; i < R; i++) {
      array[i] = br.readLine().toCharArray();
    }

    DFS(R - 1, 0, 1);
    bw.write(totalCount + System.lineSeparator());
    bw.flush();

    bw.close();
    br.close();
  }

  private void DFS(int r, int c, int count) {
    if (r == 0 && c == C - 1) {
      if (count == K) {
        totalCount++;
      }
      return;
    }

    visited[r][c] = true;

    for (int i = 0; i < 4; i++) {
      int nextR = r + dR[i];
      int nextC = c + dC[i];

      if (nextR < 0 || nextC < 0 || nextR >= R || nextC >= C
          || visited[nextR][nextC] || array[nextR][nextC] == 'T') {
        continue;
      }

      DFS(nextR, nextC, count + 1);
    }
    visited[r][c] = false;
  }
}
