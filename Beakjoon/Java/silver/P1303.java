/**
 *
 * URL : https://www.acmicpc.net/problem/1303
 *
 * 전쟁 - 전투
 *
 * 문제
 * 전쟁은 어느덧 전면전이 시작되었다. 결국 전투는 난전이 되었고, 우리 병사와 적국 병사가 섞여 싸우게 되었다. 그러나 당신의 병사들은 흰색 옷을 입고, 적국의 병사들은 파란색 옷을 입었기 때문에 서로가 적인지 아군인지는 구분할 수 있다. 문제는 같은 팀의 병사들은 모이면 모일수록 강해진다는 사실이다.
 *
 * N명이 뭉쳐있을 때는 N2의 위력을 낼 수 있다. 과연 지금 난전의 상황에서는 누가 승리할 것인가? 단, 같은 팀의 병사들이 대각선으로만 인접한 경우는 뭉쳐 있다고 보지 않는다.
 *
 * 입력
 * 첫째 줄에는 전쟁터의 가로 크기 N, 세로 크기 M(1 ≤ N, M ≤ 100)이 주어진다. 그 다음 두 번째 줄에서 M+1번째 줄에는 각각 (X, Y)에 있는 병사들의 옷색이 띄어쓰기 없이 주어진다. 모든 자리에는 병사가 한 명 있다. B는 파란색, W는 흰색이다. 당신의 병사와 적국의 병사는 한 명 이상 존재한다.
 *
 * 출력
 * 첫 번째 줄에 당신의 병사의 위력의 합과 적국의 병사의 위력의 합을 출력한다.
 *
 * 예제 입력 1
 * 5 5
 * WBWWW
 * WWWWW
 * BBBBB
 * BBBWW
 * WWWWW
 * 예제 출력 1
 * 130 65
 */

package silver;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P1303 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    int[] dN = {0, 0, -1, 1};
    int[] dM = {-1, 1, 0, 0};

    int N, M;
    char[][] soldiers;
    boolean[][] visited;

    int whitePower;
    int bluePower;

    public static void main(String[] args) throws IOException {
        new P1303().solution();
    }

    public void solution() throws IOException {
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        soldiers = new char[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            char[] array = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                soldiers[i][j] = array[j];
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j]) {
                    BFS(new Node(i, j));
                }
            }
        }

        bw.write(whitePower + " " + bluePower);
        bw.flush();

        bw.close();
        br.close();
    }

    private void BFS(Node node) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        visited[node.n][node.m] = true;
        char soldier = soldiers[node.n][node.m];

        int count = 1;
        while (!queue.isEmpty()) {
            Node nextNode = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nextN = nextNode.n + dN[i];
                int nextM = nextNode.m + dM[i];

                if (nextN >= 0 && nextM >= 0 && nextN < N && nextM < M
                        && !visited[nextN][nextM] && soldiers[nextN][nextM] == soldier) {
                    queue.offer(new Node(nextN, nextM));
                    visited[nextN][nextM] = true;
                    count++;
                }
            }
        }

        if (soldier == 'W') {
            whitePower += (int) Math.pow(count, 2);
        } else {
            bluePower += (int) Math.pow(count, 2);
        }
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
