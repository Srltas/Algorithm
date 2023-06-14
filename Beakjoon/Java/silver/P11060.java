/**
 *
 * URL : https://www.acmicpc.net/problem/11060
 *
 * 점프점프
 *
 * 문제
 * 재환이가 1×N 크기의 미로에 갇혀있다. 미로는 1×1 크기의 칸으로 이루어져 있고, 각 칸에는 정수가 하나 쓰여 있다. i번째 칸에 쓰여 있는 수를 Ai라고 했을 때, 재환이는 Ai이하만큼 오른쪽으로 떨어진 칸으로 한 번에 점프할 수 있다. 예를 들어, 3번째 칸에 쓰여 있는 수가 3이면, 재환이는 4, 5, 6번 칸 중 하나로 점프할 수 있다.
 *
 * 재환이는 지금 미로의 가장 왼쪽 끝에 있고, 가장 오른쪽 끝으로 가려고 한다. 이때, 최소 몇 번 점프를 해야 갈 수 있는지 구하는 프로그램을 작성하시오. 만약, 가장 오른쪽 끝으로 갈 수 없는 경우에는 -1을 출력한다.
 *
 * 입력
 * 첫째 줄에 N(1 ≤ N ≤ 1,000)이 주어진다. 둘째 줄에는 Ai (0 ≤ Ai ≤ 100)가 주어진다.
 *
 * 출력
 * 재환이가 최소 몇 번 점프를 해야 가장 오른쪽 끝 칸으로 갈 수 있는지 출력한다. 만약, 가장 오른쪽 끝으로 갈 수 없는 경우에는 -1을 출력한다.
 *
 * 예제 입력 1
 * 10
 * 1 2 0 1 3 2 1 5 4 2
 * 예제 출력 1
 * 5
 */

package silver;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P11060 {
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  StringTokenizer st;

  int N;
  int[] array;
  int[] count;

  public static void main(String[] args) throws IOException {
      new P11060().solution();
  }

  public void solution() throws IOException {
    N = Integer.parseInt(br.readLine());
    st = new StringTokenizer(br.readLine());
    array = new int[N];
    for (int i = 0; i < N; i++) {
      array[i] = Integer.parseInt(st.nextToken());
    }

    count = new int[N];
    Arrays.fill(count, Integer.MAX_VALUE);

    BFS();

    bw.write((count[N - 1] == Integer.MAX_VALUE ? -1 : count[N - 1]) + System.lineSeparator());
    bw.flush();

    bw.close();
    br.close();
  }

  private void BFS() {
    Queue<Node> queue = new LinkedList<>();
    queue.offer(new Node(0, array[0]));
    count[0] = 0;

    while (!queue.isEmpty()) {
      Node currNode = queue.poll();
      for (int i = 1; i <= currNode.jump; i++) {
        int nextIndex = currNode.index + i;
        if (nextIndex >= N) {
          break;
        }

        if (count[nextIndex] > count[currNode.index] + 1) {
          queue.offer(new Node(nextIndex, array[nextIndex]));
          count[nextIndex] = count[currNode.index] + 1;
        }
      }
    }
  }

  static class Node {
    int index;
    int jump;

    public Node (int index, int jump) {
      this.index = index;
      this.jump = jump;
    }
  }
}
