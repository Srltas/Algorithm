/**
 *
 * URL : https://www.acmicpc.net/problem/1504
 *
 * 특정한 최단 경로
 *
 * 문제
 * 방향성이 없는 그래프가 주어진다. 세준이는 1번 정점에서 N번 정점으로 최단 거리로 이동하려고 한다. 또한 세준이는 두 가지 조건을 만족하면서 이동하는 특정한 최단 경로를 구하고 싶은데, 그것은 바로 임의로 주어진 두 정점은 반드시 통과해야 한다는 것이다.
 *
 * 세준이는 한번 이동했던 정점은 물론, 한번 이동했던 간선도 다시 이동할 수 있다. 하지만 반드시 최단 경로로 이동해야 한다는 사실에 주의하라. 1번 정점에서 N번 정점으로 이동할 때, 주어진 두 정점을 반드시 거치면서 최단 경로로 이동하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 정점의 개수 N과 간선의 개수 E가 주어진다. (2 ≤ N ≤ 800, 0 ≤ E ≤ 200,000) 둘째 줄부터 E개의 줄에 걸쳐서 세 개의 정수 a, b, c가 주어지는데, a번 정점에서 b번 정점까지 양방향 길이 존재하며, 그 거리가 c라는 뜻이다. (1 ≤ c ≤ 1,000) 다음 줄에는 반드시 거쳐야 하는 두 개의 서로 다른 정점 번호 v1과 v2가 주어진다. (v1 ≠ v2, v1 ≠ N, v2 ≠ 1) 임의의 두 정점 u와 v사이에는 간선이 최대 1개 존재한다.
 *
 * 출력
 * 첫째 줄에 두 개의 정점을 지나는 최단 경로의 길이를 출력한다. 그러한 경로가 없을 때에는 -1을 출력한다.
 *
 * 예제 입력 1
 * 4 6
 * 1 2 3
 * 2 3 3
 * 3 4 1
 * 1 3 5
 * 2 4 5
 * 1 4 4
 * 2 3
 * 예제 출력 1
 * 7
 */

package gold;

import java.io.*;
import java.util.*;

public class P1504 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    final int MAX = 200_000_000;

    List<Edge>[] list;
    int[] distance;
    boolean[] visited;

    public static void main(String[] args) throws IOException {
        new P1504().solution();
    }

    public void solution() throws IOException {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        visited = new boolean[N + 1];
        distance = new int[N + 1];
        list = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            list[a].add(new Edge(b, c));
            list[b].add(new Edge(a, c));
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        // 1 -> v1 -> v2 -> N
        int shortDistance1 = 0;
        shortDistance1 += dijkstra(1, v1);
        shortDistance1 += dijkstra(v1, v2);
        shortDistance1 += dijkstra(v2, N);

        // 1 -> v2 -> v1 -> N
        int shortDistance2 = 0;
        shortDistance2 += dijkstra(1,v2);
        shortDistance2 += dijkstra(v2, v1);
        shortDistance2 += dijkstra(v1, N);

        int result = Math.min(shortDistance1, shortDistance2);

        if (result >= MAX) {
            bw.write(-1 + System.lineSeparator());
        } else {
            bw.write(result + System.lineSeparator());
        }
        bw.flush();

        bw.close();
        br.close();
    }

    private int dijkstra(int start, int end) {
        Arrays.fill(distance, MAX);
        Arrays.fill(visited, false);

        Queue<Edge> queue = new PriorityQueue<>();
        queue.offer(new Edge(start, 0));
        distance[start] = 0;

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
        return distance[end];
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
            return Integer.compare(this.value, nextEdge.value);
        }
    }
}
