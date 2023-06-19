/**
 *
 * URL : https://www.acmicpc.net/problem/1967
 *
 * 트리의 지름
 *
 * 문제
 * 트리(tree)는 사이클이 없는 무방향 그래프이다. 트리에서는 어떤 두 노드를 선택해도 둘 사이에 경로가 항상 하나만 존재하게 된다. 트리에서 어떤 두 노드를 선택해서 양쪽으로 쫙 당길 때, 가장 길게 늘어나는 경우가 있을 것이다. 이럴 때 트리의 모든 노드들은 이 두 노드를 지름의 끝 점으로 하는 원 안에 들어가게 된다.
 *
 *
 *
 * 이런 두 노드 사이의 경로의 길이를 트리의 지름이라고 한다. 정확히 정의하자면 트리에 존재하는 모든 경로들 중에서 가장 긴 것의 길이를 말한다.
 *
 * 입력으로 루트가 있는 트리를 가중치가 있는 간선들로 줄 때, 트리의 지름을 구해서 출력하는 프로그램을 작성하시오. 아래와 같은 트리가 주어진다면 트리의 지름은 45가 된다.
 *
 *
 *
 * 트리의 노드는 1부터 n까지 번호가 매겨져 있다.
 *
 * 입력
 * 파일의 첫 번째 줄은 노드의 개수 n(1 ≤ n ≤ 10,000)이다. 둘째 줄부터 n-1개의 줄에 각 간선에 대한 정보가 들어온다. 간선에 대한 정보는 세 개의 정수로 이루어져 있다. 첫 번째 정수는 간선이 연결하는 두 노드 중 부모 노드의 번호를 나타내고, 두 번째 정수는 자식 노드를, 세 번째 정수는 간선의 가중치를 나타낸다. 간선에 대한 정보는 부모 노드의 번호가 작은 것이 먼저 입력되고, 부모 노드의 번호가 같으면 자식 노드의 번호가 작은 것이 먼저 입력된다. 루트 노드의 번호는 항상 1이라고 가정하며, 간선의 가중치는 100보다 크지 않은 양의 정수이다.
 *
 * 출력
 * 첫째 줄에 트리의 지름을 출력한다.
 *
 * 예제 입력 1
 * 12
 * 1 2 3
 * 1 3 2
 * 2 4 5
 * 3 5 11
 * 3 6 9
 * 4 7 1
 * 4 8 7
 * 5 9 15
 * 5 10 4
 * 6 11 6
 * 6 12 10
 * 예제 출력 1
 * 45
 */

package gold;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class P1967 {
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  StringTokenizer st;

  int N;
  int maxValue = 1;
  int max;
  List<Node>[] tree;
  boolean[] visited;

  public static void main(String[] args) throws IOException {
      new P1967().solution();
  }

  public void solution() throws IOException {
    N = Integer.parseInt(br.readLine());

    tree = new ArrayList[N + 1];
    visited = new boolean[N + 1];
    for (int i = 1; i <= N; i++) {
      tree[i] = new ArrayList<>();
    }

    for (int i = 0; i < N - 1; i++) {
      st = new StringTokenizer(br.readLine());
      int s = Integer.parseInt(st.nextToken());
      int v = Integer.parseInt(st.nextToken());
      int d = Integer.parseInt(st.nextToken());

      tree[s].add(new Node(v, d));
      tree[v].add(new Node(s, d));

    }

    DFS(1, 0);
    Arrays.fill(visited, false);
    DFS(maxValue, 0);

    bw.write(max + System.lineSeparator());
    bw.flush();

    bw.close();
    br.close();
  }

  private void DFS(int vertex, int dist) {
    if (dist > max) {
      max = dist;
      maxValue = vertex;
    }

    visited[vertex] = true;

    for (Node node : tree[vertex]) {
      if (!visited[node.v]) {
        visited[node.v] = true;
        DFS(node.v, dist + node.d);
      }
    }
  }

  static class Node {
    int v;
    int d;

    public Node(int v, int d) {
      this.v = v;
      this.d = d;
    }
  }
}
