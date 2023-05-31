/**
 *
 * URL : https://www.acmicpc.net/problem/14497
 *
 * 주난의 난
 *
 * 문제
 * 주난이는 크게 화가 났다. 책상 서랍 안에 몰래 먹으려고 숨겨둔 초코바가 사라졌기 때문이다. 주난이는 미쳐 날뛰기 시작했다. 사실, 진짜로 뛰기 시작했다.
 *
 * ‘쿵... 쿵...’
 *
 * 주난이는 점프의 파동으로 주변의 모든 친구들을 쓰러뜨리고(?) 누군가 훔쳐간 초코바를 찾으려고 한다. 주난이는 N×M크기의 학교 교실 어딘가에서 뛰기 시작했다. 주난이의 파동은 상하좌우 4방향으로 친구들을 쓰러뜨릴(?) 때 까지 계속해서 퍼져나간다. 다르게 표현해서, 한 번의 점프는 한 겹의 친구들을 쓰러뜨린다. 다음의 예를 보자.
 *
 * 1 # 1 0 1 1 1
 * 1 1 0 1 0 0 1
 * 0 0 1 * 1 1 1
 * 1 1 0 1 1 1 1
 * 0 0 1 1 0 0 1
 * 주난이를 뜻하는 *은 (3, 4)에 있고, 초코바를 가진 학생 #는 (1, 2)에 있다. 0은 장애물이 없는 빈 공간임을 뜻하고, 1은 친구들이 서있음을 의미한다. 다음은 주난이의 점프에 따른 생존(?) 학생들의 변화이다.
 *
 * 1 # 1 0 1 1 1
 * 1 1 0 0 0 0 1
 * 0 0 0 * 0 1 1
 * 1 1 0 0 1 1 1
 * 0 0 1 1 0 0 1
 *
 *
 * 1 # 0 0 0 0 1
 * 0 0 0 0 0 0 0
 * 0 0 0 * 0 0 1
 * 0 0 0 0 0 1 1
 * 0 0 0 0 0 0 1
 *
 *
 * 0 X 0 0 0 0 0
 * 0 0 0 0 0 0 0
 * 0 0 0 * 0 0 0
 * 0 0 0 0 0 0 1
 * 0 0 0 0 0 0 0
 * 위의 예시에서 주난이는 3번의 점프 만에 초코바를 훔쳐간 범인을 찾아낼 수 있다!
 *
 * 주난이를 빨리 멈춰야 교실의 안녕을 도모할 수 있다. 주난이에게 최소 점프 횟수를 알려줘서 교실을 지키자.
 *
 * 입력
 * 첫째 줄에 주난이가 위치한 교실의 크기 N, M이 주어진다. (1 ≤ N, M ≤ 300)
 *
 * 둘째 줄에 주난이의 위치 x1, y1과 범인의 위치 x2, y2가 주어진다. (1 ≤ x1, x2 ≤ N, 1 ≤ y1, y2 ≤ M)
 *
 * 이후 N×M 크기의 교실 정보가 주어진다. 0은 빈 공간, 1은 친구, *는 주난, #는 범인을 뜻한다.
 *
 * 출력
 * 주난이가 범인을 잡기 위해 최소 몇 번의 점프를 해야 하는지 출력한다.
 *
 * 예제 입력 1
 * 5 7
 * 3 4 1 2
 * 1#10111
 * 1101001
 * 001*111
 * 1101111
 * 0011001
 * 예제 출력 1
 * 3
 * 예제 입력 2
 * 3 5
 * 3 5 1 1
 * #0000
 * 11111
 * 0000*
 * 예제 출력 2
 * 2
 * 예제 입력 3
 * 3 3
 * 2 2 1 1
 * #00
 * 0*0
 * 000
 * 예제 출력 3
 * 1
 */

package gold;

import java.io.*;
import java.util.*;

public class P14497 {
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  StringTokenizer st;

  final int MAX = Integer.MAX_VALUE;

  int[] dx = {0, 0, -1, 1};
  int[] dy = {-1, 1, 0, 0};

  int N, M;
  char[][] classRoom;
  int[][] distance;

  public static void main(String[] args) throws IOException {
      new P14497().solution();
  }

  public void solution() throws IOException {
    try {
      st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());

      st = new StringTokenizer(br.readLine());
      int x1 = Integer.parseInt(st.nextToken());
      int y1 = Integer.parseInt(st.nextToken());
      int x2 = Integer.parseInt(st.nextToken());
      int y2 = Integer.parseInt(st.nextToken());

      Node zhunan = new Node(x1, y1, 1);
      Node criminal = new Node(x2, y2, MAX);

      distance = new int[N + 1][M + 1];
      for (int i = 1; i <= N; i++) {
        Arrays.fill(distance[i], MAX);
      }

      classRoom = new char[N + 1][M + 1];
      for (int i = 1; i <= N; i++) {
        char[] array = br.readLine().toCharArray();
        for (int j = 1; j <= M; j++) {
          classRoom[i][j] = array[j - 1];
        }
      }

      dijkstra(zhunan);

      bw.write(distance[criminal.x][criminal.y] + System.lineSeparator());
      bw.flush();

    } finally {
      bw.close();
      br.close();
    }
  }

  private void dijkstra(Node zhunan) {
    Queue<Node> queue = new LinkedList<>();
    queue.offer(new Node(zhunan.x, zhunan.y, zhunan.count));
    distance[zhunan.x][zhunan.y] = 0;

    while (!queue.isEmpty()) {
      Node currentNode = queue.poll();

      for (int i = 0; i < 4; i++) {
        int nextX = currentNode.x + dx[i];
        int nextY = currentNode.y + dy[i];
        int count = currentNode.count;

        if (nextX > 0 && nextY > 0 && nextX <= N && nextY <= M) {
          if (classRoom[nextX][nextY] == '1') {
            count++;
          }

          if (distance[nextX][nextY] > count) {
            distance[nextX][nextY] = count;
            queue.offer(new Node(nextX, nextY, count));
          }
        }
      }
    }
  }

  static class Node {
    int x;
    int y;
    int count;

    public Node (int x, int y, int count) {
      this.x = x;
      this.y = y;
      this.count = count;
    }
  }
}
