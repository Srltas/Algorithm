/**
 *
 * URL : https://www.acmicpc.net/problem/2146
 *
 * 다리 만들기
 *
 * 문제
 * 여러 섬으로 이루어진 나라가 있다. 이 나라의 대통령은 섬을 잇는 다리를 만들겠다는 공약으로 인기몰이를 해 당선될 수 있었다. 하지만 막상 대통령에 취임하자, 다리를 놓는다는 것이 아깝다는 생각을 하게 되었다. 그래서 그는, 생색내는 식으로 한 섬과 다른 섬을 잇는 다리 하나만을 만들기로 하였고, 그 또한 다리를 가장 짧게 하여 돈을 아끼려 하였다.
 *
 * 이 나라는 N×N크기의 이차원 평면상에 존재한다. 이 나라는 여러 섬으로 이루어져 있으며, 섬이란 동서남북으로 육지가 붙어있는 덩어리를 말한다. 다음은 세 개의 섬으로 이루어진 나라의 지도이다.
 *
 *
 *
 * 위의 그림에서 색이 있는 부분이 육지이고, 색이 없는 부분이 바다이다. 이 바다에 가장 짧은 다리를 놓아 두 대륙을 연결하고자 한다. 가장 짧은 다리란, 다리가 격자에서 차지하는 칸의 수가 가장 작은 다리를 말한다. 다음 그림에서 두 대륙을 연결하는 다리를 볼 수 있다.
 *
 *
 *
 * 물론 위의 방법 외에도 다리를 놓는 방법이 여러 가지 있으나, 위의 경우가 놓는 다리의 길이가 3으로 가장 짧다(물론 길이가 3인 다른 다리를 놓을 수 있는 방법도 몇 가지 있다).
 *
 * 지도가 주어질 때, 가장 짧은 다리 하나를 놓아 두 대륙을 연결하는 방법을 찾으시오.
 *
 * 입력
 * 첫 줄에는 지도의 크기 N(100이하의 자연수)가 주어진다. 그 다음 N줄에는 N개의 숫자가 빈칸을 사이에 두고 주어지며, 0은 바다, 1은 육지를 나타낸다. 항상 두 개 이상의 섬이 있는 데이터만 입력으로 주어진다.
 *
 * 출력
 * 첫째 줄에 가장 짧은 다리의 길이를 출력한다.
 *
 * 예제 입력 1
 * 10
 * 1 1 1 0 0 0 0 1 1 1
 * 1 1 1 1 0 0 0 0 1 1
 * 1 0 1 1 0 0 0 0 1 1
 * 0 0 1 1 1 0 0 0 0 1
 * 0 0 0 1 0 0 0 0 0 1
 * 0 0 0 0 0 0 0 0 0 1
 * 0 0 0 0 0 0 0 0 0 0
 * 0 0 0 0 1 1 0 0 0 0
 * 0 0 0 0 1 1 1 0 0 0
 * 0 0 0 0 0 0 0 0 0 0
 * 예제 출력 1
 * 3
 */

package gold;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P2146 {
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  StringTokenizer st;

  int[] dx = {0, 0, -1, 1};
  int[] dy = {-1, 1, 0, 0};

  int N;
  boolean[][] tempMap;
  int[][] map;
  boolean[][] visited;
  int[][] countMap;

  public static void main(String[] args) throws IOException {
      new P2146().solution();
  }

  public void solution() throws IOException {
    N = Integer.parseInt(br.readLine());
    tempMap = new boolean[N][N];
    map = new int[N][N];
    visited = new boolean[N][N];
    countMap = new int[N][N];
    resetCountMap();

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        tempMap[i][j] = st.nextToken().equals("1");
      }
    }

    int islandNum = 1;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (tempMap[i][j] && !visited[i][j]) {
          giveIslandNum(new Node(i, j), islandNum);
          islandNum++;
        }
      }
    }

    resetVisited();

    int minNum = Integer.MAX_VALUE;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (tempMap[i][j]) {
          minNum = Math.min(BFS(new Node(i, j)), minNum);
          resetVisited();
          resetCountMap();
        }
      }
    }

    bw.write(minNum + System.lineSeparator());
    bw.flush();

    bw.close();
    br.close();
  }

  private void giveIslandNum(Node node, int islandNum) {
    Queue<Node> queue = new LinkedList();
    queue.offer(node);
    visited[node.x][node.y] = true;
    map[node.x][node.y] = islandNum;

    while (!queue.isEmpty()) {
      Node currNode = queue.poll();

      for (int i = 0; i < 4; i++) {
        int nextX = currNode.x + dx[i];
        int nextY = currNode.y + dy[i];

        if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N
            || !tempMap[nextX][nextY] || visited[nextX][nextY]) {
          continue;
        }

        queue.offer(new Node(nextX, nextY));
        visited[nextX][nextY] = true;
        map[nextX][nextY] = islandNum;
      }
    }
  }

  private int BFS(Node node) {
    int count = Integer.MAX_VALUE;
    Queue<Node> queue = new LinkedList<>();
    queue.offer(node);
    visited[node.x][node.y] = true;
    countMap[node.x][node.y] = 0;
    int currIslandNum = map[node.x][node.y];

    while (!queue.isEmpty()) {
      Node currNode = queue.poll();

      for (int i = 0; i < 4; i++) {
        int nextX = currNode.x + dx[i];
        int nextY = currNode.y + dy[i];

        if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N
            || map[nextX][nextY] == currIslandNum  || visited[nextX][nextY]) {
          continue;
        }

        if (map[nextX][nextY] == 0) {
          countMap[nextX][nextY] = countMap[currNode.x][currNode.y] + 1;
        } else if (map[nextX][nextY] != 0) {
          count = Math.min(countMap[currNode.x][currNode.y], count);
        }

        if (!tempMap[nextX][nextY]) {
          queue.offer(new Node(nextX, nextY));
          visited[nextX][nextY] = true;
        }
      }
    }
    return count;
  }

  private void resetVisited() {
    for (int i = 0; i < N; i++) {
      Arrays.fill(visited[i], false);
    }
  }

  private void resetCountMap() {
    for (int i = 0; i < N; i++) {
      Arrays.fill(countMap[i], Integer.MAX_VALUE);
    }
  }

  static class Node {
    int x;
    int y;

    public Node(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }
}
