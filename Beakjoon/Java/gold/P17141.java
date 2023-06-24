/**
 *
 * URL : https://www.acmicpc.net/problem/17141
 *
 * 연구소 2
 *
 * 문제
 * 인체에 치명적인 바이러스를 연구하던 연구소에 승원이가 침입했고, 바이러스를 유출하려고 한다. 승원이는 연구소의 특정 위치에 바이러스 M개를 놓을 것이고, 승원이의 신호와 동시에 바이러스는 퍼지게 된다.
 *
 * 연구소는 크기가 N×N인 정사각형으로 나타낼 수 있으며, 정사각형은 1×1 크기의 정사각형으로 나누어져 있다. 연구소는 빈 칸, 벽으로 이루어져 있으며, 벽은 칸 하나를 가득 차지한다.
 *
 * 일부 빈 칸은 바이러스를 놓을 수 있는 칸이다. 바이러스는 상하좌우로 인접한 모든 빈 칸으로 동시에 복제되며, 1초가 걸린다.
 *
 * 예를 들어, 아래와 같이 연구소가 생긴 경우를 살펴보자. 0은 빈 칸, 1은 벽, 2는 바이러스를 놓을 수 있는 칸이다.
 *
 * 2 0 0 0 1 1 0
 * 0 0 1 0 1 2 0
 * 0 1 1 0 1 0 0
 * 0 1 0 0 0 0 0
 * 0 0 0 2 0 1 1
 * 0 1 0 0 0 0 0
 * 2 1 0 0 0 0 2
 * M = 3이고, 바이러스를 아래와 같이 놓은 경우 6초면 모든 칸에 바이러스를 퍼뜨릴 수 있다. 벽은 -, 바이러스를 놓은 위치는 0, 빈 칸은 바이러스가 퍼지는 시간으로 표시했다.
 *
 * 6 6 5 4 - - 2
 * 5 6 - 3 - 0 1
 * 4 - - 2 - 1 2
 * 3 - 2 1 2 2 3
 * 2 2 1 0 1 - -
 * 1 - 2 1 2 3 4
 * 0 - 3 2 3 4 5
 * 시간이 최소가 되는 방법은 아래와 같고, 5초만에 모든 칸에 바이러스를 퍼뜨릴 수 있다.
 *
 * 0 1 2 3 - - 2
 * 1 2 - 3 - 0 1
 * 2 - - 2 - 1 2
 * 3 - 2 1 2 2 3
 * 3 2 1 0 1 - -
 * 4 - 2 1 2 3 4
 * 5 - 3 2 3 4 5
 * 연구소의 상태가 주어졌을 때, 모든 빈 칸에 바이러스를 퍼뜨리는 최소 시간을 구해보자.
 *
 * 입력
 * 첫째 줄에 연구소의 크기 N(5 ≤ N ≤ 50), 놓을 수 있는 바이러스의 개수 M(1 ≤ M ≤ 10)이 주어진다.
 *
 * 둘째 줄부터 N개의 줄에 연구소의 상태가 주어진다. 0은 빈 칸, 1은 벽, 2는 바이러스를 놓을 수 있는 칸이다. 2의 개수는 M보다 크거나 같고, 10보다 작거나 같은 자연수이다.
 *
 * 출력
 * 연구소의 모든 빈 칸에 바이러스가 있게 되는 최소 시간을 출력한다. 바이러스를 어떻게 놓아도 모든 빈 칸에 바이러스를 퍼뜨릴 수 없는 경우에는 -1을 출력한다.
 *
 * 예제 입력 1
 * 7 3
 * 2 0 0 0 1 1 0
 * 0 0 1 0 1 2 0
 * 0 1 1 0 1 0 0
 * 0 1 0 0 0 0 0
 * 0 0 0 2 0 1 1
 * 0 1 0 0 0 0 0
 * 2 1 0 0 0 0 2
 * 예제 출력 1
 * 5
 * 예제 입력 2
 * 7 3
 * 2 0 2 0 1 1 0
 * 0 0 1 0 1 2 0
 * 0 1 1 2 1 0 0
 * 2 1 0 0 0 0 2
 * 0 0 0 2 0 1 1
 * 0 1 0 0 0 0 0
 * 2 1 0 0 2 0 2
 * 예제 출력 2
 * 5
 * 예제 입력 3
 * 7 4
 * 2 0 2 0 1 1 0
 * 0 0 1 0 1 2 0
 * 0 1 1 2 1 0 0
 * 2 1 0 0 0 0 2
 * 0 0 0 2 0 1 1
 * 0 1 0 0 0 0 0
 * 2 1 0 0 2 0 2
 * 예제 출력 3
 * 4
 * 예제 입력 4
 * 7 5
 * 2 0 2 0 1 1 0
 * 0 0 1 0 1 2 0
 * 0 1 1 2 1 0 0
 * 2 1 0 0 0 0 2
 * 0 0 0 2 0 1 1
 * 0 1 0 0 0 0 0
 * 2 1 0 0 2 0 2
 * 예제 출력 4
 * 3
 * 예제 입력 5
 * 7 3
 * 2 0 2 0 1 1 0
 * 0 0 1 0 1 0 0
 * 0 1 1 1 1 0 0
 * 2 1 0 0 0 0 2
 * 1 0 0 0 0 1 1
 * 0 1 0 0 0 0 0
 * 2 1 0 0 2 0 2
 * 예제 출력 5
 * 7
 * 예제 입력 6
 * 7 2
 * 2 0 2 0 1 1 0
 * 0 0 1 0 1 0 0
 * 0 1 1 1 1 0 0
 * 2 1 0 0 0 0 2
 * 1 0 0 0 0 1 1
 * 0 1 0 0 0 0 0
 * 2 1 0 0 2 0 2
 * 예제 출력 6
 * -1
 * 예제 입력 7
 * 5 1
 * 2 2 2 1 1
 * 2 1 1 1 1
 * 2 1 1 1 1
 * 2 1 1 1 1
 * 2 2 2 1 1
 * 예제 출력 7
 * 4
 */

