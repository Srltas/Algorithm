/**
 *
 * URL : https://www.acmicpc.net/problem/1753
 *
 * 최단경로
 *
 * 문제
 * 방향그래프가 주어지면 주어진 시작점에서 다른 모든 정점으로의 최단 경로를 구하는 프로그램을 작성하시오. 단, 모든 간선의 가중치는 10 이하의 자연수이다.
 *
 * 입력
 * 첫째 줄에 정점의 개수 V와 간선의 개수 E가 주어진다. (1 ≤ V ≤ 20,000, 1 ≤ E ≤ 300,000) 모든 정점에는 1부터 V까지 번호가 매겨져 있다고 가정한다. 둘째 줄에는 시작 정점의 번호 K(1 ≤ K ≤ V)가 주어진다. 셋째 줄부터 E개의 줄에 걸쳐 각 간선을 나타내는 세 개의 정수 (u, v, w)가 순서대로 주어진다. 이는 u에서 v로 가는 가중치 w인 간선이 존재한다는 뜻이다. u와 v는 서로 다르며 w는 10 이하의 자연수이다. 서로 다른 두 정점 사이에 여러 개의 간선이 존재할 수도 있음에 유의한다.
 *
 * 출력
 * 첫째 줄부터 V개의 줄에 걸쳐, i번째 줄에 i번 정점으로의 최단 경로의 경로값을 출력한다. 시작점 자신은 0으로 출력하고, 경로가 존재하지 않는 경우에는 INF를 출력하면 된다.
 *
 * 예제 입력 1
 * 5 6
 * 1
 * 5 1 1
 * 1 2 2
 * 1 3 3
 * 2 3 4
 * 2 4 5
 * 3 4 6
 * 예제 출력 1
 * 0
 * 2
 * 3
 * 7
 * INF
 */

package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P1753 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(br.readLine());

        // 그래프 생성
        List<EdgeD>[] list = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            list[i] = new ArrayList<EdgeD>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            list[u].add(new EdgeD(v, w));
        }

        // 방문기록 배열
        boolean[] visited = new boolean[V + 1];
        // 거리기록 배열
        int[] distance = new int[V + 1];
        for (int i = 1; i <= V; i++) {
            distance[i] = Integer.MAX_VALUE;
        }

        PriorityQueue<EdgeD> q = new PriorityQueue();
        // K를 시작점으로 설정
        q.offer(new EdgeD(K, 0));
        distance[K] = 0;

        while (!q.isEmpty()) {
            EdgeD current = q.poll();
            int currentVertex = current.vertex;

            // 이미 방문한적 있으면 큐에 넣지 않음
            if (visited[currentVertex]) {
                continue;
            }

            visited[currentVertex] = true;
            for (int i = 0; i < list[currentVertex].size(); i++) {
                EdgeD e = list[currentVertex].get(i);
                int next = e.vertex;
                int value = e.value;

                // 최소 거리로 업데이트
                if (distance[next] > distance[currentVertex] + value) {
                    distance[next] = value + distance[currentVertex];
                    q.offer(new EdgeD(next, distance[next]));
                }
            }
        }

        for (int i = 1; i <= V; i++) {
            if (visited[i]) {
                System.out.println(distance[i]);
            } else {
                System.out.println("INF");
            }
        }
    }
}

class EdgeD implements Comparable<EdgeD> {

    int vertex, value;

    EdgeD (int vertex, int value) {
        this.vertex = vertex;
        this.value = value;
    }

    @Override
    public int compareTo(EdgeD e) {
        if (this.value > e.value) {
            return 1;
        } else {
            return -1;
        }
    }
}
