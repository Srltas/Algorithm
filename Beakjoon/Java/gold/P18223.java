/**
 *
 * URL : https://www.acmicpc.net/problem/18223
 *
 * 민준이와 마산 그리고 건우
 *
 * 문제
 * 종강을 맞은 민준이는 고향인 마산으로 내려갈 계획을 짜고 있었다. 늘 그랬듯, 마산으로 갈 버스를 예약하려던 순간 민준이는 집으로 가는 다른 방법이 떠올랐다. 그것은 직접 지도를 보고 고향으로 가는 가장 짧은 길을 찾는 것이다.
 *
 * 그때, 먼저 고향으로 내려갔던 친구인 건우에게 연락이 왔다. 건우는 고향으로 내려가던 중 알 수 없는 일에 휘말려 외딴곳에 혼자 남겨지게 되었다. 건우는 유일한 구세주인 민준이에게 도움을 청한 것이었다. 그러나 마산의 남자인 민준이에게는 마산이 먼저였다. 민준이는 처량한 건우를 무시한 채 고향으로 떠나려고 했지만, 만약 고향으로 가는 길에 건우가 있다면 겸사겸사 도움을 줄 수 있을 것 같았다.
 *
 * 지도는 양방향 그래프 형태로 되어있다. 출발지는 1번 정점 마산은 V번 정점이다. 정점은 1~V까지 있다. 건우는 P번 정점에 있다.
 * 그리고 항상 1번 정점에서 P번과 V번 정점으로 갈 수 있는 경로가 존재한다.
 * 중복되는 간선과 자기 자신을 가리키는 간선은 존재하지 않는다.
 *
 * 아래와 같은 그래프가 있을 때,
 *
 *
 *
 * 위의 경우는 최단 경로가 두 가지 있다.
 * 1→3→4→5→6 또는 1→3→5→6 이다. 이것 중에서 건우가 있는 곳, 즉 4번 정점이 포함된 최단 경로가 있으므로 이 경우에는 민준이가 건우를 도울 수 있다.
 *
 * 민준이가 건우를 도와주는 경로의 길이가 최단 경로의 길이보다 길어지지 않는다면, 민준이는 반드시 건우를 도와주러 간다.
 *
 * 어쩌면 지킬 수도 있는 민준이의 우정을 위해 우리가 도와주자!
 *
 * 입력
 * 입력의 첫 번째 줄에 정점의 개수 V와 간선의 개수 E, 그리고 건우가 위치한 정점 P가 주어진다. (2 ≤ V  ≤ 5,000, 1 ≤ E ≤ 10,000, 1 ≤ P  ≤ V)
 *
 * 두 번째 줄부터 E개의 줄에 걸쳐 각 간선의 정보 a,b,c가 공백으로 구분되어 주어진다. 이는 a번 정점과 b번 정점 사이의 거리가 c임을 의미한다. (1 ≤ a,b ≤ V, 1 ≤ c  ≤ 10,000)
 *
 * 출력
 * 민준이가 찾은 최단 경로 위에 건우가 있다면 "SAVE HIM" 을 아니면 "GOOD BYE" 를 출력한다.
 *
 * 예제 입력 1
 * 6 7 4
 * 1 2 1
 * 1 3 1
 * 2 3 10
 * 3 4 1
 * 3 5 2
 * 4 5 1
 * 5 6 1
 * 예제 출력 1
 * SAVE HIM
 * 예제 입력 2
 * 4 3 3
 * 1 2 1
 * 2 3 1
 * 2 4 1
 * 예제 출력 2
 * GOOD BYE
 */

package gold;

import java.io.*;
import java.util.*;

public class P18223 {
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  StringTokenizer st;

  int V, E, P;
  List<Node>[] list;

  public static void main(String[] args) throws IOException {
      new P18223().solution();
  }

  public void solution() throws IOException {
    try {
      st = new StringTokenizer(br.readLine());
      V = Integer.parseInt(st.nextToken());
      E = Integer.parseInt(st.nextToken());
      P = Integer.parseInt(st.nextToken());

      list = new ArrayList[V + 1];
      for (int i = 1; i <= V; i++) {
        list[i] = new ArrayList<>();
      }

      for (int i = 0; i < E; i++) {
        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        list[a].add(new Node(b, c));
        list[b].add(new Node(a, c));
      }

      int geonWoo = 0;
      // start -> 건우
      geonWoo += dijkstra(1, P);

      // 건우 -> end
      geonWoo += dijkstra(P, V);

      int minDistance = 0;
      // start -> end
      minDistance = dijkstra(1, V);

      bw.write(geonWoo == minDistance ? "SAVE HIM" : "GOOD BYE");
      bw.flush();

    } finally {
      bw.close();
      br.close();
    }
  }

  private int dijkstra(int start, int end) {
    boolean[] visited = new boolean[V + 1];
    int[] distance = new int[V + 1];
    Arrays.fill(distance, Integer.MAX_VALUE);

    PriorityQueue<Node> queue = new PriorityQueue<>();
    queue.offer(new Node(start, 0));
    distance[start] = 0;

    while (!queue.isEmpty()) {
      Node currentNode = queue.poll();
      if (!visited[currentNode.no]) {
        visited[currentNode.no] = true;

        for (Node nextNode : list[currentNode.no]) {
          if (distance[nextNode.no] > distance[currentNode.no] + nextNode.value) {
            distance[nextNode.no] = distance[currentNode.no] + nextNode.value;
            queue.offer(new Node(nextNode.no, distance[nextNode.no]));
          }
        }
      }
    }
    return distance[end];
  }

  static class Node implements Comparable<Node> {
    int no;
    int value;

    public Node(int no, int value) {
      this.no = no;
      this.value = value;
    }

    @Override
    public int compareTo(Node node) {
      return Integer.compare(this.value, node.value);
    }
  }
}
