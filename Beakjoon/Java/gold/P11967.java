/**
 *
 * URL : https://www.acmicpc.net/problem/11967
 *
 * 불켜기
 *
 * 문제
 * 농부 존은 최근에 N × N개의 방이 있는 거대한 헛간을 새로 지었다. 각 방은 (1, 1)부터 (N,N)까지 번호가 매겨져있다(2 ≤ N ≤ 100). 어둠을 무서워하는 암소 베시는 최대한 많은 방에 불을 밝히고 싶어한다.
 *
 * 베시는 유일하게 불이 켜져있는 방인 (1, 1)방에서 출발한다. 어떤 방에는 다른 방의 불을 끄고 켤 수 있는 스위치가 달려있다. 예를 들어, (1, 1)방에 있는 스위치로 (1, 2)방의 불을 끄고 켤 수 있다. 베시는 불이 켜져있는 방으로만 들어갈 수 있고, 각 방에서는 상하좌우에 인접한 방으로 움직일 수 있다.
 *
 * 베시가 불을 켤 수 있는 방의 최대 개수를 구하시오.
 *
 * 입력
 * 첫 번째 줄에는 N(2 ≤ N ≤ 100)과, M(1 ≤ M ≤ 20,000)이 정수로 주어진다.
 *
 * 다음 M줄에는 네 개의 정수 x, y, a, b가 주어진다. (x, y)방에서 (a, b)방의 불을 켜고 끌 수 있다는 의미이다. 한 방에 여러개의 스위치가 있을 수 있고, 하나의 불을 조절하는 스위치 역시 여러개 있을 수 있다.
 *
 * 출력
 * 베시가 불을 켤 수 있는 방의 최대 개수를 출력하시오.
 *
 * 예제 입력 1
 * 3 6
 * 1 1 1 2
 * 2 1 2 2
 * 1 1 1 3
 * 2 3 3 1
 * 1 3 1 2
 * 1 3 2 1
 * 예제 출력 1
 * 5
 */

package gold;

import java.io.*;
import java.util.*;

public class P11967 {
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  StringTokenizer st;

  int[] dx = {0, 0, -1, 1};
  int[] dy = {-1, 1, 0, 0};

  int N, M;
  List<Node>[][] array;
  boolean[][] turnOnLightArray;
  boolean[][] visited;

  public static void main(String[] args) throws IOException {
      new P11967().solution();
  }

  public void solution() throws IOException {
    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    turnOnLightArray = new boolean[N + 1][N + 1];
    visited = new boolean[N + 1][N + 1];
    array = new List[N + 1][N + 1];
    for (int i = 1; i <= N; i++) {
      for (int j = 1; j <= N; j++) {
        array[i][j] = new ArrayList<>();
      }
    }

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());

      array[x][y].add(new Node(a, b));
    }

    BFS();

    int count = 0;
    for (int i = 1; i <= N; i++) {
      for (int j = 1; j <= N; j++) {
        count = turnOnLightArray[i][j] ? count + 1 : count;
      }
    }
    bw.write(count + System.lineSeparator());
    bw.flush();

    bw.close();
    br.close();
  }

  private void BFS() {
    Queue<Node> queue = new LinkedList();
    turnOnLightArray[1][1] = true;
    queue.offer(new Node(1, 1));
    visited[1][1] = true;

    while (!queue.isEmpty()) {
      Node currNode = queue.poll();
      turnOnLight(currNode);
      isPossibleVisite(queue, currNode);

      for (int i = 0; i < 4; i++) {
        int nextX = currNode.x + dx[i];
        int nextY = currNode.y + dy[i];

        if (overRange(nextX, nextY) || visited[nextX][nextY] || !turnOnLightArray[nextX][nextY]) {
          continue;
        }
        queue.offer(new Node(nextX, nextY));
        visited[nextX][nextY] = true;
      }
    }
  }

  private void isPossibleVisite(Queue<Node> queue, Node currNode) {
    for (Node node : array[currNode.x][currNode.y]) {
      if (!visited[node.x][node.y]) {
        for (int i = 0; i < 4; i++) {
          int nextX = node.x + dx[i];
          int nextY = node.y + dy[i];

          if (!overRange(nextX, nextY) && visited[nextX][nextY]) {
            queue.offer(new Node(node.x, node.y));
            visited[node.x][node.y] = true;
          }
        }
      }
    }
  }

  private void turnOnLight(Node currNode) {
    for (Node node : array[currNode.x][currNode.y]) {
      turnOnLightArray[node.x][node.y] = true;
    }
  }

  private boolean overRange(int nextX, int nextY) {
    return nextX <= 0 || nextY <= 0 || nextX > N || nextY > N;
  }

  static class Node {
    int x;
    int y;

    public Node (int x, int y) {
      this.x = x;
      this.y = y;
    }
  }
}
