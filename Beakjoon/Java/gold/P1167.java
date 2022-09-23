/**
 *
 * URL : https://www.acmicpc.net/problem/1167
 *
 * 트리의 지름
 *
 * 문제
 * 트리의 지름이란, 트리에서 임의의 두 점 사이의 거리 중 가장 긴 것을 말한다. 트리의 지름을 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 트리가 입력으로 주어진다. 먼저 첫 번째 줄에서는 트리의 정점의 개수 V가 주어지고 (2 ≤ V ≤ 100,000)둘째 줄부터 V개의 줄에 걸쳐 간선의 정보가 다음과 같이 주어진다. 정점 번호는 1부터 V까지 매겨져 있다.
 *
 * 먼저 정점 번호가 주어지고, 이어서 연결된 간선의 정보를 의미하는 정수가 두 개씩 주어지는데, 하나는 정점번호, 다른 하나는 그 정점까지의 거리이다. 예를 들어 네 번째 줄의 경우 정점 3은 정점 1과 거리가 2인 간선으로 연결되어 있고, 정점 4와는 거리가 3인 간선으로 연결되어 있는 것을 보여준다. 각 줄의 마지막에는 -1이 입력으로 주어진다. 주어지는 거리는 모두 10,000 이하의 자연수이다.
 *
 * 출력
 * 첫째 줄에 트리의 지름을 출력한다.
 *
 * 예제 입력 1  복사
 * 5
 * 1 3 2 -1
 * 2 4 4 -1
 * 3 1 2 4 3 -1
 * 4 2 4 3 3 5 6 -1
 * 5 4 6 -1
 * 예제 출력 1  복사
 * 11
 */

package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P1167 {

    static List<Edge>[] A;
    static boolean[] visited;
    static int[] distance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        A = new ArrayList[N + 1];
        for (int i = 1; i < N + 1; i++) {
            A[i] = new ArrayList<Edge>();
        }

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());

            while (true) {
                int E = Integer.parseInt(st.nextToken());
                if (E == -1) {
                    break;
                }

                int V = Integer.parseInt(st.nextToken());
                A[S].add(new Edge(E,V));
            }
        }

        distance = new int[N + 1];
        visited = new boolean[N + 1];
        bfs(1);

        int max = 1;
        for (int i = 2; i < N + 1; i++) {
            if (distance[i] > distance[max]) {
                max = i;
            }
        }

        distance = new int[N + 1];
        visited = new boolean[N + 1];
        bfs(max);
        Arrays.sort(distance);
        System.out.println(distance[N]);
    }

    static void bfs(int node) {
        Queue<Integer> q = new LinkedList<Integer>();
        q.offer(node);
        visited[node] = true;

        while (!q.isEmpty()) {
            int n = q.poll();
            for (Edge edge : A[n]) {
                int e = edge.E;
                int v = edge.V;

                if (!visited[e]) {
                    visited[e] = true;
                    distance[e] = distance[n] + v;
                    q.offer(e);
                }
            }
        }
    }
}

class Edge {
    int E;
    int V;

    public Edge(int e, int v) {
        E = e;
        V = v;
    }
}

