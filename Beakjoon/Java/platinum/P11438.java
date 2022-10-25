/**
 *
 * URL : https://www.acmicpc.net/problem/11438
 *
 * LCA2
 *
 * 문제
 * N(2 ≤ N ≤ 100,000)개의 정점으로 이루어진 트리가 주어진다. 트리의 각 정점은 1번부터 N번까지 번호가 매겨져 있으며, 루트는 1번이다.
 *
 * 두 노드의 쌍 M(1 ≤ M ≤ 100,000)개가 주어졌을 때, 두 노드의 가장 가까운 공통 조상이 몇 번인지 출력한다.
 *
 * 입력
 * 첫째 줄에 노드의 개수 N이 주어지고, 다음 N-1개 줄에는 트리 상에서 연결된 두 정점이 주어진다. 그 다음 줄에는 가장 가까운 공통 조상을 알고싶은 쌍의 개수 M이 주어지고, 다음 M개 줄에는 정점 쌍이 주어진다.
 *
 * 출력
 * M개의 줄에 차례대로 입력받은 두 정점의 가장 가까운 공통 조상을 출력한다.
 *
 * 예제 입력 1
 * 15
 * 1 2
 * 1 3
 * 2 4
 * 3 7
 * 6 2
 * 3 8
 * 4 9
 * 2 5
 * 5 11
 * 7 13
 * 10 4
 * 11 15
 * 12 5
 * 14 7
 * 6
 * 6 11
 * 10 9
 * 2 6
 * 7 6
 * 8 13
 * 8 15
 * 예제 출력 1
 * 2
 * 4
 * 2
 * 1
 * 3
 * 1
 */

package platinum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P11438 {

    private static ArrayList<Integer>[] tree;
    private static int[][] parent;
    private static int[] depth;
    private static boolean[] visited;
    private static int kmax;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        tree = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            tree[s].add(e);
            tree[e].add(s);
        }

        depth = new int[N + 1];
        visited = new boolean[N + 1];

        int temp = 1;
        kmax = 0;
        // 최대 가능한 depth 구하기
        while (temp <= N) {
            temp <<= 1;
            kmax++;
        }
        parent = new int[kmax + 1][N + 1];

        BFS(1);

        // parent 배열 2^k번째 채우기
        for (int k = 1; k <= kmax; k++) {
            for (int n = 1; n <= N; n++) {
                parent[k][n] = parent[k - 1][parent[k - 1][n]];
            }
        }

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            System.out.println(excuteLCA(a, b));
        }
    }

    private static int excuteLCA(int a, int b) {
        if (depth[a] > depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }

        // 선택된 두 노드의 깊이 맞추기
        for (int k = kmax; k >= 0; k--) {
            if (Math.pow(2, k) <= depth[b] - depth[a]) {
                if (depth[a] <= depth[parent[k][b]]) {
                    b = parent[k][b];
                }
            }
        }

        // 2^k만큼 올라가면서 최소 공통 조상 찾기
        for (int k = kmax; k >= 0; k--) {
            if (parent[k][a] != parent[k][b]) {
                a = parent[k][a];
                b = parent[k][b];
            }
        }

        int LCA = a;
        if (a != b) {
            LCA = parent[0][LCA];
        }

        return LCA;
    }

    private static void BFS(int node) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(node);
        visited[node] = true;

        int level = 1;
        int count = 0;
        int currentLevelSize = 1;
        while (!q.isEmpty()) {
            int now = q.poll();
            for (int next : tree[now]) {
                if (!visited[next]) {
                    q.offer(next);
                    visited[next] = true;
                    parent[0][next] = now;
                    depth[next] = level;
                }
            }
            count++;
            if (count == currentLevelSize) {
                count = 0;
                currentLevelSize = q.size();
                level++;
            }
        }
    }
}
