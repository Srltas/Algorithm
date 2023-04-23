/**
 *
 * URL : https://www.acmicpc.net/problem/17182
 *
 * 우주 탐사선
 *
 * 문제
 * 우주 탐사선 ana호는 어떤 행성계를 탐사하기 위해 발사된다. 모든 행성을 탐사하는데 걸리는 최소 시간을 계산하려 한다. 입력으로는 ana호가 탐색할 행성의 개수와 ana호가 발사되는 행성의 위치와 ana호가 행성 간 이동을 하는데 걸리는 시간이 2차원 행렬로 주어진다. 행성의 위치는 0부터 시작하여 0은 행렬에서 0번째 인덱스에 해당하는 행성을 의미한다. 2차원 행렬에서 i, j 번 요소는 i 번째 행성에서 j 번째 행성에 도달하는데 걸리는 시간을 나타낸다. i와 j가 같을 때는 항상 0이 주어진다. 모든 행성을 탐사하는데 걸리는 최소 시간을 계산하여라.
 *
 * 탐사 후 다시 시작 행성으로 돌아올 필요는 없으며 이미 방문한 행성도 중복해서 갈 수 있다.
 *
 * 입력
 * 첫 번째 줄에는 행성의 개수 N과 ana호가 발사되는 행성의 위치 K가 주어진다. (2 ≤ N ≤ 10, 0 ≤ K < N)
 *
 * 다음 N 줄에 걸쳐 각 행성 간 이동 시간 Tij 가 N 개 씩 띄어쓰기로 구분되어 주어진다. (0 ≤ Tij  ≤ 1000)
 *
 * 출력
 * 모든 행성을 탐사하기 위한 최소 시간을 출력한다.
 *
 * 예제 입력 1
 * 3 0
 * 0 30 1
 * 1 0 29
 * 28 1 0
 * 예제 출력 1
 * 2
 * 예제 입력 2
 * 4 1
 * 0 83 38 7
 * 15 0 30 83
 * 67 99 0 44
 * 14 46 81 0
 * 예제 출력 2
 * 74
 */

package gold;

import java.io.*;
import java.util.StringTokenizer;

public class P17182 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    int N;
    int result = Integer.MAX_VALUE;
    int[][] time;
    boolean[] visited;

    public static void main(String[] args) throws IOException {
        new P17182().solution();
    }

    public void solution() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        time = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                time[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    time[i][j] = Math.min(time[i][j], time[i][k] + time[k][j]);
                }
            }
        }

        visited = new boolean[N];
        visited[K] = true;
        DFS(0, 0, K);

        bw.write(result + System.lineSeparator());
        bw.flush();

        bw.close();
        br.close();
    }

    private void DFS(int depth, int sum, int planet) {
        if (depth == N - 1) {
            result = Math.min(result, sum);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                DFS(depth + 1, sum + time[planet][i], i);
                visited[i]= false;
            }
        }
    }
}
