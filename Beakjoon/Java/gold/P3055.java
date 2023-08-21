/**
 *
 * URL : https://www.acmicpc.net/problem/3055
 *
 * 탈출
 *
 * 문제
 * 사악한 암흑의 군주 이민혁은 드디어 마법 구슬을 손에 넣었고, 그 능력을 실험해보기 위해 근처의 티떱숲에 홍수를 일으키려고 한다. 이 숲에는 고슴도치가 한 마리 살고 있다. 고슴도치는 제일 친한 친구인 비버의 굴로 가능한 빨리 도망가 홍수를 피하려고 한다.
 *
 * 티떱숲의 지도는 R행 C열로 이루어져 있다. 비어있는 곳은 '.'로 표시되어 있고, 물이 차있는 지역은 '*', 돌은 'X'로 표시되어 있다. 비버의 굴은 'D'로, 고슴도치의 위치는 'S'로 나타내어져 있다.
 *
 * 매 분마다 고슴도치는 현재 있는 칸과 인접한 네 칸 중 하나로 이동할 수 있다. (위, 아래, 오른쪽, 왼쪽) 물도 매 분마다 비어있는 칸으로 확장한다. 물이 있는 칸과 인접해있는 비어있는 칸(적어도 한 변을 공유)은 물이 차게 된다. 물과 고슴도치는 돌을 통과할 수 없다. 또, 고슴도치는 물로 차있는 구역으로 이동할 수 없고, 물도 비버의 소굴로 이동할 수 없다.
 *
 * 티떱숲의 지도가 주어졌을 때, 고슴도치가 안전하게 비버의 굴로 이동하기 위해 필요한 최소 시간을 구하는 프로그램을 작성하시오.
 *
 * 고슴도치는 물이 찰 예정인 칸으로 이동할 수 없다. 즉, 다음 시간에 물이 찰 예정인 칸으로 고슴도치는 이동할 수 없다. 이동할 수 있으면 고슴도치가 물에 빠지기 때문이다.
 *
 * 입력
 * 첫째 줄에 50보다 작거나 같은 자연수 R과 C가 주어진다.
 *
 * 다음 R개 줄에는 티떱숲의 지도가 주어지며, 문제에서 설명한 문자만 주어진다. 'D'와 'S'는 하나씩만 주어진다.
 *
 * 출력
 * 첫째 줄에 고슴도치가 비버의 굴로 이동할 수 있는 가장 빠른 시간을 출력한다. 만약, 안전하게 비버의 굴로 이동할 수 없다면, "KAKTUS"를 출력한다.
 *
 * 예제 입력 1
 * 3 3
 * D.*
 * ...
 * .S.
 * 예제 출력 1
 * 3
 * 예제 입력 2
 * 3 3
 * D.*
 * ...
 * ..S
 * 예제 출력 2
 * KAKTUS
 * 예제 입력 3
 * 3 6
 * D...*.
 * .X.X..
 * ....S.
 * 예제 출력 3
 * 6
 * 예제 입력 4
 * 5 4
 * .D.*
 * ....
 * ..X.
 * S.*.
 * ....
 * 예제 출력 4
 * 4
 */

package gold;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P3055 {
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  StringTokenizer st;

  int[] dX = {0, 0, 1, -1};
  int[] dY = {1, -1, 0, 0};

  char[][] map;
  boolean[][] visited;
  int R, C;
  Node start;

  public static void main(String[] args) throws IOException {
      new P3055().solution();
  }

  public void solution() throws IOException {
    st = new StringTokenizer(br.readLine());
    R = Integer.parseInt(st.nextToken());
    C = Integer.parseInt(st.nextToken());

    map = new char[R][C];
    visited = new boolean[R][C];
    Queue<Node> waterQueue = new LinkedList<>();

    for (int i = 0; i < R; i++) {
      String str = br.readLine();
      for (int j = 0; j < C; j++) {
        map[i][j] = str.charAt(j);
        if (map[i][j] == 'S') {
          start = new Node(i, j);
        }
        if (map[i][j] == '*') {
          waterQueue.offer(new Node(i, j));
        }
      }
    }

    int result = search(start, waterQueue);
    bw.write((result != 0 ? result : "KAKTUS") + System.lineSeparator());
    bw.flush();

    bw.close();
    br.close();
  }

  private int search(Node S, Queue<Node> waterQueue) {
    Queue<Node> queue = new LinkedList<>();
    queue.offer(S);
    visited[S.x][S.y] = true;

    int index = 1;
    while (!queue.isEmpty()) {
      water(waterQueue);
      if (BFS(queue, index)) {
        return index;
      }
      index++;
    }
    return 0;
  }

  private void water(Queue<Node> queue) {
    int size = queue.size();
    while (size-- > 0) {
      Node node = queue.poll();
      for (int i = 0; i < 4; i++) {
        int nextX = node.x + dX[i];
        int nextY = node.y + dY[i];

        if ((nextX < 0 || nextY < 0 || nextX >= R || nextY >= C)
            || map[nextX][nextY] != '.') {
          continue;
        }

        map[nextX][nextY] = '*';
        queue.offer(new Node(nextX, nextY));
      }
    }
  }

  private boolean BFS(Queue<Node> queue, int index) {
    int size = queue.size();
    while (size-- > 0) {
      Node node = queue.poll();
      for (int i = 0; i < 4; i++) {
        int nextX = node.x + dX[i];
        int nextY = node.y + dY[i];

        if ((nextX < 0 || nextY < 0 || nextX >= R || nextY >= C)
            || map[nextX][nextY] == '*'
            || map[nextX][nextY] == 'X'
            || map[nextX][nextY] == 'S'
            || visited[nextX][nextY]) {
          continue;
        }

        if (map[nextX][nextY] == 'D') {
          return true;
        }

        visited[nextX][nextY] = true;
        queue.offer(new Node(nextX, nextY));
      }
    }
    return false;
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
