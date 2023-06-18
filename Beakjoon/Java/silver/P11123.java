/**
 *
 * URL : https://www.acmicpc.net/problem/11123
 *
 * 양 한마리... 양 두마리...
 *
 * 문제
 * 얼마전에 나는 불면증에 시달렸지... 천장이 뚫어져라 뜬 눈으로 밤을 지새우곤 했었지.  그러던 어느 날 내 친구 광민이에게 나의 불면증에 대해 말했더니 이렇게 말하더군. "양이라도 세봐!"  정말 도움이 안되는 친구라고 생각했었지. 그런데 막상 또 다시 잠을 청해보려고 침대에 눕고 보니 양을 세고 있더군... 그런데 양을 세다보니 이걸로 프로그램을 하나 짜볼 수 있겠단 생각이 들더군 후후후... 그렇게 나는 침대에서 일어나 컴퓨터 앞으로 향했지.
 *
 * 양을 # 으로 나타내고 . 으로 풀을 표현하는 거야. 서로 다른 # 두 개 이상이 붙어있다면 한 무리의 양들이 있는거지. 그래... 좋았어..! 이걸로 초원에서 풀을 뜯고 있는 양들을 그리드로 표현해 보는거야!
 *
 * 그렇게 나는 양들을 그리드로 표현하고 나니까 갑자기 졸렵기 시작했어. 하지만 난 너무 궁금했지. 내가 표현한 그 그리드 위에 몇 개의 양무리가 있었는지! 그래서 나는 동이 트기 전까지 이 프로그램을 작성하고 장렬히 전사했지. 다음날 내가 잠에서 깨어났을 때 내 모니터에는 몇 개의 양무리가 있었는지 출력되어 있었지.
 *
 * 입력
 * 첫 번째 줄은 테스트 케이스의 수를 나타나는 T를 입력받는다.
 *
 * 이후 각 테스트 케이스의 첫 번째 줄에서는 H,W 를 입력받는다. H는 그리드의 높이이고, W는 그리드의 너비이다. 이후 그리드의 높이 H 에 걸쳐서 W개의 문자로 이루어진 문자열 하나를 입력받는다.
 *
 * 0 < T ≤ 100
 * 0 < H, W ≤ 100
 * 출력
 * 각 테스트 케이스마다, 양의 몇 개의 무리로 이루어져 있었는지를 한 줄에 출력하면 된다.
 *
 * 예제 입력 1
 * 2
 * 4 4
 * #.#.
 * .#.#
 * #.##
 * .#.#
 * 3 5
 * ###.#
 * ..#..
 * #.###
 * 예제 출력 1
 * 6
 * 3
 */

package silver;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P11123 {
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  StringTokenizer st;

  int[] dH = {0, 0, -1, 1};
  int[] dW = {-1, 1, 0, 0};

  int H, W;
  char[][] grid;
  boolean[][] visited;

  public static void main(String[] args) throws IOException {
      new P11123().solution();
  }

  public void solution() throws IOException {
    int T = Integer.parseInt(br.readLine());
    while (T-- > 0) {
      st = new StringTokenizer(br.readLine());
      H = Integer.parseInt(st.nextToken());
      W = Integer.parseInt(st.nextToken());

      grid = new char[H][W];
      visited = new boolean[H][W];
      for (int i = 0; i < H; i++) {
        grid[i] = br.readLine().toCharArray();
      }
      int result = 0;
      for (int i = 0; i < H; i++) {
        for (int j = 0; j < W; j++) {
          if (grid[i][j] == '#' && !visited[i][j]) {
            BFS(new Node(i, j));
            result++;
          }
        }
      }
      bw.write(result + System.lineSeparator());
    }
    bw.flush();

    bw.close();
    br.close();
  }

  private void BFS(Node startNode) {
    Queue<Node> queue = new LinkedList<>();
    queue.offer(startNode);
    visited[startNode.h][startNode.w] = true;

    while (!queue.isEmpty()) {
      Node currNode = queue.poll();

      for (int i = 0; i < 4; i++) {
        int nextH = currNode.h + dH[i];
        int nextW = currNode.w + dW[i];

        if (nextH < 0 || nextW < 0 || nextH >= H || nextW >= W
            || grid[nextH][nextW] == '.' || visited[nextH][nextW]) {
          continue;
        }

        queue.offer(new Node(nextH, nextW));
        visited[nextH][nextW] = true;
      }
    }
  }

  static class Node {
    int h;
    int w;

    public Node (int h, int w) {
      this.h = h;
      this.w = w;
    }
  }
}
