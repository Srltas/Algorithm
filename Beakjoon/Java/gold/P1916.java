/**
 *
 * URL : https://www.acmicpc.net/problem/1916
 *
 * 최소비용 구하기
 *
 * 문제
 * N개의 도시가 있다. 그리고 한 도시에서 출발하여 다른 도시에 도착하는 M개의 버스가 있다. 우리는 A번째 도시에서 B번째 도시까지 가는데 드는 버스 비용을 최소화 시키려고 한다. A번째 도시에서 B번째 도시까지 가는데 드는 최소비용을 출력하여라. 도시의 번호는 1부터 N까지이다.
 *
 * 입력
 * 첫째 줄에 도시의 개수 N(1 ≤ N ≤ 1,000)이 주어지고 둘째 줄에는 버스의 개수 M(1 ≤ M ≤ 100,000)이 주어진다. 그리고 셋째 줄부터 M+2줄까지 다음과 같은 버스의 정보가 주어진다. 먼저 처음에는 그 버스의 출발 도시의 번호가 주어진다. 그리고 그 다음에는 도착지의 도시 번호가 주어지고 또 그 버스 비용이 주어진다. 버스 비용은 0보다 크거나 같고, 100,000보다 작은 정수이다.
 *
 * 그리고 M+3째 줄에는 우리가 구하고자 하는 구간 출발점의 도시번호와 도착점의 도시번호가 주어진다. 출발점에서 도착점을 갈 수 있는 경우만 입력으로 주어진다.
 *
 * 출력
 * 첫째 줄에 출발 도시에서 도착 도시까지 가는데 드는 최소 비용을 출력한다.
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
 */

package gold;

import java.io.*;
import java.util.*;

public class P1916 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        List<Node>[] list = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<Node>();
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            list[start].add(new Node(end, value));
        }

        boolean[] visited = new boolean[N + 1];
        int[] distance = new int[N + 1];

        // 거리 배열을 큰 수로 초기화
        Arrays.fill(distance, Integer.MAX_VALUE);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int startNode = Integer.parseInt(st.nextToken());
        int endNode = Integer.parseInt(st.nextToken());

        // 다익스트라 알고리즘 구현
        PriorityQueue<Node> q = new PriorityQueue();
        distance[startNode] = 0;
        q.offer(new Node(startNode, 0));

        while (!q.isEmpty()) {
            Node nowNode = q.poll();
            int now = nowNode.targetNode;

            if (!visited[now]) {
                visited[now] = true;
                for (Node nextNode : list[now]) {
                    if (!visited[nextNode.targetNode] && distance[nextNode.targetNode] > distance[now] + nextNode.value) {
                        distance[nextNode.targetNode] = distance[now] + nextNode.value;
                        q.offer(new Node(nextNode.targetNode, distance[nextNode.targetNode]));
                    }
                }
            }
        }
        bw.write(distance[endNode] + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}

class Node implements Comparable<Node> {
    int targetNode;
    int value;

    public Node(int targetNode, int value) {
        this.targetNode = targetNode;
        this.value = value;
    }

    @Override
    public int compareTo(Node o) {
        return value - o.value;
    }
}
