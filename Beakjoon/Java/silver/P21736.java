/**
 * 문제
 * 2020년에 입학한 헌내기 도연이가 있다. 도연이는 비대면 수업 때문에 학교에 가지 못해 학교에 아는 친구가 없었다. 드디어 대면 수업을 하게 된 도연이는 어서 캠퍼스 내의 사람들과 친해지고 싶다.
 *
 * 도연이가 다니는 대학의 캠퍼스는
 * N x M 크기이며 캠퍼스에서 이동하는 방법은 벽이 아닌 상하좌우로 이동하는 것이다. 예를 들어, 도연이가 (x,y)에 있다면 이동할 수 있는 곳은 (x+1,y),
 * (x,y+1), (x-1,y), (x,y-1)이다. 단, 캠퍼스의 밖으로 이동할 수는 없다.
 *
 * 불쌍한 도연이를 위하여 캠퍼스에서 도연이가 만날 수 있는 사람의 수를 출력하는 프로그램을 작성해보자.
 *
 * 입력
 * 첫째 줄에는 캠퍼스의 크기를 나타내는 두 정수
 * N (1 <= N <= 600), M (1 <= M <= 600)이 주어진다.
 *
 * 둘째 줄부터
 * N개의 줄에는 캠퍼스의 정보들이 주어진다. O는 빈 공간, X는 벽, I는 도연이, P는 사람이다. I가 한 번만 주어짐이 보장된다.
 *
 * 출력
 * 첫째 줄에 도연이가 만날 수 있는 사람의 수를 출력한다. 단, 아무도 만나지 못한 경우 TT를 출력한다.
 *
 * 예제 입력 1
 * 3 5
 * OOOPO
 * OIOOX
 * OOOXP
 * 예제 출력 1
 * 1
 * 예제 입력 2
 * 3 3
 * IOX
 * OXP
 * XPP
 * 예제 출력 2
 * TT
 */

package silver;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P21736 {
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  StringTokenizer st;

  int[] dN = {0, 0, -1, 1};
  int[] dM = {-1, 1, 0, 0};

  int N, M;
  char[][] array;
  boolean[][] visited;

  public static void main(String[] args) throws IOException {
      new P21736().solution();
  }

  public void solution() throws IOException {
    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    array = new char[N][M];
    visited = new boolean[N][M];
    Node doYeon = new Node();
    for (int i = 0; i < N; i++) {
      char[] row = br.readLine().toCharArray();
      for (int j = 0; j < M; j++) {
        array[i][j] = row[j];
        if (array[i][j] == 'I') {
          doYeon.n = i;
          doYeon.m = j;
        }
      }
    }

    int result = BFS(doYeon);
    bw.write((result == 0 ? "TT" : result) + System.lineSeparator());
    bw.flush();

    bw.close();
    br.close();
  }

  private int BFS(Node doYeon) {
    Queue<Node> queue = new LinkedList<>();
    queue.offer(doYeon);

    int countPeople = 0;
    while (!queue.isEmpty()) {
      Node curr = queue.poll();

      for (int i = 0; i < 4; i++) {
        int nextN = curr.n + dN[i];
        int nextM = curr.m + dM[i];

        if (nextN < 0 || nextM < 0 || nextN >= N || nextM >= M
            || visited[nextN][nextM] || array[nextN][nextM] == 'X') {
          continue;
        }

        if (array[nextN][nextM] == 'P') {
          countPeople++;
        }
        visited[nextN][nextM] = true;
        queue.offer(new Node(nextN, nextM));
      }
    }
    return countPeople;
  }

  static class Node {
    int n;
    int m;

    public Node () {}

    public Node (int n, int m) {
      this.n = n;
      this.m = m;
    }
  }
}
