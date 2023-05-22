/**
 *
 * URL : https://www.acmicpc.net/problem/5972
 *
 * 택배 배송
 *
 * 문제
 * 농부 현서는 농부 찬홍이에게 택배를 배달해줘야 합니다. 그리고 지금, 갈 준비를 하고 있습니다. 평화롭게 가려면 가는 길에 만나는 모든 소들에게 맛있는 여물을 줘야 합니다. 물론 현서는 구두쇠라서 최소한의 소들을 만나면서 지나가고 싶습니다.
 *
 * 농부 현서에게는 지도가 있습니다. N (1 <= N <= 50,000) 개의 헛간과, 소들의 길인 M (1 <= M <= 50,000) 개의 양방향 길이 그려져 있고, 각각의 길은 C_i (0 <= C_i <= 1,000) 마리의 소가 있습니다. 소들의 길은 두 개의 떨어진 헛간인 A_i 와 B_i (1 <= A_i <= N; 1 <= B_i <= N; A_i != B_i)를 잇습니다. 두 개의 헛간은 하나 이상의 길로 연결되어 있을 수도 있습니다. 농부 현서는 헛간 1에 있고 농부 찬홍이는 헛간 N에 있습니다.
 *
 * 다음 지도를 참고하세요.
 *
 *            [2]---
 *           / |    \
 *          /1 |     \ 6
 *         /   |      \
 *      [1]   0|    --[3]
 *         \   |   /     \2
 *         4\  |  /4      [6]
 *           \ | /       /1
 *            [4]-----[5]
 *                 3
 * 농부 현서가 선택할 수 있는 최선의 통로는 1 -> 2 -> 4 -> 5 -> 6 입니다. 왜냐하면 여물의 총합이 1 + 0 + 3 + 1 = 5 이기 때문입니다.
 *
 * 농부 현서의 지도가 주어지고, 지나가는 길에 소를 만나면 줘야할 여물의 비용이 주어질 때 최소 여물은 얼마일까요? 농부 현서는 가는 길의 길이는 고려하지 않습니다.
 *
 * 입력
 * 첫째 줄에 N과 M이 공백을 사이에 두고 주어집니다.
 *
 * 둘째 줄부터 M+1번째 줄까지 세 개의 정수 A_i, B_i, C_i가 주어집니다.
 *
 * 출력
 * 첫째 줄에 농부 현서가 가져가야 될 최소 여물을 출력합니다.
 *
 * 예제 입력 1
 * 6 8
 * 4 5 3
 * 2 4 0
 * 4 1 4
 * 2 1 1
 * 5 6 1
 * 3 6 2
 * 3 2 6
 * 3 4 4
 * 예제 출력 1
 * 5
 */

package gold;

import java.io.*;
import java.util.*;

public class P5972 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    List<Edge>[] list;
    boolean[] visited;
    int[] distance;

    public static void main(String[] args) throws IOException {
        new P5972().solution();
    }

    public void solution() throws IOException {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        distance = new int[N + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        visited = new boolean[N + 1];
        list = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            list[s].add(new Edge(e, v));
            list[e].add(new Edge(s, v));
        }

        distance[1] = 0;
        dijkstra(new Edge(1, 0));

        bw.write(distance[N] + System.lineSeparator());
        bw.flush();

        bw.close();
        br.close();
    }

    private void dijkstra(Edge start) {
        Queue<Edge> queue = new PriorityQueue<>();
        queue.offer(start);

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
