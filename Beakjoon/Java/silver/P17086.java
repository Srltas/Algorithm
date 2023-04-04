/**
 *
 * URL : https://www.acmicpc.net/problem/17086
 *
 * 아기 상어 2
 *
 * 문제
 * N×M 크기의 공간에 아기 상어 여러 마리가 있다. 공간은 1×1 크기의 정사각형 칸으로 나누어져 있다. 한 칸에는 아기 상어가 최대 1마리 존재한다.
 *
 * 어떤 칸의 안전 거리는 그 칸과 가장 거리가 가까운 아기 상어와의 거리이다. 두 칸의 거리는 하나의 칸에서 다른 칸으로 가기 위해서 지나야 하는 칸의 수이고, 이동은 인접한 8방향(대각선 포함)이 가능하다.
 *
 * 안전 거리가 가장 큰 칸을 구해보자.
 *
 * 입력
 * 첫째 줄에 공간의 크기 N과 M(2 ≤ N, M ≤ 50)이 주어진다. 둘째 줄부터 N개의 줄에 공간의 상태가 주어지며, 0은 빈 칸, 1은 아기 상어가 있는 칸이다. 빈 칸과 상어의 수가 각각 한 개 이상인 입력만 주어진다.
 *
 * 출력
 * 첫째 줄에 안전 거리의 최댓값을 출력한다.
 *
 * 예제 입력 1
 * 5 4
 * 0 0 1 0
 * 0 0 0 0
 * 1 0 0 0
 * 0 0 0 0
 * 0 0 0 1
 * 예제 출력 1
 * 2
 * 예제 입력 2
 * 7 4
 * 0 0 0 1
 * 0 1 0 0
 * 0 0 0 0
 * 0 0 0 1
 * 0 0 0 0
 * 0 1 0 0
 * 0 0 0 1
 * 예제 출력 2
 * 2
 */

package silver;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P17086 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    int[] dn = {0, 0, -1, 1, -1, -1, 1, 1};
    int[] dm = {-1, 1, 0, 0, -1, 1, -1, 1};

    int N, M;
    int[][] array;

    public static void main(String[] args) throws IOException {
        new P17086().solution();
    }

    public void solution() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        array = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                array[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int maxDistance = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (array[i][j] == 0) {
                    maxDistance = Math.max(maxDistance, BFS(new Node(i, j)));
                }
            }
        }
        bw.write(maxDistance + System.lineSeparator());
        bw.flush();

        bw.close();
        br.close();
    }

    public int BFS(Node node) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);

        boolean[][] visited = new boolean[N][M];
        visited[node.n][node.m] = true;

        int count = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                Node nextNode = queue.poll();
                for (int i = 0; i < 8; i++) {
                    int n = nextNode.n + dn[i];
                    int m = nextNode.m + dm[i];

                    if (n >= 0 && m >= 0 && n < N && m < M && !visited[n][m]) {
                        if (array[n][m] == 1) {
                            return count;
                        }

                        queue.offer(new Node(n, m));
                        visited[n][m] = true;
                    }
                }
            }
            count++;
        }

        return 0;
    }

    static class Node {
        int n;
        int m;

        public Node(int n, int m) {
            this.n = n;
            this.m = m;
        }
    }
}
