/**
 *
 * URL : https://www.acmicpc.net/problem/11559
 *
 * Puyo Puyo
 *
 * 문제
 * 뿌요뿌요의 룰은 다음과 같다.
 *
 * 필드에 여러 가지 색깔의 뿌요를 놓는다. 뿌요는 중력의 영향을 받아 아래에 바닥이나 다른 뿌요가 나올 때까지 아래로 떨어진다.
 *
 * 뿌요를 놓고 난 후, 같은 색 뿌요가 4개 이상 상하좌우로 연결되어 있으면 연결된 같은 색 뿌요들이 한꺼번에 없어진다. 이때 1연쇄가 시작된다.
 *
 * 뿌요들이 없어지고 나서 위에 다른 뿌요들이 있다면, 역시 중력의 영향을 받아 차례대로 아래로 떨어지게 된다.
 *
 * 아래로 떨어지고 나서 다시 같은 색의 뿌요들이 4개 이상 모이게 되면 또 터지게 되는데, 터진 후 뿌요들이 내려오고 다시 터짐을 반복할 때마다 1연쇄씩 늘어난다.
 *
 * 터질 수 있는 뿌요가 여러 그룹이 있다면 동시에 터져야 하고 여러 그룹이 터지더라도 한번의 연쇄가 추가된다.
 *
 * 남규는 최근 뿌요뿌요 게임에 푹 빠졌다. 이 게임은 1:1로 붙는 대전게임이라 잘 쌓는 것도 중요하지만, 상대방이 터뜨린다면 연쇄가 몇 번이 될지 바로 파악할 수 있는 능력도 필요하다. 하지만 아직 실력이 부족하여 남규는 자기 필드에만 신경 쓰기 바쁘다. 상대방의 필드가 주어졌을 때, 연쇄가 몇 번 연속으로 일어날지 계산하여 남규를 도와주자!
 *
 * 입력
 * 총 12개의 줄에 필드의 정보가 주어지며, 각 줄에는 6개의 문자가 있다.
 *
 * 이때 .은 빈공간이고 .이 아닌것은 각각의 색깔의 뿌요를 나타낸다.
 *
 * R은 빨강, G는 초록, B는 파랑, P는 보라, Y는 노랑이다.
 *
 * 입력으로 주어지는 필드는 뿌요들이 전부 아래로 떨어진 뒤의 상태이다. 즉, 뿌요 아래에 빈 칸이 있는 경우는 없다.
 *
 * 출력
 * 현재 주어진 상황에서 몇연쇄가 되는지 출력한다. 하나도 터지지 않는다면 0을 출력한다.
 *
 * 예제 입력 1
 * ......
 * ......
 * ......
 * ......
 * ......
 * ......
 * ......
 * ......
 * .Y....
 * .YG...
 * RRYG..
 * RRYGG.
 * 예제 출력 1
 * 3
 */

package gold;

import java.io.*;
import java.util.*;

public class P11559 {
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  StringTokenizer st;

  int[] dX = {0, 0, -1, 1};
  int[] dY = {-1, 1, 0, 0};

  char[][] array = new char[12][6];

  public static void main(String[] args) throws IOException {
      new P11559().solution();
  }

  public void solution() throws IOException {
    for (int i = 0; i < 12; i++) {
      char[] row = br.readLine().toCharArray();
      for (int j = 0; j < 6; j++) {
        array[i][j] = row[j];
      }
    }

    int count = 0;
    while (start()) {
      count++;
    }
    bw.write(count + System.lineSeparator());
    bw.flush();

    bw.close();
    br.close();
  }

  private boolean start() {
    boolean[][] visited = new boolean[12][6];
    boolean isBoom = false;

    for (int i = 0; i < 12; i++) {
      for (int j = 0; j < 6; j++) {
        if (!visited[i][j] && array[i][j] != '.') {
          boolean result = search(i, j, visited);
          if (!isBoom) {
            isBoom = result;
          }
        }
      }
    }
    sort();
    return isBoom;
  }

  private boolean search(int x, int y, boolean[][] visited) {
    char color = array[x][y];
    Queue<Node> queue = new LinkedList<>();
    List<Node> list = new ArrayList<>();

    queue.offer(new Node(x, y));
    list.add(new Node(x, y));
    visited[x][y] = true;

    while (!queue.isEmpty()) {
      Node currNode = queue.poll();

      for (int i = 0; i < 4; i++) {
        int nextX = currNode.x + dX[i];
        int nextY = currNode.y + dY[i];

        if (checkRange(nextX, nextY)
            && !visited[nextX][nextY]
            && array[nextX][nextY] != '.'
            && array[nextX][nextY] == color) {
          queue.offer(new Node(nextX, nextY));
          list.add(new Node(nextX, nextY));
          visited[nextX][nextY] = true;
        }
      }
    }
    return remove(list);
  }

  private boolean checkRange(int x, int y) {
    return x >= 0 && y >= 0 && x < 12 && y < 6;
  }

  private boolean remove(List<Node> list) {
    if (list.size() >= 4) {
      for (Node node : list) {
        array[node.x][node.y] = '.';
      }
      return true;
    }
    return false;
  }

  private void sort() {
    for (int i = 11; i > 0; i--) {
      for (int j = 5; j >= 0; j--) {
        if (array[i][j] == '.') {
          for (int k = i - 1; k >= 0; k--) {
            if (array[k][j] != '.') {
              array[i][j] = array[k][j];
              array[k][j] = '.';
              break;
            }
          }
        }
      }
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
