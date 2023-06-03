/**
 *
 * URL : https://www.acmicpc.net/problem/11779
 *
 * 최소비용 구하기 2
 *
 * 문제
 * n(1≤n≤1,000)개의 도시가 있다. 그리고 한 도시에서 출발하여 다른 도시에 도착하는 m(1≤m≤100,000)개의 버스가 있다. 우리는 A번째 도시에서 B번째 도시까지 가는데 드는 버스 비용을 최소화 시키려고 한다. 그러면 A번째 도시에서 B번째 도시 까지 가는데 드는 최소비용과 경로를 출력하여라. 항상 시작점에서 도착점으로의 경로가 존재한다.
 *
 * 입력
 * 첫째 줄에 도시의 개수 n(1≤n≤1,000)이 주어지고 둘째 줄에는 버스의 개수 m(1≤m≤100,000)이 주어진다. 그리고 셋째 줄부터 m+2줄까지 다음과 같은 버스의 정보가 주어진다. 먼저 처음에는 그 버스의 출발 도시의 번호가 주어진다. 그리고 그 다음에는 도착지의 도시 번호가 주어지고 또 그 버스 비용이 주어진다. 버스 비용은 0보다 크거나 같고, 100,000보다 작은 정수이다.
 *
 * 그리고 m+3째 줄에는 우리가 구하고자 하는 구간 출발점의 도시번호와 도착점의 도시번호가 주어진다.
 *
 * 출력
 * 첫째 줄에 출발 도시에서 도착 도시까지 가는데 드는 최소 비용을 출력한다.
 *
 * 둘째 줄에는 그러한 최소 비용을 갖는 경로에 포함되어있는 도시의 개수를 출력한다. 출발 도시와 도착 도시도 포함한다.
 *
 * 셋째 줄에는 최소 비용을 갖는 경로를 방문하는 도시 순서대로 출력한다.
 *
 * 예제 입력 1
 * 5
 * 8
 * 1 2 2
 * 1 3 3
 * 1 4 1
 * 1 5 10
 * 2 4 2
 * 3 4 1
 * 3 5 1
 * 4 5 3
 * 1 5
 * 예제 출력 1
 * 4
 * 3
 * 1 3 5
 */

package gold;

import java.io.*;
import java.util.*;

public class P11779 {
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  StringTokenizer st;

  List<Node>[] list;
  int[] distance;
  int[] preCity;
  
  public static void main(String[] args) throws IOException {
      new P11779().solution();
  }
  
  public void solution() throws IOException {
    int N = Integer.parseInt(br.readLine());
    int M = Integer.parseInt(br.readLine());

    distance = new int[N + 1];
    Arrays.fill(distance, Integer.MAX_VALUE);

    preCity = new int[N + 1];

    list = new ArrayList[N + 1];
    for (int i = 1; i <= N; i++) {
      list[i] = new ArrayList<>();
    }

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int s = Integer.parseInt(st.nextToken());
      int e = Integer.parseInt(st.nextToken());
      int d = Integer.parseInt(st.nextToken());

      list[s].add(new Node(e,d));
    }

    st = new StringTokenizer(br.readLine());
    int start = Integer.parseInt(st.nextToken());
    int end = Integer.parseInt(st.nextToken());

    dijkstra(start);
    bw.write(distance[end] + System.lineSeparator());

    Stack<Integer> stack = new Stack<>();
    stack.push(end);
    int count = 1;
    while (preCity[end] != 0) {
      count++;
      stack.push(preCity[end]);
      end = preCity[end];
    }

    bw.write(count + System.lineSeparator());
    while (!stack.isEmpty()) {
      bw.write(stack.pop() + " ");
    }
    bw.flush();
  
    bw.close();
    br.close();
  }

  private void dijkstra(int start) {
    PriorityQueue<Node> queue = new PriorityQueue<>();
    queue.offer(new Node(start, 0));
    distance[start] = 0;

    while (!queue.isEmpty()) {
      Node currNode = queue.poll();

      if (distance[currNode.end] < currNode.distance) {
        continue;
      }

      for (Node nextNode : list[currNode.end]) {
        if (distance[nextNode.end] > distance[currNode.end] + nextNode.distance) {
          distance[nextNode.end] = distance[currNode.end] + nextNode.distance;
          queue.offer(new Node(nextNode.end, distance[nextNode.end]));
          preCity[nextNode.end] = currNode.end;
        }
      }
    }
  }

  static class Node implements Comparable<Node> {
    int end;
    int distance;

    public Node(int end, int distance) {
      this.end = end;
      this.distance = distance;
    }

    @Override
    public int compareTo(Node node) {
      return this.distance - node.distance;
    }
  }
}
