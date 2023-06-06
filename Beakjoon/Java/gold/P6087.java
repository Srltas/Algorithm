/**
 *
 * URL : https://www.acmicpc.net/problem/6087
 *
 * 레이저 통신
 *
 * 문제
 * 크기가 1×1인 정사각형으로 나누어진 W×H 크기의 지도가 있다. 지도의 각 칸은 빈 칸이거나 벽이며, 두 칸은 'C'로 표시되어 있는 칸이다.
 *
 * 'C'로 표시되어 있는 두 칸을 레이저로 통신하기 위해서 설치해야 하는 거울 개수의 최솟값을 구하는 프로그램을 작성하시오. 레이저로 통신한다는 것은 두 칸을 레이저로 연결할 수 있음을 의미한다.
 *
 * 레이저는 C에서만 발사할 수 있고, 빈 칸에 거울('/', '\')을 설치해서 방향을 90도 회전시킬 수 있다.
 *
 * 아래 그림은 H = 8, W = 7인 경우이고, 빈 칸은 '.', 벽은 '*'로 나타냈다. 왼쪽은 초기 상태, 오른쪽은 최소 개수의 거울을 사용해서 두 'C'를 연결한 것이다.
 *
 * 7 . . . . . . .         7 . . . . . . .
 * 6 . . . . . . C         6 . . . . . /-C
 * 5 . . . . . . *         5 . . . . . | *
 * 4 * * * * * . *         4 * * * * * | *
 * 3 . . . . * . .         3 . . . . * | .
 * 2 . . . . * . .         2 . . . . * | .
 * 1 . C . . * . .         1 . C . . * | .
 * 0 . . . . . . .         0 . \-------/ .
 *   0 1 2 3 4 5 6           0 1 2 3 4 5 6
 * 입력
 * 첫째 줄에 W와 H가 주어진다. (1 ≤ W, H ≤ 100)
 *
 * 둘째 줄부터 H개의 줄에 지도가 주어진다. 지도의 각 문자가 의미하는 것은 다음과 같다.
 *
 * .: 빈 칸
 * *: 벽
 * C: 레이저로 연결해야 하는 칸
 * 'C'는 항상 두 개이고, 레이저로 연결할 수 있는 입력만 주어진다.
 *
 * 출력
 * 첫째 줄에 C를 연결하기 위해 설치해야 하는 거울 개수의 최솟값을 출력한다.
 *
 * 예제 입력 1
 * 7 8
 * .......
 * ......C
 * ......*
 * *****.*
 * ....*..
 * ....*..
 * .C..*..
 * .......
 * 예제 출력 1
 * 3
 */

package gold;

import java.io.*;
import java.util.*;

public class P6087 {
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  StringTokenizer st;

  int[] dH = {-1, 0, 1, 0};
  int[] dW = {0, -1, 0, 1};

  int H, W;
  char[][] array;
  int[][][] mirrors;

  public static void main(String[] args) throws IOException {
      new P6087().solution();
  }

  public void solution() throws IOException {
    st = new StringTokenizer(br.readLine());
    W = Integer.parseInt(st.nextToken());
    H = Integer.parseInt(st.nextToken());

    array = new char[H][W];
    mirrors = new int[4][H][W];
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < H; j++) {
        Arrays.fill(mirrors[i][j], Integer.MAX_VALUE);
      }
    }

    int startH = 0;
    int startW = 0;
    for (int i = 0; i < H; i++) {
      char[] temp = br.readLine().toCharArray();
      for (int j = 0; j < W; j++) {
        array[i][j] = temp[j];
        if (array[i][j] == 'C') {
          startH = i;
          startW = j;
        }
      }
    }

    array[startH][startW] = '.';
    bw.write(dijkstra(startH, startW) + System.lineSeparator());
    bw.flush();

    bw.close();
    br.close();
  }

  private int dijkstra(int startH, int startW) {
    PriorityQueue<Node> queue = new PriorityQueue<>();
    queue.offer(new Node(startH, startW, -5, -1));
    int min = Integer.MAX_VALUE;

    while (!queue.isEmpty()) {
      Node curr = queue.poll();

      if (array[curr.h][curr.w] == 'C') {
        min = Math.min(min, curr.mirror);
        continue;
      }

      for (int i = 0; i < 4; i++) {
        int nextH = curr.h + dH[i];
        int nextW = curr.w + dW[i];
        int nextMirror = (curr.direction == i) ? curr.mirror : curr.mirror + 1;

        if (nextH < 0 || nextW < 0 || nextH >= H || nextW >= W
            || array[nextH][nextW] == '*' || Math.abs(curr.direction - i) == 2) {
          continue;
        }

        if (mirrors[i][nextH][nextW] > nextMirror) {
          mirrors[i][nextH][nextW] = nextMirror;
          queue.offer(new Node(nextH, nextW, i, nextMirror));
        }
      }
    }

    return min;
  }
  static class Node implements Comparable<Node> {
    int h;
    int w;
    int direction;
    int mirror;

    public Node(int h, int w, int direction, int mirror) {
      this.h = h;
      this.w = w;
      this.direction = direction;
      this.mirror = mirror;
    }

    @Override
    public int compareTo(Node node) {
      return this.mirror - node.mirror;
    }
  }
}
