/**
 *
 * URL : https://www.acmicpc.net/problem/16947
 *
 * 서울 지하철 2호선
 *
 * 문제
 * 서울 지하철 2호선은 다음과 같이 생겼다.
 *
 *
 *
 * 지하철 2호선에는 51개의 역이 있고, 역과 역 사이를 연결하는 구간이 51개 있다. 즉, 정점이 51개이고, 양방향 간선이 51개인 그래프로 나타낼 수 있다. 2호선은 순환선 1개와 2개의 지선으로 이루어져 있다. 한 역에서 출발해서 계속 가면 다시 출발한 역으로 돌아올 수 있는 노선을 순환선이라고 한다. 지선은 순환선에 속하는 한 역에서 시작하는 트리 형태의 노선이다.
 *
 * 두 역(정점) 사이의 거리는 지나야 하는 구간(간선)의 개수이다. 역 A와 순환선 사이의 거리는 A와 순환선에 속하는 역 사이의 거리 중 최솟값이다.
 *
 * 지하철 2호선과 같은 형태의 노선도가 주어졌을 때, 각 역과 순환선 사이의 거리를 구해보자.
 *
 * 입력
 * 첫째 줄에 역의 개수 N(3 ≤ N ≤ 3,000)이 주어진다. 둘째 줄부터 N개의 줄에는 역과 역을 연결하는 구간의 정보가 주어진다. 같은 구간이 여러 번 주어지는 경우는 없고, 역은 1번부터 N번까지 번호가 매겨져 있다. 임의의 두 역 사이에 경로가 항상 존재하는 노선만 입력으로 주어진다.
 *
 * 출력
 * 총 N개의 정수를 출력한다. 1번 역과 순환선 사이의 거리, 2번 역과 순환선 사이의 거리, ..., N번 역과 순환선 사이의 거리를 공백으로 구분해 출력한다.
 *
 * 예제 입력 1
 * 4
 * 1 3
 * 4 3
 * 4 2
 * 1 2
 * 예제 출력 1
 * 0 0 0 0
 * 예제 입력 2
 * 6
 * 1 2
 * 3 4
 * 6 4
 * 2 3
 * 1 3
 * 3 5
 * 예제 출력 2
 * 0 0 0 1 1 2
 * 예제 입력 3
 * 51
 * 1 2
 * 2 3
 * 3 4
 * 4 5
 * 5 6
 * 6 7
 * 7 8
 * 8 9
 * 9 10
 * 10 11
 * 11 12
 * 12 13
 * 13 14
 * 14 15
 * 15 16
 * 16 17
 * 17 18
 * 18 19
 * 19 20
 * 20 21
 * 21 22
 * 22 23
 * 23 24
 * 24 25
 * 25 26
 * 26 27
 * 27 28
 * 28 29
 * 29 30
 * 30 31
 * 31 32
 * 32 33
 * 33 34
 * 34 35
 * 35 36
 * 36 37
 * 37 38
 * 38 39
 * 39 40
 * 40 41
 * 41 42
 * 42 43
 * 43 1
 * 11 44
 * 44 45
 * 45 46
 * 46 47
 * 34 48
 * 48 49
 * 49 50
 * 50 51
 * 예제 출력 3
 * 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 2 3 4 1 2 3 4
 * 서울 지하철 2호선 노선이다.
 *
 * 1번부터 43번까지는 역 번호와 일치하며, 성수역은 11번, 신도림역은 34번이다.
 *
 * 예제 입력 4
 * 38
 * 1 2
 * 2 3
 * 3 4
 * 4 5
 * 5 6
 * 6 1
 * 1 7
 * 7 8
 * 8 9
 * 9 10
 * 10 11
 * 11 12
 * 12 13
 * 13 14
 * 14 15
 * 15 16
 * 16 17
 * 17 18
 * 18 19
 * 19 20
 * 20 21
 * 21 22
 * 22 23
 * 23 24
 * 24 25
 * 25 26
 * 26 27
 * 27 28
 * 28 29
 * 29 30
 * 30 31
 * 31 32
 * 32 33
 * 33 34
 * 34 35
 * 35 36
 * 36 37
 * 37 38
 * 예제 출력 4
 * 0 0 0 0 0 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32
 * 서울 지하철 6호선이다. 실제로는 일부 구간이 양방향이 아니다.
 *
 * 예제 입력 5
 * 12
 * 1 3
 * 3 4
 * 4 5
 * 5 6
 * 6 7
 * 7 8
 * 8 4
 * 2 3
 * 7 9
 * 9 12
 * 7 10
 * 10 11
 * 예제 출력 5
 * 2 2 1 0 0 0 0 0 1 1 2 2
 */

package gold;

import java.io.*;
import java.util.*;

public class P16947 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    int N;
    List<Integer>[] list;
    boolean[] cycle;
    boolean[] visited;
    int[] distance;

    public static void main(String[] args) throws IOException {
        new P16947().solution();
    }

    public void solution() throws IOException {
        N = Integer.parseInt(br.readLine());

        list = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        cycle = new boolean[N + 1];
        distance = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int E = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());

            list[E].add(V);
            list[V].add(E);
        }

        // 사이클을 구함
        for (int i = 1; i <= N; i++) {
            if (DFS(i, i, i)) {
                break;
            }
            visited = new boolean[N + 1];
        }

        // 노드와 사이클 거리를 구함
        BFS();

        for (int i = 1; i <= N; i++) {
            bw.write(distance[i] + " ");
        }
        bw.flush();

        bw.close();
        br.close();
    }

    public void BFS() {
        Queue<Integer> queue = new LinkedList<>();
        visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            if (cycle[i]) {
                visited[i] = true;
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int next : list[node]) {
                if (!visited[next]) {
                    queue.offer(next);
                    visited[next] = true;
                    distance[next] = distance[node] + 1;
                }
            }
        }
    }

    public boolean DFS(int start, int prev, int now) {
        visited[now] = true;

        for (int next : list[now]) {
            if (!visited[next]) {
                if (DFS(start, now, next)) {
                    cycle[next] = true;
                    return true;
                }
            } else if (next != prev && next == start) {
                cycle[next]= true;
                return true;
            }
        }

        return false;
    }
}
