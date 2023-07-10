/**
 *
 * URL : https://www.acmicpc.net/problem/2206
 *
 * 벽 부수고 이동하기
 *
 * 문제
 * N×M의 행렬로 표현되는 맵이 있다. 맵에서 0은 이동할 수 있는 곳을 나타내고, 1은 이동할 수 없는 벽이 있는 곳을 나타낸다. 당신은 (1, 1)에서 (N, M)의 위치까지 이동하려 하는데, 이때 최단 경로로 이동하려 한다. 최단경로는 맵에서 가장 적은 개수의 칸을 지나는 경로를 말하는데, 이때 시작하는 칸과 끝나는 칸도 포함해서 센다.
 *
 * 만약에 이동하는 도중에 한 개의 벽을 부수고 이동하는 것이 좀 더 경로가 짧아진다면, 벽을 한 개 까지 부수고 이동하여도 된다.
 *
 * 한 칸에서 이동할 수 있는 칸은 상하좌우로 인접한 칸이다.
 *
 * 맵이 주어졌을 때, 최단 경로를 구해 내는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 N(1 ≤ N ≤ 1,000), M(1 ≤ M ≤ 1,000)이 주어진다. 다음 N개의 줄에 M개의 숫자로 맵이 주어진다. (1, 1)과 (N, M)은 항상 0이라고 가정하자.
 *
 * 출력
 * 첫째 줄에 최단 거리를 출력한다. 불가능할 때는 -1을 출력한다.
 *
 * 예제 입력 1
 * 6 4
 * 0100
 * 1110
 * 1000
 * 0000
 * 0111
 * 0000
 * 예제 출력 1
 * 15
 * 예제 입력 2
 * 4 4
 * 0111
 * 1111
 * 1111
 * 1110
 * 예제 출력 2
 * -1
 */

package gold;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P2206 {
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  StringTokenizer st;

  int[] dx = {0, 0, -1, 1};
  int[] dy = {-1, 1, 0, 0};

  int N, M;
  int[][] map, visited;

  public static void main(String[] args) throws IOException {
      new P2206().solution();
  }

  public void solution() throws IOException {
    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    map = new int[N][M];
    visited = new int[N][M];
    for (int i = 0; i < N; i++) {
      String[] array = br.readLine().split("");
      for (int j = 0; j < M; j++) {
        map[i][j] = Integer.parseInt(array[j]);
        visited[i][j] = Integer.MAX_VALUE;
      }
    }

    int result = BFS();
    bw.write((result == Integer.MAX_VALUE ? "-1" : result) + System.lineSeparator());
    bw.flush();

    bw.close();
    br.close();
  }

  private int BFS() {
    Queue<Node> queue = new LinkedList<>();
    queue.offer(new Node(0, 0, 1, 0));
    visited[0][0] = 0;

    int minCount = Integer.MAX_VALUE;
    while (!queue.isEmpty()) {
      Node currNode = queue.poll();
      if (currNode.x == N - 1 && currNode.y == M - 1) {
        minCount = Math.min(minCount, currNode.dis);
        continue;
      }

      for (int i = 0; i < 4; i++) {
        int nextX = currNode.x + dx[i];
        int nextY = currNode.y + dy[i];

        if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
          continue;
        }

        if (visited[nextX][nextY] <= currNode.breakWall) {
          continue;
        }

        if (map[nextX][nextY] == 0) {
          visited[nextX][nextY] = currNode.breakWall;
          queue.offer(new Node(nextX, nextY, currNode.dis + 1, currNode.breakWall));
        } else {
          if (currNode.breakWall == 0) {
            visited[nextX][nextY] = currNode.breakWall + 1;
            queue.offer(new Node(nextX, nextY, currNode.dis + 1, currNode.breakWall + 1));
          }
        }
      }
    }
    return minCount;
  }

  static class Node {
    public int x;
    public int y;
    public int dis;
    public int breakWall;

    public Node(int x, int y, int dis, int breakWall) {
      this.x = x;
      this.y = y;
      this.dis = dis;
      this.breakWall = breakWall;
    }
  }
}