package gold;

import java.io.*;
import java.util.*;

public class P17141 {
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  StringTokenizer st;

  int[] dx = {0, 0, -1 , 1};
  int[] dy = {-1, 1, 0, 0};

  int N, M;
  int minTime = Integer.MAX_VALUE;
  int[][] array;
  boolean[][] visited;
  List<Node> virusSpot = new ArrayList<>();
  boolean[] virusVisited;
  int[] virus;

  public static void main(String[] args) throws IOException {
      new P17141().solution();
  }

  public void solution() throws IOException {
    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    array = new int[N][N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        array[i][j] = Integer.parseInt(st.nextToken());

        if (array[i][j] == 2) {
          virusSpot.add(new Node(i,j));
          array[i][j] = 0;
        } else if (array[i][j] == 1) {
          array[i][j] = -1;
        }
      }
    }

    virusVisited = new boolean[virusSpot.size()];
    virus = new int[M];

    DFS(0, 0);

    bw.write((minTime == Integer.MAX_VALUE ? -1 : minTime) + System.lineSeparator());
    bw.flush();

    bw.close();
    br.close();
  }

  private void DFS(int level, int start) {
    if (level == M) {
      int time = BFS();
      minTime = time >= 0 ? Math.min(minTime, time) : minTime;
      return;
    }

    for (int i = start; i < virusSpot.size(); i++) {
      if (!virusVisited[i]) {
        virus[level] = i;
        virusVisited[i] = true;
        DFS(level + 1, i + 1);
        virusVisited[i] = false;
      }
    }
  }

  private int BFS() {
    visited = new boolean[N][N];
    int[][] temp = new int[N][N];

    for (int i = 0; i < N; i++) {
      temp[i] = array[i].clone();
    }

    Queue<Node> queue = new LinkedList<>();
    List<Node> startVirusSpot = new ArrayList<>();
    for (int i = 0; i < M; i++) {
      Node v = virusSpot.get(virus[i]);
      queue.offer(v);
      startVirusSpot.add(v);
      visited[v.x][v.y] = true;
    }

    int maxTime = 0;
    while (!queue.isEmpty()) {
      Node currNode = queue.poll();

      for (int i = 0; i < 4; i++) {
        int nextX = currNode.x + dx[i];
        int nextY = currNode.y + dy[i];

        if ((nextX < 0 || nextY < 0 || nextX >= N || nextY >= N)
            || visited[nextX][nextY] || temp[nextX][nextY] == -1) {
          continue;
        }

        queue.offer(new Node(nextX, nextY));
        temp[nextX][nextY] = temp[currNode.x][currNode.y] + 1;
        visited[nextX][nextY] = true;
        maxTime = Math.max(maxTime, temp[nextX][nextY]);
      }
    }
    return allSpread(temp, startVirusSpot) ? maxTime : -1;
  }

  private boolean allSpread(int[][] temp, List<Node> startVirusSpot) {
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (!isVirusStartSpot(i, j, startVirusSpot) && temp[i][j] == 0) {
          return false;
        }
      }
    }
    return true;
  }

  private boolean isVirusStartSpot(int i, int j, List<Node> startVirusSpot) {
    for (Node node : startVirusSpot) {
      if (node.x == i && node.y == j) {
        return true;
      }
    }
    return false;
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
