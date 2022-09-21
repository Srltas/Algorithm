/**
 *
 * URL : https://www.acmicpc.net/problem/2178
 *
 * 미로 탐색
 *
 * 문제
 * N×M크기의 배열로 표현되는 미로가 있다.
 *
 * 1	0	1	1	1	1
 * 1	0	1	0	1	0
 * 1	0	1	0	1	1
 * 1	1	1	0	1	1
 * 미로에서 1은 이동할 수 있는 칸을 나타내고, 0은 이동할 수 없는 칸을 나타낸다. 이러한 미로가 주어졌을 때, (1, 1)에서 출발하여 (N, M)의 위치로 이동할 때 지나야 하는 최소의 칸 수를 구하는 프로그램을 작성하시오. 한 칸에서 다른 칸으로 이동할 때, 서로 인접한 칸으로만 이동할 수 있다.
 *
 * 위의 예에서는 15칸을 지나야 (N, M)의 위치로 이동할 수 있다. 칸을 셀 때에는 시작 위치와 도착 위치도 포함한다.
 *
 * 입력
 * 첫째 줄에 두 정수 N, M(2 ≤ N, M ≤ 100)이 주어진다. 다음 N개의 줄에는 M개의 정수로 미로가 주어진다. 각각의 수들은 붙어서 입력으로 주어진다.
 *
 * 출력
 * 첫째 줄에 지나야 하는 최소의 칸 수를 출력한다. 항상 도착위치로 이동할 수 있는 경우만 입력으로 주어진다.
 *
 * 예제 입력 1  복사
 * 4 6
 * 101111
 * 101010
 * 101011
 * 111011
 * 예제 출력 1  복사
 * 15
 * 예제 입력 2  복사
 * 4 6
 * 110110
 * 110110
 * 111111
 * 111101
 * 예제 출력 2  복사
 * 9
 * 예제 입력 3  복사
 * 2 25
 * 1011101110111011101110111
 * 1110111011101110111011101
 * 예제 출력 3  복사
 * 38
 * 예제 입력 4  복사
 * 7 7
 * 1011111
 * 1110001
 * 1000001
 * 1000001
 * 1000001
 * 1000001
 * 1111111
 * 예제 출력 4  복사
 * 13
 */

package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P2178 {

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static boolean[][] visited;
    static int[][] A;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        A = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String line = st.nextToken();
            for (int j = 0; j < M; j++) {
                A[i][j] = Integer.parseInt(line.substring(j, j + 1));
            }
        }

        visited = new boolean[N][M];
        bfs(0, 0);
        System.out.println(A[N - 1][M - 1]);
    }

    static void bfs(int i, int j) {
        Queue<int[]> q = new LinkedList<int[]>();
        q.offer(new int[] {i, j});
        visited[i][j] = true;

        while (!q.isEmpty()) {
            int n[] = q.poll();

            for (int k = 0; k < 4; k++) {
                int x = n[0] + dx[k];
                int y = n[1] + dy[k];

                if (x >= 0 && y >= 0 && x < N && y < M) {
                    if (A[x][y] != 0 && !visited[x][y]) {
                        visited[x][y] = true;
                        A[x][y] = A[n[0]][n[1]] + 1;
                        q.offer(new int[]{x, y});
                    }
                }
            }
        }
    }
}
