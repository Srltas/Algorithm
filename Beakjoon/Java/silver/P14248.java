/**
 *
 * URL : https://www.acmicpc.net/problem/14248
 *
 * 점프 점프
 *
 * 문제
 * 영우는 개구리다 개굴개굴개굴
 *
 * 영우는 지금 n개의 돌이 일렬로 놓여있는 돌다리에 있다. 그리고 돌다리의 돌에는 숫자가 하나씩 적혀있다. 영우는 이 숫자가 적혀있는 만큼 왼쪽이나 오른쪽으로 점프할 수 있는데, 이때 돌다리 밖으로 나갈 수는 없다.
 *
 * 영우는 이 돌다리에서 자기가 방문 가능한 돌들의 개수를 알고 싶어한다. 방문 가능하다는 것은 현재위치에서 다른 돌을 적절히 밟아 해당하는 위치로 이동이 가능하다는 뜻이다.
 *
 * 현재 위치가 주어졌을 때, 영우가 방문 가능한 돌들의 개수를 출력하시오.
 *
 * 입력
 * 첫 번째 줄에는 돌다리의 돌 개수 n이 주어진다.(1≤n≤100,000) 돌의 번호는 왼쪽부터 1번에서 n번이다. 다음 줄에는 그 위치에서 점프할 수 있는 거리 Ai가 주어진다.(1≤Ai≤100,000)
 *
 * 다음 줄에는 출발점 s가 주어진다.(1≤s≤n)
 *
 * 출력
 * 영우가 방문 가능한 돌들의 개수를 출력하시오.
 *
 * 예제 입력 1
 * 5
 * 1 4 2 2 1
 * 3
 * 예제 출력 1
 * 5
 */

package silver;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P14248 {
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  StringTokenizer st;

  int N;
  int[] array;
  boolean[] visited;

  public static void main(String[] args) throws IOException {
      new P14248().solution();
  }

  public void solution() throws IOException {
    N = Integer.parseInt(br.readLine());
    array = new int[N + 1];
    visited = new boolean[N + 1];

    st = new StringTokenizer(br.readLine());
    for (int i = 1; i <= N; i++) {
      array[i] = Integer.parseInt(st.nextToken());
    }

    int start = Integer.parseInt(br.readLine());
    BFS(start);

    int count = 0;
    for (int i = 1; i <= N; i++) {
      if (visited[i]) {
        count++;
      }
    }
    bw.write(count + System.lineSeparator());
    bw.flush();

    bw.close();
    br.close();
  }

  private void BFS(int start) {
    Queue<Integer> queue = new LinkedList<>();
    queue.offer(start);
    visited[start] = true;

    while (!queue.isEmpty()) {
      int nowIndex = queue.poll();

      int preNextIndex = nowIndex - array[nowIndex];
      int postNextIndex = nowIndex + array[nowIndex];

      if (validation(preNextIndex) && !visited[preNextIndex]) {
        visited[preNextIndex] = true;
        queue.offer(preNextIndex);
      }

      if (validation(postNextIndex) && !visited[postNextIndex]) {
        visited[postNextIndex] = true;
        queue.offer(postNextIndex);
      }
    }
  }

  private boolean validation(int index) {
    return index > 0 && index <= N;
  }
}
