/**
 *
 * URL : https://www.acmicpc.net/problem/14284
 *
 * 간선 이어가기 2
 *
 * 문제
 * 정점 n개, 0개의 간선으로 이루어진 무방향 그래프가 주어진다. 그리고 m개의 가중치 간선의 정보가 있는 간선리스트가 주어진다. 간선리스트에 있는 간선 하나씩 그래프에 추가해 나갈 것이다. 이때, 특정 정점 s와 t가 연결이 되는 시점에서 간선 추가를 멈출 것이다. 연결이란 두 정점이 간선을 통해 방문 가능한 것을 말한다.
 *
 * s와 t가 연결이 되는 시점의 간선의 가중치의 합이 최소가 되게 추가하는 간선의 순서를 조정할 때, 그 최솟값을 구하시오.
 *
 * 입력
 * 첫째 줄에 정점의 개수 n, 간선리스트의 간선 수 m이 주어진다.(2≤n≤5000,1≤m≤100,000)
 *
 * 다음 m줄에는 a,b,c가 주어지는데, 이는 a와 b는 c의 가중치를 가짐을 말한다. (1≤a,b≤n,1≤c≤100,a≠b)
 *
 * 다음 줄에는 두 정점 s,t가 주어진다. (1≤s,t≤n,s≠t)
 *
 * 모든 간선을 연결하면 그래프는 연결 그래프가 됨이 보장된다.
 *
 * 출력
 * s와 t가 연결되는 시점의 간선의 가중치 합의 최솟값을 출력하시오,
 *
 * 예제 입력 1
 * 8 9
 * 1 2 3
 * 1 3 2
 * 1 4 4
 * 2 5 2
 * 3 6 1
 * 4 7 3
 * 5 8 6
 * 6 8 2
 * 7 8 7
 * 1 8
 * 예제 출력 1
 * 5
 */

package gold;

import java.io.*;
import java.util.*;

public class P14284 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    List<Edge>[] list;
    int[] distance;
    boolean[] visited;

    final int MAX_VALUE = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        new P14284().solution();
    }

    public void solution() throws IOException {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        list = new ArrayList[N + 1];
        distance = new int[N + 1];
        visited = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }
        Arrays.fill(distance, MAX_VALUE);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            list[u].add(new Edge(v, w));
            list[v].add(new Edge(u, w));
        }

        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        distance[s] = 0;
        dijkstra(new Edge(s, 0));

        bw.write(distance[t] + System.lineSeparator());
        bw.flush();

        bw.close();
        br.close();
    }

    private void dijkstra(Edge startEdge) {
        Queue<Edge> queue = new PriorityQueue<>();
        queue.offer(startEdge);

        while (!queue.isEmpty()) {
            Edge currEdge = queue.poll();
            if (!visited[currEdge.vertex]) {
                visited[currEdge.vertex] = true;
                for (Edge nextEdge : list[currEdge.vertex]) {
                    if (distance[nextEdge.vertex] > distance[currEdge.vertex] + nextEdge.value) {
                        distance[nextEdge.vertex] = distance[currEdge.vertex] + nextEdge.value;
                        queue.offer(new Edge(nextEdge.vertex, distance[nextEdge.vertex]));
                    }
                }
            }
        }
    }

    static class Edge implements Comparable<Edge> {
        int vertex;
        int value;

        public Edge(int vertex, int value) {
            this.vertex = vertex;
            this.value = value;
        }

        @Override
        public int compareTo(Edge nextEdge) {
            if (this.value > nextEdge.value) {
                return 1;
            }
            return -1;
        }
    }
}
